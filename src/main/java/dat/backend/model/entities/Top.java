package dat.backend.model.entities;

public class Top {

    private int id;
    private String name;
    private int price;

    public Top(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Top(int id, String name, int price) {
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

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Top{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", price=" + this.price +
                '}';
    }
}