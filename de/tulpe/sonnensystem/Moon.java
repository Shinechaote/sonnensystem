/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tulpe.sonnensystem;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.util.Duration;


/*
 * @author praktikant
 
public class Moon {

    double bigHalfAx;
    double smallHalfAx;
    double tPlanet;
    double phi0;
    double tSteps;
    double tStart;
    double tStepSize;
    double phi;
    double r;
    double eps;
    double velocity;
    double p;
    planet orbitPlanet;
    PointSystem firstPoint;
    double xDiff;
    double yDiff;
    Circle moonRepresentation;
    Path moonPath;
    PathTransition moonPathTransition;

    Moon(double bigHalfAx, double smallHalfAx, double tPlanet, double phi0, double tSteps, double tStart, double tStepSize, planet orbitPlanet) {
        this.bigHalfAx = bigHalfAx;
        this.smallHalfAx = smallHalfAx;
        this.tPlanet = tPlanet;
        this.phi0 = phi0;
        this.tSteps = tSteps;
        this.tStart = tStart;
        this.tStepSize = tStepSize;
        this.phi = phi0;
        this.p = calcP();
        this.eps = calcEps();
        this.r = calcR();
        this.velocity = calcVelocity();
        this.orbitPlanet = orbitPlanet;
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.moonRepresentation = new Circle(1);
        this.firstPoint = new PointSystem(r, phi0, orbitPlanet.pathCoordinates.get(0).x, orbitPlanet.pathCoordinates.get(0).y);
        createPathTransition();

    }

    PointSystem coordinatesCalculation(double tStep) {
//        System.out.println("Orbit Stepsize:" + orbitPlanet.tStepSize);
        System.out.println((int)(tStep/this.tStepSize)%1000);

//        System.out.println(tStep/orbitPlanet.tStepSize);
        this.velocity = calcVelocity();
        this.eps = calcEps();
        this.p = calcP();
        this.phi += tStepSize * (2 * velocity * tPlanet) / (r * r);
        this.r = calcR();
        return new PointSystem(r, phi - phi0, orbitPlanet.pathCoordinates.get((int)(tStep*28)%1000).x, orbitPlanet.pathCoordinates.get((int)(tStep*28)%1000).y);
//        return new PointSystem(r, phi - phi0, orbitPlanet.pathCoordinates.get(0).x, orbitPlanet.pathCoordinates.get(0).y);
    }

    double calcR() {
        return p / (1 - eps * Math.cos(phi));
    }

    double calcP() {
        return smallHalfAx * smallHalfAx / bigHalfAx;
    }

    double calcEps() {
        return Math.pow(1 - (smallHalfAx * smallHalfAx) / (bigHalfAx * bigHalfAx), 0.5);
    }

    double calcVelocity() {
        return Math.PI * bigHalfAx * smallHalfAx / tPlanet;
    }

    void createPathTransition() {
        List<PathElement> elements = new ArrayList<>();

        elements.add(new MoveTo(this.firstPoint.x, this.firstPoint.y));

        for (double i = 0; i < this.tSteps; i += this.tStepSize) {
            PointSystem newCoordinates = coordinatesCalculation(i);
            if (i == this.tStepSize) {
                this.moonRepresentation.setTranslateX(newCoordinates.x);
                this.moonRepresentation.setTranslateY(newCoordinates.y);

            }
            elements.add(new LineTo(newCoordinates.x, newCoordinates.y));

        };

        this.moonPath = new Path(elements);
        PathTransition pathTransition = new PathTransition(Duration.seconds(this.tPlanet / this.orbitPlanet.timelapseSpeed), this.moonPath, this.moonRepresentation);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(false);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        this.moonPathTransition = pathTransition;

    }

    void changeSpeed(double timelapseSpeed) {
        double time = this.moonPathTransition.getCurrentTime().toSeconds();
        double duration = this.moonPathTransition.getCycleDuration().toSeconds();

        this.moonPathTransition.setDuration(Duration.seconds(this.tPlanet / timelapseSpeed));
        this.moonPathTransition.stop();

        this.moonPathTransition.jumpTo(Duration.seconds(time * ((this.tPlanet / timelapseSpeed) / duration)));
        this.moonPathTransition.play();
    }

}
 */
