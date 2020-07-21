/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.analyzer;

import java.util.ArrayList;
import java.util.List;
import truongvl.constants.Constants;
import truongvl.dtos.Color;
import truongvl.dtos.House;
import truongvl.dtos.Houses;
import truongvl.dtos.MovieCharacter;
import truongvl.dtos.MovieCharacters;
import truongvl.dtos.Product;
import truongvl.dtos.Products;

/**
 *
 * @author Nero
 */
public class Analyzer {
    
    public List<Product> getColorOfProduct(Products products, Color color) {
        List<Product> list = new ArrayList<>();
        products.getProducts().forEach((t) -> {
            if (t.getDescription().toLowerCase().contains(color.getName().toLowerCase())) list.add(t);
        });
        return list;
    }
    
    public MovieCharacter getCharacterOfProduct(Product product, MovieCharacters movieCharacters) {
        for (MovieCharacter movieCharacter : movieCharacters.getCharacters()) {
            if (product.getName().toLowerCase().contains(movieCharacter.getName().toLowerCase())) return movieCharacter;
        }
        return null;
    }
    
    public House getHouseOfProduct(Product product, Houses houses) {
        for (House house : houses.getHouses()) {
            if (product.getName().toLowerCase().contains(house.getName().toLowerCase())) return house;
        }
        return null;
    }
    
    public String removeIgnorePhrases(String content) {
        for(String string : Constants.IGNORE_PHRASES) {
            if (content.contains(string)) return content.replace(string, "");
        }
        return content;
    }
}
