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
public class PointSystem {

    double x;
    double y;
    double r;
    double phi;

    public PointSystem(double r, double phi, double xDiff, double yDiff) {
        this.x = r * Math.cos(phi) * 0.0000179509 + xDiff;
        this.y = r * Math.sin(phi) * 0.0000179509 + yDiff;
        this.r = r;
        this.phi = phi;
    }
}
