package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.services.BlueprintsServices;

public class Main {

    static BlueprintsServices s;
    
	public static void main(String[] args) {
		s = new BlueprintsServices();
		System.out.println(s.getAllBlueprints());
		
	}

}