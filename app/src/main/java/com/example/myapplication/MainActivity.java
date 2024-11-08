package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.fragments.CollectionFragment;
import com.example.myapplication.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // Make content extend behind system bars
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        loadFragment(new HomeFragment(), R.string.home_page_title);

        // Set window insets listener in onCreate
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Importation of BottomNavigationView
        BottomNavigationView navigationView = findViewById(R.id.navigationview);
        navigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home_page) {
                loadFragment(new HomeFragment(), R.string.home_page_title);
                return true;
            } else if (itemId == R.id.add_plant_page) {
                loadFragment(new CollectionFragment(), R.string.collection_page_title);
                return true;
            } else {
                return false;
            }
        });
    }

    // Updated loadFragment function (moved outside onCreate)
    private void loadFragment(Fragment fragment, int string) {
        PlantRepository repo = new PlantRepository();
        repo.updateData(new PlantRepository.UpdateDataCallback() {
            @Override
            public void onDataUpdated() {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment); // Load the passed fragment
                transaction.addToBackStack(null);
                transaction.commit();

                // Update the page title after the fragment transaction is committed
                TextView pageTitle = findViewById(R.id.page_title);
                pageTitle.setText(getResources().getString(string));
            }
        });
    }
}
