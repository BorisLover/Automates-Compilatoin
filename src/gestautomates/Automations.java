/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestautomates;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author KENFACK TAGUEZEM BORIS
 */
public class Automations {
    String e_init="init";
    String e_fin="final";
    String e_nill="nill";
    char epsilon='$';
    List<Etats> autmat;
    
    public List<Etats> getAutmat(List<Etats> autmat){
        return autmat;
    }
    public void setAutmat(List<Etats> autmat){
        this.autmat=autmat;
    }
    StringOpperation post=new StringOpperation();
    CharEtEtat etas=new CharEtEtat();
    public Etats getEtatInit(List<Etats> a){
        for (Etats etats : a) {
            if(etats.typ.equals(e_init) || etats.qc==0){
            return etats;
            }
        }
        return (new Etats());
    }
    public Etats getEtatFin(List<Etats> a){
        for (Etats etats : a) {
            if(etats.typ.equals(e_fin)){
            return etats;
            }
        }
        return new Etats();
    }
    public List setEtat(List<Etats> a,int b){
        Etats etI=getEtatInit(a);
        Etats etF=getEtatFin(a);
        etI.qc=(etI.qc)+b;
        etF.qc=(etF.qc)+b;
        List res=new ArrayList();
        res.add(etI);
        for (Etats etats : a) {
            if(!etats.typ.equals(e_fin) && !etats.typ.equals(e_init)){
                res.add(etats);
            }
        }
        res.add(etF);
        return res;
    }
    public List setEtatF(List<Etats> a,int b){
        Etats etF=getEtatFin(a);
        etF.qc=(etF.qc)+b;
        List res=new ArrayList();
        for (Etats etats : a) {
            if(!etats.typ.equals(e_fin)){
                res.add(etats);
            }
        }
        res.add(etF);
        return res;
    }
    public boolean checkInTabSuiv(List<CharEtEtat>l,int a){
        for(CharEtEtat et: l){
            if(et.qs==a)
                return true;
        }
        return false;
    }
    
    //
    public boolean checkInTabSuivChar(List<CharEtEtat>l,char a){
        for(CharEtEtat et: l){
            if(et.car==a)
                return true;
        }
        return false;
    }
    
    public CharEtEtat getCharEtEtatFromTabSuiv(List<CharEtEtat>l,char a){
        for(CharEtEtat et: l){
            if(et.car==a)
                return et;
        }
        return null;
    }
    
    //
    public boolean checkInQi(List l, int a){
        for(Object n:l){
            int num=(int)n;
            if(num==a)
                return true;
        }
        return false;
    }
    
    public boolean checkInAutmate(List<Etats>l,int a){
        for(Etats et: l){
            if(et.qc==a)
                return true;
        }
        return false;
    }
    
    public void affichMap(List<CharEtEtat> p){
        p.forEach((CharEtEtat h)->{
            System.out.println("caractere : "+h.car+" etats suivant : "+h.qs);           
        });
    }
    public CharEtEtat trans(char ch,Etats etat){
        for(Object et: etat.tabSuiv){
           CharEtEtat etatSuiv=(CharEtEtat )et;
           if(etatSuiv.car==ch)
               return etatSuiv;
        }
        return (new CharEtEtat());
    }
    public void affichList(List p){
        p.forEach((h)->{
            System.out.println("qi- : "+h);          
        });
    }
    
