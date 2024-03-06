import java.awt.geom.*;

class Cegielka extends Rectangle2D.Float {
    private boolean aktywna;

    Cegielka(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.aktywna = true;
    }

    boolean isAktywna() {
        return aktywna;
    }

    void setAktywna(boolean aktywna) {
        this.aktywna = aktywna;
    }
}
