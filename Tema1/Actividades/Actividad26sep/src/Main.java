//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DownloadThread archivo1 = new DownloadThread("Archivo1.mp4", 1000);
        DownloadThread archivo2 = new DownloadThread("Archivo2.pdf", 2000);
        DownloadThread archivo3 = new DownloadThread("Archivo3.jpg", 3000);
        DownloadThread archivo4 = new DownloadThread("Archivo4.zip", 4000);
        DownloadThread archivo5 = new DownloadThread("Archivo5.mov", 5000);

        long tiempoEmpezado = System.currentTimeMillis();

        archivo1.start();
        archivo2.start();
        archivo3.start();
        archivo4.start();
        archivo5.start();

        try {
            archivo1.join();
            archivo2.join();
            archivo3.join();
            archivo4.join();
            archivo5.join();
        } catch (InterruptedException e) {
            System.out.println("Algo ha ocurrido.");
        }

        long tiempoFinalizado = System.currentTimeMillis();

        System.out.println("Todas las descargas han finalizado.");

        System.out.println("Tiempo pasado desde que inicia la descarga del archivo 1 hasta que termina la descarga del archivo 5: " + DownloadThread.getTimeMs()/1000 + " segundos.");

    }
}