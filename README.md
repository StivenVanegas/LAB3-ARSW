
  
  
### Escuela Colombiana de Ingeniería
### Arquitecturas de Software – ARSW


## Laboratorio – Programación concurrente, condiciones de carrera y sincronización de hilos - Caso Inmortales

### Descripción
Este laboratorio tiene como fin que el estudiante conozca y aplique conceptos propios de la programación concurrente, además de estrategias que eviten condiciones de carrera.
### Dependencias:

* [Ejercicio Introducción al paralelismo - Hilos - BlackList Search](https://github.com/ARSW-ECI-beta/PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH)
#### Parte I – Antes de terminar la clase.

Control de hilos con wait/notify. Productor/consumidor.

1. Revise el funcionamiento del programa y ejecútelo. Mientras esto ocurren, ejecute jVisualVM y revise el consumo de CPU del proceso correspondiente. A qué se debe este consumo?, cual es la clase responsable?

  - El consumo se debe a que que hay un hilo que se esta ejecutando por el productor y otro hilo que ser esta ejecutando por el consumidor

![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/PUNTO%201%2C%20lab%203%2C%20item%201.png)

2. Haga los ajustes necesarios para que la solución use más eficientemente la CPU, teniendo en cuenta que -por ahora- la producción es lenta y el consumo es rápido. Verifique con JVisualVM que el consumo de CPU se reduzca.

![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/item%202%20lab%203%20arsw.png)

3. Haga que ahora el productor produzca muy rápido, y el consumidor consuma lento. Teniendo en cuenta que el productor conoce un límite de Stock (cuantos elementos debería tener, a lo sumo en la cola), haga que dicho límite se respete. Revise el API de la colección usada como cola para ver cómo garantizar que dicho límite no se supere. Verifique que, al poner un límite pequeño para el 'stock', no haya consumo alto de CPU ni errores.

  - Productor produce mas rapido y el consumidor mas lento.
  
  ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/item%203%20lab%203%20arsw%2C%20parte%201.png)
  
  - Apartir de revisar la clase de productor, se pudo establecer que el limite de stock es aproximadamente 922337203685......
  
  ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/consumo%2C%20item%203.png)
  
  - Se verifico para garantizar que dicho limite no se superara en el API. Se establecio en el limite del pequeño stock un valor de 10.000, garantizando que la CPU, no tuviera       altos consumos de recursos
  
  ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/item%203%2C%20lab%203%2C%20parte%202%2C%20cambio%20valor.png)


#### Parte II. – Antes de terminar la clase.

Teniendo en cuenta los conceptos vistos de condición de carrera y sincronización, haga una nueva versión -más eficiente- del ejercicio anterior (el buscador de listas negras). En la versión actual, cada hilo se encarga de revisar el host en la totalidad del subconjunto de servidores que le corresponde, de manera que en conjunto se están explorando la totalidad de servidores. Teniendo esto en cuenta, haga que:

- La búsqueda distribuida se detenga (deje de buscar en las listas negras restantes) y retorne la respuesta apenas, en su conjunto, los hilos hayan detectado el número de ocurrencias requerido que determina si un host es confiable o no (_BLACK_LIST_ALARM_COUNT_).

  - EJecucion del programa BlackListAlarm, en donde se puede verificar el uso correcto de las ocurrencias requerida, retornandolo en la respuestas en conjunto de numeros.
  
  ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/parte%202%2C%20item%201%2C%20lab%203.png)
  
- Lo anterior, garantizando que no se den condiciones de carrera.

#### Parte II. – Avance para la siguiente clase

Sincronización y Dead-Locks.

![](http://files.explosm.net/comics/Matt/Bummed-forever.png)

1. Revise el programa “highlander-simulator”, dispuesto en el paquete edu.eci.arsw.highlandersim. Este es un juego en el que:

	* Se tienen N jugadores inmortales.
	* Cada jugador conoce a los N-1 jugador restantes.
	* Cada jugador, permanentemente, ataca a algún otro inmortal. El que primero ataca le resta M puntos de vida a su contrincante, y aumenta en esta misma cantidad sus propios puntos de vida.
	* El juego podría nunca tener un único ganador. Lo más probable es que al final sólo queden dos, peleando indefinidamente quitando y sumando puntos de vida.

2. Revise el código e identifique cómo se implemento la funcionalidad antes indicada. Dada la intención del juego, un invariante debería ser que la sumatoria de los puntos de vida de todos los jugadores siempre sea el mismo(claro está, en un instante de tiempo en el que no esté en proceso una operación de incremento/reducción de tiempo). Para este caso, para N jugadores, cual debería ser este valor?.

   - La funcionalidad del juego antes indicada se implento, en la clase inmortal, en donde mediante diferentes metodos como: Fight, Inmortal, entre otros, que hacen que se 	      defina el juego y el numero de inmortales que va a ver.
   
   - En el caso de de que no funciona la operacion de incremento o reducción, para N jugadores, seria el valor de N*100

3. Ejecute la aplicación y verifique cómo funcionan las opción ‘pause and check’. Se cumple el invariante?.

  	- La funcion de "Pause and Check" funciona de tal forma en que cuando se da en el boton de "Pause and Check" nos muestra el numero jugadores establecidos, clasificados             desde cero para arriba, con su vida respectiva de cada uno. Además nos muestra la sumatoria total de vidas.
  
  	![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/PARTE%203%20%2C%20Item%203.png)
  
  	- La invariente no se cumple ya que la suma total de la vida de los jugadores, No es N*100.
  
  	![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/Parte%203%2C%20item%203%2C%20%20segunda%20imagen.png)
	
4. Una primera hipótesis para que se presente la condición de carrera para dicha función (pause and check), es que el programa consulta la lista cuyos valores va a imprimir, a la vez que otros hilos modifican sus valores. Para corregir esto, haga lo que sea necesario para que efectivamente, antes de imprimir los resultados actuales, se pausen todos los demás hilos. Adicionalmente, implemente la opción ‘resume’.

    - Se implementan los metodos de "Pause" y "Resume", en donde en la misma interfaz GUI, se implento adicionalmente un contador       de fehca y hora, para poder verificar la funcion de los botones de pause and resume.
    
    ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/parte%203%2C%20item%204.png)
    
    - Como vemos, en la interfaz de Java GUI, vemos el Pause, la fecha y hora, ahora veremos que si se implento bien las 	         operaciones, al ver que continua el programa y vuelvo a parar dos minutos despues.
    
    ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/parte%203%2C%20item%204%2C%20parte%202.png)
    

