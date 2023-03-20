package dat.backend.model.entities;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Cupcake> cupcakeList = new ArrayList<>();

    public ShoppingCart() {
    }

    public void addCupcake(Cupcake cupcake) {
        cupcakeList.add(cupcake);
    }

    public void removeCupcake(Cupcake cupcake) {
        cupcakeList.remove(cupcake);
    }

    public void clearCart() {
        cupcakeList.clear();
    }


    public float getTotalPrice() {

        float totalPrice = 0;

        for (Cupcake cupcake : cupcakeList) {
            totalPrice += cupcake.getPrice();
        }
        return totalPrice;
    }

    public List<Cupcake> getCupcakeList() {
        return cupcakeList;
    }







}
