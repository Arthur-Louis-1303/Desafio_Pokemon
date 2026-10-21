package com.example.apirestwithspring.console;

import com.example.apirestwithspring.entities.PokemonListItem;
import com.example.apirestwithspring.services.PokemonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Ao iniciar a aplicação, imprime no console uma tabela com os 50 primeiros Pokémons.
 * Desative com: pokemon.console.enabled=false
 */
@Component
@ConditionalOnProperty(name = "pokemon.console.enabled", havingValue = "true", matchIfMissing = true)
public class PokemonConsolePrinter implements CommandLineRunner {

    private static final int ID_W = 6;     // " #001 "
    private static final int NAME_W = 22;  // " Bulbasaur          "
    private static final int TOTAL_W = ID_W + 1 + NAME_W;

    private final PokemonService service;

    public PokemonConsolePrinter(PokemonService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        try {
            print(service.listPokemons(50, 0));
        } catch (Exception e) {
            System.out.println("\n[!] Não foi possível listar os Pokémons: " + e.getMessage());
        }
    }

    private void print(List<PokemonListItem> pokemons) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("╔").append("═".repeat(TOTAL_W)).append("╗\n");
        sb.append("║").append(center("POKÉDEX · PRIMEIROS " + pokemons.size(), TOTAL_W)).append("║\n");
        sb.append("╠").append("═".repeat(ID_W)).append("╦").append("═".repeat(NAME_W)).append("╣\n");
        sb.append(String.format("║ %-4s ║ %-20s ║%n", "ID", "NOME"));
        sb.append("╠").append("═".repeat(ID_W)).append("╬").append("═".repeat(NAME_W)).append("╣\n");

        for (PokemonListItem p : pokemons) {
            sb.append(String.format("║ #%03d ║ %-20s ║%n", p.id(), capitalize(p.name())));
        }

        sb.append("╚").append("═".repeat(ID_W)).append("╩").append("═".repeat(NAME_W)).append("╝\n");
        sb.append("  Fonte: pokeapi.co\n");
        System.out.print(sb);
    }

    private static String capitalize(String s) {
        return s == null || s.isEmpty() ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private static String center(String text, int width) {
        int left = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, left)) + text + " ".repeat(Math.max(0, width - text.length() - left));
    }
}
