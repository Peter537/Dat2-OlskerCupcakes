package dat.backend.model.entities;

public class Bottom {

    private int id;
    private String name;
    private float price;

    public Bottom(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Bottom(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Bottom{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", price=" + this.getPrice() +
                '}';
    }
}