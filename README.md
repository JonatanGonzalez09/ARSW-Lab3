# ARSW-Lab3 Introduction to Spring and Configuration using annotations #

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

2. Make a program in which you create (through Spring) an instance of _BlueprintServices_, and rectify its functionality: register plans, consult plans, register specific plans, etc.
3. You want the plan query operations to perform a filtering process, before returning the planes consulted. These filters are looking to reduce the size of the plans, removing redundant data or simply sub-sampling, before returning them. Adjust the application (adding the abstractions and implementations you consider) so that the BlueprintServices class is injected with one of two possible 'filters' (or possible future filters). The use of more than one at a time is not contemplated:
  - (A) Redundancy filtering: deletes consecutive points from the plane that are repeated.
  - (B) Subsampling filtering: suppresses 1 out of every 2 points in the plane, interspersed. 
4. Add the corresponding tests to each of these filters, and test its operation in the test program, verifying that only by changing the position of the annotations - without changing anything else - the program returns the filtered planes in the way (A) or in the way (B).
