/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestautomates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Interface.GestAuto;

/**
 *
 * @author KENFACK TAGUEZEM BORIS
 */
public class GestAutomates {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        
       /* WordAgnolege wodAg=new WordAgnolege();
       String expres="a*.b.c";
       StringOpperation post=new StringOpperation();
       DetemisedAtaumates detAut=new DetemisedAtaumates();
       String s=post.traitString(expres);
       String exp=post.postfixe(s);
       Automations A=new Automations();
       List list1=A.creatAutomation(exp);
       List symbol=post.getSymbols(expres);
       String tes=post.listToString(symbol);
        System.out.println("aut****************************************************");
       //A.affichListList(list1);
       A.affichListList(list1);
       list1=detAut.convertToDetAutmat(list1,symbol);
        System.out.println("det****************************************************");
       A.affichListList(list1);
       MinimisedAutomate minAu=new MinimisedAutomate();
        System.out.println("avant complet**************************************");
       List l1=minAu.completAutomate((ArrayList<Etats>)list1, symbol);
       A.affichListList(l1);
       System.out.println("apres complet***************************************");
       List l2=minAu.Sciender(l1,(ArrayList<Character>)symbol);
       System.out.println("apres sciender");
       List l3=minAu.converToMinDetemisedAut(l1,l2, (ArrayList<Character>)symbol);
       System.out.println("min****************************************************");
       A.affichListList(l3);
        */
        /*
       String word="aaaaaaaaaaaabcccccccccccccccccc";
       HashMap recon=wodAg.reconnaissence(list1,word,symbol);
       if(!recon.isEmpty()){
           wodAg.afficheTrai(recon);
           System.out.println(" Le mot "+"'"+word+"' est un mot du langauge "+s);
       }
       else
           System.out.println(" Le mot "+"'"+word+"' n'est un mot du langauge "+s);*/
      //GestAuto ges=new GestAuto();
        
        
        
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestAuto().setVisible(true);
            }
        });
    }
    
}
