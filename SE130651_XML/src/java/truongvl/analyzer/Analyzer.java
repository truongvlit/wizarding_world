/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import truongvl.constants.Constants;
import truongvl.dtos.Color;
import truongvl.dtos.House;
import truongvl.dtos.Houses;
import truongvl.dtos.MovieCharacter;
import truongvl.dtos.MovieCharacters;
import truongvl.dtos.Product;
import truongvl.dtos.Products;
import truongvl.dtos.Answer;
import truongvl.dtos.ColorOfProduct;
import truongvl.dtos.ColorOfProducts;
import truongvl.dtos.Question;

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
    
    public ArrayList<Product> analyzeProducts(Products products, ColorOfProducts colorOfProducts, HashMap<Question, List<Answer>> map) {
        Analyzer analyzer = new Analyzer();
        ArrayList<Product> list = products.getProducts();
        // set points to 0
        list.forEach((product) -> {
            product.setPoint(0);
        });
        // analyze user's answer
        map.keySet().forEach((question) -> {
            switch (question.getTargetedAttribute()) {
                case Constants.TARGETED_COLUMN_NAME:
                    map.get(question).forEach((answer) -> {
                        int point = answer.getPoint();
                        // clean data
                        String content = analyzer.removeIgnorePhrases(answer.getContent());
                        // analyze
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getName().toLowerCase().contains(content.toLowerCase())) {
                                Product product = list.get(i);
                                product.setPoint(product.getPoint() + point);
                                list.set(i, product);
                            }
                        }
                        // end analyze
                    });
                    break;
                case Constants.TARGETED_COLUMN_CHARACTER:
                    map.get(question).forEach((answer) -> {
                        int point = answer.getPoint();
                        String content = analyzer.removeIgnorePhrases(answer.getContent());
                        // analyze
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getCharacterId() != null) {
                                if (list.get(i).getCharacterId().getName().toLowerCase().contains(content.toLowerCase())) {
                                    Product product = list.get(i);
                                    product.setPoint(product.getPoint() + point);
                                    list.set(i, product);
                                }
                            }
                        }
                        // end analyze
                    });
                    break;
                case Constants.TARGETED_COLUMN_DESCRIPTION:
                    map.get(question).forEach((answer) -> {
                        int point = answer.getPoint();
                        String content = analyzer.removeIgnorePhrases(answer.getContent());
                        // analyze
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getDescription().toLowerCase().contains(content.toLowerCase())) {
                                Product product = list.get(i);
                                product.setPoint(product.getPoint() + point);
                                list.set(i, product);
                            }
                        }
                        // end analyze
                    });
                    break;
                case Constants.TARGETED_COLUMN_PRICE:
                    Answer minPrice = map.get(question).get(0);
                    Answer maxPrice = map.get(question).get(1);
                    int tmp = maxPrice.getPoint();
                    // analyze
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getPrice() <= Float.parseFloat(maxPrice.getContent()) 
                                && list.get(i).getPrice() >= Float.parseFloat(minPrice.getContent())) {
                            Product product = list.get(i);
                            product.setPoint(product.getPoint() + tmp);
                            list.set(i, product);
                        }
                    }
                    // end analyze
                    break;
                case Constants.TARGETED_COLUMN_NOTE:
                    map.get(question).forEach((answer) -> {
                        int point = answer.getPoint();
                        String content = analyzer.removeIgnorePhrases(answer.getContent());
                        for (int i = 1; i < Constants.SHIPPING_DAYS.length; i++) {
                            if (content.contains(Constants.SHIPPING_DAYS[i])) {
                                for (int j = 1; j <= i; j++) {
                                    content += " " + Constants.SHIPPING_DAYS[j];
                                }
                                break;
                            }
                        }
                        // analyze
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getNote().toLowerCase().contains(content.toLowerCase())) {
                                Product product = list.get(i);
                                product.setPoint(product.getPoint() + point);
                                list.set(i, product);
                            }
                        }
                        // end analyze
                    });
                    break;
                case Constants.TARGETED_COLUMN_COLOR:
                    map.get(question).forEach((answer) -> {
                        int point = answer.getPoint();
                        String content = analyzer.removeIgnorePhrases(answer.getContent());
                        // analyze
                        for (ColorOfProduct colorOfProduct : colorOfProducts.getColorOfProduct()) {
                            if (colorOfProduct.getColorId().getName().toLowerCase().equals(content.toLowerCase())) {
                                for (int i = 0; i < list.size(); i++) {
                                    if ((int)list.get(i).getId() == (int)colorOfProduct.getProductId().getId()) {
                                        Product product = list.get(i);
                                        product.setPoint(product.getPoint() + point);
                                        list.set(i, product);
                                    }
                                }
                            }
                        }
                        // end analyze
                    });
                    break;
                case Constants.TARGETED_COLUMN_HOUSE:
                    map.get(question).forEach((answer) -> {
                        int point = answer.getPoint();
                        String content = analyzer.removeIgnorePhrases(answer.getContent());
                        // analyze
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getHouseId() != null) {
                                if (list.get(i).getHouseId().getName().toLowerCase().contains(content.toLowerCase())) {
                                    Product product = list.get(i);
                                    product.setPoint(product.getPoint() + point);
                                    list.set(i, product);
                                }
                            }
                        }
                        // end analyze
                    });
                    break;
                case Constants.TARGETED_COLUMN_CATEGORY:
                    map.get(question).forEach((answer) -> {
                        int point = answer.getPoint();
                        String content = analyzer.removeIgnorePhrases(answer.getContent());
                        // analyze
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getCategory()!= null) {
                                if (list.get(i).getCategory().toLowerCase().contains(content.toLowerCase())) {
                                    Product product = list.get(i);
                                    product.setPoint(product.getPoint() + point);
                                    list.set(i, product);
                                }
                            }
                        }
                        // end analyze
                    });
                    break;
            }
        });
        list.sort((o1, o2) -> {
            return o2.getPoint() - o1.getPoint();
        });
        return new ArrayList(list.subList(0, 100));
    }
}