    //
    public List copyListToList(List listToCopy,List resultList){
        //List a=new ArrayList();
        for(Object o:listToCopy){
            resultList.add(o);
        }
        return resultList;
    }
    
/* Copie une liste dans une autre . Elle remplace uniquement l'élement en position "pos" par le contenu de la liste a copier */
    public List copyListToListAtPos(List listToCopy,List resultList,int pos){
        //List a=new ArrayList();
        List part1=new ArrayList();
        List part2=new ArrayList();
        int i=0;
        while(i<pos && i<resultList.size()){
                part1.add(resultList.get(i));
            i++;
        }
        i++;
        while(i<resultList.size()){
                part2.add(resultList.get(i));
            i++;
        }
        resultList=new ArrayList();
        resultList=copyListToList(part1, resultList);
        resultList=copyListToList(listToCopy, resultList);
        resultList=copyListToList(part2, resultList);
        return resultList;
    }
    public boolean belongToAlphabet(List l,String word){
        int i=0;
        while(i<word.length()){
            char ch=word.charAt(i);
            if(!l.contains(ch) && ch!=epsilon)
                return false;
            i++;
        }
        return true;
    }
    /*public List correct(List<Etats> a){
        Etats etF=getEtatFin(a);
        Etats etI=getEtatInit(a);
        List res=new ArrayList();
        for(Etats et:a){
            if(checkInTabSuiv(etI.tabSuiv,et.qc)){
               // System.out.println(et.qc+"*******1");
                et.qi=new ArrayList();
                et.qi.add(etI.qc);
                //System.out.println(et.qc+"*******2");
            }
            if(checkInQi(etF.qi,et.qc)){
                //System.out.println(et.qc+"*******3");
                //et.tabSuiv.add(new CharEtEtat(etF.qc));
                for(Object o:et.tabSuiv){
                    CharEtEtat t=(CharEtEtat)o;
                    if(t.qs==etF.qc){
                        et.tabSuiv=new ArrayList();
                        et.tabSuiv.add(new CharEtEtat(t.car,etF.qc));
                        break;
                    }
                }
                //System.out.println(et.qc+"*******4");
            }
            res.add(et);
        }
        return res;
    }*/
    public Stack copyListToStack(List l,Stack s){
        for(Object o:l){
            s.add(o);
        }
        return s;
    }
    public void affichListList(List<Etats> a){
       a.forEach((Etats g)->{
                        if(g.typ.equalsIgnoreCase(e_fin))
                            System.out.println("Etats final ------------------------------ "+g.qc);
                        if(g.typ.equalsIgnoreCase(e_init))
                            System.out.println("Etats initial ------------------------------ "+g.qc);
                       System.out.println("Etats qc : "+g.qc);
                        affichList(g.qi);
                        affichMap(g.tabSuiv);
                   });
    }
    public ArrayList modifiList(List<CharEtEtat> l,int b){
        ArrayList<CharEtEtat>newqi=new ArrayList();
        l.forEach((CharEtEtat qis)->{
                    qis.qs=qis.qs+b;
                    newqi.add(qis);
                });
                //l.clear();
        return newqi;
    }
    public List renumber(List<Etats> a,int b){
        ArrayList res=new ArrayList();
        Etats etF=getEtatFin(a);
        Etats etI=getEtatInit(a);
        res.add(etI);
        ArrayList newqi=new ArrayList();
        ArrayList newqi1=new ArrayList();
        for(Etats etats :a) {
            if(!e_init.equals(etats.typ) && !e_fin.equals(etats.typ)){
                
                for(Object qis :etats.tabSuiv){
                    CharEtEtat g=(CharEtEtat)qis;
                    g.qs=(g.qs)+b;
                    newqi1.add(g);
                }
                etats.tabSuiv=newqi1;
                newqi1=new ArrayList();
                for(Object qis:etats.qi){
                    newqi.add((int)qis+b);
                }
                etats.qi=newqi;
                newqi=new ArrayList();
                
               // System.out.println("*******");
                if(checkInTabSuiv(etI.tabSuiv,etats.qc)){
                   // System.out.println(etats.qc+"*******1");
                    etats.qi=new ArrayList();
                    etats.qi.add((etI.qc)+b);
                    //System.out.println(etats.qc+"*******2");
                }
                if(checkInQi(etF.qi,etats.qc)){
                    //System.out.println(etats.qc+"*******3");
                    //et.tabSuiv.add(new CharEtEtat(etF.qc));
                    for(Object o:etats.tabSuiv){
                        CharEtEtat t=(CharEtEtat)o;
                            //System.out.println(etats.qc+"*******9");
                        if(t.qs==etF.qc){
                            etats.tabSuiv=new ArrayList();
                            etats.tabSuiv.add(new CharEtEtat(t.car,(etF.qc)+b));
                            break;
                        }
                    }
                    //System.out.println(etats.qc+"*******4");
                }
                etats.qc=etats.qc+b;
                res.add(etats);
            }
            
        }
        res.add(etF);
        return res;
    }
    
