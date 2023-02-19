package HANOIMVC;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class VistaHanoi extends JFrame {
    JButton btnIniciar;
    Timer timer;
    private int noDiscos;
    private Graphics g;
    ArrayList<Movimiento> movimientos;
    private ArrayList<Disco> discos;
    private Color[] colors;

    private Torre[] torres;
    int accion;
    private Image screenoff;
    private boolean haciaAbajo, asignado;
    private int numTorre;
    private HashMap<Integer, Integer> multiplos;

    public VistaHanoi(int noDiscos) {
        super("Torre de Hanoi");
        this.noDiscos = noDiscos;
        torres = new Torre[3];
        accion = 0;
        HazInterfaz();
    }

    private void HazInterfaz() {
        setSize(1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        multiplos = new HashMap<>();

        btnIniciar = new JButton("Iniciar");
        add(btnIniciar, BorderLayout.NORTH);

        int calculo = noDiscos;
        for (int i = 0; i < noDiscos; i++) {
            multiplos.put(i + 1, calculo);
            calculo--;
        }

        colors = new Color[]{Color.CYAN, Color.GREEN, Color.MAGENTA, Color.PINK, Color.ORANGE};
        discos = new ArrayList<>();

        int[] posX = {57, 467, 877};

        for (int i = 0; i < torres.length; i++) {
            torres[i] = new Torre(posX[i]);
        }

        setResizable(false);
        setVisible(true);
        screenoff = createImage(getWidth(), getHeight());
        g = screenoff.getGraphics();

        generarDiscos();
        construirBases();

    }

    public void paint(Graphics g) {
        g.drawImage(screenoff, 0, 0, getWidth(), getHeight(), this);

    }

    public void construirBases() {
        super.paint(g);

        g.setColor(new Color(126, 126, 126));
        g.fillRect(57, 665, 350, 48);
        g.fillRect(464, 665, 346, 48);
        g.fillRect(871, 665, 373, 48);

        construirPalos();

        for (int i = 0; i < discos.size(); i++) {
            g.setColor(colors[i]);
            g.fillRoundRect(discos.get(i).getPosX(), discos.get(i).getPosY(), discos.get(i).getWidth(), 40, 8, 8);
        }

        repaint();
    }

    public void construirPalos() {
        g.setColor(new Color(191, 191, 191));

        g.fillRect(222, 270, 20, 395);
        g.fillRect(629, 270, 20, 395);
        g.fillRect(1036, 270, 20, 395);
    }

    public void generarDiscos() {
        int posHorizontal = 57;
        int posVertical = 625;
        int inicialWidth = 310;
        int disco = noDiscos;

        for (int i = 0; i < noDiscos; i++) {
            posHorizontal += 20;
            discos.add(new Disco(posHorizontal, posVertical, inicialWidth, disco));
            posVertical -= 40;
            inicialWidth -= 40;
            disco--;
        }
        Collections.reverse(discos);

        torres[0].setDiscos(discos.size());
    }

    public boolean subir(Movimiento movimiento) {
        super.paint(g);
        int noDisco = movimiento.getDisco();
        Disco disco = discos.get(noDisco - 1);

        if (disco.getPosY() > 230 && !haciaAbajo) {
            disco.setPosY(disco.getPosY() - 10);
            construirBases();
            return true;
        }

        if (!asignado) {

            int numTorreFin =obtenerNumTorre(movimiento.getFin());
            int numTorreIni = obtenerNumTorre(movimiento.getIni());

            torres[numTorreIni].setDiscos(torres[numTorreIni].getDiscos() - 1);
            torres[numTorreFin].setDiscos(torres[numTorreFin].getDiscos() + 1);
            asignado = true;
        }
        return false;
    }

    public boolean bajar(Movimiento movimiento) {


        int height = 665 - (40 * torres[numTorre].getDiscos());
        Disco disco = discos.get(movimiento.getDisco() - 1);

        if (haciaAbajo) {
            disco.setPosY(disco.getPosY() + 10);

            if (disco.getPosY() == height) {
                haciaAbajo = false;
                accion++;
                asignado = false;
                checarSiTermina();
            }
            construirBases();
            return true;
        }

        return false;
    }

    public void mover(Movimiento movimiento) {
        char torreDestino = movimiento.getFin();
        char torreInicio = movimiento.getIni();
        int movilidad = torreDestino < torreInicio
                        ? -10
                        : 10;

        int numTorre = obtenerNumTorre(torreDestino);

        int destinoPosX = torres[numTorre].getPosX();
        Disco disco = discos.get(movimiento.getDisco() - 1);

        if (disco.getPosX() != destinoPosX + (20 * multiplos.get(movimiento.getDisco()))) {
            disco.setPosX(disco.getPosX() + movilidad);
            construirBases();
            return;
        }

        this.numTorre = numTorre;
        haciaAbajo = true;

    }

    private void checarSiTermina() {
        if (accion == movimientos.size()) {
            System.out.println("entra");
            timer.stop();
        }
    }

    private int obtenerNumTorre(char torre) {
        return switch (torre) {
            case 'A': yield 0;
            case 'B': yield 1;
            case 'C': yield 2;
            default: throw new IllegalArgumentException("Dato no valido");
        };
    }

    public void setControlador(ControladorHanoi c) {
        timer = new Timer(10, c);
        btnIniciar.addActionListener(c);
    }

    public void obtenerMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
        this.movimientos.forEach(movimiento -> System.out.println(movimiento.getIni() + "\t" + movimiento.getFin() + "\t" + movimiento.getDisco()));
    }

}
