import com.sun.deploy.panel.JreDialog;
import com.sun.org.apache.regexp.internal.RE;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Vector;

public class ManageSystem extends JFrame{

    private JScrollPane pane;
    private JButton buttonNew, buttonOpen, buttonSave, buttonSaveas;
    private JButton buttonAdd, buttonDelete, buttonChange;
    private JList list;
    private JFileChooser chooser = new JFileChooser();
    private File file;
    private Vector Strings;
    private final String introduction = "StudentID" + "     " + "Name" + "          " + "Age";

    private void RefreshDisplay(File nowfile) {
        try {
            Scanner inFile = new Scanner(nowfile);
            Strings.clear();
            while(inFile.hasNext()) {
                String temp = inFile.next();
                Strings.add(DotToSpace(temp));
                //System.out.println(DotToSpace(temp));
            }
            inFile.close();
            Reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String DotToSpace(String string) {
        String res = "";
        String[] dotSplit = string.split(",");
        //System.out.println(dotSplit.length);
        int len = dotSplit.length;
        for(int i = 0; i < len - 1; i++) {
            res += SetSpace(dotSplit[i]);
        }
        res += dotSplit[len - 1];
        return res;
    }

    private String SpaceToDot(String string) {
        String[] spaceSplit = string.split(" ");
        int len = spaceSplit.length;
        String res = "";
        for(int i = 0; i < len - 1; i++) {
            if (!spaceSplit[i].equals("")) {
                res = res + spaceSplit[i] + ',';
            }
        }
        res += spaceSplit[len - 1];
        //System.out.println(res);
        return res;
    }

    private String SetSpace(String string) {
        for(int i = string.length(); i <= 13; i++) {
            string += " ";
        }
        return string;
    }

    private void Reset() {
        list.setListData(Strings);
        pane.setViewportView(list);
    }

    private void UpdataFile(File nowfile) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(nowfile), true);
            for(int i = 0; i < Strings.size(); i++) {
                printWriter.println(SpaceToDot((String)Strings.get(i)));
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ManageSystem() {
        setLayout(null);
        setTitle("学生管理系统");
        file = null;

        buttonNew = new JButton("新建");
        buttonNew.setBounds(8, 2, 90, 50);
        buttonNew.setBackground(Color.white);
        buttonNew.setFont(new Font("宋体",1, 17));
        buttonNew.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = chooser.showOpenDialog(new Frame());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    Path path = chooser.getSelectedFile().toPath();
                    String string = JOptionPane.showInputDialog("请输入文件名:");
                    if(string != null) {
                        System.out.println(path + "\\" + string + ".csv");
                        file = new File(path + "\\" + string + ".csv");
                        if (!file.exists()) {
                            try {
                                file.createNewFile();
                                System.out.println("File creates");
                                Strings.clear();
                                Strings.add(introduction);
                                Reset();
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(new Frame(), "此文件已存在！");
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonNew);

        buttonOpen = new JButton("打开");
        buttonOpen.setBounds(8 + 90 + 5, 2, 90, 50);
        buttonOpen.setBackground(Color.white);
        buttonOpen.setFont(new Font("宋体",1, 17));
        buttonOpen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnVal = chooser.showOpenDialog(new JFrame());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File attemptfile = chooser.getSelectedFile().getAbsoluteFile();
                    System.out.println(attemptfile.toString());
                    String temp = attemptfile.toString();
                    int len = temp.length();
                    System.out.println(temp.substring(len - 4, len));
                    if(temp.substring(len - 4, len).equals(".csv")) {
                        System.out.println("Successful open");
                        file = attemptfile;
                        RefreshDisplay(file);
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(),"文件必须是csv类型");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonOpen);

        buttonSave = new JButton("保存");
        buttonSave.setBounds(8 + 90 * 2 + 5 * 2, 2, 90, 50);
        buttonSave.setBackground(Color.white);
        buttonSave.setFont(new Font("宋体", 1, 17));
        buttonSave.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(file != null) {
                    UpdataFile(file);
                    System.out.println("File updates");
                }
                else {
                    JOptionPane.showMessageDialog(new JFrame(), "尚未打开或创建文件");
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonSave);

        buttonSaveas = new JButton("另存为");
        buttonSaveas.setBounds(8 + 90 * 3 + 5 * 3, 2, 90, 50);
        buttonSaveas.setBackground(Color.white);
        buttonSaveas.setFont(new Font("宋体", 1, 17));
        buttonSaveas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(file != null) {
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int returnVal = chooser.showOpenDialog(new JFrame());
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        String filename = JOptionPane.showInputDialog("请输入文件名:");
                        filename = chooser.getSelectedFile().toString() + "\\" + filename + ".csv";
                        File tempfile = new File(filename);
                        UpdataFile(tempfile);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(new JFrame(), "尚未打开或创建文件");
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonSaveas);

        Strings = new Vector();
        Strings.clear();


        list = new JList();
        list.setListData(Strings);
        list.setFont(new Font("宋体", 1, 15));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        pane = new JScrollPane(list);
        pane.setBounds(8, 60, 300, 200);
        add(pane);

        buttonAdd = new JButton("添加");
        buttonAdd.setBounds(308 + 5, 60, 70,65);
        buttonAdd.setBackground(Color.white);
        buttonAdd.setFont(new Font("宋体", 1, 15));
        buttonAdd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(file != null) {
                    String studentID = JOptionPane.showInputDialog("请输入学号:");
                    if (studentID != null) {
                        if (studentID.length() > 13) {
                            JOptionPane.showMessageDialog(new JFrame(), "学号过长！");
                        } else {
                            String name = JOptionPane.showInputDialog("请输入姓名:");
                            if (name != null) {
                                if (name.length() > 10) {
                                    JOptionPane.showMessageDialog(new JFrame(), "姓名过长！");
                                } else {
                                    String age = JOptionPane.showInputDialog("请输入年龄");
                                    if (age != null) {
                                        if (age.length() > 3) {
                                            JOptionPane.showMessageDialog(new JFrame(), "年龄过大！");
                                        } else {
                                            String sum = SetSpace(studentID) + SetSpace(name) + age;
                                            Strings.add(sum);
                                            Reset();
                                            System.out.println("Element adds");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonAdd);

        buttonChange = new JButton("修改");
        buttonChange.setBounds(308 + 5, 127, 70, 65);
        buttonChange.setBackground(Color.white);
        buttonChange.setFont(new Font("宋体", 1, 15));
        buttonChange.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(file != null) {
                    int index = list.getSelectedIndex();
                    if (list.getSelectedIndex() != 0) {
                        String studentID = JOptionPane.showInputDialog("请输入学号:");
                        if (studentID != null) {
                            if (studentID.length() > 13) {
                                JOptionPane.showMessageDialog(new JFrame(), "学号过长！");
                            } else {
                                String name = JOptionPane.showInputDialog("请输入姓名:");
                                if (name != null) {
                                    if (name.length() > 10) {
                                        JOptionPane.showMessageDialog(new JFrame(), "姓名过长！");
                                    } else {
                                        String age = JOptionPane.showInputDialog("请输入年龄");
                                        if (age != null) {
                                            if (age.length() > 3) {
                                                JOptionPane.showMessageDialog(new JFrame(), "年龄过大！");
                                            } else {
                                                String sum = SetSpace(studentID) + SetSpace(name) + age;
                                                Strings.remove(index);
                                                Strings.insertElementAt(sum, index);
                                                Reset();
                                                System.out.println("Element changes");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonChange);

        buttonDelete = new JButton("删除");
        buttonDelete.setBounds(308 + 5, 194, 70,65);
        buttonDelete.setBackground(Color.white);
        buttonDelete.setFont(new Font("宋体", 1, 15));
        buttonDelete.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(file != null) {
                    int index = list.getSelectedIndex();
                    if (index != 0) {
                        int returnVal = JOptionPane.showConfirmDialog(new JFrame(), "你确定要删除吗?");
                        if (returnVal == JOptionPane.OK_OPTION) {
                            Strings.remove(index);
                            System.out.println("Element deletes");
                            Reset();
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonDelete);

        setBounds(200, 200, 400, 300);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setResizable(false);

    }

    public static void main(String[] args) throws Exception{

        ManageSystem manageSystem = new ManageSystem();
        manageSystem.setVisible(true);

    }

}
