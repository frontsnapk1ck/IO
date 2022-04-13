package frontsnapk1ck.io;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * @param T the type of the thing your are saving
 * @param F the file to which you are saving
 */
public abstract class SaverXML< T , F > {
    
    public abstract boolean save( T data , F file );

    /**
     * creates a documents for saving xml to
     * 
     * @return the documents for saving the xml to
     * @throws ParserConfigurationException if a DocumentBuilder 
     *          cannot be created which satisfies the 
     *          configuration requested.
     */
    protected Document loadDoc() throws ParserConfigurationException 
    {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.newDocument();
    }

    /**
     * 
     * @param doc the filled document of XML that is going to be saved 
     * @param f the file that the document is going to be saved in
     * @throws TransformerException If an unrecoverable error occurs 
     *          during the course of the transformation.
     * @throws TransformerFactoryConfigurationError Thrown in case of 
     *          service configuration error or if the implementation 
     *          is not available or cannot be instantiated.
     * @throws TransformerConfigurationException - When it is not possible
     *          to create a Transformer instance.
     */
    protected void saveDoc(Document doc, File f) throws TransformerException 
    {
        //write the content into xml file
        TransformerFactory transformerFactory =  TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
            
        StreamResult result =  new StreamResult(f);
        transformer.transform(source, result);        
    }

}
