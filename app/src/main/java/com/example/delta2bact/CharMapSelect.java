package com.example.delta2bact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharMapSelect extends AppCompatActivity {

    private CharacterApi characterApi;
    private CharacterAdapter characterAdapter;
    private int selectedCharacterId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_map_select);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button startButton = findViewById(R.id.startButton);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://your-api-base-url.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create CharacterApi instance
        characterApi = retrofit.create(CharacterApi.class);

        // Create CharacterAdapter
        characterAdapter = new CharacterAdapter(this, new CharacterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int characterId) {
                selectedCharacterId = characterId;
                Toast.makeText(CharMapSelect.this, "Selected character: " + characterId, Toast.LENGTH_SHORT).show();
            }
        });

        // Set up RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(characterAdapter);

        // Load characters from the API
        loadCharacters();

        // Start button click listener
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCharacterId != -1) {
                    // Start the next activity and pass the selected character ID
                    // Intent intent = new Intent(CharMapSelect.this, NextActivity.class);
                    // intent.putExtra("characterId", selectedCharacterId);
                    // startActivity(intent);
                } else {
                    Toast.makeText(CharMapSelect.this, "Please select a character", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadCharacters() {
        Call<List<Character>> call = characterApi.getCharacters();
        call.enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                if (response.isSuccessful()) {
                    List<Character> characters = response.body();
                    characterAdapter.setCharacters(characters);
                } else {
                    Toast.makeText(CharMapSelect.this, "Failed to load characters", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                Toast.makeText(CharMapSelect.this, "Failed to load characters", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
