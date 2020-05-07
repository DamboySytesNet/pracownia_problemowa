package com.pracownia.vanet.model.devices;

import com.pracownia.vanet.model.event.Event;
import com.pracownia.vanet.model.Point;
import com.pracownia.vanet.model.event.Task;
import com.pracownia.vanet.model.network.ConnectionRoute;
import com.pracownia.vanet.model.network.Network;
import com.pracownia.vanet.model.road.CrossRoad;
import com.pracownia.vanet.model.road.Road;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Vehicle extends Device {

    /*------------------------ FIELDS REGION ------------------------*/
    private int id;
    private Road road;
    private double speed;
    private boolean direction = true; // True if from starting point to end point

    private Date date;
    @Setter(AccessLevel.NONE)
    private Point previousCrossing;
    private List<Task> tasks;

    /*------------------------ METHODS REGION ------------------------*/
    public Vehicle() {
        this.tasks = new ArrayList<>();
        this.occurrences = new AtomicInteger();
        road = new Road();
        currentLocation = new Point();
    }

    public Vehicle(Road road, int id, double range, double speed) {
        this.occurrences = new AtomicInteger();
        this.tasks = new ArrayList<>();
        this.road = road;
        this.id = id;
        this.range = range;
        this.speed = speed + 0.001;
        this.currentLocation = new Point(road.getStartPoint().getX(), road.getStartPoint().getY());
    }

    public void setPreviousCrossing(Point previousCrossing) {
        this.previousCrossing = previousCrossing;
        this.setDate(new Date());
    }

    @Override
    public void move() {
        double distanceToEndPoint = Math.sqrt(Math.pow(road.getEndPoint()
                                                           .getX() - currentLocation.getX(), 2) +
                                                      Math.pow(road.getEndPoint().getY() - currentLocation.getY(), 2));

        double cos = (road.getEndPoint().getX() - currentLocation.getX()) / distanceToEndPoint;
        double sin = (road.getEndPoint().getY() - currentLocation.getY()) / distanceToEndPoint;

        double distanceToStart;

        if (direction) {
            distanceToStart = Math.sqrt(Math.pow(currentLocation.getX() - road.getStartPoint()
                                                                              .getX(), 2) +
                                                Math.pow(currentLocation.getY() - road.getStartPoint().getY(), 2));
            currentLocation.setX(currentLocation.getX() + cos * speed);
            currentLocation.setY(currentLocation.getY() + sin * speed);
        } else {
            distanceToStart = Math.sqrt(Math.pow(currentLocation.getX() - road.getEndPoint()
                                                                              .getX(), 2) +
                                                Math.pow(currentLocation.getY() - road.getEndPoint().getY(), 2));

            currentLocation.setX(currentLocation.getX() - cos * speed);
            currentLocation.setY(currentLocation.getY() - sin * speed);
        }

        if (distanceToStart >= road.getDistance()) {
            direction = !direction;
        }
    }

    @Override
    public void send( Network dynamicNetwork ) {
        if (tasks.isEmpty()) {
            return;
        }
        tasks.forEach(task1 ->
                              task1.prepareEvent()
                                   .ifPresent(event -> {
                                       Optional<ConnectionRoute> route = dynamicNetwork.getRoute(this,
                                                                                                 event.getTarget());
                                       event.setRoutingPath(String.valueOf(id));
                                       route.ifPresent(r -> r.send(Optional.of(event)));
                                   }));
    }

    @Override
    public Optional<Event> transfer(Event event, Device receivedFrom) {
        event.setRoutingPath(event.getRoutingPath() + "->" + id);
        return Optional.of(event);
    }

    @Override
    public void receive(Event event) {
        System.out.println("I vehicle: " + this.id + "Message Received: " + event.toString());
    }

    @Override
    public void turn(CrossRoad crossRoad) {
        crossRoad.transportVehicle(this);
    }


    //samohcody nie dziele saie dtas
    @Override
    public void registerTask( Task task ) {
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        return "ID:\t" + id;
    }
}
    