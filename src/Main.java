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

        // Se crea un thread productor por cada productor que se haya ingresado en
        // consola
        for (int i = 0; i < nProductores; i++) {
            new Productor(i, planta).start();
        }

        // Solo se crea un despachador
        Despachador despachador = new Despachador(planta);
        despachador.start();

        // Se crea un thread repartidor por cada repartidor que se haya ingresado en
        // consola
        for (int i = 0; i < mRepartidores; i++) {
            new Repartidor(i, despachador).start();
        }

        try {
            despachador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La simulaci�n ha finalizado correctamente.");
    }
}