//** Laura Salivonaite
package coursework;

import java.awt.*;
import java.awt.event.*;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class UpdateLibrary extends JFrame implements ActionListener {
    JTextField trackNo = new JTextField(2);
    TextArea information = new TextArea(10, 50);
    ImageIcon l= new ImageIcon("buttons/listAllbutton.png");
    JButton list = new JButton("List All Tracks",l);
    ImageIcon c= new ImageIcon("buttons/checkbutton.png");
    JButton check = new JButton("Check Track",c);
    ImageIcon r= new ImageIcon("buttons/ratebutton.png");
    JButton rate = new JButton("Rate",r);
    ImageIcon ex= new ImageIcon("buttons/exitbutton.png");
    JButton exit = new JButton("Exit",ex);
    
    public UpdateLibrary() {
        setLayout(new BorderLayout());
        setSize(600, 350);
        setTitle("Update Library");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel top = new JPanel();
        top.add(new JLabel("Enter Track Number:"));
        top.add(trackNo);
        
        
        add("North", top);
        top.setBackground(Color.white);
        
        JPanel middle = new JPanel();
        information.setText(LibraryData.listAll());
        middle.add(information);
        add("Center", middle);
        Color mm = new Color(201,234,255);
        middle.setBackground(mm);
        
        JPanel right= new JPanel();
        right.add(check);
        right.add(list);
        right.add(rate);
        right.add(exit); 
        add("East", right);
        right.setLayout(new GridLayout(0,1));
        right.setBackground(Color.white);
        
        list.addActionListener(this);
        check.addActionListener(this);
        rate.addActionListener(this);
        exit.addActionListener(this);
        
        JPanel bottom= new JPanel();
        JLabel background=new JLabel(new ImageIcon("Untitled3.png"));
        bottom.add(background);
        add("South",bottom);

        list.setHorizontalAlignment(SwingConstants.LEFT);
        check.setHorizontalAlignment(SwingConstants.LEFT);
        rate.setHorizontalAlignment(SwingConstants.LEFT);
        exit.setHorizontalAlignment(SwingConstants.LEFT);
        
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == list) {
            information.setText(LibraryData.listAll());
        } else {
            String key = trackNo.getText();
            String name = LibraryData.getName(key);
            if (name == null) {
                information.setText("No such track number");
            } else { 
                information.setText(name + " - " + LibraryData.getArtist(key));
                information.append("\nRating: " + stars(LibraryData.getRating(key)));
                information.append("\nPlay count: " + LibraryData.getPlayCount(key));
            }}
         if (e.getSource() == rate){
             String key = trackNo.getText();
             String name = LibraryData.getName(key);
             LibraryData.getRating(key);
             String rating = JOptionPane.showInputDialog("Enter a new rating");
             int n = Integer.parseInt(rating);
              if ( n<0 || n>6){
                  showMessageDialog(null,"Please enter rating between 0 and 6");

             } else {
                LibraryData.setRating(key, n);
                information.setText(name + " - " + LibraryData.getArtist(key));
                information.append("\nRating: " + stars(LibraryData.getRating(key)));
                information.append("\nPlay count: " + LibraryData.getPlayCount(key));
              }
         }
         if(e.getSource()== exit){
            System.exit(0);
        }
    }     
    private String stars(int rating) {
        String stars = "";
        for (int i = 0; i < rating; ++i) {
            stars += "*";
        }
        return stars;
    }
}
