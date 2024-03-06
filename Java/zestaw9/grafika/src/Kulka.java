import java.awt.*;
import java.awt.geom.*;

class Kulka extends Ellipse2D.Float {
    Plansza p;
    int dx, dy;
    int punkty;

    Kulka(Plansza p, int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 10;

        this.p = p;
        this.dx = dx;
        this.dy = dy;
        this.punkty = 0;
    }

    void nextKrok() {
        x += dx;
        y += dy;

        if (getMinX() < 0 || getMaxX() > p.getWidth()) {
            dx = -dx;
        }

        if (getMinY() < 0 || getMaxY() > p.getHeight()) {
            dy = -dy;
        }

        // Sprawdzenie kolizji z belką
        if (intersects(p.b)) {
            // Odbicie od belki
            dy = -dy;
        }

        // Sprawdzenie kolizji z cegielkami
        for (Cegielka c : p.cegielki) {
            if (c.isAktywna() && intersects(c)) {
                // Odbicie od cegielki
                dy = -dy;
                c.setAktywna(false); // Zniszcz cegielkę
                punkty += 10; // Dodaj punkty za zniszczenie cegielki
            }
        }

        p.zaktualizujPunkty(punkty);

        p.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
}
