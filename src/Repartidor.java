import java.util.Random;
import java.util.concurrent.CyclicBarrier;
class Repartidor extends Thread {
    private int id;
    private Planta planta;

    public Repartidor(int id, Planta planta) {
        this.id = id;
        this.planta = planta;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (true) {
            try {
                Producto producto = planta.getBodega().tomarProducto();
                System.out.println("Repartidor " + id + " toma producto " + producto.getId());
                Thread.sleep((random.nextInt(8) + 3) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}