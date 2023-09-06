import java.util.Random;

class Productor extends Thread {
    private int id;
    private Planta planta;
    static Boolean todoListo = true;

    public Productor(int id, Planta planta) {
        this.id = id;
        this.planta = planta;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (Main.totalProductos > 0) {
            try {
                Thread.sleep(random.nextInt(1000));
                Producto producto = new Producto(Main.totalProductos);
                synchronized (planta.getBodega()) {
                    planta.getBodega().almacenarProducto(producto);
                    Main.totalProductos--;
                }

                synchronized (producto) {
                    wait();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Los productores han terminado de producir :p");
        todoListo = false;
    }
}