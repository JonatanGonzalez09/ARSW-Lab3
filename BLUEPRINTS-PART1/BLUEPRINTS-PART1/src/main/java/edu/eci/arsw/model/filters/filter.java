/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.model.filters;

import edu.eci.arsw.blueprints.model.*;
import java.util.Set;

/**
 *
 * @author david.caycedo
 */
public interface filter {
    
    
    public Set<Blueprint> filtrate(Set<Blueprint> blueprints);
}
