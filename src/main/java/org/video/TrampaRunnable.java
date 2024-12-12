package org.video;

public class TrampaRunnable implements Runnable{
    private  boolean dormido;
    public static final int TIEMPO_ENTRE_TRAMPAS=2000;//2 segundos
    public static final int INCREMENTO_TRAMPAS=5;//2 segundos
    public static final int NUMERO_TRAMPAS=3;

    @Override
    public void run() {
        Object monitorNotificaciones=Programa.getMonitorNotificaciones();
        try {
            while(true) {//Se repite siempre , hasta que se interrumpa el hilo
                synchronized (monitorNotificaciones) {//Comienza dormido
                    dormido = true;//Marcamos que lo dormimos
                    monitorNotificaciones.wait();//Se duerme el hilo. TODO habria que ponerlo dentro de un bucle, para evitar despertares espureos.
                    //Se queda a la espera de recibir una notificacion
                }
                dormido = false;
                for (int i = 0; i < NUMERO_TRAMPAS; i++) {
                    Programa.incrementarVisitasA(INCREMENTO_TRAMPAS);//Usamos el metodo de la clase
                    //que ya tiene la seccion critica protegida
                    Thread.sleep(TIEMPO_ENTRE_TRAMPAS);//Dormimos el hilo 2 segundos.

                }
            }
        }catch (InterruptedException e){
            System.out.println("Hilo interrumpido");
        }

    }

    public boolean isDormido() {
        return dormido;
    }

    public void setDormido(boolean dormido) {
        this.dormido = dormido;
    }
}
