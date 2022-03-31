package Startup;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class tileClick extends AbstractAction{

 //   public tileClick(String inpText, ImageIcon inpIcon,
 //                    String inpDesc, Integer inpMnnemonic){

    public tileClick(String inpText){

    //    super(inpText, inpIcon);
    //    putValue(SHORT_DESCRIPTION, inpDesc);
    //    putValue(MNEMONIC_KEY, inpMnnemonic)
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("a Tile was clicked");
    }

}
