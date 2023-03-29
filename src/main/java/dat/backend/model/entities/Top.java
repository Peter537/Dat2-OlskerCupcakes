package dat.backend.model.entities;

public class Top {

    private int id;
    private String name;
    private float price;

    public Top(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Top(int id, String name, float price) {
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
        return "Top{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", price=" + this.getPrice() +
                '}';
    }
}