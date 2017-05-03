import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static double dis(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static void main(String[] args) throws Exception {
        java.io.File file1 = new java.io.File("KMeans_Set.txt");
        java.io.File file2 = new java.io.File("KMeans_Set2.txt");
        Scanner inputFile = new Scanner(file1);
        /*
        If you want to deal with the second set of data
        just change the file1 to file2
         */
        int cnt = 0;
        double[] x = new double[100];
        double[] y = new double[100];
        while(inputFile.hasNext()) {
            cnt++;
            x[cnt] = inputFile.nextDouble();
            y[cnt] = inputFile.nextDouble();
        }
        inputFile.close();
        Scanner ConsoleInput = new Scanner(System.in);
        System.out.print("How many clusters do you expect: ");
        int k;
        k = ConsoleInput.nextInt();
        if(k <= 0 || k > cnt) {
            throw new Exception("Input error!");
        }
        final int times = 1000;
        double[] cluster_x = new double[100];
        double[] cluster_y = new double[100];
        for(int i = 1; i <= k; i++) {
            cluster_x[i] = x[i];
            cluster_y[i] = y[i];
        }
        for(int t = 0; t < times; t++) {
            double[] newCluster_x = new double[100];
            double[] newCluster_y = new double[100];
            double[] sum = new double[100];
            for(int i = 0; i < 100; i++) {
                newCluster_x[i] = 0;
                newCluster_y[i] = 0;
                sum[i] = 0;
            }
            for(int i = 1; i <= cnt; i++) {
                double distance = dis(x[i], y[i], cluster_x[1], cluster_y[1]);
                int num = 1;
                for(int j = 2; j <= k; j++) {
                    double tmp = dis(x[i], y[i], cluster_x[j], cluster_y[j]);
                    if(distance > tmp) {
                        distance = tmp;
                        num = j;
                    }
                }
                newCluster_x[num] += x[i];
                newCluster_y[num] += y[i];
                sum[num]++;
            }
            for(int i = 1; i <= k; i++) {
                if(sum[i] != 0) {
                    cluster_x[i] = newCluster_x[i] / sum[i];
                    cluster_y[i] = newCluster_y[i] / sum[i];
                }
            }
        }
        for(int i = 1; i <= k; i++) {
            System.out.printf("%.2f %.2f\n", cluster_x[i], cluster_y[i]);
        }
        ConsoleInput.close();
    }

}
