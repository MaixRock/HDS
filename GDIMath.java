package com.example.gearquicker.hydrodinamicsimulator;


public class GDIMath {

    public static double calcPressure(ModelData modelData, Formula f, double t){
        double ans = stefest(f,t);
        ans = modelData.getPressure() - 18.41 * modelData.getVolume_factor() * modelData.getViscosity() * ans / modelData.getPermeability() / modelData.getLayerWidth();
        return ans;
    }

    public static double stefest(Formula f, double t) {
        int N = 8;
        double summN = 0;
        for (int n = 1; n <= N; n++) {
            double summP = 0;
            for (int p = (int) ((n + 1) / 2); p <= Math.min(n, N / 2); p++) {
                summP = summP
                        + (Math.pow(-1, n + N / 2) * Math.pow(p, N / 2) * factorial(2 * p)) / (factorial(N / 2 - p)
                        * factorial(p) * factorial(p - 1) * factorial(n - p) * factorial(2 * p - n));
            }
            summN = summN + summP * f.calc(n * Math.log(2) / t);
        }
        double ans = summN * Math.log(2) / t;
        return ans;
    }

    public static double factorial(double n) {
        double factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial = factorial * i;
        }
        return factorial;
    }

    public static double besselI0(double x) {

        double ax, ans;
        double y;

        if ((ax = Math.abs(x)) < 3.75) {
            y = x / 3.75;
            y = y * y;
            ans = 1.0 + y * (3.5156229
                    + y * (3.0899424 + y * (1.2067492 + y * (0.2659732 + y * (0.360768e-1 + y * 0.45813e-2)))));
        } else {
            y = 3.75 / ax;
            ans = (Math.exp(ax) / Math.sqrt(ax))
                    * (0.39894228 + y * (0.1328592e-1 + y * (0.225319e-2 + y * (-0.157565e-2 + y * (0.916281e-2
                    + y * (-0.2057706e-1 + y * (0.2635537e-1 + y * (-0.1647633e-1 + y * 0.392377e-2))))))));
        }
        return ans;
    }

    public static double besselK0(double x) {

        double y, ans;

        if (x <= 2.0) {
            y = x * x / 4.0;
            ans = (-Math.log(x / 2.0) * besselI0(x)) + (-0.57721566 + y * (0.42278420
                    + y * (0.23069756 + y * (0.3488590e-1 + y * (0.262698e-2 + y * (0.10750e-3 + y * 0.74e-5))))));
        } else {
            y = 2.0 / x;
            ans = (Math.exp(-x) / Math.sqrt(x)) * (1.25331414 + y * (-0.7832358e-1 + y
                    * (0.2189568e-1 + y * (-0.1062446e-1 + y * (0.587872e-2 + y * (-0.251540e-2 + y * 0.53208e-3))))));
        }
        return ans;
    }

}
