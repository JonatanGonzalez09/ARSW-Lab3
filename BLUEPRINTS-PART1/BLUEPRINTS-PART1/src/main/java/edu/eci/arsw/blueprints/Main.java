package edu.eci.arsw.blueprints;

import java.util.HashSet;
import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
        

	
        public static void main(String a[]) throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
                
        
        Point[] pts=new Point[]{new Point(10, 10),new Point(10, 10),new Point(3, 3)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        Point[] pts1=new Point[]{new Point(1, 1),new Point(11, 11)};
        Blueprint bp1=new Blueprint("jack", "thestatue",pts1);
        bps.addNewBlueprint(bp);
        bps.addNewBlueprint(bp0);
        bps.addNewBlueprint(bp1);
        
        System.out.println("Points in the filter blueprint : ");
        for(Blueprint b : bps.getBlueprintsByAuthor("john")){
            System.out.println(b.toString());
            for(Point p : b.getPoints()){
            System.out.println("X : " + p.getX() + "   Y : " + p.getY());
        }
        }
        }
       
        
        
        /*
        System.out.println(bps.getAllBlueprints());
        

  
		InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
		Point[] pts00=new Point[]{new Point(40, 40),new Point(15, 15)};
		Point[] pts11=new Point[]{new Point(80, 80),new Point(30, 30)};
		Blueprint bp00=new Blueprint("Juan", "My-paint",pts0);
		Blueprint bp11=new Blueprint("Luis", "The-paint",pts1);
		ibpp.saveBlueprint(bp00);
		ibpp.saveBlueprint(bp11);

		Set<Blueprint> zed = new HashSet<>();
		zed.add(bp00);
		zed.add(bp11);
		
		//System.out.println(zed);
		System.out.println("Todos los puntos: "+ibpp.getAllBlueprints());
		System.out.println("Puntos Juan: ("+pts00[0].getX()+","+pts00[0].getY()+")");
		System.out.println("Puntos Luis: ("+pts11[0].getX()+","+pts11[0].getY()+")");
		System.out.println("Busqueda por Autor: "+ibpp.getBlueprintsByAuthor("_authorname_"));
                
        }*/
        
}
                
                      
	

