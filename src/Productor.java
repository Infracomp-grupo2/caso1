import java.util.Random;

class Productor extends Thread {
    private int id;
    private Planta planta;

    // Esta variable es para saber si los productores ya terminaron de producir, si
    // está en true significa que aún quedan productos por producir

    public Productor(int id, Planta planta) {
        this.id = id;
        this.planta = planta;
    }

    @Override
    public void run() {

        while (Main.totalProductos > 0) {
            try {
                // Thread.sleep(random.nextInt(1000));
                Producto producto;
                synchronized (planta.getBodega()) {
                    producto = new Producto(Main.totalProductos);
                    System.out.println("Productor " + id + " produce producto " + producto.getId());
                    planta.getBodega().almacenarProducto(producto);
                    Main.totalProductos--;
                }

                // Después de mandar su producto a la bodega, el productor se duerme hasta que
                // su producto sea entregado
                synchronized (producto) {
                    System.out.println("Productor " + id + " a mimir :3");
                    producto.wait();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("El productor" + id + "ha terminado de producir :p");

    }
}