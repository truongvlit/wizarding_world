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
public class Question implements Serializable {

    private Integer id, type, targetedAttribute;
    private String content;

    public Question() {
    }

    public Question(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    @XmlElement(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "questionContent")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlElement(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
    @XmlElement(name = "targetedAttribute")
    public Integer getTargetedAttribute() {
        return targetedAttribute;
    }

    public void setTargetedAttribute(Integer targetedAttribute) {
        this.targetedAttribute = targetedAttribute;
    }
}
