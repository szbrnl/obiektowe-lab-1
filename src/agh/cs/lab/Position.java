package agh.cs.lab;

/**
 * Created by student31 on 2017-10-10.
 */
public class Position {
    public final int x;
    public final int y;

    private final String cachedToString;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        cachedToString = "("+x+","+y+")";
    }

    public String toString() {
        return cachedToString;
    }

    public boolean smaller(Position other) {
        return x<=other.x && y<=other.y;
    }

    public boolean larger(Position other) {
        return x>=other.x && y>=other.y;
    }

    public Position add(Position other) {
        return new Position(x+other.x, y+other.y);
    }

    public Position subtract(Position other) {
        return new Position(x-other.x, y-other.y);
    }
    
    public boolean equals(Object other) {
        if(this==other) return true;
        if(!(other instanceof Position)) return false;

        Position that = (Position) other;

        return x == that.x && y == that.y;
    }
}
