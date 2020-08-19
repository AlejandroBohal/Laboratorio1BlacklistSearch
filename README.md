# Black List Search

Esta libreria está implementada en **JAVA** como laboratorio de **ARSW**

Su funcionamiento se centra en una busqueda de un servidor en una lista negra,tiene por objetivo explicar los fundamentos de los hilos.


## Part I - Introduction to threads in JAVA

1. In agreement with the lectures, complete the classes CountThread, so that they define the life cycle of a thread that prints the numbers between A and B on the screen.

2. Complete the main method of the CountMainThreads class so that: 
  - Create 3 threads of type CountThread, assigning the first interval [0..99], the second [99..199], and the third [200..299]. 
  - Start the three threads with start(). Run and check the output on the screen. 
  El funcionamiento se puede ver a partir de ejecutar el comando
  ```mvn package```
  Segido de la siguiente linea para correr la ejecución:
  ``` java -cp target\PiDigits-1.0-SNAPSHOT.jar edu.eci.arsw.threads.CountThreadsMain ```
  
  **Run execution:**
  
  <img width="232" alt="run_execution" src="https://user-images.githubusercontent.com/49318314/90572880-b3fa3380-e17a-11ea-8f74-e0f87cf12a12.png">
  
  **start execution:**
  
  <img width="172" alt="start_execution" src="https://user-images.githubusercontent.com/49318314/90572882-b492ca00-e17a-11ea-8442-3829aa672722.png">

  - Change the beginning with start() to run(). How does the output change? Why?
  
  El cambio en la salida puede notarse a partir del orden en el que los números salen en la ejecución.
  Esto se debe ya que al llamar el método ```run()``` hacemos lo mismo que llamar cualquier método en una clase, por tanto, hasta que esta no termina no llama a la otra. 
  En cambio, la ejecución del método ```start()``` inicializa el Thread, por lo cual, este ejecuta su procesamiento al mismo tiempo que se ejecutan los otros hilos.
  
  
#Part II - Black List Search Exercise 

For an automatic computer security surveillance software, a component is being developed to validate the IP addresses in several thousands of known malicious blacklists (of malicious hosts), and to report those that exist in at least five of said lists.


## Part III - Discussion
The strategy of parallelism previously implemented is inefficient in certain cases, since the search is still carried out even when the N threads (as a whole) have already found the minimum number of occurrences required to report to the server as malicious. How could the implementation be modified to minimize the number of queries in these cases? What new element would this bring to the problem?

- Anteriormente se hizo una implementación que ignoraba un mínimo de casos por los cuales el *servidor* podría considerarse como malicioso, por este motivo. a pesar de haber encontrado el servidor en más de 20 casos, este seguía buscando en todos lo servidores, haciéndolo totalmente ineficiente. 

- Como propuesta a solucionar este problema, se propone el uso de un ```Atomic Integer``` que permite asegurar que para todos los ```N``` *Threads* se evite una búsqueda innecesariamente larga, de este modo se permite que todos los *Threads* sean consientes de un máximo de casos a evaluar para considear malicioso el servidor.


## Part IV - Performance Evaluation 
 
 Podemos ejecutar esta función a partir los siguientes comandos:
 
 Primero, usamos ```mvn package```, posteriormente podemos usar
 
```java -cp target\PiDigits-1.0-SNAPSHOT.jar edu.eci.arsw.blacklistvalidator.Main```

### A Single Thread
Con un solo thread obtenemos la siguiente información:

