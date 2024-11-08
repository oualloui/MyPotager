package com.example.myapplication.fragments;

import static com.example.myapplication.PlantRepository.Singleton.plantList;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.PlantModel;
import com.example.myapplication.R;
import com.example.myapplication.adapter.PlantAdapter;
import com.example.myapplication.adapter.PlantItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment{


    private MainActivity context;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (MainActivity) context; // Cast to MainActivity
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);







        //recuperation du recyclerview
        RecyclerView horizontalRecyclerView = view.findViewById(R.id.horizontal_recycler_view);
        horizontalRecyclerView.setAdapter(new PlantAdapter(context, plantList, R.layout.item_horizontal_plant));
        RecyclerView verticalRecyclerView = view.findViewById(R.id.vertical_recycler_view);
        verticalRecyclerView.setAdapter(new PlantAdapter(context, plantList, R.layout.item_vertical_plant));
        verticalRecyclerView.addItemDecoration(new PlantItemDecoration());


        return view;
    }
}
