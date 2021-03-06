/*
 * SimpleLine.java
 *
 * @author Gill Windall
 *
 * Represents a line that can be drawn on a drawing area
 *
 */
package Model;

import java.awt.Color;
import java.awt.Point;
import java.util.List;
import javax.ejb.Stateless;
import Controller.ShapeType;

@Stateless
public class SimpleLine extends SimpleShape{

    public SimpleLine(List<Point> v, Color c, int t, ShapeType so) {
        super(v,c,t,so);
    }
}
