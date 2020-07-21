/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.dtos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nero
 */
@XmlRootElement
public class Product implements Serializable {
    
    private Integer id, point;
    private String name, category, note, description, image;
    private float price;
    private House houseId;
    private MovieCharacter characterId;

    public Product() {
    }
    
    @XmlElement(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    @XmlElement(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "img")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlElement(name = "id")
    public Integer getId() {
        return id;
    }

    public Integer getPoint() {
        return point;
    }

    @XmlElement(name = "point")
    public void setPoint(Integer point) {
        this.point = point;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "houseId")
    public House getHouseId() {
        return houseId;
    }

    public void setHouseId(House houseId) {
        this.houseId = houseId;
    }

    @XmlElement(name = "characterId")
    public MovieCharacter getCharacterId() {
        return characterId;
    }

    public void setCharacterId(MovieCharacter characterId) {
        this.characterId = characterId;
    }

}
