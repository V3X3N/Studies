import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;

public class Applet2 extends Applet {
    public void paint(Graphics g) {
        // Rysowanie prostokąta
        g.setColor(Color.blue);
        g.fillRect(20, 20, 150, 100);

        // Rysowanie napisu wewnątrz prostokąta
        g.setColor(Color.white);
        g.drawString("To jest drugi applet!", 30, 70);
    }
}
