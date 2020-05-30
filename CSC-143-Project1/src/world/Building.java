package world;

public abstract class Building {
    private String location;

    //This will define the parameters of a building
    protected Building(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
