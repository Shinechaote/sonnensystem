/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tulpe.sonnensystem;

/**
 *
 * @author praktikant
 */
public class DragClass {

    double startX;
    double startY;
    boolean pressed;
    double originX;
    double originY;

    public DragClass(double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
        this.pressed = false;

    }

    public DragClass(double originX,double originY,double dNM) {
        this.startX = 0;
        this.startY = 0;
        this.pressed = false;
        this.originX = originX;
        this.originY = originY;
    }

}
