package edu.eci.arsw.blueprints;

import java.util.HashSet;
import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

public class Main {

	static BlueprintsServices s;

	public static void main(String[] args) throws BlueprintPersistenceException {
		InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
		Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
		Point[] pts1=new Point[]{new Point(80, 80),new Point(30, 30)};
		Blueprint bp0=new Blueprint("Juan", "My-paint",pts0);
		Blueprint bp1=new Blueprint("Luis", "The-paint",pts1);
		ibpp.saveBlueprint(bp0);
		ibpp.saveBlueprint(bp1);

		Set<Blueprint> zed = new HashSet<>();
		zed.add(bp0);
		zed.add(bp1);
		
		System.out.println(zed);
		System.out.println("Puntos Juan: ("+pts0[0].getX()+","+pts0[0].getY()+")");
		System.out.println("Puntos Luis: ("+pts1[0].getX()+","+pts1[0].getY()+")");
	}

}