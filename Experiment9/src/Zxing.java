import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jdk.nashorn.internal.scripts.JO;
import oracle.jrockit.jfr.JFR;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Zxing extends JFrame{

    private JButton buttonEncode, buttonDecode;
    private JFileChooser chooser;

    private void encode(String content, Path file) {
        int height = 300, width = 300;
        String format = "png";

        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            file = new File(file + "/" + content + ".png").toPath();
            //System.out.println(file);
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            //System.out.println(file.toString());
            ImageIcon icon = new ImageIcon(file.toString());
            Image temp = icon.getImage().getScaledInstance(300, 300, icon.getImage().SCALE_DEFAULT);
            icon = new ImageIcon(temp);
            JLabel label = new JLabel();
            label.setIcon(icon);
            label.setBounds(0, 0, 300, 300);
            JFrame display = new JFrame("显示图片");
            display.setVisible(true);
            display.setBounds(100, 100, 320, 360);
            JPanel panel = (JPanel) display.getContentPane();
            panel.setOpaque(false);
            display.getLayeredPane().add(label);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String decode(Path file) {
        try {
            MultiFormatReader reader = new MultiFormatReader();
            BufferedImage image = ImageIO.read(file.toFile());
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            HashMap hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            Result result = reader.decode(binaryBitmap, hints);
            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Zxing() {

        chooser = new JFileChooser();

        buttonEncode = new JButton("编码");
        buttonEncode.setBounds(30, 35, 100, 80);
        buttonEncode.setBackground(Color.white);
        buttonEncode.setFont(new Font("宋体", 1, 20));
        buttonEncode.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                String string = JOptionPane.showInputDialog("请输入你的文本信息");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = chooser.showOpenDialog(new Frame());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    Path path = chooser.getSelectedFile().toPath();
                    //System.out.println(path);
                    encode(string, path);
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
        add(buttonEncode);

        buttonDecode = new JButton("解码");
        buttonDecode.setBounds(175, 35, 100, 80);
        buttonDecode.setBackground(Color.white);
        buttonDecode.setFont(new Font("宋体", 1, 20));
        buttonDecode.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnVal = chooser.showOpenDialog(new Frame());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    Path path = chooser.getSelectedFile().toPath();
                    String temp = path.toString();
                    //System.out.println(temp.substring(temp.length() - 4, temp.length()));
                    if(temp.substring(temp.length() - 4, temp.length()).equals(".png")) {
                        String ans = decode(path);
                        JOptionPane.showMessageDialog(new Frame(), "解码结果: " + ans);
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
        add(buttonDecode);

        setLayout(null);
        setTitle("二维码的解码与编码");
        setBounds(300, 200, 300, 200);
        setResizable(false);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Zxing zxing = new Zxing();
        zxing.setVisible(true);
    }

}
