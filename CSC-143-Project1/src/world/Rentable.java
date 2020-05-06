package world;

import lib.Pair;

import java.util.Collection;

public interface Rentable {
    public void registerRental(String occupant);
    public void endRental(String occupant);
}
