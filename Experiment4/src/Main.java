import java.util.Scanner;

/**
 * Created by 47678 on 2017/5/3.
 */

public class Main {

    public static void main(String[] args) throws Exception {
        java.io.File file1 = new java.io.File("LR_ex0.txt");
        java.io.File file2 = new java.io.File("LR_ex1.txt");
        Scanner inputFile = new Scanner(file1);
        int cnt = 0;
        double[] x = new double[250];
        double[] y = new double[250];
        while(inputFile.hasNext()) {
            cnt++;
            double tmp = inputFile.nextDouble();
            x[cnt] = inputFile.nextDouble();
            y[cnt] = inputFile.nextDouble();
        }
        inputFile.close();
        double A, B;
        double sumx = 0, sumy = 0, sumxy = 0, sumx2 = 0;
        for(int i = 1; i <= cnt; i++) {
            sumx += x[i];
            sumy += y[i];
            sumxy += x[i] * y[i];
            sumx2 += x[i] * x[i];
        }
        B = (sumy * sumx2 - sumxy * sumx) / (sumx2 * cnt - sumx * sumx);
        A = (sumxy - sumx * B) / sumx2;
        System.out.println("The linear function:");
        System.out.printf("f(x) = %.3fx + %.3f\n", A, B);
    }

}
