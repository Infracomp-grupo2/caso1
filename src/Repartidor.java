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

        while (true) {
            try {
                Producto producto = despachador.entregarProducto();
                System.out.println("Repartidor " + id + " toma producto " + producto.getId());
                Thread.sleep((random.nextInt(8) + 3) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}