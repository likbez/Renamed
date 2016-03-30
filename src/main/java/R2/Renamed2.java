package R2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Ренейм группы файлов находящимся в одном каталоге.
 * Второй вариант, более компактный, но глючный и недоделанный,
 * переименовывет все подряд, держу ради примера.
 */
class sw implements ActionListener
{
    JLabel jlab; JLabel jlab2;
    JTextField jtf;
    JButton jb; JButton jb2;
    JFileChooser fileopen;
    String file;
    Path folder;
    File newFile;

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

                folder = Paths.get(file);
                File dir = new File(file);
                String []st = dir.list();

                for (int i = 0; i < st.length; i++) {
                    newFile = extension(st[i]);

                    File original = folder.resolve(st[i]).toFile();

                    if (original.exists() & original.isFile() & original.canWrite()) {
                        original.renameTo(newFile);
                    }
                }
            }
        });
    }
    /**
     *    Принимает имя файла из массива имен, проходит по всем символам строки имени файла,
     * доходит до точки и записывает символы после точки, начиная с точки (.bmp).
     *    После скрещиваем новое имя с нашим расширением и выдаем наружу.
     **/
    public File extension(String str){
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        for (i = 0; i < str.length() - 1; i++) {
            if(str.charAt(i) == '.'){
                for (int j = i; j < str.length(); j++) {
                    stringBuilder.append(str.charAt(j));
                }
            }
        }
        String strBu = stringBuilder.toString();
        String s = (jtf.getText() + (i + 1) + strBu);
        newFile = folder.resolve(s).toFile();
        return newFile;
    }

    public void actionPerformed(ActionEvent e) {
    }
}

public class Renamed2 {
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
