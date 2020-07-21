/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Nero
 */
public class XMLUtils implements Serializable {
    public static XPath createXPath() throws Exception {
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xPath = xpf.newXPath();
        return xPath;
    }
    
    public static Schema getSchema(String file) throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return schemaFactory.newSchema(new File(file));
    }
    
    public static XMLStreamReader getXMLStreamReader(InputStream inputStream) throws Exception {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        return xif.createXMLStreamReader(inputStream);
    }
    
    public static boolean validateXMLUsingXsd(String xsdPath, ByteArrayInputStream inputStream) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsdPath));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(inputStream));
        return true;
    }
    
    public static boolean validateXMLUsingDtd(ByteArrayInputStream inputStream) throws Exception {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setValidating(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        builder.setErrorHandler(new ErrorHandler() {
            @Override
            public void error(SAXParseException exception) throws SAXException {
                // do something more useful in each of these handlers
                exception.printStackTrace();
            }
            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                exception.printStackTrace();
            }

            @Override
            public void warning(SAXParseException exception) throws SAXException {
                exception.printStackTrace();
            }
        });
        builder.parse(inputStream);
        return true;
    }
    
    public static Marshaller getMarshaller(Class clazz) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        return jaxbContext.createMarshaller();
    }
    
    public static String marshal(Object object) throws Exception {
        Marshaller marshaller = getMarshaller(object.getClass());
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }
    
    public static Unmarshaller getUnmarshaller(Class clazz) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        return jaxbContext.createUnmarshaller();
    }

    public static Object unmarshal(ByteArrayInputStream inputStream, Class clazz) throws Exception {
        Unmarshaller unmarshaller = getUnmarshaller(clazz);
        return unmarshaller.unmarshal(inputStream);
    }
}
