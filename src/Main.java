import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        // Leer los parámetros de la simulación
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de productores: ");
        int nProductores = scanner.nextInt();

        System.out.print("Ingrese el número de repartidores: ");
        int mRepartidores = scanner.nextInt();

        System.out.print("Ingrese la capacidad de la bodega: ");
        int capacidadBodega = scanner.nextInt();

        System.out.print("Ingrese el total de productos: ");
        int totalProductos = scanner.nextInt();

        Planta planta = new Planta(capacidadBodega);

        for (int i = 0; i < nProductores; i++) {
            new Productor(i, planta, totalProductos / nProductores).start();
        }

        for (int i = 0; i < mRepartidores; i++) {
            new Repartidor(i, planta).start();
        }

        Despachador despachador = new Despachador(planta, totalProductos);
        despachador.start();

        try {
            despachador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La simulación ha finalizado correctamente.");
    }
}