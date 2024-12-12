package org.video;

import gestion.MenuUsuario;

public class Programa {
    private static Object monitorNotificaciones=new Object();
    private static Integer visitasVideoA=0;//visitas del video A
    private static Integer visitasVideoB=0;//Visitas del video B

    public static void run() {
        //Inicializamos el programa
        Boolean salir=false;
        TrampaRunnable trampaRunnable=new TrampaRunnable();
        Thread h2=new Thread(trampaRunnable);//Creamos el hilo
        h2.start();//lanzamos el video al principio del programa.

        //Creamos el menu principal del programa
        while(!salir) {
            int eleccion= menuPrincipal();//Variable para almacenar la opcion elegida por el usuario
            switch (eleccion) {//En funcion de la opcion elegida por el usuario
                case 1->incrementarVisitasA();
                case 2->incrementarVisitasB();
                case 3->hacerTrampas(trampaRunnable);
                case 4-> mostrarVisitas();
                case 5-> {
                    salir = true;
                    terminarHilo(h2);
                }
                }
            }
        } //TODO crear constantes para las opciones del switch

    private static void terminarHilo(Thread hilo) {
        if (hilo.isAlive()) {//Si el hilo sigue con vida
            hilo.interrupt();//Se interrumpe.
        }

    }


    private static void mostrarVisitas() {
        System.out.println("************** Visitas **************");
        System.out.println("video A: "+visitasVideoA);
        System.out.println("video B: "+visitasVideoB);
    }

    private static void hacerTrampas(TrampaRunnable trampaRunnable) {
        if (trampaRunnable.isDormido()) {//Si el hilo esta dormido
            synchronized (monitorNotificaciones) {
                monitorNotificaciones.notify();//Se depierta el hilo. Usamos el monitor.
            }
        }else{//En caso contrario, no se hace nada, y se avia al usuario
            System.out.println("El hilo no ha terminado. Por favor, espere.");
        }

    }

    private static void incrementarVisitasB() {
        visitasVideoB++; //Por el momento no hay una seccion critica
    }

    /**
     * Incrementa las visitas en uno del video A.
     * @implNote  Metodo sincronizado
     */
    public static void incrementarVisitasA() {
       incrementarVisitasA(1);
    }

    /**
     * Incrementa las visitas en uno del video A.
     * @param  numero indica cuantas veces se incrementan las visitas
     * @implNote  Metodo sincronizado
     */
    public static void incrementarVisitasA(int numero) {
        //Se protege la seccion critica
        synchronized (visitasVideoA){
            visitasVideoA+=numero;
        }
    }

    private static int menuPrincipal() {
        //Creamos el menu principal del programa
        MenuUsuario menuPrincipal=new MenuUsuario("¿Que desea realizar?");
        int eleccion;//Variable para almacenar la opcion elegida por el usuario
        menuPrincipal.addOpcion("Ver video A");
        menuPrincipal.addOpcion("Ver video B");
        menuPrincipal.addOpcion("Hacer trampas con el video A");
        menuPrincipal.addOpcion("Mostrar visitas");
        menuPrincipal.addOpcion("Salir");//No se pide, pero lo hemos puesto
        eleccion=menuPrincipal.elegirOpcion();
        return eleccion;
    }

    public static void setMonitorNotificaciones(Object monitorNotificaciones) {
        Programa.monitorNotificaciones = monitorNotificaciones;
    }

    public static Object getMonitorNotificaciones() {
        return monitorNotificaciones;
    }
}
