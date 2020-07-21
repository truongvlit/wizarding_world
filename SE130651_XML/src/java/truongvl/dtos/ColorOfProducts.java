/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.dtos;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nero
 */
@XmlRootElement(name = "colorOfProducts")
public class ColorOfProducts {
    
    private List<ColorOfProduct> colorOfProduct;

    public ColorOfProducts() {
    }

    @XmlElement(name = "colorOfProduct")
    public List<ColorOfProduct> getColorOfProduct() {
        return colorOfProduct;
    }

    public void setColorOfProduct(List<ColorOfProduct> colorOfProduct) {
        this.colorOfProduct = colorOfProduct;
    }
}
