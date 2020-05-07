package world;

import lib.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Home extends Building implements Residential {
    String occupant;

    public Home(String location) {
        super(location);
        this.occupant = null;
    }

    public void moveIn(String thisOccupant) {
        if(this.occupant == null) {
            this.occupant = thisOccupant;
        }
    }

    public void moveOut(String thisOccupant) {
        if(this.occupant.equals(thisOccupant)) {
            this.occupant = null;
        }
    }

    public Collection<String> getOccupants() {
        if (this.occupant == null) {
            return Arrays.asList();
        } else {
            return Arrays.asList(this.occupant);
        }
    }
}
