package world;

import lib.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Restaurant extends Store {
    public HashMap<String, Collection<Pair<String, Integer>>> recipes = new HashMap<>();
    public Restaurant(String location, String company) {
        super(location, company);
    }

    public void learnRecipe(String name, Collection<Pair<String, Integer>> ingredients) {
        recipes.put(name, ingredients);
    }

    public Collection<Pair<String, Integer>> getProducts() {
        ArrayList<Pair<String, Integer>> productList = new ArrayList<>();

        // cycle through every recipe
        for (String recipeName : this.recipes.keySet()) {
            Integer recipeQty = null;

            // cycle through every ingredient of this recipe
            for (Pair recipePart : this.recipes.get(recipeName)) {
                String  ingredName = recipePart.left.toString();
                Integer ingredQty = (Integer) recipePart.right;

                // figure out maximum qty to prepare with this ingredient inventory as limit
                Integer ingredAvail = this.supplies.get(ingredName);
                if (ingredAvail != null) {
                    Double floorCalc = Math.floor(ingredAvail / ingredQty);
                    Integer ingredCap = floorCalc.intValue();
                    if (recipeQty == null || ingredCap < recipeQty) recipeQty = ingredCap;
                }
            }

            // add minimum producible recipe quantity to result list
            if (recipeQty == null) recipeQty = 0;
            Pair<String, Integer> productCap = new Pair<>(recipeName, recipeQty);
            productList.add(productCap);
        }
        return productList;
    }

    @Override
    // copied from Supermarket class so this class would compile. Must rewrite this method.
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
