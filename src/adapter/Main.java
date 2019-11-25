package adapter;

import adapter.Cars.Ferrari;

public class Main {
    public static void main(String[] args) {
        AdapterToFerrarri adapterToFerrarri = new AdapterToFerrarri(new Ferrari());
        System.out.println(adapterToFerrarri.tryToStartMe());
    }
}
