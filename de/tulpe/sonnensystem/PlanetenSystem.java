/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tulpe.sonnensystem;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

/**
 *
 * @author praktikant
 */
public class PlanetenSystem {
    List<planet> planets;
    PlanetenSystem(planet earth,planet mars, planet venus, planet jupiter, planet uranus, planet neptun, planet saturn, planet merkur){
        List<planet> planetSystem = new ArrayList();
        planetSystem.add(earth);
        planetSystem.add(mars);
        planetSystem.add(venus);
        planetSystem.add(jupiter);
        uranus.planetPath.setStrokeWidth(15);
        planetSystem.add(uranus);
        neptun.planetPath.setStrokeWidth(10);
        planetSystem.add(neptun);
        saturn.planetPath.setStrokeWidth(5);
        planetSystem.add(saturn);
        planetSystem.add(merkur);
        
        this.planets = planetSystem;
        
    }
    void play(){
        for(int i = 0;i<this.planets.size();i+=1){
            this.planets.get(i).planetTransition.play();
        }
    
    }
    void pause(){
        for(int i = 0;i<this.planets.size();i+=1){
            this.planets.get(i).planetTransition.pause();
        }
    
    }
    void stop(){
        for(int i = 0;i<this.planets.size();i+=1){
            this.planets.get(i).timelapseSpeed=10;
        }
        for(int i = 0;i<this.planets.size();i+=1){
            this.planets.get(i).changeSpeed(10);
        }
        for(int i = 0;i<this.planets.size();i+=1){
            this.planets.get(i).planetTransition.jumpTo(Duration.ZERO);
        }
        for(int i = 0;i<this.planets.size();i+=1){
            this.planets.get(i).planetTransition.stop();
        }
    
    }
    void changeTimeLapse(double speed){
        for(int i = 0;i<this.planets.size();i+=1){
            this.planets.get(i).timelapseSpeed=speed;
        }
        for(int i = 0;i<this.planets.size();i+=1){
            this.planets.get(i).changeSpeed(this.planets.get(i).timelapseSpeed);
        }
    
    
    }
    void newImage(List<Image> planetImages){
        for(int i = 0;i<this.planets.size();i+=1){
            double oldStrokeWidth = this.planets.get(i).planetRepresentation.getStrokeWidth();
            this.planets.get(i).planetRepresentation.setFill(new ImagePattern(planetImages.get(i)));
            this.planets.get(i).planetRepresentation.setStrokeWidth(0);
            this.planets.get(i).planetRepresentation.setRadius(oldStrokeWidth+this.planets.get(i).planetRepresentation.getRadius());
        }
    
    }
    void newPathColor(List<Color> planetPathColors){
        for(int i = 0;i<this.planets.size();i+=1){
            this.planets.get(i).planetPath.setStroke(planetPathColors.get(i));
        }
    }
    void makeVisible(double changeRadius){
        for(int i = 0;i<this.planets.size();i+=1){
            this.planets.get(i).planetRepresentation.setRadius(this.planets.get(i).planetRepresentation.getRadius()*changeRadius);
        }
    }
}
