class Calculadora {
    public Calculadora() {
        System.out.println("Estoy instanciando un nuevo objeto de la clase Calculadora");
    }

    public int sumar(int a, int b) {
        return a + b;
    }

    public boolean esPar(int x) {
        if (x % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void mostrarNumerosHasta(int x) {
        // for(int i = 0; i <= x; i++) {
        //     System.out.println(i);
        // }

        int i = 0;
        while (i <= x) {
            System.out.println(i);
            i++;
        }
    }
}