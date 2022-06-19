package common.vehicle;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Vehicle implements Comparable<Vehicle>, Serializable {

    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.util.Date creationDate;
    private Float enginePower;
    private Long numberOfWheels;
    private VehicleType type;
    private FuelType fuelType;
    private String username;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public int getNumberOfWheels() {
        return numberOfWheels.intValue();
    }

    public String getUsername() {
        return username;
    }

    public VehicleType getType() {
        return type;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setEnginePower(Float enginePower) {
        this.enginePower = enginePower;
    }

    public void setNumberOfWheels(Long numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate.toString() +
                ", enginePower=" + enginePower +
                ", numberOfWheels=" + numberOfWheels +
                ", type=" + type +
                ", fuelType=" + fuelType +
                '}';
    }

    @Override
    public int compareTo(Vehicle o) {
        if (this.getName().length() > o.getName().length()) {
            return -1;
        } else if (this.getName().length() < o.getName().length()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getId(), vehicle.getId()) && Objects.equals(getName(), vehicle.getName()) && Objects.equals(getCoordinates(), vehicle.getCoordinates()) && Objects.equals(getCreationDate(), vehicle.getCreationDate()) && Objects.equals(getEnginePower(), vehicle.getEnginePower()) && Objects.equals(getNumberOfWheels(), vehicle.getNumberOfWheels()) && getType() == vehicle.getType() && getFuelType() == vehicle.getFuelType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCoordinates(), getCreationDate(), getEnginePower(), getNumberOfWheels(), getType(), getFuelType());
    }

}
