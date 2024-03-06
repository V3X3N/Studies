import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Plansza extends JPanel implements MouseMotionListener {
    Belka b;
    Kulka a;
    SilnikKulki s;
    ArrayList<Cegielka> cegielki;
    int punkty;
    boolean graRozpoczeta;

    private static final int OKNO_STARTOWE_SZEROKOSC = 375;
    private static final int OKNO_STARTOWE_WYSOKOSC = 250;

    Plansza() {
        super();
        addMouseMotionListener(this);

        b = new Belka(100);
        a = new Kulka(this, 100, 150, 1, 1);
        s = new SilnikKulki(a);
        cegielki = new ArrayList<>();

        punkty = 0;
        graRozpoczeta = false;

        // Dodaj przycisk START
        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGry();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(20, 0, 0, 0);
        add(startButton, gbc);
    }

    private void dodajCegielki(int rows, int cols, int width, int height) {
        cegielki.clear();  // Wyczyść listę cegiełek
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cegielka c = new Cegielka(j * (width + 5), i * (height + 5), width, height);
                cegielki.add(c);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (graRozpoczeta) {
            g2d.fill(a);
            g2d.fill(b);

            for (Cegielka c : cegielki) {
                if (c.isAktywna()) {
                    g2d.fill(c);
                }
            }

            // Rysuj punkty na planszy
            g2d.setColor(Color.GREEN);
            Font font = new Font("Arial", Font.BOLD, 16);
            g2d.setFont(font);
            g2d.drawString("Punkty: " + punkty, 10, 20);

            // Sprawdź, czy kulka dotknęła dolnej krawędzi
            if (a.getMaxY() >= getHeight()) {
                zakonczGre();
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (graRozpoczeta) {
            b.setX(e.getX() - 50);
            repaint();
        }
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void zaktualizujPunkty(int nowePunkty) {
        punkty = nowePunkty;
    }

    private void startGry() {
        punkty = 0;
        graRozpoczeta = true;
        removeIfExist(JButton.class);
        setPreferredSize(new Dimension(400, 300));
        cegielki.clear();
        dodajCegielki(5, 8, 40, 20);  // Dodaj cegiełki na nowo
        revalidate();
        repaint();
    }

    private void zakonczGre() {
        graRozpoczeta = false;
        JOptionPane.showMessageDialog(this, "Koniec gry! Twoje punkty: " + punkty, "Koniec gry", JOptionPane.INFORMATION_MESSAGE);
        punkty = 0;
        dodajPrzyciskStart();
        repaint();
    }

    private void removeIfExist(Class<?> componentType) {
        for (Component comp : getComponents()) {
            if (componentType.isInstance(comp)) {
                remove(comp);
            }
        }
    }

    private void dodajPrzyciskStart() {
        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGry();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(20, 0, 0, 0);
        add(startButton, gbc);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame jf = new JFrame();
            Plansza plansza = new Plansza();
            jf.add(plansza);

            jf.setTitle("Test grafiki");
            jf.setSize(OKNO_STARTOWE_SZEROKOSC, OKNO_STARTOWE_WYSOKOSC);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setLocationRelativeTo(null);
            jf.setVisible(true);
        });
    }
}