    //Renumerotation avec la sum
    public List renumberPlus(List<Etats> a,List<Etats>a2, int b,Etats E1, Etats E2){
        List<Etats> aut1=renumber(a,b);
        aut1=setEtat(aut1,b);
        //System.out.println("===========aut1 =========="+aut1);
        //System.out.println("===========aut1 ==========");
        //affichListList(aut1);
        ArrayList l1 =new ArrayList();
        ArrayList l2 =new ArrayList();
        ArrayList newqi=new ArrayList();
        ArrayList newqi1=new ArrayList();
        int c=getEtatFin(aut1).qc+1;
        List<Etats> aut2=renumber(a2,c);
        //affichListList(aut2);
        aut2=setEtat(aut2,c);
        //System.out.println("===========aut2 ==========");
        //affichListList(aut2);
        Etats etI=getEtatInit(aut2);
        Etats etF=getEtatFin(aut2);
        //System.out.println("===========aut2 =========="+aut2);
        E2.qc=getEtatFin(aut2).qc+1;
        E1.qc=0;
        E1.tabSuiv=new ArrayList();
        E2.qi=new ArrayList();
        int tes=0;
        for (Etats etats1 : aut1) {
            if(etats1.typ.equals(e_init)){
                etats1.qi=new ArrayList();
                etats1.qi.add(E1.qc);
                for(Object qis : etats1.tabSuiv){
                    CharEtEtat g;
                    g=(CharEtEtat)qis;
                    g.qs=g.qs+b;
                    l1.add(g);
                }
                etats1.tabSuiv=l1;
                etats1.typ=e_nill;
                E1.tabSuiv.add(new CharEtEtat(epsilon,etats1.qc));
                tes++;
            }
            else if(etats1.typ.equals(e_fin)){
                etats1.tabSuiv=new ArrayList();
                etats1.tabSuiv.add(new CharEtEtat(epsilon,E2.qc));
                etats1.typ=e_nill;
                etats1.qi.forEach((qis)->{
                    newqi1.add(((int)qis)+b);
                });
                etats1.qi=newqi1;
                E2.qi.add(etats1.qc);
                tes++;
            }
            if(tes==2)
                break;
        }
        tes=0;
        
        for (Etats etats : aut2) {
            if(etats.typ.equals(e_init)){
                etats.typ=e_nill;
                etats.qi=new ArrayList();
                etats.qi.add(E1.qc);
                etats.tabSuiv.forEach((qis)->{
                    CharEtEtat g=(CharEtEtat)qis;
                    g.qs=g.qs+c;
                    l2.add(g);
                });
                etats.tabSuiv=l2;
                E1.tabSuiv.add(new CharEtEtat(epsilon,etats.qc));
                tes++;
            }
            else if(etats.typ.equals(e_fin)){
                etats.tabSuiv=new ArrayList();
                etats.tabSuiv.add(new CharEtEtat(epsilon,E2.qc));
                 etats.qi.forEach((qis)->{
                    newqi.add(((int)qis)+c);
                });
                etats.qi=newqi;
                E2.qi.add(etats.qc);
                E2.typ=e_fin;
                etats.typ=e_nill;
                System.out.println("aut25 entrrrrrrrrrrrrr "+((etF.qc)-1));
                tes++;
            }
            else if(!etats.typ.equals(e_fin)&&!etats.typ.equals(e_init)&&checkInTabSuiv(etI.tabSuiv,etats.qc)&&checkInTabSuiv(etI.tabSuiv,etF.qc)){
                etats.qi.add((etF.qc)-1);
                System.out.println("entrrrrrrrrrrrrr "+((etF.qc)-1));
            }
            if(tes>=2)
                break;
        }
        
        aut1=copyListToList(aut2,aut1);
        aut1.add(E2);
        aut1.add(0, E1);
        //affichListList(aut1);
        return aut1;
    }
    
//Renumerotation avec L'etoile (*)
    public List renumberStar(List<Etats> a,int b,Etats E1, Etats E2){
        List<Etats> aut=renumber(a,b);
         aut=setEtat(aut,b);
         Etats ini=getEtatInit(aut);
         Etats fin=getEtatFin(aut);
        //System.out.println("+++++++++++++++");
        //affichListList(aut);
        //System.out.println("+++++++++++++++");
        ArrayList newqi=new ArrayList();
        //ArrayList newqi1=new ArrayList();
        ArrayList l1 =new ArrayList();
        //ArrayList l2 =new ArrayList();
        int c=getEtatFin(aut).qc;
        int d=getEtatInit(aut).qc;
        int tes=0;
        for (Etats etats : aut) {
            if(etats.typ.equals(e_init)){      
                etats.qi=new ArrayList();
                etats.qi.add((d-b));
                etats.qi.add(c);
                for(Object qis:etats.tabSuiv){
                    CharEtEtat g=(CharEtEtat)qis;
                    g.qs=(g.qs)+b;
                    l1.add(g);
                }
                etats.tabSuiv=l1;
                l1=new ArrayList();
                E1.qc=(d-b);
                E1.tabSuiv=new ArrayList();
                E1.tabSuiv.add(new CharEtEtat(epsilon,d));
                E1.tabSuiv.add(new CharEtEtat(epsilon,(c+b)));
                E1.typ=e_init;
                // System.out.println("+++++++++++++++123");
                // affichMap(E1.tabSuiv);
                //  System.out.println("+++++++++++++++123");
                //etats.tabSuiv=modifiList(etats.tabSuiv, b);
                etats.typ=e_nill;
                
                tes++;
            }
            else if(etats.typ.equals(e_fin)){
                etats.tabSuiv=new ArrayList();
                /*CharEtEtat et2=new CharEtEtat();
                et2.qs=(b+c);
                et2.car=epsilon;*/
                etats.tabSuiv.add(new CharEtEtat(epsilon,(c+b)));
                /*CharEtEtat et3=new CharEtEtat();
                et3.qs=d;
                et3.car=epsilon;*/
                etats.tabSuiv.add(new CharEtEtat(epsilon,d));
                for(Object qis:etats.qi){
                    newqi.add((int)qis+b);
                }
                etats.qi=newqi;
                newqi=new ArrayList();
                E2.qc=(c+b);
                E2.qi=new ArrayList();
                E2.qi.add(c);
                E2.qi.add((d-b));
                E2.typ=e_fin;
                etats.typ=e_nill;
                //System.out.println("etassss "+etats.qc);
                tes++;
            }
            else if(!etats.typ.equals(e_fin)&&!etats.typ.equals(e_init)&&checkInTabSuiv(ini.tabSuiv,etats.qc)&&checkInTabSuiv(ini.tabSuiv,fin.qc)){
                etats.qi.add((fin.qc)-b);
                tes++;
            }
            if(tes==3)
                break;
        }
        etas.car=' ';
        etas.qs=(c+b);
        E2.tabSuiv=new ArrayList();
        E2.tabSuiv.add(new CharEtEtat(' ',-1));
        //E2.tabSuiv.add(etas);
        //System.out.println("****** a "+aut+" et E1 "+E1+" E2 "+E2);
        aut.add(E2);
        aut.add(0, E1);
       // System.out.println("****** a "+aut);
       // affichListList(aut);
        return aut;
    } 
    
    
 //Renumerotation avec concatenation
 public List renumberConcat(List<Etats> a,List<Etats>a2){
     //System.out.println("bmhhgcg gcgfxfdzfd****************************************1");
      int c=getEtatFin(a).qc;
      List<Etats> aut2=renumber(a2,c);
      aut2=setEtat(aut2,c);
      Etats etI=getEtatInit(aut2);
      Etats etF=getEtatFin(aut2);
      //affichListList(aut2);
     
      ArrayList l1 =new ArrayList();
      Etats E=getEtatInit(a2);
      for(Object qis:E.tabSuiv){
                    CharEtEtat g;
                    g=(CharEtEtat)qis;
                    g.qs=g.qs+c;
                    l1.add(g);
                }
                E.tabSuiv=l1;
                l1 =new ArrayList();
                 //System.out.println(getEtatInit(aut2).qc+"  bmhhgcg gcgfxfdzfd****************************************155   = "+getEtatFin(aut2).qc);
      ArrayList newqi=new ArrayList();
      int tes=0;
        for (Etats etats : a) {
            if(etats.typ.equals(e_fin)){
                etats.tabSuiv=E.tabSuiv;
                etats.typ=e_nill;
                tes++;
            }
            if(tes>0)
                break;
        }
        tes=0;
        //
        for (Etats etats : aut2) {
            if(etats.typ.equals(e_fin)){
                for(Object qis:etats.qi){
                    newqi.add((int)qis+c);
                }
                etats.qi=newqi;
                newqi=new ArrayList();
                etats.tabSuiv=new ArrayList();
                etats.tabSuiv.add(new CharEtEtat(' ',-1));
                //et=etats;
                //System.out.println("bmhhgcg gcgfxfdzfd**************************************** qc : "+etats.qc);
                //affichList(etats.qi);
                //System.out.println("mapppppppppp");
                //affichMap(etats.tabSuiv);
                tes++;
            }
            else if(!etats.typ.equals(e_fin)&&!etats.typ.equals(e_init)&&checkInTabSuiv(etI.tabSuiv,etats.qc)&&checkInTabSuiv(etI.tabSuiv,etF.qc)){
                etats.qi.add((etF.qc)-c);
                tes++;
            }
            if(tes==2)
                break;
        }
        aut2.remove(getEtatInit(aut2));
        a=copyListToList(aut2, a);
      return a;
 }
    
    
    
