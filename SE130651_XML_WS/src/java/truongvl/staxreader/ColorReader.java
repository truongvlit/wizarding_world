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
import truongvl.dtos.Color;
import truongvl.dtos.Colors;
import truongvl.utils.XMLUtils;

/**
 *
 * @author Nero
 */
public class ColorReader {
    public List<Color> getColors(ByteArrayInputStream inputStream, String xsdPath) throws Exception {
        List<Color> list = new ArrayList<>();
        XMLStreamReader reader = XMLUtils.getXMLStreamReader(inputStream);
        Unmarshaller um = XMLUtils.getUnmarshaller(Colors.class);
        Schema schema = XMLUtils.getSchema(xsdPath);
        um.setSchema(schema);
        while(reader.hasNext()) {
            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                try {
                    if (reader.getLocalName().equals("colors")) {
                        JAXBElement<Colors> colorJAXBElement = um.unmarshal(reader, Colors.class);
                        Colors colors = colorJAXBElement.getValue();
                        colors.getColors().forEach((t) -> {
                            if (t.getName().isEmpty()) {
                                // do nothing
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
