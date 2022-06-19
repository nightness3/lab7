package common.vehicle;

import java.io.Serializable;

public class Coordinates implements Serializable {

    private Long x;
    private long y;

    public Coordinates (long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
