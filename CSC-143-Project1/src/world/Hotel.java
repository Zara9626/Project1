package world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Hotel extends Building implements Residential, Rentable, Business {
    String company;

    /* New to HashSet?
        Think of HashSet as a collection without duplicates.
        You can add items, remove items, and see if the set contains items.
        You don't have to use HashSet, but I recommend it.

        Check out the docs here:
        https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/HashSet.html
     */
    HashSet<String> rentals = new HashSet<>();
    HashSet<String> occupants = new HashSet<>();

    public Hotel(String location, String company) {
        super(location);
        this.company = company;
    }
    public void registerRental(String occupant) {
        rentals.add(occupant);
    }
    public void endRental(String occupant) {
        rentals.remove(occupant);
        occupants.remove(occupant);
    }

    public void moveIn(String occupant) {
        if(rentals.contains(occupant)) {
            occupants.add(occupant);
        }

    }

    public void moveOut(String occupant) {
        occupants.remove(occupant);
    }

    /* YOUR CODE HERE */
    public Collection<String> getOccupants() {
        ArrayList<String>person = new ArrayList<String>();
        for(String x:occupants) {
            person.add(x);
        }
        return person;
     //Return properties of current occupants
    }

    @Override
    public void setCompany(String company) {
        this.company = company;

    }

    @Override
    public String getCompany() {
        return company;
    }
}
