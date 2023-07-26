package com.example.delta2bact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private Context context;
    private List<Character> characters;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int characterId);
    }

    public CharacterAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characters.get(position);

        // Load character image using Picasso or any other image loading library
        Picasso.get()
                .load(character.getImageUrl())
                .placeholder(R.drawable.character_placeholder)
                .into(holder.characterImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(character.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return characters != null ? characters.size() : 0;
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        public ImageView characterImageView;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            characterImageView = itemView.findViewById(R.id.characterImageView);
        }
    }
}

