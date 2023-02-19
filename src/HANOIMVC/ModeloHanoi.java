package HANOIMVC;

import java.util.ArrayList;

public class ModeloHanoi {
    private int noDiscos;
    private ArrayList<Movimiento> movtos;

    public ModeloHanoi(int noDiscos) {
        this.noDiscos = noDiscos;
        movtos = new ArrayList<>();
        Hanoi('A', 'B', 'C', noDiscos);

    }

    public void Hanoi(char Inicial, char Central, char Final, int N) {
        if (N == 1) {
            movtos.add(new Movimiento(Inicial, Final, N));
            return;
        }
        Hanoi(Inicial, Final, Central, N - 1);
        movtos.add(new Movimiento(Inicial, Final, N));
        Hanoi(Central, Inicial, Final, N - 1);
    }

    public ArrayList<Movimiento> getMovtos() {
        return movtos;
    }

    public int getNoDiscos() {
        return noDiscos;
    }

}
