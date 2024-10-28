package TaskNumerosImparesWhile;

public class imparesWhile {

    public static void main(String[] args) {
        int contador = 1;

        while (contador <= 50) {

            if (contador % 2 == 1 ) {
              System.out.println("Numeros Impares While: " + contador);  
            }
        contador++;
        }
    }
}
