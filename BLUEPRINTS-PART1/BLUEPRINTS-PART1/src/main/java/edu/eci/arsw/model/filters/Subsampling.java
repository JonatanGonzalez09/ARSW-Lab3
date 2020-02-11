/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.model.filters;

import java.util.HashSet;
import java.util.Set;
import edu.eci.arsw.blueprints.model.*;


public class Subsampling implements filter{

    Set<Blueprint> lista = new HashSet<>();

    @Override
    public Set<Blueprint> filtrate(Set<Blueprint> blueprints) {
        int i=0;
        for (Blueprint bp : blueprints) {
            for (Point p : bp.getPoints()){
                if (i%2==0){
                    bp.getPoints().remove(p);
                    i++;
                }
                i++;
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
