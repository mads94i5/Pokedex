package com.example.pokedex.repository;

import com.example.pokedex.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepository {

    public List<Pokemon> getAll() {
        List<Pokemon> pokemons = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokedex","root","test");

            PreparedStatement psts = conn.prepareStatement("SELECT * FROM pokemon");
            ResultSet resultSet = psts.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int speed = resultSet.getInt(3);
                int specialDefence = resultSet.getInt(4);
                int specialAttack = resultSet.getInt(5);
                int defence = resultSet.getInt(6);
                int attack = resultSet.getInt(7);
                int hp = resultSet.getInt(8);
                String primaryType = resultSet.getString(9);
                String secondaryType = resultSet.getString(10);
                pokemons.add(new Pokemon(id, name, speed, specialDefence, specialAttack, defence, attack, hp, primaryType, secondaryType));
            }
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
            e.printStackTrace();
        }
        return pokemons;
    }

    public void create(Pokemon newPokemon) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokedex","root","test");

            String query = "INSERT INTO pokemon (name, speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psts = conn.prepareStatement(query);

            psts.setString(1, newPokemon.getName());
            psts.setInt(2, newPokemon.getSpeed());
            psts.setInt(3, newPokemon.getSpecialDefence());
            psts.setInt(4, newPokemon.getSpecialAttack());
            psts.setInt(5, newPokemon.getDefence());
            psts.setInt(6, newPokemon.getAttack());
            psts.setInt(7, newPokemon.getHp());
            psts.setString(8, newPokemon.getPrimaryType());
            psts.setString(9, newPokemon.getSecondaryType());

            psts.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
            e.printStackTrace();
        }
    }

    public void update(Pokemon pokemon) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokedex", "root", "test");

            String query = "UPDATE pokemon " +
                    "SET name=?, speed=?, special_defence=?, special_attack=?, defence=?, attack=?, hp=?, primary_type=?, secondary_type=? WHERE pokedex_number=?";
            PreparedStatement psts = conn.prepareStatement(query);

            psts.setString(1, pokemon.getName());
            psts.setInt(2, pokemon.getSpeed());
            psts.setInt(3, pokemon.getSpecialDefence());
            psts.setInt(4, pokemon.getSpecialAttack());
            psts.setInt(5, pokemon.getDefence());
            psts.setInt(6, pokemon.getAttack());
            psts.setInt(7, pokemon.getHp());
            psts.setString(8, pokemon.getPrimaryType());
            psts.setString(9, pokemon.getSecondaryType());
            psts.setInt(10, pokemon.getId());

            psts.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokedex", "root", "test");

            String query = "DELETE FROM pokemon WHERE pokedex_number=?";
            PreparedStatement psts = conn.prepareStatement(query);

            psts.setInt(1, id);

            psts.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Cannot connect to database.");
            e.printStackTrace();
        }
    }
}
