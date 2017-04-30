package com.company;

public class Main {

        public static double f(double x) {
            return x * x * x - 10 * x + 23;
        }

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            double l = -10.0, r = 5.0;
            double exps = 0.001;
            while(Math.abs(l - r) > exps) {
                double mid = (l + r) / 2;
                if(f(mid) == 0) {
                    l = mid;
                    break;
                }
                else if(f(mid) * f(l) <= 0) {
                    r = mid;
                }
                else {
                    l = mid;
                }
            }
            System.out.println("The answer is " + l);
        }

}
