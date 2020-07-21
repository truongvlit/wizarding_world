/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.dtos;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nero
 */
@XmlRootElement(name = "movieCharacters")
public class MovieCharacters implements Serializable {
    
    private List<MovieCharacter> characters;

    public MovieCharacters() {
    }

    @XmlElement(name = "movieCharacter")
    public List<MovieCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(List<MovieCharacter> characters) {
        this.characters = characters;
    }
}
