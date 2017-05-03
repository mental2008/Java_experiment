import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by 47678 on 2017/5/3.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        java.io.File file = new java.io.File("score.csv");
        Scanner inputFile = new Scanner(file);
        int cnt = 0;
        int[] score = new int[100];
        while(inputFile.hasNext()) {
            String line = inputFile.next();
            String item[] = line.split(";");
            String last = item[item.length - 1];
            cnt++;
            score[cnt] = Integer.parseInt(last);
        }
        inputFile.close();
        int MaxScore = -1, MinScore = 101;
        int sum = 0;
        int[] level = new int[15];
        for(int i = 0; i < 15; i++) {
            level[i] = 0;
        }
        for(int i = 1; i <= cnt; i++) {
            MaxScore = Math.max(MaxScore, score[i]);
            MinScore = Math.min(MinScore, score[i]);
            sum += score[i];
            level[score[i] / 10]++;
        }
        FileOutputStream fs = new FileOutputStream(new File("answer.txt"));
        PrintStream p = new PrintStream(fs);
        p.println("The maximum score: " + MaxScore);
        p.println("The minimum score: " + MinScore);
        p.printf("The average score: %.2f\n", ((double)sum / cnt));
        level[9] += level[10];
        for(int i = 6; i <= 9; i++) {
            if(i == 9) {
                p.println("90~100: " + level[9]);
                continue;
            }
            p.println(i + "0~" + i + "9: " + level[i]);
        }
    }

}
