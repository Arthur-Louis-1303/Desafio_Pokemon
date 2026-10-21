package com.example.apirestwithspring.entities;

public record PokemonSummary(String name, String url) {

    /** Extrai o id da URL, ex.: https://pokeapi.co/api/v2/pokemon/1/ -> 1 */
    public Integer extractId() {
        String[] parts = url.split("/");
        return Integer.valueOf(parts[parts.length - 1]);
    }
}
