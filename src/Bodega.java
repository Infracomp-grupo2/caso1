class Bodega {
    private int capacidad;
    private int productosEnBodega = 0;

    public Bodega(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void almacenarProducto(Producto producto) throws InterruptedException {
        while (productosEnBodega >= capacidad) {
            wait();
        }
        productosEnBodega++;
        System.out.println("Productor deposita producto " + producto.getId() + " en la bodega. Productos en bodega: "
                + productosEnBodega);
        notifyAll();
    }

    public synchronized Producto tomarProducto() throws InterruptedException {
        while (productosEnBodega <= 0) {
            wait();
        }
        productosEnBodega--;
        notifyAll();
        return new Producto(productosEnBodega + 1);
    }

    public synchronized boolean isBodegaVacia() {
        return productosEnBodega == 0;
    }
}
