/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestautomates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Taguezem
 */

// ici -20 est consideré comme l'état Puis
public class MinimisedAutomate {
    String e_init="init";
    String e_fin="final";
    String e_nill="nill";
    int puis=-20;
    char epsilon='$';
    Automations aut=new Automations();
    DetemisedAtaumates detAu=new DetemisedAtaumates();
    public List completAutomate(List<Etats> au,List sym){
        List res=new ArrayList();
        Etats ets=new Etats();
        ets.setqc(puis);
        for(Etats et:au){
            for(Object o:sym){
                if(!aut.checkInTabSuivChar(et.tabSuiv, (char)o))
                    et.tabSuiv.add(new CharEtEtat((char)o,puis));
            }
            res.add(et);
        }
        for(Object o:sym){
            ets.tabSuiv.add(new CharEtEtat((char)o,puis));
        }
        res.add(ets);
        return res;
    }
    
    public CharEtEtat trans(Etats et,char car){
        for(Object ch:et.tabSuiv){
            CharEtEtat chE=(CharEtEtat)ch;
            if(chE.car==car)
                return chE;
        }
        return null;
    }
    
    public boolean containsAtLeastOne(List lToCheckIn,List lToCheckOut){
        int i=0;
        while(i<lToCheckOut.size()){
            if(lToCheckIn.contains(lToCheckOut.get(i)))
                return true;
            i++;
        }
        return false;
    }

    public List SetDivision(List iemDivision,Stack<Etats> elem,char car){
        List res=new ArrayList();
        HashMap div=new HashMap();
        while(!elem.isEmpty()){
            Etats et=elem.pop();
            System.out.println(et.qc+" ///////////////////////");
            int tes=0;
            int j=0;
            while(tes==0 && j<iemDivision.size()){
                ArrayList<Etats> lEtat = (ArrayList)iemDivision.get(j);
                CharEtEtat chE=aut.getCharEtEtatFromTabSuiv(et.tabSuiv, car);
                if(chE!=null){
                    if(aut.checkInAutmate(lEtat, chE.qs)){
                        ArrayList <Etats> e=new ArrayList();
                        if(div.containsKey(j)){
                            e=(ArrayList)div.get(j);
                            e.add(et);
                            div.put(j,e);
                        }else{
                            e.add(et);
                            div.put(j,e);
                        }
                        e=null;
                        tes++;
                    }
                }
                j++;
            }
            tes=0; 
        }
        System.out.println("yesssss");
        if(div.isEmpty())
            return null;
        else{
            div.forEach((k,v)->res.add(v));
            return res;
        }
    }

//
    public List calAScienderPourChar(List iemDivision, int i,char car){
        //List res=new ArrayList();
        System.out.println("calAScienderPourChar");
        Stack<Etats> newDivisionStack=new Stack();
        //List newDivision=new ArrayList();
        //List oldDivision=new ArrayList();
        ArrayList<Etats> lEtat = (ArrayList)iemDivision.get(i);
        for(Etats et:lEtat){
            //CharEtEtat chE=trans(et,car);
            System.out.println(trans(et,car)+" -----------------------------------"+trans(et,car).qs+" car "+trans(et,car).car);
            if(trans(et,car)!=null && !newDivisionStack.contains(et)){
                newDivisionStack.add(et);
                System.out.println(et);
            }
        }
         System.out.println("fin calAScienderPourChar");   
        return SetDivision(iemDivision,newDivisionStack,car);
    }
    
    //
    public List calAScienderPourAphabet(List iemDivision, int i,ArrayList<Character>symbols){
        List res=iemDivision;
        ArrayList<Etats> lEtat = (ArrayList)iemDivision.get(i);
        for(char sym:symbols){
            List tempo=calAScienderPourChar(res, i,(char)sym);
            if(!lEtat.equals(tempo))
                if(tempo!=null){
                    res=aut.copyListToListAtPos(tempo, res, i);
                }
        }System.out.println("fin calAScienderPour Alpha");
        return res;
    }
    
    //Sciende pour chaque division et par character
    public List calAScienderPourAut(List iemDivision,ArrayList<Character>symbols){
        List res=iemDivision;
        int tes=0;
        int i=0;
        while(tes==0){
            if(i==res.size())
                tes++;
            else{
                res=calAScienderPourAphabet(res,i,symbols);
                i++;
            }
        }//System.out.println("calAScienderPour aut : "+res);
        return res;
    }
    
