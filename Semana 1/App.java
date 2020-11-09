class App {
    private static String nombre = "Andy";

    public static void main(String[] args) {
        /* System.out.println("Hola, mi nombre es " + nombre + "!!"); */
        // Calculadora calc1 = new Calculadora();
        // System.out.println(calc1.sumar(3, 2));
        // System.out.println(calc1.esPar(3));
        // calc1.mostrarNumerosHasta(10);

        CalculadoraCientifica calc2 = new CalculadoraCientifica();
        calc2.mostrarNumerosHasta(5, 10);
    }
}