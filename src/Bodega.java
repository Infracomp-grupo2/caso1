class Bodega {
    private int capacidad;
    private int productosEnBodega = 0;

    public Bodega(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void almacenarProducto(Producto producto) throws InterruptedException {
        // Si la bodega esta llena, el productor debe esperar para ser guardado
        while (productosEnBodega >= capacidad) {
            wait();
        }
        // Si la bodega no esta llena, el productor puede guardar el producto en la
        // bodega y se notifica a los demas
        productosEnBodega++;
        System.out.println("Productor deposita producto " + producto.getId() + " en la bodega. Productos en bodega: "
                + productosEnBodega);
        notifyAll();
    }

    // Esta función permite que el despachador pueda tomar un producto de la bodega
    public synchronized Producto tomarProducto() throws InterruptedException {
        // Si la bodega esta vacia, el despachador debe esperar a que haya productos en
        // la bodega
        while (productosEnBodega <= 0) {
            wait();
        }

        // Si la bodega no esta vacia, el despachador puede tomar un producto de la
        // bodega y se notifica a los demas
        productosEnBodega--;
        notifyAll();
        return new Producto(productosEnBodega + 1);
    }

    public synchronized boolean isBodegaVacia() {
        return productosEnBodega == 0;
    }
}
