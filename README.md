# ARSW-Lab3 Introduction to Spring and Configuration using annotations #

## Realizado por: Jonatan Esteban Gonzalez Rodriguez y David Eduardo Caycedo

Para compilar utilice el comando``` mvn package```

Para ejecutar las pruebas ``` mvn test```

Para ejecutar el main de blueprints ``` mvn exec:java -Dexec.mainClass="edu.eci.arsw.blueprints.Main"```

## Part I - Basic workshop ##
To illustrate the use of the Spring framework, and the development environment for its use through Maven (and NetBeans), the configuration of a text analysis application will be made, which makes use of a grammar verifier that requires a spelling checker. The grammar checker will be injected, at the time of execution, with the spelling checker required (for now, there are two available: English and Spanish).
  1. Open the project sources in NetBeans
  2. Review the Spring configuration file already included in the project (src / main / resources). It indicates that Spring will automatically search for the 'Beans' available in the indicated package.
  3. Making use of the Spring configuration based on annotations mark with the annotations @Autowired and @Service the dependencies that must be injected, and the 'beans' candidates to be injected -respectively-:
  - GrammarChecker will be a bean, which depends on something like 'SpellChecker'.
  - EnglishSpellChecker and SpanishSpellChecker are the two possible candidates to be injected. One must be selected, or another, but NOT both (there would be dependency resolution conflict). For now, have EnglishSpellChecker used. 
  
    ``` @Autowired : Sustituye la declaración de los atributos del bean en el XML.  ```
  
    ``` @Service : Se usa para indicar que la clase sera un servicio. ```
  
  4. Make a test program, where an instance of GrammarChecker is created by Spring, and use it:
  
  Se ejecuto el programa con el comando ```mvn exec:java -Dexec.mainClass=edu.eci.arsw.springdemo.ui.Main``` y este fue el resultado del **EnglishSpellChecker**
  
  - **Ingles:**
  
  ![](https://github.com/JonatanGonzalez09/ARSW-Lab3/blob/master/GRAMMAR-CHECKER/GRAMMAR-CHECKER/src/Imagenes/Parte1(Ingles).jpg)
  
## Parte II ##
1. Modify the configuration with annotations so that the Bean 'GrammarChecker' now makes use of the SpanishSpellChecker class (so that GrammarChecker is injected with EnglishSpellChecker instead of SpanishSpellChecker.) Verify the new result.

Se modifico con la _anotación_ **@Qualifier(_Se pone el servicio que se desea ejecutar; en este caso se puso SpanishSpellChecker_)** poniendosela a la clase **GrammarChecker** y en cada clase SpanishSpellChecker o EnglishSpellChecker se puso la _anotación_ **@Service(_El nombre segun corresponda la clase_)** ; este fue el resultado de ejecutar el comando ```mvn exec:java -Dexec.mainClass=edu.eci.arsw.springdemo.ui.Main```:

  - **Español:**
  
  ![](https://github.com/JonatanGonzalez09/ARSW-Lab3/blob/master/GRAMMAR-CHECKER/GRAMMAR-CHECKER/src/Imagenes/Parte1(Espa%C3%B1ol).jpg)

# Blueprint Management 1  #
## Part I ##
1. Add the dependencies of Spring. Add the Spring settings. Configure the application - by means of annotations - so that the persistence scheme is injected when the _BlueprintServices_ bean is created. Complete the _getBluePrint()_ and _getBlueprintsByAuthor()_ operations. Implement everything required from the lower layers (for now, the available persistence scheme _InMemoryBlueprintPersistence_) by adding the corresponding tests in _InMemoryPersistenceTest_.

Metodos implementados
```
    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) {
        Set<Blueprint> lista = new HashSet<>();
        blueprints.forEach((key, value) -> {
            if (key.o1.equals(author)){
                lista.add(value);
            }
        });
        return lista;
    }
```
Pruebas realizadas
```
@Test
    public void getBluePrintTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
                
                  
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);               
        
        Blueprint bp1 = ibpp.getBlueprint("mack", "mypaint");     
        
        
        assertEquals("Cargo un blueprint distinto.",bp0,bp1);
        
    }
```
```
    @Test
    public void getBluePrintByAuthorTest() throws BlueprintPersistenceException, BlueprintNotFoundException{

        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "pantera",pts);
        
        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);
        
        try {
            ibpp.saveBlueprint(bp);
            ibpp.saveBlueprint(bp0);
            ibpp.saveBlueprint(bp2);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
      

        try{
            ibpp.saveBlueprint(bp);
            ibpp.saveBlueprint(bp0);
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
        Set<Blueprint> zed = new HashSet<>();
        zed.add(bp);
        zed.add(bp2);
        
        assertEquals("Cargo un blueprint distinto.",zed,ibpp.getBlueprintsByAuthor("john"));

        
    }
```    

2. Make a program in which you create (through Spring) an instance of _BlueprintServices_, and rectify its functionality: register plans, consult plans, register specific plans, etc.
```   

public class Main {
	
        

	
        public static void main(String a[]) throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
                
        
        Point[] pts=new Point[]{new Point(10, 10),new Point(10, 10),new Point(10, 10)};
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

            }
        
}
```   

3. You want the plan query operations to perform a filtering process, before returning the planes consulted. These filters are looking to reduce the size of the plans, removing redundant data or simply sub-sampling, before returning them. Adjust the application (adding the abstractions and implementations you consider) so that the BlueprintServices class is injected with one of two possible 'filters' (or possible future filters). The use of more than one at a time is not contemplated:
  - (A) Redundancy filtering: deletes consecutive points from the plane that are repeated.
```   
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
```   
  - (B) Subsampling filtering: suppresses 1 out of every 2 points in the plane, interspersed. 
```   
  @Service("Subsampling")
public class Subsampling implements filter{

    Set<Blueprint> lista = new HashSet<>();

    @Override
    public Set<Blueprint> filtrate(Set<Blueprint> blueprints) {
       
        for (Blueprint bp : blueprints) {
            List<Point> lista = new ArrayList<>();
            for (int i=0;i<bp.getPoints().size();i++){
                if (i%2==0){
                    lista.add(bp.getPoints().get(i));
                }
                      
            }    
        bp.setPoints(lista);
        }
        return blueprints;
    
    }  
}
```   
4. Add the corresponding tests to each of these filters, and test its operation in the test program, verifying that only by changing the position of the annotations - without changing anything else - the program returns the filtered planes in the way (A) or in the way (B).

Se añadieron pruebas correspondientes a los filtros para visualizar su funcionamiento en el main.



