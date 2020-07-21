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
import truongvl.dtos.MovieCharacter;
import truongvl.dtos.MovieCharacters;
import truongvl.utils.XMLUtils;

/**
 *
 * @author Nero
 */
public class MovieCharacterReader {
    public List<MovieCharacter> getCharacters(ByteArrayInputStream inputStream, String xsdPath) throws Exception {
        List<MovieCharacter> list = new ArrayList<>();
        XMLStreamReader reader = XMLUtils.getXMLStreamReader(inputStream);
        Unmarshaller um = XMLUtils.getUnmarshaller(MovieCharacters.class);
        Schema schema = XMLUtils.getSchema(xsdPath);
        um.setSchema(schema);
        while(reader.hasNext()) {
            if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                try {
                    if (reader.getLocalName().equals("movieCharacters")) {
                        JAXBElement<MovieCharacters> characterJAXBElement = um.unmarshal(reader, MovieCharacters.class);
                        MovieCharacters characters = characterJAXBElement.getValue();
                        characters.getCharacters().forEach((t) -> {
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
