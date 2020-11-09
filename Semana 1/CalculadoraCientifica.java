class CalculadoraCientifica extends Calculadora {
    public CalculadoraCientifica() {
    }

    public void mostrarNumerosHasta(int x) {
        int i = 1;
        while (i <= x) {
            System.out.println(i);
            i++;
        }
    }

    public void mostrarNumerosHasta(int x, int y) {
        while (x <= y) {
            System.out.println(x);
            x++;
        }
    }
}