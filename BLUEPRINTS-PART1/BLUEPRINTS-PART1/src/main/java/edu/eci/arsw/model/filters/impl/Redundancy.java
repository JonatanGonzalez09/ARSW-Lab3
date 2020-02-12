/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.model.filters.impl;

import java.util.Set;
import edu.eci.arsw.blueprints.model.*;
import edu.eci.arsw.model.filters.filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Service;


/**
 *
 * @author david.caycedo
 */
@Service("Redundancy")
public class Redundancy implements filter {

    @Override
    public Set<Blueprint> filtrate(Set<Blueprint> blueprints) {
        
        
        for (Blueprint bp : blueprints) {
            List<String> pointsIn = new ArrayList<>();

            List<Point> points = bp.getPoints();
            Point[] ptsNuevos = new Point[points.size()];
            int i = 0 ;
            for ( Point p : points){
            if (!(pointsIn.contains(p.getX()+" "+p.getY()))){
                pointsIn.add(p.getX()+ " "+p.getY());
                ptsNuevos[i] = new Point(p.getX(),p.getY());
                i++;
            } 
        }
        
        Point[] ptsNuevos2 = new Point[i];
        for (int j = 0 ; j < i ; j++){
            ptsNuevos2[j] = ptsNuevos[j];
        }
               
        bp.setPoints(Arrays.asList(ptsNuevos2));
        }
        return blueprints;
    }
}
    

