package Startup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class tileClick implements ActionListener {

 //   public tileClick(String inpText, ImageIcon inpIcon,
 //                    String inpDesc, Integer inpMnnemonic){

    public tileClick(houseTile inphouseTile){
        System.out.println(inphouseTile.getTileName());

    //    super(inpText, inpIcon);
    //    putValue(SHORT_DESCRIPTION, inpDesc);
    //    putValue(MNEMONIC_KEY, inpMnnemonic)
    }

    public void actionPerformed(ActionEvent e) {

        System.out.println("a Tile was clicked");
   }

}
