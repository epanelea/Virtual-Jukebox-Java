//** Laura Salivonaite
package coursework;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Jukebox extends JFrame
                  implements ActionListener {
        ImageIcon ch= new ImageIcon("jubuttons/ch.png");
        JButton check = new JButton("Check Library",ch);
        ImageIcon pl= new ImageIcon("jubuttons/pl.png");
        JButton playlist = new JButton("Create Playlist",pl);
        ImageIcon up= new ImageIcon("jubuttons/up.png");
        JButton update = new JButton("Update Library",up);
        ImageIcon qt= new ImageIcon("jubuttons/qt.png");
        JButton quit = new JButton("Quit",qt);

    public static void main(String[] args) {
        new Jukebox();
    }
    
    public Jukebox() {
        setLayout(new BorderLayout());
        setSize(680, 438);
        setTitle("Jukebox");

        // close application only by clicking the quit button
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        JLabel background=new JLabel(new ImageIcon("backgroundmain.png"));
	add(background);   
	background.setLayout(new FlowLayout());
        getContentPane().setBackground(Color.gray);
        
        
        JPanel bottom = new JPanel();
        Color el = new Color(201,234,255);
        bottom.setBackground(el);
        bottom.add(check); check.addActionListener(this);
        bottom.add(playlist); playlist.addActionListener(this);
        bottom.add(update); update.addActionListener(this);
        bottom.add(quit); quit.addActionListener(this);
        add("South", bottom);

        setResizable(false);
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
       try{ 
           if (e.getSource() == check) {
            new CheckLibrary();
        } 
        else if (e.getSource() == playlist){
            new CreatePlaylist();
        }
        else if (e.getSource()== update){
            new UpdateLibrary();
        }
        else if (e.getSource() == quit) {
            LibraryData.close();
            System.exit(0);
        }
      }catch(Exception ee){
    }
  }
}