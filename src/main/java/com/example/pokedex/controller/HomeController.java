package com.example.pokedex.controller;

import com.example.pokedex.model.Pokemon;
import com.example.pokedex.repository.PokemonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    PokemonRepository pokemonRepository;
    public HomeController(PokemonRepository repo) {
        pokemonRepository = repo;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pokemon", pokemonRepository.getAll());
        return "Home/index";
    }

    @GetMapping("/create")
    public String showCreatePokemon(Model model) {
        Pokemon pokemon = new Pokemon();
        model.addAttribute("pokemon", pokemon);
        return "Home/create";
    }

    @PostMapping("/create")
    public String createPokemon(@ModelAttribute("pokemon") Pokemon pokemon) {
        Pokemon newPokemon = new Pokemon();

        newPokemon.setName(pokemon.getName());
        newPokemon.setSpeed(pokemon.getSpeed());
        newPokemon.setSpecialDefence(pokemon.getSpecialDefence());
        newPokemon.setSpecialAttack(pokemon.getSpecialAttack());
        newPokemon.setDefence(pokemon.getDefence());
        newPokemon.setAttack(pokemon.getAttack());
        newPokemon.setHp(pokemon.getHp());
        newPokemon.setPrimaryType(pokemon.getPrimaryType());
        newPokemon.setSecondaryType(pokemon.getSecondaryType());

        pokemonRepository.create(newPokemon);

        return "redirect:/";
    }

    @GetMapping("/update")
    public String showUpdatePokemon(Model model) {
        Pokemon pokemon = new Pokemon();
        model.addAttribute("pokemon", pokemon);
        return "Home/update";
    }
    @PostMapping("/update")
    public String updatePokemon(@ModelAttribute("pokemon") Pokemon pokemon) {
        Pokemon updatedPokemon = new Pokemon();

        updatedPokemon.setId(pokemon.getId());
        updatedPokemon.setName(pokemon.getName());
        updatedPokemon.setSpeed(pokemon.getSpeed());
        updatedPokemon.setSpecialDefence(pokemon.getSpecialDefence());
        updatedPokemon.setSpecialAttack(pokemon.getSpecialAttack());
        updatedPokemon.setDefence(pokemon.getDefence());
        updatedPokemon.setAttack(pokemon.getAttack());
        updatedPokemon.setHp(pokemon.getHp());
        updatedPokemon.setPrimaryType(pokemon.getPrimaryType());
        updatedPokemon.setSecondaryType(pokemon.getSecondaryType());

        pokemonRepository.update(updatedPokemon);

        return "redirect:/";
    }

    @GetMapping("/delete")
    public String showDeletePokemon() {
        return "Home/delete";
    }


    @PostMapping("/delete")
    public String deletePokemon(@RequestParam("id") int id) {

        pokemonRepository.deleteById(id);

        return "redirect:/";
    }
}
