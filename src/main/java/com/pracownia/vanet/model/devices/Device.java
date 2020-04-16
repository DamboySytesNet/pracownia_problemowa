package com.pracownia.vanet.model.devices;

import com.pracownia.vanet.model.event.Event;
import com.pracownia.vanet.model.Point;
import com.pracownia.vanet.model.network.Network;
import com.pracownia.vanet.model.road.CrossRoad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class Device {

    /*------------------------ FIELDS REGION ------------------------*/
    protected int id;
    protected Point currentLocation = new Point();
    protected double range;
    protected List<Vehicle> connectedVehicles = new ArrayList<>();
    protected List<Event> collectedEvents = new ArrayList<>();
    protected List<Event> encounteredEvents = new ArrayList<>();

    /*------------------------ METHODS REGION ------------------------*/
    public Device(int id, Point currentLocation, double range) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.range = range;
    }

    public abstract void move();

    public abstract void send(Network dynamicNetwork);
    public abstract Event transfer(Event event, Device receivedFrom);
    public abstract void receive(Event event);

    protected double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    public abstract void turn(CrossRoad crossRoad);
}
    