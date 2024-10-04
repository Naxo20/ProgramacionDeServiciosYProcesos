public class DownloadThread extends Thread {

    private int milisegundos;
    private static long timeStartMills;
    private static long timeEndMills;
    private static long timeMs;

    public DownloadThread(String nombre, int milisegundos) {
        this.setName(nombre);
        this.milisegundos = milisegundos;
    }

    @Override
    public void run() {
        try {
            timeStartMills = System.currentTimeMillis();
            System.out.println("Comenzando la descarga del archivo: " + Thread.currentThread().getName());
            Thread.sleep(milisegundos);
            System.out.println("Descarga completada del archivo: " + Thread.currentThread().getName());
            timeEndMills = System.currentTimeMillis();
            timeMs = timeEndMills - timeStartMills;
        } catch (InterruptedException e) {
            System.out.println("Algo falló en la descarga del archivo: " + Thread.currentThread().getName());
        }

    }

    public static long getTimeMs() {
        return timeMs;
    }

}
