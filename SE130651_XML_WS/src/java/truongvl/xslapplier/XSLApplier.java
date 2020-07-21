/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.xslapplier;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Nero
 */
public class XSLApplier {
    private TransformerFactory tf = TransformerFactory.newInstance();

    private Transformer getTransformer(String stylesheet) throws TransformerException {
        StreamSource xsltFile = new StreamSource(stylesheet);
        Templates templates = tf.newTemplates(xsltFile);
        return templates.newTransformer();
    }

    private ByteArrayOutputStream applyStylesheet(String stylesheet, InputStream xmlStream) throws TransformerException {
        Transformer transformer = getTransformer(stylesheet);
        StreamSource xmlFile = new StreamSource(xmlStream);
        StreamResult resultStream = new StreamResult(new ByteArrayOutputStream());
        transformer.transform(xmlFile, resultStream);
        return (ByteArrayOutputStream) resultStream.getOutputStream();
    }

    public ByteArrayOutputStream applyStylesheet(String stylesheet, String xmlContent) throws TransformerException {
        return applyStylesheet(stylesheet, new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));
    }
    
        
    public ByteArrayOutputStream applyStylesheetWithDtd(String dtdPath, String xslPath, String xmlContent) throws Exception {
        // apply dtd to xsl
        StreamSource source = new StreamSource(new File(xslPath));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer(source);
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtdPath);
        // apply xsl to xml stream
        StreamSource xmlFile = new StreamSource(new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));
        StreamResult result = new StreamResult(new ByteArrayOutputStream());
        transformer.transform(xmlFile, result);
        return (ByteArrayOutputStream) result.getOutputStream();
    }
}
