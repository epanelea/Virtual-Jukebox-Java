//** Laura Salivonaite
package coursework;
import javax.swing.JLabel;

import java.awt.*;                                 
import java.awt.event.*;                           
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

                                                   
public class CheckLibrary extends JFrame           
                  implements ActionListener {   
    
    String[] artistNames = {
         " ",
         "AC/DC",
         "Three Days Grace",
         "Linkin Park",
         "Bon Jovi",
         "My Chemical Romance",
         "Skillet",
         "Sum41",
         "Guns n Roses",
         "Fall Out Boy",
         "Whitesnake",
         "HIM",
         "Airbourne"
         };
    
    JComboBox drop = new JComboBox(artistNames);
    JTextField trackNo = new JTextField(2);        
    TextArea information = new TextArea(6, 50);
    ImageIcon l= new ImageIcon("buttons/listAllbutton.png");
    JButton list1 = new JButton("List All Tracks",l);
    ImageIcon c= new ImageIcon("buttons/checkbutton.png");
    JButton check = new JButton("Check Track",c);
    JButton checkt= new JButton("Check Artist", c);
    ImageIcon ex= new ImageIcon("buttons/exitbutton.png");
    JButton exit = new JButton("Exit",ex);
    TextArea songs= new TextArea(6,50);

    public CheckLibrary() { 
 
        setLayout(new BorderLayout());             
        setSize(400, 500);
               
        setTitle("Check Library");                 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel top = new JPanel();
        top.setBackground(Color.white);
        top.add(new JLabel("Enter Track Number:"));
        top.add(trackNo);                          
        top.add(check);
        top.add(exit);
        top.add(list1);                                  
                                                   
        add("North", top);
        
        JPanel middle = new JPanel();
        Color mm = new Color(201,234,255);
        middle.setBackground(mm);
        information.setText(LibraryData.listAll());
        middle.add(information);
        middle.add(new JLabel("Select an Artist"));
        middle.add(drop);
        middle.add(checkt); 
        middle.add(songs);
      
        add("Center", middle);    
        
        JPanel bottom= new JPanel();
        JLabel background=new JLabel(new ImageIcon("Untitled2.png"));
	
	bottom.add(background);

        add("South",bottom);
        exit.addActionListener(this);
        drop.addActionListener(this);
        list1.addActionListener(this);              
        checkt.addActionListener(this);                                          
        check.addActionListener(this);  
        
        setResizable(false);                       
        setVisible(true);   
        
    }

    public void actionPerformed(ActionEvent e) {   
        try{
            if (e.getSource() == list1) {               
            information.setText(LibraryData.listAll());   
                                          
        }
            if(e.getSource()== exit){
            System.exit(0);
        }
        if (e.getSource()== check){                                                    
            String key = trackNo.getText();            
            String name = LibraryData.getName(key);    
            if (name == null) {
                information.setText("No such track number"); 
                                                             
            } else {                                         
               
                information.setText(name + " - " + LibraryData.getArtist(key)); 
                information.append("\nRating: " + stars(LibraryData.getRating(key)));
                information.append("\nPlay count: " + LibraryData.getPlayCount(key));
        
       }
      }
        if(e.getSource()== checkt){
           
            Object selected = drop.getSelectedItem();
            
                if(selected.toString().equals("AC/DC"))
                    songs.setText("Back in Black" + "\n"+"Highway to Hell"+ "\n"+ "TNT");        
                else if (selected.toString().equals("Airbourne"))
                    songs.setText("Fat City");
                else if (selected.toString().equals("Bon Jovi"))
                    songs.setText("Born to be my baby" + "\n"+"Always");
                else if (selected.toString().equals("Fall Out Boy"))
                    songs.setText("Thanks for the memories" + "\n"+"Save rock n roll");
                else if (selected.toString().equals("Guns n Roses"))
                    songs.setText("Paradise City");
                else if (selected.toString().equals("HIM"))
                    songs.setText("Gone with the sin");
                else if (selected.toString().equals("Linkin Park"))
                    songs.setText("Crawling" + "\n"+"From the inside");
                else if (selected.toString().equals("My Chemical Romance"))
                    songs.setText("Na Na Na");
                else if (selected.toString().equals("Skillet"))
                    songs.setText("Monster");
                else if (selected.toString().equals("Sum41"))
                    songs.setText("Pieces" + "\n"+"Fat lip");
                else if (selected.toString().equals("Three Days Grace"))
                    songs.setText("Animal I have become" + "\n"+"Pain" +"\n" + "Riot"+ "\n"+ "Never Too Late");
                else if (selected.toString().equals("Whitesnake"))
                    songs.setText("Here I go again");
        }
     }catch(Exception ee){
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