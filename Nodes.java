public class Nodes {
    
    private long Xcoords;
    private long Ycoords;

    private int Value;

    private long nodeIndetifier;

    public Nodes(long Xcoords, long Ycoords, int value) {       

        this.Xcoords = Xcoords;
        this.Ycoords = Ycoords;

        this.Value = value;

    }

    public long getXCoordinate() {

        return this.Xcoords;

    }

    public long getYCoordinate() {

        return this.Ycoords;
        
    }

    public int getValue() {

        return this.Value;
        
    }

    public long getIndetifier() {

        return this.nodeIndetifier;
        
    }

    @Override
    public String toString() {

        return "The node: " + this.nodeIndetifier + ". Is at coordinates: " + this.Xcoords + ", " + this.Ycoords + " and has the value: " + this.Value;

    }

}
