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

        // cycle through every menu item requested
        for (Pair orderItem : order) {
            Integer recipeQty = null;
            String orderRecipe = orderItem.left.toString();
            Integer orderQty = (Integer) orderItem.right;
            try {
                // cycle through every ingredient in this menu item
                for (Pair itemIngred : recipes.get(orderRecipe)) {
                    String ingredName = itemIngred.left.toString();
                    Integer ingredQty = (Integer) itemIngred.right;
                    // figure out maximum qty to prepare with this ingredient inventory as limit
                    Integer ingredAvail = this.supplies.get(ingredName);
                    if (ingredAvail == null) ingredAvail = 0;
                    if (ingredAvail != null) {
                        Double floorCalc = Math.floor(ingredAvail / ingredQty);
                        Integer ingredCap = floorCalc.intValue();
                        if (recipeQty == null || ingredCap < recipeQty) recipeQty = ingredCap;
                    }
                }
            } catch (NullPointerException e) { /* Recipe requested that was not on menu */ }

            if (recipeQty == null) recipeQty = 0;
            if (recipeQty > 0) {
                // add menu items with sufficient ingredients to result set
                Pair<String, Integer> itemMade = new Pair<>(orderRecipe, recipeQty);
                purchaseList.add(itemMade);
                // subtract ingredients used in menu items from supplies
                for (Pair itemIngred : recipes.get(orderRecipe)) {
                    String ingredName = itemIngred.left.toString();
                    Integer ingredQty = ((Integer) itemIngred.right) * recipeQty;
                    Integer ingredRemain = this.supplies.get(ingredName) - ingredQty;
                    this.supplies.replace(ingredName, ingredRemain);
                }
            }
        }

        return purchaseList;
    }



}
