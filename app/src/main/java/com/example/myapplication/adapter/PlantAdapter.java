package com.example.myapplication.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.MainActivity;
import com.example.myapplication.PlantModel;
import com.example.myapplication.PlantPopup;
import com.example.myapplication.PlantRepository;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {

    public MainActivity context;
    private int layoutId;
    private List<PlantModel> plantList;
    private PlantRepository repo;
    private PlantAdapter currentPlant;

    // Constructeur qui prend en paramètre le layout
    public PlantAdapter(MainActivity context, List<PlantModel> plantList, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
        this.plantList = plantList;
        this.repo = new PlantRepository();
    }

    // Classe ViewHolder pour gérer les composants de chaque item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView plantImage;
        public TextView plantName;
        public TextView plantDescription;
        public ImageView starIcon;


        public ViewHolder(View view) {
            super(view);
            // Initialisation de l'ImageView
            starIcon = itemView.findViewById(R.id.star_icon);
            plantImage = view.findViewById(R.id.image_item);
            plantName = view.findViewById(R.id.name_item);
            plantDescription = view.findViewById(R.id.description_item);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Création de la vue de chaque item
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        TextView plantName = view.findViewById(R.id.name_item);
        TextView plantDescription = view.findViewById(R.id.description_item);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //recupere les infos de la plante

        PlantModel currentPlant = plantList.get(position);

        //utilise la bibliotheque glide pour recup les images

        Glide.with(context).load(Uri.parse(currentPlant.getImageUrl())).into(holder.plantImage);

        //mettre a jour le nom de la plante
        if (holder.plantName != null) {
            holder.plantName.setText(currentPlant.getName());
        }
        //mise a jour de la description
        if (holder.plantDescription != null) {
            holder.plantDescription.setText(currentPlant.getDescription());
        }
        updateStarIcon(holder, currentPlant);

        // Interaction lors du clic sur une plante (déplacé en dehors de updateStarIcon)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Affiche la popup
                PlantPopup popup = new PlantPopup(context, PlantAdapter.this, currentPlant); // Passe currentPlant au constructeur
                popup.show();
            }
        });
    }
    private void updateStarIcon(final ViewHolder holder, final PlantModel plant) {
        if (plant.isLiked()) {
            holder.starIcon.setImageResource(R.drawable.ic_action_like);
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_action_unlike);
        }

        holder.starIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inverser l'état "liked" et mettre à jour la propriété liked de currentPlant
                plant.setLiked(!plant.isLiked());

                // Mettre à jour l'icône
                updateStarIcon(holder, plant); // Appel récursif pour mettre à jour l'icône

                // Mettre à jour l'objet plante dans la base de données
                repo.updatePlant(plant); // Utilisation de l'instance repo de la classe
            }

        });

    }




    @Override
    public int getItemCount() {
        return plantList.size(); // Remplacez 5 par la taille réelle de votre liste de données si nécessaire
    }

}
