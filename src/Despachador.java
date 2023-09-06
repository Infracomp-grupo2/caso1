import java.util.concurrent.CyclicBarrier;

class Despachador extends Thread {

    private Planta planta;

    public Despachador(Planta planta) {
        this.planta = planta;
    }

    @Override
    public void run() {
        while (Productor.todoListo) {
            try {
                synchronized (planta.getBodega()) {
                    if (!planta.getBodega().isBodegaVacia()) {
                        Producto producto = planta.getBodega().tomarProducto();
                        System.out.println("Despachador toma producto " + producto.getId());
                    } else {
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
