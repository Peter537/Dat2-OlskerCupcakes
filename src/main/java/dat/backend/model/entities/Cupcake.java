package dat.backend.model.entities;

public class Cupcake {

    private int id;
    private Bottom bottom;
    private Top top;

    public Cupcake(Bottom bottom, Top top) {
        this.bottom = bottom;
        this.top = top;
    }

    public Cupcake(int id, Bottom bottom, Top top) {
        this.id = id;
        this.bottom = bottom;
        this.top = top;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bottom getBottom() {
        return this.bottom;
    }

    public void setBottom(Bottom bottom) {
        this.bottom = bottom;
    }

    public Top getTop() {
        return this.top;
    }

    public void setTop(Top top) {
        this.top = top;
    }

    public float getPrice() {
        return this.bottom.getPrice() + this.top.getPrice();
    }

    @Override
    public String toString() {
        return "Cupcake{" +
                "bottom=" + this.getBottom() +
                ", top=" + this.getTop() +
                '}';
    }
}