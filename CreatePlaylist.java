//** Laura Salivonaite
package coursework;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JOptionPane.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CreatePlaylist extends JFrame implements ActionListener {
    JTextField trackNo = new JTextField(2);
    TextArea information = new TextArea(10, 65);
    TextArea playlist = new TextArea(16, 65);
    ImageIcon a= new ImageIcon("buttons/addbutton.png");
    JButton add = new JButton("Add to PlayList",a);
    ImageIcon p= new ImageIcon("buttons/playbutton.png");
    JButton play = new JButton("Play PlayList",p);
    ImageIcon rr= new ImageIcon("buttons/resetbutton.png");
    JButton reset = new JButton("Reset",rr);
    ImageIcon s= new ImageIcon("buttons/savebutton.png");
    JButton save = new JButton("Save Playlist",s);
    ImageIcon u= new ImageIcon("buttons/uploadbutton.png");
    JButton upload = new JButton("Open Playlist",u);
    ImageIcon d= new ImageIcon("buttons/deletebutton.png");
    JButton deletet = new JButton("Delete Track",d);
    ImageIcon ex= new ImageIcon("buttons/exitbutton.png");
    JButton exit = new JButton("Exit",ex);
    
    private FileWriter outFile;
    private BufferedReader inFile;
    String song; 

    private ArrayList<String> myList; 

    
    public CreatePlaylist() {
        setLayout(new BorderLayout());
        setSize(700, 600);
        setLocationRelativeTo(null);
        
        setTitle("Create Play List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
        
        JPanel top = new JPanel();
        top.add(new JLabel("Enter Track Number:"));
        top.add(trackNo);
        add("North", top);
        top.setBackground(Color.white);
        
        JPanel right= new JPanel();
        right.add(add);      
        right.add(play);
        right.add(reset); 
        right.add(save); 
        right.add(upload); 
        right.add(deletet);
        right.add(exit); 
        reset.addActionListener(this);
        add.addActionListener(this);
        play.addActionListener(this);
        exit.addActionListener(this);
        save.addActionListener(this);
        upload.addActionListener(this);
        deletet.addActionListener(this);
        right.setLayout(new GridLayout(0,1));
        add("East", right);
        right.setBackground(Color.white);
       
        
        JPanel middle = new JPanel();
        middle.add(new JLabel("Tack List:"));
        Color mm = new Color(201,234,255);
        information.setText(LibraryData.listAll());
        information.setBackground(Color.white);
        middle.add(information);       
        middle.add(new JLabel("Playlist:    "));
        middle.add(playlist);
        add("Center", middle);
        playlist.setBackground(Color.white);
        playlist.setEditable(false);
        middle.setBackground(mm);
        
        JPanel bottom= new JPanel();
        add("South", bottom);
        JLabel background=new JLabel(new ImageIcon("Untitled.png"));
	
	bottom.add(background);
        
        setResizable(false);
        setVisible(true);
        
        save.setHorizontalAlignment(SwingConstants.LEFT);
        upload.setHorizontalAlignment(SwingConstants.LEFT);
        play.setHorizontalAlignment(SwingConstants.LEFT);
        exit.setHorizontalAlignment(SwingConstants.LEFT);
        deletet.setHorizontalAlignment(SwingConstants.LEFT);
        reset.setHorizontalAlignment(SwingConstants.LEFT);
        
        myList = new ArrayList<String>();
    }

    public void actionPerformed(ActionEvent e) {
        
        information.setText(LibraryData.listAll());
        
       
        String key = trackNo.getText();
        String name = LibraryData.getName(key);
        String artist = LibraryData.getArtist(key);
        int rating = LibraryData.getRating(key);
        int playCount = LibraryData.getPlayCount(key);
  
        if (e.getSource() == reset){
        playlist.setText("");
        trackNo.setText("");
          
        }  
        if (e.getSource() == add){
        if (name == null) {
            showMessageDialog(null, "No such track number");
        } 
        else{                
             song = "Artist: " + name + "\n" + "Song Name: " + artist + "\n" + "Rating: " + rating + "\n" + "Play Count: " + playCount ;
             myList.add(key);
             playlist.append(song);
             playlist.append("\n" + " "); 
             playlist.append("\n" + " ");
             playlist.append("\n");
        } }
        if (e.getSource() == play){
            try {
            for(String k: myList){
            Clip clip = AudioSystem.getClip();
            File file = new File("music/05.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip.open(ais);
            clip.start();
            playlist.setText("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
            for( String k : myList){
            LibraryData.incrementPlayCount(k);
            playlist.setText("");
            }     
            for(String k: myList){
            playlist.append("Artist: " + LibraryData.getName(k)); 
            playlist.append("\n" + "Song Name: " + LibraryData.getArtist(k));
            playlist.append("\n" + "Rating: " + LibraryData.getRating(k));
            playlist.append("\n" + "Play Count: " + LibraryData.getPlayCount(k));
            playlist.append("\n" + " ");
            playlist.append("\n");
            }       
        }
        if(e.getSource()== exit){
            System.exit(0);
        }
        if(e.getSource()== save){
             try{
                 String pname = JOptionPane.showInputDialog("Enter Playlist name");
                 outFile = new FileWriter(pname + ".txt");
             
                 outFile.write(playlist.getText());
                 
                  showMessageDialog(null, "Playlist has been created successfuly.");
                 outFile.close(); 
             }
             catch(Exception ee)
             {
                 
             }
        }
        if(e.getSource()== upload){
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(this);
            File file = chooser.getSelectedFile();
            try {
                inFile = new BufferedReader(new FileReader(file));
                playlist.setText(""); // clear the input area
                String line;
                while ((line = inFile.readLine()) != null) {
                    playlist.append(line + "\n");
                }
                inFile.close();
        }
      catch(Exception ee){
      }

        }
        if(e.getSource()== deletet){
            
        
    
}
      
    }
}