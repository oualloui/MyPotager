package com.example.myapplication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlantRepository {

    public static class Singleton { // Classe interne statique pour Singleton
        public static final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("plants");
        public static final List<PlantModel> plantList = new ArrayList<>();
    }

    public interface UpdateDataCallback { // Interface pour le callback
        void onDataUpdated();
    }

    public void updateData(UpdateDataCallback callback) { // Méthode updateData avec callback
        Singleton.databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Singleton.plantList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PlantModel plant = ds.getValue(PlantModel.class);
                    if (plant != null) {
                        Singleton.plantList.add(plant);
                    }
                }
                // Appeler le callback après la mise à jour des données
                callback.onDataUpdated();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("PlantRepository2", "Erreur : " + error.getMessage());
            }
        });
    }
    //met a jour l'objet plante en BDD
    public void updatePlant(PlantModel plant) {
        Singleton.databaseRef.child(plant.getId()).setValue(plant);
    }

    //methode pour supprimer une plante de la base de données

    public void deletePlant(final PlantModel plant) {
        Singleton.databaseRef.child(plant.getId()).removeValue();
    }

}
