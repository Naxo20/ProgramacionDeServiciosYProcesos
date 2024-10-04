public class MiHilo extends Thread{

    public MiHilo(String nombre) {
        this.setName(nombre);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                if (Thread.currentThread().getName().equals("Hilo2")) {
                    throw new RuntimeException();
                } else {
                    Thread.sleep(1000);
                    System.out.println("Hilo " + Thread.currentThread().getName() + " se está ejecutando.");
                }
            } catch (InterruptedException ie) {
                System.out.println("Hilo " + Thread.currentThread().getName() + " ha sido interrumpido.");
            } catch (RuntimeException re) {
                System.out.println("El hilo " + Thread.currentThread().getName() + " ha sido interrumpido de manera forzada.");
                break;
            }
        }
        System.out.println("Hilo " + Thread.currentThread().getName() + " terminó.");
    }

}