    public boolean equalList(List l1,List l2){
        if(l1.size()==l2.size()){
            int i=0;
            while(i<l1.size()){
                if(!((ArrayList)l1.get(i)).containsAll(((ArrayList)l2.get(i))))
                    return false;
                i++;
                 System.out.println("eqqqqq 1 : "+l1.size());
            }
            System.out.println("ewww 1 : "+l1.size());
            return true;
        }
         System.out.println("eqqqqqqq : ");
        return false;
    }
    
    public void affich(List l){
        int i=0;
        while(i<l.size()){
            System.out.print("[");
            for(Object o:(ArrayList)l.get(i)){
                Etats et=(Etats)o;
                System.out.print(et.qc+",");
            }
            System.out.println("]");
            i++;
        }
    }
    //
    public List Sciender(List <Etats> autmate,ArrayList<Character>symbols){
        List iemDivision=new ArrayList();
        List setOfInit=new ArrayList();
        List setOfFinal=new ArrayList();
        for(Etats et:autmate){
            if(et.typ.equalsIgnoreCase(e_fin))
                setOfFinal.add(et);
            else
                setOfInit.add(et);
        }
        iemDivision.add(setOfInit);
        iemDivision.add(setOfFinal);
        List newDivision=calAScienderPourAut(iemDivision,symbols);
        System.out.println("sciende : "+iemDivision);
        System.out.println("sciende 1 : "+newDivision);
        while(!equalList(iemDivision, newDivision)){
            iemDivision=newDivision;
            newDivision=calAScienderPourAut(iemDivision,symbols);
            System.out.println("while aut : "+iemDivision);
            System.out.println("while new aut : "+newDivision);
        }System.out.println("calAScienderPour aut : "+iemDivision);
        
        affich(iemDivision);
        
        return iemDivision;
    }
    
    //
    public List converToMinDetemisedAut(List<Etats> auts, List iemDivision,ArrayList<Character>symbols){
        int i=0;
        ArrayList res=new ArrayList();
        ArrayList<Etats> etatInit=new ArrayList();
        ArrayList letats=new ArrayList();
        Etats etatIni=aut.getEtatInit(auts);
        List etatFins=detAu.getEtatFinal(auts);
        for(Object o:iemDivision){
            ArrayList<Etats> lEtat= (ArrayList<Etats>)o;
            if(lEtat.contains(etatIni))
                etatInit=lEtat;
            else
                letats.add(o);
        }
        ArrayList newDivision=new ArrayList();
        newDivision.add(etatInit);
        aut.copyListToList(letats, newDivision);
        while(i<newDivision.size()){
            List suivant=new ArrayList();
            List iemPartie=(ArrayList)newDivision.get(i);
            Etats etatsC=new Etats();
            etatsC.setqc(i);
            if(containsAtLeastOne((ArrayList<Etats>)iemPartie, (ArrayList<Etats>)etatFins))
                etatsC.settyp(e_fin);
            else if(((ArrayList<Etats>)iemPartie).contains(etatIni))
                etatsC.settyp(e_init);
            for(Object o:iemPartie){
                Etats et=(Etats)o;
                for(Object sui:et.tabSuiv){
                    int j=0;
                    CharEtEtat suiv=(CharEtEtat)sui;
                    while(j<newDivision.size()){
                         if(!suivant.contains(newDivision.get(j))){
                            if(aut.checkInAutmate((ArrayList<Etats>)newDivision.get(j), suiv.qs)){
                               CharEtEtat chE=new CharEtEtat(suiv.car,j);
                                if(!etatsC.tabSuiv.contains(chE)){
                                    etatsC.tabSuiv.add(chE);
                                    suivant.add(newDivision.get(j));
                                    System.out.println(newDivision.size()+"*********************etas : "+etatsC.qc+" suivant ; car : "+chE.car+" etat : "+chE.qs+" et j = "+j);
                                }
                            }
                         }
                        j++;
                    }
                }

            }
            res.add(etatsC);
            i++;
            etatsC=new Etats();
        }
        return res;
    }
}