    public List creerAuto(char a){
        //ici "typ" vaut soit e_init soit  e_nill soit e_fin
        List etatsAuto = new ArrayList();
        if(a=='~'){
            etas.qs=0;
            etas.car=' ';
            Etats E1=new Etats(0,0,etas,e_init);
            etatsAuto.add(E1);
        }
        else{
            CharEtEtat et=new CharEtEtat();
            Etats E1=new Etats(0,0,new CharEtEtat(a,1),e_init);
            Etats E2=new Etats(0,1,new CharEtEtat(' ',-1),e_fin);
            //affichMap(E1.tabSuiv);
            etatsAuto.add(E1);
            etatsAuto.add(E2);
        }
        return etatsAuto;
    }
            
    public List calcAutoBinair(List a,List b,char op){
        List etatsAuto = new ArrayList();
        if(post.opperator(op)){
            if(op=='.'){
                etatsAuto=renumberConcat(a,b);
            }
            else if(op=='+' || op=='|') {
                 Etats E1=new Etats(0,0,new CharEtEtat('$',1),e_init);
                 Etats E2=new Etats(0,1,new CharEtEtat(' ',-1),e_fin);
                 etatsAuto=renumberPlus(a,b,1,E1,E2);
            }
        }
        
        //affichListList(etatsAuto);
        return etatsAuto;
    }
    public List calcAutoUnair(List a,char op){
        List etatsAuto = new ArrayList();
        if(post.opperator(op)){
           if(op=='*'){
                Etats E1=new Etats(0,0,new CharEtEtat(epsilon,1),e_init);
                Etats E2=new Etats(0,1,new CharEtEtat(' ',1),e_fin);
                etatsAuto=renumberStar(a,1,E1,E2);
            }
        }
        return etatsAuto;
    }
    
