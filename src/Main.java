import java.util.Scanner;

public class Main {

    static int totalProductos;

    public static void main(String[] args) {
        // Leer los par�metros de la simulaci�n
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el n�mero de productores: ");
        int nProductores = scanner.nextInt();

        System.out.print("Ingrese el n�mero de repartidores: ");
        int mRepartidores = scanner.nextInt();

        System.out.print("Ingrese la capacidad de la bodega: ");
        int capacidadBodega = scanner.nextInt();

        System.out.print("Ingrese el total de productos: ");
        int totalProductos = scanner.nextInt();

        Planta planta = new Planta(capacidadBodega);

        for (int i = 0; i < nProductores; i++) {
            new Productor(i, planta).start();
        }

        Despachador despachador = new Despachador(planta);
        despachador.start();

        for (int i = 0; i < mRepartidores; i++) {
            new Repartidor(i, planta, despachador).start();
        }

        try {
            despachador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La simulaci�n ha finalizado correctamente.");
    }
}