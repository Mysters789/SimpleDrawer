/*
 * XMLShapeREader.java
 *
 * @author Gill Windall
 *
 * Used to read shapes from a file stored in XML format using 
 * the class ShapeEventGeneratorFromXML
 *
 */
package Controller;

import Model.Event.ShapeEventListener;
import Model.Event.ShapeEventGeneratorFromXML;
import Model.Event.ShapeEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class XMLShapeReader extends ShapeReader {

    private ShapeEventGeneratorFromXML segfx; // used to read the data

    public XMLShapeReader() throws ParserConfigurationException, SAXException {

        try {
            segfx = new ShapeEventGeneratorFromXML();
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(XMLShapeReader.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        // Create a listerner to receive callbacks from the
        // ShapeEventGeneratorFromXML object each time a Shape has
        // been read from file.
        ShapeEventListener sel = new ShapeEventListener() {
            /**
             *
             * @param originator unused
             * @param se the shape event object containing details of the shape
             * that has just been read from file.
             */
            @Override
            public void processShapeEvent(Object originator, ShapeEvent se) {
                storeShape(se);
            }
        };
        segfx.registerShapeEventListener("SHAPE", sel);
    }

    /**
     * Called to initiate the processing of the XML file containing shapes.
     *
     * @param file the name of the XML file to process
     * @throws IOException
     * @throws SAXException
     */
    public void getShapesFromFile(String file) throws IOException, SAXException {
        segfx.processScriptFile(file);
    }

    /**
     * Called each time a shape has been read. The shape is stored in the
     * appropriate list according to type.
     *
     * @param se the ShapeEvent object containing details of the shape to be
     * stored.
     */
    @Override
    public void storeShape(ShapeEvent se) {
        super.storeShape(se);
    }

    /**
     * Main method is just for testing in standalone mode
     *
     * @param args unused
     */
    public static void main(String[] args) {
        try {
            XMLShapeReader me = new XMLShapeReader();

            me.getShapesFromFile("stored_shapes.xml");
            System.out.println("Lines loaded = " + getSlList().size());
            System.out.println("Ovals loaded = " + getOlList().size());
            System.out.println("Triangles loaded = " + getStList().size());
            System.out.println("Quadrilateral  loaded = " + getQlList().size());

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLShapeReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
