import java.util.concurrent.CyclicBarrier;
class Despachador extends Thread {
    private Planta planta;
    private int totalProductos;

    public Despachador(Planta planta, int totalProductos) {
        this.planta = planta;
        this.totalProductos = totalProductos;
    }

    @Override
    public void run() {
        while (totalProductos > 0) {
            try {
                Thread.sleep(100); // Simulate other task
                synchronized (planta.getBodega()) {
                    if (planta.getBodega().isBodegaVacia()) {
                        planta.getBodega().wait();
                    } else {
                        Producto producto = planta.getBodega().tomarProducto();
                        totalProductos--;
                        System.out.println("Despachador toma producto " + producto.getId() + " de la bodega.");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
