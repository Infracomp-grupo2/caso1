import java.util.concurrent.CyclicBarrier;

class Despachador extends Thread {

    private Planta planta;

    private Producto producto = null;

    // Al despachador le entra la plata por parámetro que es donde se va a conectar
    // con la bodega
    public Despachador(Planta planta) {
        this.planta = planta;
    }

    public synchronized Producto entregarProducto() throws InterruptedException {

        while (producto == null) {
            wait();
        }

        notifyAll();
        return producto;
    }

    @Override
    public void run() {

        // Mientras que los productores no hana terminado de producir, el despachador
        // sigue enviando productos
        while (Productor.todoListo) {
            try {
                synchronized (planta.getBodega()) {
                    // Si la bodega tiene productos, el despachador puede tomar un producto
                    if (!planta.getBodega().isBodegaVacia()) {
                        producto = planta.getBodega().tomarProducto();
                        System.out.println("Despachador toma producto " + producto.getId());
                    }
                    // Si la bodega esta vacia, el despachador hace espera activa
                    else {
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
