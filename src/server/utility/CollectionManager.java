package server.utility;

import common.vehicle.Vehicle;
import server.database.DBWorker;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionManager {

    private DBWorker dbWorker;
    private Set<Vehicle> vehicles;

    public CollectionManager (DBWorker dbWorker) {
        this.dbWorker = dbWorker;
        vehicles = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void updateCollection () {
        vehicles = VehicleCollection.getCollection(dbWorker);
    }

    public synchronized void add (Vehicle v) {
        vehicles.add(v);
    }

    public synchronized boolean remove (Vehicle v) {
        return vehicles.remove(v);
    }

    public Set<Vehicle> getCollection () {
        return vehicles;
    }

    public synchronized void clear (String username) {
        vehicles.stream().filter(v -> v.getUsername().equals(username)).forEach(v -> remove(v));
    }

}
