package dat.backend.model.entities;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Cupcake> cupcakeList = new ArrayList<>();

    public ShoppingCart() { }

    public void addCupcake(Cupcake cupcake) {
        this.cupcakeList.add(cupcake);
    }

    public void removeCupcake(Cupcake cupcake) {
        this.cupcakeList.remove(cupcake);
    }

    public void removeCupcakeById(int id) {
        for (Cupcake cupcake : this.cupcakeList) {
            if (cupcake.getId() == id) {
                this.cupcakeList.remove(cupcake);
                break;
            }
        }
    }

    public void clearCart() {
        this.cupcakeList.clear();
    }

    public List<Cupcake> getCupcakeList() {
        return this.cupcakeList;
    }

    public float getTotalPrice() {
        float totalPrice = 0;
        for (Cupcake cupcake : this.cupcakeList) {
            totalPrice += cupcake.getPrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cupcakeList=" + this.getCupcakeList() +
                '}';
    }
}