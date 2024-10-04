//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        MiHilo hilo1 = new MiHilo("Hilo1");
        MiHilo hilo2 = new MiHilo("Hilo2");
        MiHilo hilo3 = new MiHilo("Hilo3");
        MiHilo hilo4 = new MiHilo("Hilo4");

        hilo1.setPriority(Thread.MAX_PRIORITY);
        hilo2.setPriority(Thread.NORM_PRIORITY);
        hilo3.setPriority(Thread.MIN_PRIORITY);
        hilo4.setPriority(Thread.NORM_PRIORITY);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        /*Observaciones:
        * El hilo con mayor prioridad es más probable que se ejecute el primero pero no es seguro que se ejecute el primero.
        * Si todos los hilos tienen la misma prioridad, tendrán la misma probabilidad de ejecutarse los primeros*/

    }
}