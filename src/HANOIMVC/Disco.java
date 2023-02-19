package HANOIMVC;

public class Disco {

    private int noDisco;
    private int posX, posY, width;

    public Disco(int posX, int posY, int width, int noDisco) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.noDisco = noDisco;
    }

    public int getNoDisco() {
        return noDisco;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public void setNoDisco(int noDisco) {
        this.noDisco = noDisco;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "noDisco=" + noDisco +
                ", posX=" + posX +
                ", posY=" + posY +
                ", width=" + width +
                '}';
    }
}