    public List creatAutomation(String exp){
       int i=0;
       Stack<List> aut=new Stack<List>();
       List a;
       List a1;
       List a2;
       List res;
       while(exp.length()>i){
           char ch=exp.charAt(i);
           if(!post.opperator(ch))
                aut.add(creerAuto(ch));
           else if(post.opperator(ch)){
               if(ch=='*' && aut.size()>0){
                   a1=aut.pop();
                   a=calcAutoUnair(a1,'*');
                   aut.add(a);
               }
               else if(ch=='+' || ch=='|'){
                   //System.out.println("sum,,,,,,,,,,,,,,,,,,,,,,,,,,,");
                       if(aut.size()>1){
                            a1=aut.pop();
                            a2=aut.pop();
                            a=calcAutoBinair(a2,a1,'+');
                            
        //affichListList(a);
                   //System.out.println("sum,,,,,,,,,,,,,,,,,,,,,,,,,,,"+a);
                            
                            aut.add(a);
               }}
               else if((ch=='.') && aut.size()>1){
                   a1=aut.pop();
                   a2=aut.pop();
                   a=calcAutoBinair(a2,a1,'.');
                   //affichListList(a);
                   aut.add(a);
               }
           }
           i++;
       }
       res=aut.pop();
       //affichListList(res);
        setAutmat(res);
       return res;
    }
    
 
    

}

