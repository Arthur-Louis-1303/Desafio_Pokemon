package com.example.apirestwithspring.entities;

import java.util.List;

public record PokemonListResponse(
        int count,
        String next,
        String previous,
        List<PokemonSummary> results) {
}
