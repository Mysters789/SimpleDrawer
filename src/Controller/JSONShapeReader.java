/*
 * JSONShapeREader.java
 *
 * @author Gill Windall
 *
 * Used to read shapes from a file stored in JSON format.  Uses the Gson
 * library to convert the JSON from the file into Java objects in memory.
 * You can read more about Gson at https://code.google.com/p/google-gson/
 *
 */
package Controller;

import Model.ShapeType;
import Event.ShapeEvent;
import com.google.gson.Gson;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONShapeReader extends ShapeReader {

    // ListOfShapeEvents is an inner class used to wrap a list of 
    // ShapeEvent objects which hold shape details
    static class ListOfShapeEvents {

        List<ShapeEvent> listOfShapeEvents;
    }

    private ListOfShapeEvents los; // list of all the shapes

    private Gson gson; // gson object used to "parse" the JSON

    public JSONShapeReader() {
        gson = new Gson();
    }

    /**
     * Read the shapes in JSON format from file. The shapes are initially read
     * into a single list (listOfShapeEvents) and then split into separate lists
     * according to type of shape.
     *
     * @param file the file from which to read the JSON
     * @throws FileNotFoundException
     */
    public void getShapesFromFile(String file) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        los = gson.fromJson(br, ListOfShapeEvents.class); // load the shapes
        /**
         * Read through the list of shapes that have been loaded from file and
         * create an appropriate shape object according to type and store it in
         * the relevant list.
         */
        for (ShapeEvent se : los.listOfShapeEvents) {
            super.StoreShapes(se);
        }
    }

    /**
     * This method is used to create some initial data in the JSON file which
     * can then be loaded later
     *
     * @param file the file into which to write the JSON
     */
    private static void generateTestJSON(String file) {
        List<ShapeEvent> list = new ArrayList<>();
        // load in some hard-coded shapes
        list.add(new ShapeEvent(20, 40, 30, 90, Color.red, 5, ShapeType.LINE, "SHAPE"));
        list.add(new ShapeEvent(20, 40, 70, 90, Color.blue, 5, ShapeType.OVAL, "SHAPE"));
        //list.add(new ShapeEvent(80, 95, 70, 45, 60, 70, Color.green, 5, ShapeType.TRIANGLE, "SHAPE"));
        //list.add(new ShapeEvent(34, 45, 67, 35, 80, 90, 60, 70, Color.red, 5, ShapeType.SQUARE, "SHAPE"));
        ListOfShapeEvents los = new ListOfShapeEvents();
        los.listOfShapeEvents = list;
        Gson gson = new Gson();
        String json = gson.toJson(los); // convert the object to a JSON string

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(json); // write the JSON string to file
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method is just for testing in standalone mode
     *
     * @param args unused
     */
    public static void main(String[] args) throws FileNotFoundException {

//        generateTestJSON("stored_shapes.json"); // uncomment if you wish to 
        // create a file of JSON
        // Read the JSON from file and output number of lines and number
        // of ovals read.
        JSONShapeReader me = new JSONShapeReader();
        me.getShapesFromFile("stored_shapes.json");
        System.out.println("Lines loaded = " + me.slList.size());
        System.out.println("Ovals loaded = " + me.olList.size());
        System.out.println("Triangles loaded = " + me.stList.size());
        System.out.println("Squares loaded = " + me.ssList.size());
    }
}