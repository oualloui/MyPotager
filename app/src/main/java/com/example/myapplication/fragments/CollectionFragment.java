package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.PlantRepository;
import com.example.myapplication.R;
import com.example.myapplication.adapter.PlantAdapter;
import com.example.myapplication.adapter.PlantItemDecoration;

import java.util.stream.Collectors;

public class CollectionFragment extends Fragment {

    private MainActivity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        //recupere la recyclerview
        context = (MainActivity) getActivity();
        RecyclerView collectionRecyclerView = view.findViewById(R.id.collection_recycler_list);
        collectionRecyclerView.setAdapter(new PlantAdapter(context, PlantRepository.Singleton.plantList.stream()
                .filter(plant -> plant.isLiked())
                .collect(Collectors.toList()),
                R.layout.item_vertical_plant));        collectionRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        collectionRecyclerView.addItemDecoration(new PlantItemDecoration());
        return view;

    }
}
