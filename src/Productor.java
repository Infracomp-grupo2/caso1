import java.util.Random;

class Productor extends Thread {
    private int id;
    private Planta planta;
    private int totalProductos;

    public Productor(int id, Planta planta, int totalProductos) {
        this.id = id;
        this.planta = planta;
        this.totalProductos = totalProductos;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (totalProductos > 0) {
            try {
                Thread.sleep(random.nextInt(1000));
                Producto producto = new Producto(totalProductos);
                synchronized (planta.getBodega()) {
                    planta.getBodega().almacenarProducto(producto);
                    totalProductos--;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}