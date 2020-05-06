package world;

import lib.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Supermarket extends Store {
    public Supermarket(String location, String company) {
        super(location, company);
    }

    @Override
    public Collection<Pair<String, Integer>> getProducts() {
        ArrayList<Pair<String, Integer>> productList = new ArrayList<>();

        for (String thisKey : this.supplies.keySet()) {
            Integer thisValue = this.supplies.get(thisKey);
            Pair<String, Integer> thisPair = new Pair<>(thisKey, thisValue);
            productList.add(thisPair);
        }

        return productList;
    }

    @Override
    public Collection<Pair<String, Integer>> purchase(Collection<Pair<String, Integer>> order) {
        ArrayList<Pair<String, Integer>> purchaseList = new ArrayList<>();

        for (Pair thisPair : order) {
            String ordProduct = thisPair.left.toString();
            Integer ordQuantity = (Integer) thisPair.right;
            Integer invQuantity = this.supplies.get(ordProduct);

            if (invQuantity == null) {
                // handle requests for things not in inventory
            } else if (invQuantity < ordQuantity ) {
                // handle requests that cannot be covered by current inventory
                Pair<String, Integer> thisItem = new Pair<>(ordProduct, invQuantity);
                this.supplies.replace(ordProduct, 0);
                purchaseList.add(thisItem);
            } else {
                // handle requests that can be covered by current inventory
                Pair<String, Integer> thisItem = new Pair<>(ordProduct, ordQuantity);
                this.supplies.replace(ordProduct, invQuantity - ordQuantity);
                purchaseList.add(thisItem);
            }
        }
        return purchaseList;
    }

}
