package com.example.apirestwithspring.controllers;

import com.example.apirestwithspring.entities.PokemonListItem;
import com.example.apirestwithspring.services.PokemonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokeApi")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping("/pokemon/{pokemon_name}")
    public String whoIsThatPokemon(@PathVariable String pokemon_name) {
        return service.getPokemonByName(pokemon_name);
    }

    @GetMapping("/pokemon")
    public List<PokemonListItem> listPokemons(
            @RequestParam(defaultValue = "50") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return service.listPokemons(limit, offset);
    }
}
