
class Despachador extends Thread {

    private Planta planta;
    private Producto producto = null;
    static Boolean todoListo = true;

    // Al despachador le entra la plata por parámetro que es donde se va a conectar
    // con la bodega
    public Despachador(Planta planta) {
        this.planta = planta;
    }

    public synchronized Producto llevarseProducto() throws InterruptedException {
        while (producto == null) {
            wait();
        }
        Producto productoEntregado = producto;
        producto = null;
        synchronized (Despachador.class) {
            System.out.println("Despachador a despertar :3");
            Despachador.class.notifyAll();
        }

        return productoEntregado;
    }

    public synchronized void tomarProductoBodega() throws InterruptedException {
        producto = planta.getBodega().tomarProducto();
        notifyAll();
    }

    @Override
    public void run() {
        // Mientras que los productores no hana terminado de producir, el despachador
        // sigue enviando productos
        while (Main.totalProductos > 0 || !planta.getBodega().isBodegaVacia()) {
            try {
                // Si la bodega tiene productos, el despachador puede tomar un producto
                if (!planta.getBodega().isBodegaVacia()) {
                    synchronized (Despachador.class) {
                        tomarProductoBodega();
                        System.out.println("Despachador toma producto " + producto.getId());
                        System.out.println("Despachador se va a mimir");
                        Despachador.class.wait();
                    }
                    System.out.println("Despachador despierta");
                }
                // Si la bodega esta vacia, el despachador hace espera activa
                else {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("El despachador ha terminado de despachar :p");
        todoListo = false;
    }
}
