package world;

import java.util.Collection;

public interface Residential {
    public void moveIn(String occupant);
    public void moveOut(String occupant);
    public Collection<String> getOccupants();
}
