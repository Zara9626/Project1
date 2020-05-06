package world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Home extends Building implements Residential {
    String occupant;


    public Home(String location) {
        super(location);
        this.occupant = "";
    }
  public void moveIn(String occupant) {
        if(occupant == "") {
            this.occupant = occupant;
        }
  }
  public void moveOut(String occupant) {
        if(occupant == this.occupant) {
            this.occupant = "";
        }
  }
    public Collection<String> getOccupants() {
        ArrayList<String> people = new ArrayList<String> ();
        people.add(this.occupant);
        return people;
    }
}
