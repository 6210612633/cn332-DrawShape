import java.awt.Canvas;
import java.awt.Graphics;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.util.Scanner;

public class DrawShape extends Canvas {

    public static void main(String[] args) {

        JFrame theGUI = new JFrame("GUI");
        Canvas canvas = new DrawShape();
        canvas.setSize(300, 300);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theGUI.add(canvas);
        theGUI.pack();
        theGUI.setVisible(true);
        
    }

    public void paint(Graphics g) {

        String fileName= "src\\database2.csv";
        File file= new File(fileName);

        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");

                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (List<String> line : lines){
            String[] i = line.toArray(new String[line.size()]);

            if (i[3].equals("square")) {
                g.setColor(MyColor.getColor(i[10]));
                g.fillRect(Integer.parseInt(i[1]), Integer.parseInt(i[2]), Integer.parseInt(i[4]), Integer.parseInt(i[4]));
            }
            else if (i[3].equals("circle")) {
                g.setColor(MyColor.getColor(i[10]));
                g.fillOval(Integer.parseInt(i[1]), Integer.parseInt(i[2]), Integer.parseInt(i[4])/2, Integer.parseInt(i[4])/2);
            }
            else if (i[3].equals("triangle")) {
                int x_pos = Integer.parseInt(i[1]);
                int y_pos = Integer.parseInt(i[2]);
                int[] x = {Integer.parseInt(i[4] + x_pos), Integer.parseInt(i[6] + x_pos), Integer.parseInt(i[8] + x_pos)};
                int[] y = {Integer.parseInt(i[5] + y_pos), Integer.parseInt(i[7] + y_pos), Integer.parseInt(i[9] + y_pos)};
                g.setColor(MyColor.getColor(i[10]));
                g.fillPolygon(x, y, 3);
            }
            else if (i[3].equals("rectangle")) {
                g.setColor(MyColor.getColor(i[10]));
                g.fillRect(Integer.parseInt(i[1]), Integer.parseInt(i[2]), Integer.parseInt(i[4]), Integer.parseInt(i[5]));
            }


        }

    }
}
