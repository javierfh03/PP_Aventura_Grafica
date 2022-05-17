# PP_Aventura_Gráfica
Este proyecto es un trabajo de final de curso de la asignatura de programación del ciclo de grado superior DUAL de DAM-DAW.

## Requisitos
El proyecto consiste en crear un videojuego de aventura gráfica en la que se tiene que salir de un laberinto.
La forma de salir del laberinto y jugabilidad del juego es elección del el alumno, sin embargo se deben seguir una
serie de pautas:
- El juego debe estar programado en java.
- El juego debe ser un laberinto en el que se tiene que buscar una salida.
- El programa debe estar creado con swing.
- Se deben añadir acciones como ir, coger, poner...
- Los mapas se deben cargar de un fichero de texto.
- Al iniciar el juego el usuario debe elegir el mapa que quiera iniciar.
- Se deben crear dos mapas con al menos 15 salas.

## Mi proyecto
Diseñé un juego en el que el usuario debe salir de un laberinto en el cual hay puertas bloqueadas y estas son desbloqueadas
recogiendo llaves o resolviendo preguntas de unos ordenadores los cuales están repartidos por el mapa.

### Iniciar el juego
Al iniciar el juego podemos elegir cargar un mapa por defecto el cuál se encuentra dentro del proyecto.
También tiene la opción de cargar un mapa creado por el usuario.

### Estructura del juego
El laberinto está formado por salas en las que hay puertas que llevan a otras salas, las puertas tienen dos estados:
- L: La puerta no está bloqueada.
- B: La puerta está bloqueada.

Para identificar donde se encuentra alguna de la sala que está alrededor de una sala se usan puntos cardinales:
- N: Representa el norte.
- S: Representa el sur.
- E: Representa el este.
- O: Representa el oeste.

Esto mismo pasa con la posición del usuario en una sala, se reperenta con un punto cardinal.

También en perspectiva en la que el usuario ve la sala se usa un punto cardinal.

En las salas puede haber objetos, estos desbloquean puertas, pueden ser:
- M: Un ordenador que cuenta con una pregunta que debe ser resuelta.
- B: Una llave azul.
- Y: Una llave amarilla.
- R: Una llave roja.
- V: Una llave verde.

### Acciones
El usuario puede realizar las siguientes acciones para poder salir del laberinto:
- Movimientos: Sirven para moverse por las salas del laberinto.
- Coger: Sirve para recoger llaves en caso de que encontremos alguna.
- Usar: Sirve para poder utilizar los ordenadores que hay repartidos por el laberinto.
- Ayuda: Da una pequeña descripción de la sala.
- Salir: Devuelve al usuario al menú.

### Crear mapas
Siguiendo los requisitos, los mapas creados por el usuario deben ser cargados de un archivo de texto. Para crear un mapa se
debe seguir una sintaxis.

Los archivos usados para cargar el mapa se dividen en 5 fases con las que se cargarán el mapa las cuales son:
1. Establecer las salas que habrá en el juego, pondremos los siguientes parámetros:
    - Nombre de la sala.
    - Puerta/as junto a su estado.

    Cosas a tener en cuenta:
    - La primera sala que se establezca será la sala de inicio.
    - La última sala que se establezca será la sala del final.
    - Al comenzar el juego el usuario se posicionará en la perspectiva norte de la sala de inicio.

    Por ejemplo, se quiere crear una sala con una salida libre al este y otra al oeste bloqueada:
    ```
    Sala1#ELOB;
    ```

2. Definir las uniones de las salas, de esta manera el programa sabrá que salas hay alrededor de una sala, pondremos los 
siguientes parametros:
    - Nombre de la sala a la que le estableceremos las salas.
    - Ubicación de la salida/as junto a la sala que se quiere establecer en la salida, si se quieren establecer varias salidas ponemos - 
    para separarlas. 

    Por ejemplo, se quiere establecer las salas que hay alrededor de la Sala1:
    ```
    Sala1#ESala2-OSala3;
    ```

3. Definir los objetos que hay por el mapa, pondremos los siguientes parámetros:
    - La sala en la que se encuentra el objeto.
    - El tipo de objeto.
    - La sala en la que se encuentra la puerta que desbloaquea el objeto.
    - Posición de la puerta.

    Cosas a tener en cuenta:
    - Las llaves deben estar en salas en las que tengamos solo una puerta atrás de nuestra perspectiva ya que la idea es que las llaves 
    estén apartadas.
    - Esto último no aplica a los ordenadores.

    Por ejemplo, se quiere establecer un ordenador en la Sala2 el cual desbloquea la puerta oeste de la Sala1:
    ```
    Sala2#M#Sala1#O;
    ```
    Otro ejemplo, se quiere establecer una llave azul en la Sala3 la cual desbloquea la salida norte de la Sala2:
    ```
    Sala3#B#Sala2#N;
    ```

4. Establecer las preguntas de los ordenadores, pondremos los siguientes parámetros:
    - Nombre de la sala en la que se encuentra el ordenador.
    - La pregunta.
    - La respuesta.

    Cosas a tener en cuenta:
    - Las preguntas tienen un máximo de 171 carácteres.
    - Al poner una respuesta en un ordenador no distingue entre mayúsculas y minúsculas.

    Por ejemplo, se quiere establecer la pregunta y la respuesta al ordenador de la Sala2.
    ```
    Sala2#¿Cómo se llama el compilador de c++ creado por GNU?#GCC;
    ```

5. Establecer imágenes personalizadas, pondremos los siguientes parámetros:
    - Nombre de la sala.
    - La imagen.
    - La perspectiva en la que se debe visualizar la imagen.

    Cosas a tener en cuenta:
    - La imagen debe estar en la misma ruta que la del mapa que quiere cargar.
    - La sala final no puede tener una imagen personalizada.
    - No es recomendable establecer una imagen personalizada en las salas con objetos o salas con puertas bloqueadas ya que no funciona 
    correctamente y dependiendo de la imagen puede llegar a confundir al usuario.
    - Las medidas recomendadas para el mapa son 534 × 405.
    - En este directorio hay una plantilla SVG para crear tus propias salas: https://raspifj.duckdns.org/Directorio/Proyectos/PP_Aventura_Grafica/cuarto.svg

    Por ejemplo, se quiere establecer una imagen personalizada en la Sala4 en su perspectiva sur:
    ```
    Sala4#personalizada.png#S;
    ```

Por último para separar cada fase hay que poner al finalizar cada una un ```@``` y se pueden poner comentarios de esta 
forma: ```// Comentario```.

Pudes descargar un mapa sencillo en este link: https://raspifj.duckdns.org/Directorio/Proyectos/PP_Aventura_Grafica/demo.zip

## Probar el juego
Puedes descargar el juego en un archivo JAR en el siguiente link: https://raspifj.duckdns.org/Directorio/Proyectos/PP_Aventura_Grafica/PP_3aEv_Aventura_Grafica.jar

El proyecto fué compilado en java 1.8
