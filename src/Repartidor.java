import java.util.Random;

class Repartidor extends Thread {
    private int id;
    private Despachador despachador;

    public Repartidor(int id, Despachador despachador) {
        this.id = id;
        this.despachador = despachador;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (Despachador.todoListo) {
            try {
                Producto producto = despachador.llevarseProducto();
                System.out.println("Repartidor " + id + " toma producto " + producto.getId());
                Thread.sleep((random.nextInt(8) + 3) * 1000);
                synchronized (producto) {
                    producto.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}