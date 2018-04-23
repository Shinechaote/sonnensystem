/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tulpe.sonnensystem;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.util.Duration;

/**
 *
 * @author praktikant
 */
public class planet {

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
    PointSystem firstPoint;
    Circle planetRepresentation;
    PathTransition planetPathTransition;
    RotateTransition planetRotateTransition;
    ParallelTransition planetTransition;
    Path planetPath;
    double timelapseSpeed;
    double xDiff;
    double yDiff;
    List<PointSystem> pathCoordinates = new ArrayList();
    double tSpinTime;

    public planet(double bigHalfAx, double smallHalfAx, double tPlanet, double phi0, double tSteps, double tStart, double tStepSize, double radius, double timelapseSpeed, double xDiff, double yDiff, double tSpinTime) {
        this.bigHalfAx = bigHalfAx;
        this.smallHalfAx = smallHalfAx;
        this.phi0 = phi0;
        this.tPlanet = tPlanet;
        this.tSteps = tSteps;
        this.tStart = tStart;
        this.tStepSize = tStepSize;
        this.phi = phi0;
        this.velocity = calcVelocity();
        this.eps = calcEps();
        this.p = calcP();
        this.r = calcR();
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.firstPoint = new PointSystem(this.r, this.phi0, this.xDiff, this.yDiff);
        this.tSpinTime = tSpinTime;

        this.planetRepresentation = new Circle(12.5 / (696342 / radius));
        this.planetRepresentation.setTranslateX(this.firstPoint.x);
        this.planetRepresentation.setTranslateY(this.firstPoint.y);

        this.timelapseSpeed = timelapseSpeed;
        createPathTransition();
        createRotateTransition();
        createParallelTransition(this.planetPathTransition, this.planetRotateTransition);

    }
   
    PointSystem coordinatesCalculation() {
        this.velocity = calcVelocity();
        this.eps = calcEps();
        this.p = calcP();
        this.phi += tStepSize * (2 * velocity * tPlanet) / (r * r);
        this.r = calcR();
        return new PointSystem(r, phi - phi0, xDiff, yDiff);
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

    void setStepSize(double sizeStep) {
        tStepSize = sizeStep;
    }

    PointSystem getCoordinates() {

        return new PointSystem(r, phi - phi0, xDiff, yDiff);
    }

    void createPathTransition() {
        List<PathElement> elements = new ArrayList<>();

        elements.add(new MoveTo(this.firstPoint.x, this.firstPoint.y));
        this.pathCoordinates.add(this.firstPoint);

        for (double i = 0; i < this.tSteps; i += this.tStepSize) {
            PointSystem newCoordinates = coordinatesCalculation();
            if (i == this.tStepSize) {
                this.planetRepresentation.setTranslateX(newCoordinates.x);
                this.planetRepresentation.setTranslateY(newCoordinates.y);

            }
            elements.add(new LineTo(newCoordinates.x, newCoordinates.y));
            this.pathCoordinates.add(newCoordinates);

        };

        this.planetPath = new Path(elements);
        PathTransition pathTransition = new PathTransition(Duration.seconds(this.tPlanet / this.timelapseSpeed), this.planetPath, this.planetRepresentation);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(false);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        this.planetPathTransition = pathTransition;

    }

    void createRotateTransition() {
        RotateTransition rotate = new RotateTransition(Duration.seconds(2 * this.tSpinTime / this.timelapseSpeed), this.planetRepresentation);
        rotate.setFromAngle(0);
        rotate.setToAngle(720);
        rotate.setCycleCount(Timeline.INDEFINITE);
        rotate.setAutoReverse(false);
        rotate.setInterpolator(Interpolator.LINEAR);
//        System.out.println("tPlanet:"+ this.tPlanet+ "Duration:" + rotate.getDuration().toSeconds());
//        System.out.println(this.tSpinTime);
        this.planetRotateTransition = rotate;
    }

    void createParallelTransition(PathTransition planetPathTransition, RotateTransition planetRotateTransition) {
        this.planetTransition = new ParallelTransition(this.planetRepresentation, planetPathTransition, planetRotateTransition);
        this.planetTransition.setCycleCount(Timeline.INDEFINITE);

        this.planetTransition.setAutoReverse(false);

    }

    void changeSpeed(double timelapseSpeed) {
        double timePath = this.planetPathTransition.getCurrentTime().toSeconds();
        double durationPath = this.planetPathTransition.getCycleDuration().toSeconds();
//        
//        double timeRotate = this.planetRotateTransition.getCurrentTime().toSeconds();
//        double durationRotate = this.planetRotateTransition.getCycleDuration().toSeconds();

        double timeParallel = this.planetTransition.getCurrentTime().toSeconds();
        double durationParallel = this.planetTransition.getCycleDuration().toSeconds();

//        System.out.println(timeRotate);
//        System.out.println(durationRotate);
//        this.planetPathTransition.setDuration(Duration.seconds((this.tPlanet/timelapseSpeed)));
//        this.planetRotateTransition.setDuration(Duration.seconds((this.tSpinTime/timelapseSpeed)));
        this.planetTransition.stop();

        this.planetRotateTransition.setDuration(Duration.seconds(this.tSpinTime / timelapseSpeed));
        this.planetPathTransition.setDuration(Duration.seconds(this.tPlanet / timelapseSpeed));
        createParallelTransition(this.planetPathTransition, this.planetRotateTransition);

        this.planetTransition.jumpTo(Duration.seconds(timePath * ((this.tPlanet / timelapseSpeed) / durationPath)));

        this.planetTransition.play();

    }
}
