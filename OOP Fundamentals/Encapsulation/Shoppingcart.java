package Encapsulation;

import java.util.HashMap;

public class Shoppingcart {

    private HashMap<String, Double> items = new HashMap<>();
    private boolean discountApplied = false;
    private boolean isCheckedOut = false;

    public void addItem(String name, double price) {
        if (isCheckedOut) {
            System.out.println("Cannot modify a checked-out cart");
            return;
        }
        items.put(name, price);
        return;
    }

    public boolean applyDiscount(String code) {
        if (!code.equals("SAVE10") || discountApplied || isCheckedOut)
            return false;
        discountApplied = true;
        return true;
    }

    public double getTotal() {
        double sum = 0;
        for (double price : items.values()) {
            sum += price;
        }
        sum = Math.round((sum) * 100.0) / 100.0;
        double dicounted = Math.round((sum - (sum * 0.1)) * 100.0) / 100.0;
        return discountApplied ? dicounted : sum;
    }

    public void checkout() {
        if (items.size() > 0 && !isCheckedOut)
            isCheckedOut = true;
        return;
    }
}
