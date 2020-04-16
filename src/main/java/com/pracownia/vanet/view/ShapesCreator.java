package com.pracownia.vanet.view;

import com.pracownia.vanet.Main;
import com.pracownia.vanet.model.road.Road;
import com.pracownia.vanet.model.devices.Vehicle;
import com.pracownia.vanet.model.event.EventSource;
import com.pracownia.vanet.model.devices.Device;
import com.pracownia.vanet.model.devices.RoadSide;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
@Deprecated
public class ShapesCreator {

//    /*------------------------ FIELDS REGION ------------------------*/
//    private Group root;
//    private Simulation simulation;
//    private Main main;
//
//    /*------------------------ METHODS REGION ------------------------*/
//    public ShapesCreator(Group root, Simulation simulation, Main main) {
//        this.root = root;
//        this.simulation = simulation;
//        this.main = main;
//    }
//
//    private Circle circleCreator(Vehicle vehicle) {
//        Circle circle = new Circle();
//        circle.setCenterX(vehicle.getCurrentLocation().getX());
//        circle.setCenterY(vehicle.getCurrentLocation().getY());
//        circle.setFill(Color.BLACK);
//        circle.setRadius(8.0);
//        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
//            main.getSpeedField().setText(String.valueOf(vehicle.getSpeed()));
//            main.getTrustLevelField().setText(String.valueOf(vehicle.getTrustLevel()));
//            main.getVehIdField().setText(String.valueOf(vehicle.getId()));
//            main.getConnPointsField().setText(String.valueOf(vehicle.getConnectedPoints().size()));
//            main.getConnVehField().setText(String.valueOf(vehicle.getConnectedVehicles().size()));
//            main.getConnEventsField().setText(String.valueOf(vehicle.getCollectedEvents().size()));
//        });
//        return circle;
//    }
//
//    private Circle circleCreator(EventSource eventSource) {
//        Circle circle = new Circle();
//        circle.setCenterX(eventSource.getLocalization().getX());
//        circle.setCenterY(eventSource.getLocalization().getY());
//        circle.setFill(Color.RED);
//        circle.setRadius(8.0);
//        return circle;
//    }
//
//    private Circle circleCreator(RoadSide stationaryNetworkPoint) {
//        Circle circle = new Circle();
//        circle.setCenterX(stationaryNetworkPoint.getCurrentLocation().getX());
//        circle.setCenterY(stationaryNetworkPoint.getCurrentLocation().getY());
//        circle.setFill(Color.BLUE);
//        circle.setRadius(6.0);
//        return circle;
//    }
//
//    private Circle rangeCreator(Vehicle vehicle) {
//        Circle circle = new Circle();
//        circle.setRadius(vehicle.getRange());
//        circle.setCenterX(vehicle.getCurrentLocation().getX());
//        circle.setCenterY(vehicle.getCurrentLocation().getY());
//        circle.setFill(Color.TRANSPARENT);
//        circle.setStroke(Color.TRANSPARENT);
//        return circle;
//    }
//
//    private Circle rangeCreator(EventSource eventSource) {
//        Circle circle = new Circle();
//        circle.setRadius(eventSource.getRange());
//        circle.setCenterX(eventSource.getLocalization().getX());
//        circle.setCenterY(eventSource.getLocalization().getY());
//        circle.setFill(Color.TRANSPARENT);
//        circle.setStroke(Color.TRANSPARENT);
//        return circle;
//    }
//
//    private Line lineCrator(Road road) {
//        Line line = new Line();
//        line.setStartX(road.getStartPoint().getX());
//        line.setStartY(road.getStartPoint().getY());
//        line.setEndX(road.getEndPoint().getX());
//        line.setEndY(road.getEndPoint().getY());
//
//        return line;
//    }
//
//    private Label labelCreator(Device device) {
//        Label label = new Label();
//        label.setText(String.valueOf(device.getCollectedEvents().size()));
//        label.setLayoutX(device.getCurrentLocation().getX());
//        label.setLayoutY(device.getCurrentLocation().getY());
//
//        return label;
//    }
//
//    public void setRoutesLines(Simulation simulation) {
//        for (int i = 0; i < simulation.getMapRepresentation().getRoads().size(); i++) {
//            Line line = lineCrator(simulation.getMapRepresentation().getRoads().get(i));
//            root.getChildren().add(line);
//        }
//    }
//
//    public void setVehicleCircles(Simulation simulation, int amount) {
//        for (int i = simulation.getMapRepresentation().getVehicles().size() - amount; i < simulation.getMapRepresentation()
//                                                                                                    .getVehicles()
//                                                                                                    .size(); i++) {
//            Circle circle = circleCreator(simulation.getMapRepresentation().getVehicles().get(i));
//            Circle rangeCircle = rangeCreator(simulation.getMapRepresentation().getVehicles().get(i));
//            simulation.getCircleList().add(circle);
//            simulation.getRangeList().add(rangeCircle);
//            root.getChildren().add(rangeCircle);
//            root.getChildren().add(circle);
//        }
//    }
//
//    public void setCopyCircle(Vehicle vehicle) {
//        Circle circle = circleCreator(vehicle);
//        Circle rangeCircle = rangeCreator(vehicle);
//        simulation.getCircleList().add(circle);
//        simulation.getRangeList().add(rangeCircle);
//        root.getChildren().add(rangeCircle);
//        root.getChildren().add(circle);
//    }
//
//    public void setSourceEventCircles(Simulation simulation) {
//        for (int i = 0; i < simulation.getMapRepresentation().getEventSources().size(); i++) {
//            Circle circle = circleCreator(simulation.getMapRepresentation().getEventSources().get(i));
//            Circle rangeCircle = rangeCreator(simulation.getMapRepresentation().getEventSources().get(i));
//            root.getChildren().add(circle);
//            root.getChildren().add(rangeCircle);
//        }
//    }
//
//    public void setStationaryPointCircles(Simulation simulation) {
//        for (int i = 0; i < simulation.getMapRepresentation().getRoadSides().size(); i++) {
//            Circle circle = circleCreator(simulation.getMapRepresentation().getRoadSides().get(i));
//            simulation.getStationaryCirclelist().add(circle);
//            root.getChildren().add(circle);
//        }
//    }
//
//    public void setLabels(Simulation simulation, int amount) {
//        for (int i = 0; i < simulation.getMapRepresentation().getRoadSides().size(); i++) {
//            Label label = labelCreator(simulation.getMapRepresentation().getRoadSides().get(i));
//            root.getChildren().add(label);
//        }
//
//        for (int i = simulation.getMapRepresentation().getVehicles().size() - amount; i < simulation.getMapRepresentation()
//                                                                                                    .getVehicles()
//                                                                                                    .size(); i++) {
//            Label label = labelCreator(simulation.getMapRepresentation().getVehicles().get(i));
//            simulation.getLabelList().add(label);
//            root.getChildren().add(label);
//        }
//    }
}
    