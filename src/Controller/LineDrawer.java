/*
 * LineDrawer.java
 *
 * @author Gill Windall
 *
 * This class can be given a SimpleLine object and draw it using a 
 * Graphics2D object.
 * 
 */
package Controller;

import Model.SimpleLine;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class LineDrawer extends Drawer{

    private SimpleLine line; // the line to be drawn

    public LineDrawer(SimpleLine line) {
        this.line = line;
    }

    /**
     * Draw the shape using a Graphics2D object
     *
     * @param g2d Graphics2D object used for drawing
     * @param currentBrightness the current brightness being used to draw
     */
    public void drawShape(Graphics2D g2d, float currentBrightness) {
        // scale the brightness of the colour
        Color c = scaleColour(line.getColour(), currentBrightness);
        g2d.setColor(c);
        // set the thickness of the line
        g2d.setStroke(new BasicStroke(line.getThickness()));
        // get start point
        int xs = line.getXStart();
        int ys = line.getYStart();

        // draw the line
        g2d.drawLine(xs, ys, line.getXEnd(), line.getYEnd());
    }

    
    
}