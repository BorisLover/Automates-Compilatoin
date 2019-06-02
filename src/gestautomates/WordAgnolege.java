/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestautomates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author KENFACK TAGUEZEM BORIS
 */
class Traite{
    char car;
    int number;

}
public class WordAgnolege {
    HashMap <Character,Integer>donnees=new HashMap();
    DetemisedAtaumates det=new DetemisedAtaumates();
    char eps='$';
    public void afficheTrai(HashMap m){
        System.out.println("Le Mot contient les characteres comme suite");
        m.forEach((key, number)->{
            System.out.print("Les character : '"+key+"' apparait : "+number+" fois ");
        });
    }
        
    public String afficheTrait(HashMap m){
        StringOpperation strOpp=new StringOpperation();
        List ans=new ArrayList();
        m.forEach((key, number)->{
           ans.add(("Less character : '"+key+"' apparait : "+number+" fois \n"));
        });
        return strOpp.listToString(ans);
    }
    public HashMap comptNumOfEachChar(String word,int n){
        HashMap donnees=new HashMap();
        Traite t;
        int i=0;
        while(i<n){
            char ch=word.charAt(i);
            if(donnees.containsKey(ch)){
                t=(Traite)donnees.get(ch);
                donnees.put(t.car, t.number++);
                t=new Traite();
            }
            else{
               donnees.put(ch, 0); 
            }
            i++;
        }
        return donnees;
    }
    /*return une list de 2 element donc le premier est un table de hachage contenant le traitement des donnée 
    et le deuxiem est le chemain de recognaissence*/
    public  List reconnaissence(List <Etats>autmat,String word,List alphabet){
        List chemain=new ArrayList();
        List res=new ArrayList();
        donnees=new HashMap();
        int i=0;
        chemain.add(0);
        Automations A=new Automations();
        Etats init=A.getEtatInit(autmat);
        if(!A.belongToAlphabet(alphabet,word)){
            System.out.print("not belong++++++++++++++++++++++++++++++++");
            res=new ArrayList();
            res.add(false);
            return res;
        }
        while(i<word.length()){
            char ch=word.charAt(i);
            if(ch!=eps){
                CharEtEtat ce=A.trans(ch,init);
                init=det.rechEtats(autmat,ce.qs);
                if(init.qc==-1){
                    System.out.println("qc==-1++++++++++++++++++++++++++++++++++++++");
                    res=new ArrayList();
                    res.add(false);
                    return res;
                }
                if(donnees.containsKey(ch)){
                    int number=(int)donnees.get(ch)+1;
                    donnees.put(ch, number);
                    chemain.add(ce.qs);
                }
                else{
                    donnees.put(ch, 1);
                    chemain.add(ce.qs);
                }
                i++;
            }else
                i++;
        }
        if(init.typ.equals("final")){
            res.add(true);
            res.add(donnees);
            res.add(chemain);
            System.out.println("reconuuuuuuuuuu");
            return res;
        }else{
            res.add(false);
            return res;
        }
    }
}
