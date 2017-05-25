import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.io.*;
import java.nio.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by 47678 on 2017/5/25.
 */
public class Checksum {

    public static HashMap hashMap = new HashMap();

    public static String MD5CheckSum (File file) throws Exception {
        HashCode result = com.google.common.io.Files.hash(file, Hashing.md5());
        return result.toString();
    }

    public static void getAllFiles(Path path) throws Exception {
        File root = path.toFile();
        File[] files = root.listFiles();
        for(File file : files) {
            if(file.isDirectory()) {
                getAllFiles(file.toPath());
            }
            else {
                String checksum = MD5CheckSum(file);
                hashMap.put(file, checksum);
                //System.out.println(file + " " + checksum);
            }
        }
    }

    public static boolean judgeEqual(File file1, File file2) throws Exception {
        if(file1.length() != file2.length()) return false;
        else {
            Scanner in = new Scanner(file1);
            Vector file1Content = new Vector();
            while(in.hasNext()) {
                String temp = in.next();
                file1Content.add(temp);
            }
            Vector file2Content = new Vector();
            in.close();
            in = new Scanner(file2);
            while(in.hasNext()) {
                String temp = in.next();
                file2Content.add(temp);
            }
            for(int i = 0; i < file1Content.size(); i++) {
                String tmp1 = (String) file1Content.get(i);
                String tmp2 = (String) file2Content.get(i);
                if(!tmp1.equals(tmp2)) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        hashMap.clear();
        JOptionPane.showMessageDialog(new JFrame(), "请选择你想要查询的文件目录:");
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(new JFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            Path path = chooser.getSelectedFile().toPath();
            System.out.println("查询的文件目录: " + path.toString() + '\n');
            getAllFiles(path);
            int countin = 0, countout = 0;
            for(Object stringin : hashMap.keySet()) {
                countout = 0;
                Object sumin = hashMap.get(stringin);
                for(Object stringout: hashMap.keySet()) {
                    Object sumout = hashMap.get(stringout);
                    if(sumin.toString().equals(sumout.toString()) && countin != countout) {
                        File file1 = new File(stringin.toString());
                        File file2 = new File(stringout.toString());
                        if(judgeEqual(file1, file2)) {
                            System.out.println("重复文件: ");
                            System.out.println(stringin.toString());
                            System.out.println(stringout.toString());
                            System.out.println();
                        }
                    }
                    countout++;
                }
                countin++;
            }
        }
        System.exit(0);
    }

}
