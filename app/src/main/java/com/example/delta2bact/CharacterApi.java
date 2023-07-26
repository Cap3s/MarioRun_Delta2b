package com.example.delta2bact;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface CharacterApi {

    @GET("/characters")
    Call<List<Character>> getCharacters();

    @GET("/characters/{id}")
    Call<Character> getCharacterById(@Path("id") int id);

    @POST("/characters")
    Call<Character> createCharacter(@Body Character character);

    @PUT("/characters/{id}")
    Call<Character> updateCharacter(@Path("id") int id, @Body Character character);

    @DELETE("/characters/{id}")
    Call<Void> deleteCharacter(@Path("id") int id);
}
