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
            List<Point> lista = new ArrayList<>();
            for (Point p : bp.getPoints()){
                if (!lista.contains(p)){
                    lista.add(p);
                }
                
            }    
        bp.setPoints(lista);
        }
        return blueprints;
    }
    
}
