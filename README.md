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
  
  Se realizarón las pruebas correspondientes para cada GrammarChecker:
  
  - **Español:**
  
  ![](https://github.com/JonatanGonzalez09/ARSW-Lab3/blob/master/GRAMMAR-CHECKER/GRAMMAR-CHECKER/src/Imagenes/Parte1(Espa%C3%B1ol).jpg)
  
  - **Ingles:**
  
  ![](https://github.com/JonatanGonzalez09/ARSW-Lab3/blob/master/GRAMMAR-CHECKER/GRAMMAR-CHECKER/src/Imagenes/Parte1(Ingles).jpg)
