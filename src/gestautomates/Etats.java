/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestautomates;

//import java.awt.List;

//import java.awt.List;
import java.util.ArrayList;
import java.util.List;

//<Character,Character>
/**
 *
 * @author KENFACK TAGUEZEM BORIS
 */

public class Etats {
    ArrayList qi;
    int qc;
    ArrayList tabSuiv;
    String typ;

    public Etats(){
        qc=-1;
        qi=new ArrayList();
        tabSuiv=new ArrayList();
        typ="nill";
    }
    public Etats(int a,int b,CharEtEtat d,String e){
        qi=new ArrayList();
        qi.add(a);
        qc=b;
        tabSuiv=new ArrayList();
        tabSuiv.add(d);
        typ=e;
    }
    public List gettabSuiv(){return tabSuiv;}
    public int getqc(){return qc;}
    public String gettyp(){return typ;}
    public void settabSuiv(CharEtEtat chE){tabSuiv.add(chE);}
    public void setqc(int qc){this.qc=qc;}
    public void settyp(String typ){this.typ=typ;}
}
