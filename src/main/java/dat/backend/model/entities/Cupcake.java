package dat.backend.model.entities;

public class Cupcake {

    private int id;
    private Bottom bottom;
    private Top top;
    private int quantity;

    public Cupcake(Bottom bottom, Top top, int quantity) {
        this.bottom = bottom;
        this.top = top;
        this.quantity = quantity;
    }

    public Cupcake(int id, Bottom bottom, Top top, int quantity) {
        this.id = id;
        this.bottom = bottom;
        this.top = top;
        this.quantity = quantity;
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

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.bottom.getPrice() + this.top.getPrice();
    }

    @Override
    public String toString() {
        return "Cupcake{" +
                "bottom=" + this.bottom +
                ", top=" + this.top +
                ", quantity=" + this.quantity +
                '}';
    }
}