5. Verifique nuevamente el funcionamiento (haga clic muchas veces en el botón). Se cumple o no el invariante?.

    - No se cumple la invariante, debido a que el valor de la suma de la vida total, cambia por cada vez que se inicia, y no mantiene constante como lo indica las instrucciones      del juego.
    
       ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/Parte%203%2C%20Item%205%20.png)
       
    - Como podemos ver en las dos imagenes adjuntas, el valor de la suma de la vida total cambia en la corrida del programa. Se puede evidenviar en la fecha y hora de pausa del       juego.
    
       ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/parte%203%2C%20item%205%20segunda%20parte.png)

6. Identifique posibles regiones críticas en lo que respecta a la pelea de los inmortales. Implemente una estrategia de bloqueo que evite las condiciones de carrera. Recuerde que si usted requiere usar dos o más ‘locks’ simultáneamente, puede usar bloques sincronizados anidados:

	```java
	synchronized(locka){
		synchronized(lockb){
			…
		}
	}
	```

    - Se implemento una estrategia de bloqueo que evita las condiciones de carrera, usando bloques sincronizados anidados, como lo podemos ver en la implementación.
    
    	![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/parte%203%20item%206.png)
	
    - En el siguiente metodo tambien podemos evidenviar una parte de la implementación.
    
    	![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/parte%203%2C%20item%206%20segundo.png)
	
	
7. Tras implementar su estrategia, ponga a correr su programa, y ponga atención a si éste se llega a detener. Si es así, use los programas jps y jstack para identificar por qué el programa se detuvo.

  - El programa no se detuvo, duro corriendo 7 minutos y no se detuvo. Por tanto no fue necesario el uso de los programas de JPS y JsTack.
  
    ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/parte%203%2C%20item%207.png)

8. Plantee una estrategia para corregir el problema antes identificado (puede revisar de nuevo las páginas 206 y 207 de _Java Concurrency in Practice_).

  - No aplica la estrategia, ya que no se presenta el problema del punto anterior y por tanto no fue necesario corregir el problema identificado.

9. Una vez corregido el problema, rectifique que el programa siga funcionando de manera consistente cuando se ejecutan 100, 1000 o 10000 inmortales. Si en estos casos grandes se empieza a incumplir de nuevo el invariante, debe analizar lo realizado en el paso 4.

  - Pudimos evidenciar que todos los casos el especificados en este punto, siempre corrió el programa y no se detuvo, cada caso se dejo corriendo dos minutos, con el fin de         poder verificar que si cumplía la condición en los tres casos. 
  
  - Programa corrido con el valor de 100
  
    ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/Parte%203%2C%20item%209%20prueba%20100.png)
   
   - Programa corrido con el valor de 1000
   
    ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/Parte%203%2C%20Item%209%20prueba%201000.png)
    
    - Programa corrido con el valor de 10.000
    
      ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/Parte%203%2C%20Item%209%20prueba%201000.png)
    

10. Un elemento molesto para la simulación es que en cierto punto de la misma hay pocos 'inmortales' vivos realizando peleas fallidas con 'inmortales' ya muertos. Es necesario ir suprimiendo los inmortales muertos de la simulación a medida que van muriendo. Para esto:
	* Analizando el esquema de funcionamiento de la simulación, esto podría crear una condición de carrera? Implemente la funcionalidad, ejecute la simulación y observe qué problema se presenta cuando hay muchos 'inmortales' en la misma. Escriba sus conclusiones al respecto en el archivo RESPUESTAS.txt.
	* Corrija el problema anterior __SIN hacer uso de sincronización__, pues volver secuencial el acceso a la lista compartida de inmortales haría extremadamente lenta la simulación.
	
	- Se implemento la funcionalidad en la siguiente imagen adjunta y además las conclusiones están agregadas en el documento del lab 3, "Respuestas.txt"
	
	  ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/parte%203%2C%20item%2010.png)

11. Para finalizar, implemente la opción STOP.

  - Para finalizar podemos ver el método de implementación de Stop, en donde se creó la función del botón de stop en el mismo juego, para que así detuviera el programa.
  
     ![](https://github.com/StivenVanegas/LAB3-ARSW/blob/master/Images/Parte%203%2C%20item%2011.png)


<a rel="license" href="http://creativecommons.org/licenses/by-nc/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc/4.0/88x31.png" /></a><br />Este contenido hace parte del curso Arquitecturas de Software del programa de Ingeniería de Sistemas de la Escuela Colombiana de Ingeniería, y está licenciado como <a rel="license" href="http://creativecommons.org/licenses/by-nc/4.0/">Creative Commons Attribution-NonCommercial 4.0 International License</a>.
