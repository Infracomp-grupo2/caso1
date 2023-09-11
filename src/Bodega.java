import java.util.ArrayList;

class Bodega {
    private int capacidad;
    private ArrayList<Producto> productosEnBodega = new ArrayList<>();

    public Bodega(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void almacenarProducto(Producto producto) throws InterruptedException {
        // Si la bodega esta llena, el productor debe esperar para ser guardado
        while (productosEnBodega.size() >= capacidad) {
            wait();
        }
        // Si la bodega no esta llena, el productor puede guardar el producto en la
        // bodega y se notifica a los demas
        productosEnBodega.add(producto);
        System.out.println("Productor deposita producto " + producto.getId() + " en la bodega. Productos en bodega: "
                + productosEnBodega.size());
        notifyAll();
    }

    // Esta función permite que el despachador pueda tomar un producto de la bodega
    public synchronized Producto tomarProducto() throws InterruptedException {
        // Si la bodega esta vacia, el despachador debe esperar a que haya productos en
        // la bodega
        while (productosEnBodega.size() <= 0) {
            wait();
        }
        // Si la bodega no esta vacia, el despachador puede tomar un producto de la
        // bodega y se notifica a los demas
        Producto productoTomado = productosEnBodega.remove(productosEnBodega.size() - 1);
        notifyAll();
        return productoTomado;
    }

    public synchronized boolean isBodegaVacia() {
        return productosEnBodega.size() == 0;
    }
}