Resultados:
![result1Thread](https://user-images.githubusercontent.com/49318314/90576194-15be9b80-e183-11ea-91ab-d00551083be4.png)

Como podemos ver, hace una consulta a más de 70.000 servidores:
y tiene por consumo en el servidor un aproxumado al 20% - 25%.

![jvm1Thread](https://user-images.githubusercontent.com/49318314/90576190-15260500-e183-11ea-9296-6fc9f98e28e5.png)

###As many threads as processing cores (have the program determine this using the Runtime API). 

Para el uso de la cantidad de procesadores, tenemos el método ```getProcessors()```:

![4ProcessorsResult](https://user-images.githubusercontent.com/49318314/90576183-148d6e80-e183-11ea-965a-7176ad21ede5.png)

y como resultado en la consulta podemos encontrar la siguiente información
![4ProcessorsJMV](https://user-images.githubusercontent.com/49318314/90576182-13f4d800-e183-11ea-80ae-d758800a96fb.png)

###As many threads as twice the number of processing cores. 
AL momento de duplicar la cantidad de hilos por procesador obtenemos la siguiente información.

![4ProcessorsX2TJMV](https://user-images.githubusercontent.com/49318314/90576184-148d6e80-e183-11ea-8afb-cc6ed801b4d5.png)

###50 threads 
Al hacer la consulta con una cantidad de 50 threads, podemos ver que hace la búsqueda a una cantidad de 49.000 servidores.

![result2Thread](https://user-images.githubusercontent.com/49318314/90576195-16573200-e183-11ea-89dd-30f8c15f314b.png)

![jvm2Thread](https://user-images.githubusercontent.com/49318314/90576191-15260500-e183-11ea-9928-86a778fb4880.png)

###100 threads

Al usar 100 threads, la cantidad de consultas vuelve a subir a 60.000 servidores.

![result3Thread](https://user-images.githubusercontent.com/49318314/90576180-135c4180-e183-11ea-9dda-26c31745ea11.png)

![jvm3Thread](https://user-images.githubusercontent.com/49318314/90576193-15be9b80-e183-11ea-9916-ea40bb5a5041.png)

###Conclusión

Como conclusión podemos ver que un aumento en la cantidad de hilos que procesan la información (Sin saturar el procesador).

<img width="195" alt="data" src="https://user-images.githubusercontent.com/49318314/90576187-148d6e80-e183-11ea-91b0-e27da54621bd.png">

![graphic](https://user-images.githubusercontent.com/49318314/90576189-15260500-e183-11ea-9956-6ace78205149.png)

Ahora bien, para 200 o 500 el rendimiento para este programa sigue aumentando aunque se plantea que no, como ejemplo podemos ver las siguientes ejecuciones con 200 Threads, 500 threads y 700 threads, en donde el rendimiento sigue aumentando en tiempo de respuesta:

**200 Threads**

<img width="466" alt="200T" src="https://user-images.githubusercontent.com/49318314/90580891-92f00d80-e18f-11ea-968e-b5db6794f1d6.png">

**500 Threads**

<img width="468" alt="500T" src="https://user-images.githubusercontent.com/49318314/90580892-9388a400-e18f-11ea-9924-b6548dd5664a.png">

A partir de este caso el rendimiento se empieza a ver comprometido con la cantidad de hilos.
**1000 Threads**
<img width="460" alt="1000T" src="https://user-images.githubusercontent.com/49318314/90580894-9388a400-e18f-11ea-926a-224abf51e3f0.png">
**2000 Threads**
<img width="462" alt="2000T" src="https://user-images.githubusercontent.com/49318314/90580895-9388a400-e18f-11ea-8a02-43bb1758a9db.png">
**10.000 Threads**
<img width="470" alt="10000T" src="https://user-images.githubusercontent.com/49318314/90580896-94213a80-e18f-11ea-9254-de81d81cfc99.png">
**40.000 Threads**
<img width="455" alt="40000T" src="https://user-images.githubusercontent.com/49318314/90580897-94213a80-e18f-11ea-84a2-77616c152368.png">


Finalmente poseemos esta tabla, donde presentamos los valores y como el rendimiento empieza a perder tiempo de respuesta a partir de la cantidad de hilos.



<img width="169" alt="data2" src="https://user-images.githubusercontent.com/49318314/90580887-92577700-e18f-11ea-8769-c607785c96a7.png">



<img width="367" alt="Graphic2" src="https://user-images.githubusercontent.com/49318314/90580889-92f00d80-e18f-11ea-9265-554e00dc8c0f.png">



Es posible asumir que este limite de procesamiento se vea influenciado a partir de los componentes de hardware.
- También, pueden presentarse retrasos a partir de la comunicación que posee. 

### Prerequisitos.

Es necesario/recomendable que posea las siguientes herramientas:

- git instalado en su computador.
- Maven configurado en sus **Variables de Entorno**.
- Interprete de lenguaje de programacion **JAVA** (Eclipse, netbeans, Intellij, etc.)

si necesita instalar algunos de los servicios mencionados puede encontrarlos aquí:

- **Git** puede descargarlo [aqui.](https://git-scm.com/downloads)

- **Maven** puede descargarlo [aqui.](https://maven.apache.org/download.cgi)

- **IntelliJ** puede descargarlo [aqui.](https://www.jetbrains.com/es-es/idea/download/)



## Built With

* [IntelliJ](https://www.jetbrains.com/es-es/idea/) - The develop enviroment
* [Maven](https://maven.apache.org/) - Dependency Management
* [JUnit](https://junit.org/junit5/) - Used to generate Unitary Test


## Authors

* **Juan Carlos García** - *Finalización Laboratorio* - [IJuanchoG](https://github.com/IJuanchoG)

* **Alejandro Bohorquez ** - *Finalización Laboratorio* - [AlejandroBohal](https://github.com/AlejandroBohal)

## License

Este proyecto es de libre uso y distribución, para más detalles vea el archivo [LICENSE.md](LICENSE.md).
