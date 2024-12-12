package gestion;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase utilizada para crear menus de los usuarios
 *
 * @author Raul SG
 *
 */
public class MenuUsuario {

    //ATRIBUTOS
    private final String pregunta;
    private final String[] opciones;//TODO seria mejor hacerlo con un List, pero no lo utilizo porque
    //no lo hemos dado aun. Con List, seria mas simple.

    private int i; //Indice por donde vamos añadiendo las opciones en el array de opciones. Inicialmente vale 0.
    public static final  int TAM_MAX=20;//Hemos considerado que el numero maximo de opciones en un menu va a ser 20
    //Usando List, no existiria un limite.


    //METODOS

    //Constructor
    /**
     * Constructor de la clase MenuUsuario. Es utilizado para crear un objeto menu de usuario
     * indicandole la pregunta principal del menu
     *
     * @param pregunta principal del menu
     */
    public MenuUsuario(String pregunta) {
        this.pregunta = pregunta;
        this.opciones=new String[TAM_MAX];
        this.i=0;
    }

    /**
     * Metodo utilizado para añadir las posibles opciones que se van a presentar en el menu
     *
     * @param opcionNueva que se quiere agregar a las opciones ya existentes
     * @return devuelve true si se ha podiso agregar, devuelve false, si se ha llegado al
     * limite de opciones posibles, y por tanto no se pueden agregar mas opciones
     */
    public  boolean addOpcion(String opcionNueva) {
        boolean agregado=false;
        if(i<TAM_MAX) {//Si no se ha llegado al limite
            opciones[i]=opcionNueva;//guardamos la nueva opcion
            i++;//Incrementamos el indice, para que apunte al siguiente hueco de la array.
            agregado=true; //Se indica que se ha añadido la nueva opcion
        }
        return agregado;//Devolvenos si hemos agregado la opcion o no
    }

    /**
     * Metodo utilizado para mostrar el menu
     */
    public String toString() {
        String resultado="\n";
        resultado+=pregunta;//Mostramos la pregunta
        for(int i=0;i<this.i;i++) {//Mostramos todas las opciones, numeradas
            resultado+="\n\t"+(i+1)+".- "+opciones[i]; //TODO usar StringBuilder
        }
        return resultado;
    }

    /**
     * Metodo utilizado para leer la opcion elegida por el usuario desde teclado.
     * Se controla que la eleccion este dentro del numero de opciones disponibles, en caso contrario se le avisara al usuario y
     * tendra que introducir un numero de nuevo.
     * Muestra la pregfyntra y las opciones disponibles.
     * @return devuelve la eleccion seleccionada por el usuario, la cual siempre estara entre 1 , y el numero de opciones disponibles
     */
    public int elegirOpcion() {
        System.out.println(toString());//Mostramos el enunciado
        int inicio=1;//Las opciones posibles estan entre 1
        int fin=i;//Y el numero de opciones añadidas
        int eleccion=0;
        boolean textoIntroducido=false;//Bandera que indica si se ha introducido un texto
        Scanner sc=new Scanner(System.in);
        try {
            eleccion=sc.nextInt();//Leemos un entero por teclado
        }
        catch (InputMismatchException e) {//Excepcion que
            //se produce al introducir texto
            textoIntroducido=true;
        }
        while(textoIntroducido || eleccion<inicio || eleccion>fin) {//Si elegimos un valor fuera del rango posible
            System.out.println("Eleccion incorrecta. Se debe seleccionar un numero entre "+inicio+" y "+fin);
            textoIntroducido=false;//
            try {
                sc.nextLine();//Con esta accion limpiamos el buffer, quitando el dato introducido previamente.
                eleccion=sc.nextInt();//Volvemos a leer un valor por teclado
            }
            catch (InputMismatchException e) {//Excepcion que
                //se produce al introducir texto
                textoIntroducido=true;
            }
        }
        return eleccion;
    }

    /**
     * Metodo utilizado para elegir un int por teclado. Controla
     * que no se introduzca texto, en tal caso vuelve a solicitar un numero
     * al usuario.
     *
     * @param pregunta . Texto que se le muestra al usuario antes de
     * darle la opcion de teclear un entero.
     * @return eleccion. Devuelve el numero tecleado por el usuario
     */
    public static int elegirInt(String pregunta) {
        System.out.println(pregunta);//Se muestra el texto pasado como parametro
        boolean textoIntroducido=true;
        int eleccion=0;
        Scanner sc=new Scanner(System.in);

        while(textoIntroducido) {//Miuentras se introduzca texto, en lugar de un numero
            try {
                eleccion=sc.nextInt();//Volvemos a leer un valor por teclado
                textoIntroducido=false;//Si todo sale bien, la variable se pone a false, lo que detendra el bucle
            }
            catch (InputMismatchException e) {//Excepcion que se
                sc.nextLine();//Con esta accion limpiamos el buffer, quitando el dato introducido previamente.
                //producce al introducir texto por teclado
                System.out.println("Eleccion incorrecta. Se debe seleccionar un numero");

            }
        }
        return eleccion;
    }

    /**
     * Metodo utilizado para mostrar un texto, y
     * obetener una cadena de texto introducida por teclado
     * @param pregunta . Texto que se le muestra al usuario por pantalla
     * @return texto tecleado por el usuario.
     */
    public static String elegirString(String pregunta) {
        System.out.println(pregunta);
        Scanner sc=new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Metodo utilizado para elegir un double por teclado. Controla
     * que no se introduzca texto, en tal caso vuelve a solicitar un numero
     * al usuario.
     *
     * @param pregunta . Texto que se le muestra al usuario antes de
     * darle la opcion de teclear un entero.
     * @return eleccion . Devuelve el numero tecleado por el usuario
     */
    public static double elegirDouble(String pregunta) {
        System.out.println(pregunta);//Se muestra el texto pasado como parametro
        boolean textoIntroducido=true; //TODO cunir en un unico metodo pedir double e int, ya que comparten codigo.
        double eleccion=0;
        Scanner sc=new Scanner(System.in);

        while(textoIntroducido) {//Miuentras se introduzca texto, en lugar de un numero
            try {
                eleccion=sc.nextDouble();//Volvemos a leer un valor por teclado
                textoIntroducido=false;//Si todo sale bien, la variable se pone a false, lo que detendra el bucle
            }
            catch (InputMismatchException e) {//Excepcion que se
                sc.nextLine();//Con esta accion limpiamos el buffer, quitando el dato introducido previamente.
                //producce al introducir texto por teclado
                System.out.println("Eleccion incorrecta. Se debe seleccionar un numero");

            }
        }
        return eleccion;
    }
}