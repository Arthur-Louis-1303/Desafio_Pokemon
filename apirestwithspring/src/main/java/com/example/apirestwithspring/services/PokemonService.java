package com.example.apirestwithspring.services;

import com.example.apirestwithspring.entities.Pokemon;
import com.example.apirestwithspring.entities.PokemonListItem;
import com.example.apirestwithspring.entities.PokemonListResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@Service
public class PokemonService {

    private final RestClient client;

    public PokemonService(RestClient.Builder builder) {
        this.client = builder.baseUrl("https://pokeapi.co/api/v2").build();
    }

    public String getPokemonByName(String pokemon_name) {
        return Objects.requireNonNull(client.get()
                        .uri("/pokemon/{pokemon_name}", pokemon_name)
                        .retrieve()
                        .body(Pokemon.class))
                .toString();
    }

    public List<PokemonListItem> listPokemons(int limit, int offset) {
        PokemonListResponse response = client.get()
                .uri("/pokemon?limit={limit}&offset={offset}", limit, offset)
                .retrieve()
                .body(PokemonListResponse.class);

        return Objects.requireNonNull(response).results().stream()
                .map(p -> new PokemonListItem(p.extractId(), p.name()))
                .toList();
    }
}
