/*
 * TriangleDrawer.java
 *
 * @author Gill Windall
 *
 * This class can be given a SimpleTriangle object and draw it using a 
 * Graphics2D object.
 * 
 */
package Controller;

import Model.SimpleTriangle;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class TriangleDrawer extends Drawer{

    private SimpleTriangle triangle; // the triangle to be drawn

    public TriangleDrawer(SimpleTriangle triangle) {
        this.triangle = triangle;
    }

    /**
     * Draw the shape using a Graphics2D object
     *
     * @param g2d Graphics2D object used for drawing
     * @param currentBrightness the current brightness being used to draw
     */
    
    @Override
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(triangle.getColour(), currentBrightness);
        g2d.setColor(c);
        // set the thickness of the line
        g2d.setStroke(new BasicStroke(triangle.getThickness()));

        // draw the triangle
        g2d.drawLine(triangle.getVertices().get(0).x, triangle.getVertices().get(0).y, triangle.getVertices().get(1).x, triangle.getVertices().get(1).y);
        g2d.drawLine(triangle.getVertices().get(2).x, triangle.getVertices().get(2).y, triangle.getVertices().get(1).x, triangle.getVertices().get(1).y);
        g2d.drawLine(triangle.getVertices().get(2).x, triangle.getVertices().get(2).y, triangle.getVertices().get(0).x, triangle.getVertices().get(0).y);
    }

}
