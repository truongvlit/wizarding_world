/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.staxreader;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.validation.Schema;
import truongvl.dtos.Product;
import truongvl.dtos.Products;
import truongvl.utils.XMLUtils;

/**
 *
 * @author Nero
 */
public class ProductReader {
    
    public List<Product> getProducts(ByteArrayInputStream inputStream, String xsdPath) throws Exception {
        List<Product> list = new ArrayList<>();
        XMLStreamReader reader = XMLUtils.getXMLStreamReader(inputStream);
        Unmarshaller um = XMLUtils.getUnmarshaller(Products.class);
        Schema schema = XMLUtils.getSchema(xsdPath);
        um.setSchema(schema);
        while(reader.hasNext()) {
            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                try {
                    if (reader.getLocalName().equals("products")) {
                        JAXBElement<Products> productJAXBElement = um.unmarshal(reader, Products.class);
                        Products products = productJAXBElement.getValue();
                        products.getProducts().forEach((t) -> {
                            if (t.getCategory().isEmpty() || t.getName().isEmpty()||
                                    t.getImage().isEmpty() || t.getDescription().isEmpty() || t.getNote().isEmpty() || t.getPrice() == 0.0) {
                                // do  nothing
                            } else {
                                list.add(t);
                            }
                        });
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (reader.getEventType() != XMLStreamConstants.END_DOCUMENT) {
                reader.next();
            }
        }
        reader.close();
        return list;
    }
}
