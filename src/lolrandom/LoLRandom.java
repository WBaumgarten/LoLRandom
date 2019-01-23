package lolrandom;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class LoLRandom {
    
    public static LinkedList<Champion> champions = new LinkedList<Champion>();
    public static BufferedImage missingImage = null;
    public static BufferedImage smallMissingImage = null;
    public static JDialog loadingPopup;

    public static void main(String[] args) {
        JLabel loadingText = new JLabel("LOADING...", SwingConstants.CENTER);
        loadingText.setFont(new Font("Tahoma", Font.BOLD, 36));
        loadingText.setBorder(BorderFactory.createLineBorder(Color.black));
        loadingPopup = new JDialog();
        loadingPopup.setSize(260, 90);
        loadingPopup.setLocationRelativeTo(null);
        loadingPopup.setTitle("LoLRandom");
        loadingPopup.setUndecorated(true);
        loadingPopup.add(loadingText);
        loadingPopup.setVisible(true);
        initChamps();
        new GUI().setVisible(true);       
    }
    
    public static void initChamps(){
    Scanner scFile = null;
    boolean ad, ap, melee, ranged, top, jungle, mid, supp, adc, owned;
        try {
            scFile = new Scanner(new File("src\\lolrandom\\res\\championData.txt"));
            scFile.nextLine();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "The \"championData.txt\" file was not found.");
            System.err.println("The \"championData.txt\" file was not found.");
            System.exit(1);
        }
        
        if(scFile != null){
            try {
                smallMissingImage = ImageIO.read(new File("src\\lolrandom\\res\\SmallImg\\missingImage.jpg"));
                missingImage = ImageIO.read(new File("src\\lolrandom\\res\\missingImage.jpg"));
            } catch (Exception e) {
                System.err.println("The missingImage file was not found!");
            }  
            String imgName = "";
            while(scFile.hasNext()){
                String[] temp = scFile.nextLine().split(", ");
                BufferedImage pic;
                BufferedImage smallPic;
                if(temp.length == 0){
                    System.err.println("Input error!");
                    System.exit(1);
                }else if(temp.length != 11){
                    System.err.println("Input error at champion: " + temp[0] + ".");
                    System.exit(1);
                }
                
                imgName = temp[0].replaceAll(" ", "");
                imgName = imgName.replaceAll("\\.", "");
                imgName = imgName.replaceAll("\'", "");
                try {                   
                    pic = ImageIO.read(new File("src\\lolrandom\\res\\LargeImg\\"+imgName+"_0.jpg"));                    
                } catch (IOException ex) {
                    System.err.println("The large image \""+imgName+"_0.jpg\" was not found.");
                    pic = missingImage;                    
                }
                
                try {
                    smallPic = ImageIO.read(new File("src\\lolrandom\\res\\SmallImg\\"+imgName+".jpg"));
                } catch (Exception e) {
                    System.err.println("The small image \""+imgName+".jpg\" was not found.");
                    smallPic = smallMissingImage;
                }
                        
                
                ad = temp[1].equalsIgnoreCase("true");
                ap = temp[2].equalsIgnoreCase("true");
                melee = temp[3].equalsIgnoreCase("true");
                ranged = temp[4].equalsIgnoreCase("true");
                top = temp[5].equalsIgnoreCase("true");
                jungle = temp[6].equalsIgnoreCase("true");
                mid = temp[7].equalsIgnoreCase("true");
                supp = temp[8].equalsIgnoreCase("true");
                adc = temp[9].equalsIgnoreCase("true");
                owned = temp[10].equalsIgnoreCase("true");
                
                champions.add(new Champion(temp[0], pic, smallPic, ad, ap, melee, ranged, top, jungle, mid, supp, adc, owned));
          }
        }
    } 
}