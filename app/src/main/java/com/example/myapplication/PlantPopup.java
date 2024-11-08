package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.myapplication.adapter.PlantAdapter;

public class PlantPopup extends Dialog {

    private Context context; // Stocke le contexte
    private PlantAdapter adapter;
    private PlantModel currentPlant;

    public PlantPopup(Context context, PlantAdapter adapter, PlantModel currentPlant) { // Ajout de currentPlant au constructeur
        super(context);
        this.context = context; // Initialise le contexte
        this.adapter = adapter;
        this.currentPlant = currentPlant; // Initialise currentPlant
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_plant_details);
        setupComponents();
        setupCloseButton();
        setupDeleteButton();
        setupStarButton();
    }

    private void updateStar(final ImageView button){
        if (currentPlant.isLiked()){
            button.setImageResource(R.drawable.ic_action_like);
        }
        else{
            button.setImageResource(R.drawable.ic_action_unlike);
        }
    }

    private void setupStarButton() {
        final ImageView starButton = findViewById(R.id.unlike_button);
        updateStar(starButton);

        starButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPlant.setLiked(!currentPlant.isLiked());
                updateStar(starButton); // Mettre à jour l'icône après le clic

                PlantRepository repo = new PlantRepository();
                repo.updatePlant(currentPlant); // Mettre à jour la base de données après le clic
            }
        });
    }

    private void setupDeleteButton() {
        findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() { // Utiliser android.view.View.OnClickListener
            @Override
            public void onClick(View v) { // Utiliser android.view.View
                // supprime la plante de la base de données
                final PlantRepository repo = new PlantRepository();
                repo.deletePlant(currentPlant);
                dismiss();
            }
        });
    }


    private void setupCloseButton() {
        findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() { // Utiliser android.view.View.OnClickListener
            @Override
            public void onClick(View v) { // Utiliser android.view.View
                // Ferme la fenêtre popup
                dismiss();
            }
        });
    }

    private void setupComponents() {
        // Actualise l'image de la plante
        ImageView plantImage = findViewById(R.id.image_item);
        Glide.with(context) // Utilise le contexte stocké
                .load(Uri.parse(currentPlant.getImageUrl()))
                .into(plantImage);

        // Affiche le nom de la plante
        TextView plantName = findViewById(R.id.popup_plant_name); // Assurez-vous que vous avez un TextView avec cet ID dans votre layout
        plantName.setText(currentPlant.getName());

        // Affiche la description de la plante
        TextView plantDescription = findViewById(R.id.pop_up_plant_description); // Assurez-vous que vous avez un TextView avec cet ID dans votre layout
        plantDescription.setText(currentPlant.getDescription());

        //affiche la description detaillé de la plante

        TextView plantGrow = findViewById(R.id.pop_up_plant_grow_subtitle); // Assurez-vous que vous avez un TextView avec cet ID dans votre layout
        plantGrow.setText(currentPlant.getGrow()); // Utilisez plantGrow.setText() pour afficher la croissance

// Affiche la conso d'eau
        TextView plantWater = findViewById(R.id.pop_up_plant_water_subtitle); // Assurez-vous que vous avez un TextView avec cet ID dans votre layout
        plantWater.setText(currentPlant.getWater()); // Utilisez plantWater.setText() pour afficher la consommation d'eau


    }
}