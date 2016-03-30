import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Ренейм группы файлов находящимся в одном каталоге.
 * Первый вариант, ГРОМО́ЗДКИЙ, но рабочий.
 * Переименовывает строго определенные файлы,
 * в данном случае графические.
 */


class sw implements ActionListener
{
    JLabel jlab; JLabel jlab2;
    JTextField jtf;
    JButton jb; JButton jb2;
    JFileChooser fileopen;
    String file;

    sw(){
        JFrame jfrm = new JFrame("Переименовалка");

        // установить диспетчер компоновки FlowLayout
        jfrm.setLayout(new FlowLayout()); // определяет расположение компоновки

        jfrm.setSize(280, 170);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jlab = new JLabel("  Индекс (foto, moto, ...):   ");
        jb2 = new JButton("Выбрать папку с файлами");
        jlab2 = new JLabel("[                                          ]");
        jtf = new JTextField(10); // форма ввода
        jb = new JButton("Go");
        fileopen = new JFileChooser();

        jfrm.add(jlab);   //Индекс
        jfrm.add(jtf);    //поле ввода
        jfrm.add(jb2);    //кнопка Каталог
        jfrm.add(jlab2);  //поле вывода выбора каталога
        jfrm.add(jb);     //кнопка Go

        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileopen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int ret = fileopen.showDialog(null, "Выбрать папку");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile().getAbsolutePath();
                    jlab2.setText(file);
                }
            }
        });
        jfrm.setVisible(true);

        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                Path folder = Paths.get(file);
                File dir = new File(file);
                String []st = dir.list();

                for (int i = 0; i < st.length; i++) {

                    File newFile = new File("");

                    if (st[i].endsWith("jpeg")) {
                        String s = (jtf.getText() + (i + 1) + ".jpeg");
                        newFile = folder.resolve(s).toFile();
                    } else if (st[i].endsWith("JPEG")) {
                        String s = (jtf.getText() + (i + 1) + ".JPEG");
                        newFile = folder.resolve(s).toFile();
                    } else if (st[i].endsWith("jpg")) {
                        String s = (jtf.getText() + (i + 1) + ".jpg");
                        newFile = folder.resolve(s).toFile();
                    } else if (st[i].endsWith("JPG")) {
                        String s = (jtf.getText() + (i + 1) + ".JPG");
                        newFile = folder.resolve(s).toFile();
                    } else if (st[i].endsWith("png")) {
                        String s = (jtf.getText() + (i + 1) + ".png");
                        newFile = folder.resolve(s).toFile();
                    } else if (st[i].endsWith("PNG")) {
                        String s = (jtf.getText() + (i + 1) + ".PNG");
                        newFile = folder.resolve(s).toFile();
                    } else if (st[i].endsWith("gif")) {
                        String s = (jtf.getText() + (i + 1) + ".gif");
                        newFile = folder.resolve(s).toFile();
                    } else if (st[i].endsWith("GIF")) {
                        String s = (jtf.getText() + (i + 1) + ".GIF");
                        newFile = folder.resolve(s).toFile();
                    } else if (st[i].endsWith("bmp")) {
                        String s = (jtf.getText() + (i + 1) + ".bmp");
                        newFile = folder.resolve(s).toFile();
                    } else if (st[i].endsWith("BMP")) {
                        String s = (jtf.getText() + (i + 1) + ".BMP");
                        newFile = folder.resolve(s).toFile();
                    }

                    File original = folder.resolve(st[i]).toFile();

                    if (original.exists() & original.isFile() & original.canWrite()) {
                        original.renameTo(newFile);
                    }
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
    }
}

public class Renamed {
    public static void main(String[] args) {
        // создать рамку окна в потоке диспетчеризации событий
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new sw() {
                };
            }
        });
    }
}
