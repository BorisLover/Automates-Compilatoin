/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestautomates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author KENFACK TAGUEZEM BORIS
 */




class ElemDtran{
    ArrayList A;
    boolean marqu;
    public ElemDtran(List A,boolean t){
        this.A=(ArrayList)A;
        marqu=t;
    }
    public ElemDtran(){
        this.A=new ArrayList();
        marqu=false;
    }
}
class CharEtEtatL{
    public char car;
    public List qs;

    public CharEtEtatL(char d, List c) {
        car=d;
        qs=c;
    }

    public CharEtEtatL() {
        car=' ';qs=new ArrayList();
    }
}

class DetEtat{
    ArrayList qc;
    ArrayList suiv;
    ArrayList qi;
    String typ;
    public DetEtat(List q,List s,String typ){
        qc=(ArrayList)q;
        suiv=(ArrayList)s;
        this.typ=typ;
    }
    public DetEtat(){
        qc=new ArrayList();
        qi=new ArrayList();
        suiv=new ArrayList();
    }
}
class EspTrans{
    ArrayList epsi;//le resultats apres calcul de epsilon-trasition exemple : eps-fermeture(tran(A,b))
    ArrayList tran;//la transition optenue par un ensemle et un character exemple :  tran(A,b)
    public EspTrans(List epsi,List tran){
        this.epsi=(ArrayList)epsi;
        this.tran=(ArrayList)tran;
    }
    public EspTrans(){
        epsi=new ArrayList();
        tran=new ArrayList();
    }
}
public class DetemisedAtaumates {
    public static char eps='$';
    String e_init="init";
    String e_fin="final";
    String e_nill="nill";
    Automations A=new Automations();
    List<Etats> autmat;
    
    public List<Etats> getAutmat(List<Etats> autmat){
        return autmat;
    }
    public void setAutmat(List<Etats> autmat){
        this.autmat=autmat;
    }
    public Etats rechEtats(List<Etats> l,int a){
        for(Etats e:l){
            if(e.qc==a){
                return e;
            }
        }
        return new Etats();
    }
    public int rechPosEtats(List<DetEtat> l,Object a){
        int res=0;
        for(DetEtat e:l){
            if(e.qc.equals(a)){
                return res;
            }
            res++;
        }
        return (-1);
    }
    public List getEtatFinal(List autmat){
        List res=new ArrayList();
        for (Iterator iterator = autmat.iterator(); iterator.hasNext();) {
            Etats next = (Etats) iterator.next();
            if(next.typ.equals(e_fin))
                res.add(next);
        }
        return res;
    }
    public int nonMarque(List<ElemDtran> A){
        for(ElemDtran e: A){
            if(!e.marqu)
                return A.indexOf(e);
        }
        return -1;
    }
    public List marque(List<ElemDtran> A,List e){
        List res=new ArrayList();
        for(ElemDtran e1: A){
            if(e1.A.equals(e))
                e1.marqu=true;
            res.add(e1);
        }
        return res;
    }
    public ElemDtran a_marquer(List<ElemDtran> A){
        for(ElemDtran e1: A){
            if(!e1.marqu)
                return e1;
        }
        return (new ElemDtran());
    }
    public boolean contain(List<ElemDtran> l,List A){
        for(ElemDtran o : l){
            if(o.A.equals(A))
                return true;
        }
        return false;
    }
    public boolean containEtats(List<CharEtEtat> l, CharEtEtat e){
       for(CharEtEtat o : l){
            if(o.car==e.car && o.qs==e.qs)
                return true;
        }
       return false;
    }
    /*public boolean containsEtatsqc(List<Etats> l, CharEtEtat e){
       for(CharEtEtat o : l){
            if(o.car==e.car && o.qs==e.qs)
                return true;
        }
       return false;
    }*/
    public EspTrans recEspTrans(List lEspTrans,List tran){
        for(Object es : lEspTrans){
            EspTrans espTrans=(EspTrans)es;
            if(espTrans.tran.equals(tran))
                return espTrans;
        }
        return (new EspTrans());
    }
    public void affichEpsFer(List l){
        System.out.print("A=[");
        for(Object ini:l){
            Etats et=(Etats)ini;
            System.out.print(et.qc+" , ");
        }
        System.out.println("]");
        
    }

    
    //calcul des epsillons transition
    public List epsTrans(Etats e,List<Etats> eps_ferm,List<Etats> autmat){
        List res=new ArrayList();
        //System.out.println("++++++++++++++++--------------------------------");
        //A.affichListList(autmat);
        autmat.forEach((Etats e1)->{
            //System.out.println("++++++++++++++++---+++ -------------------------***********************etats : "+e1.qc);
            //A.affichMap(e.tabSuiv);
             //System.out.println("++++++++++++++++---+++  qc : "+e1.qc);
            //A.affichList(e1.qi);
            //System.out.println("++++++++++++++++---+++  e2 qc : "+e2.car+" car "+e2.qs);
            if(containEtats(e.tabSuiv,new CharEtEtat('$',e1.qc)) && !res.contains(e1) && !eps_ferm.contains(e1)){
                //System.out.println("++++++++++++++++--------------------------------++++++++++++++++++-------- "+res);
                res.add(e1);
            }
            //System.out.println("after++++++++++++++++--- ");
        });
        return res;
    }
    
    public List trans(List<Etats> l,List<Etats> autmat,char a){
        List res=new ArrayList();
        //A.affichListList(l);
        l.forEach((e) -> {
            //System.out.println("t-----------");
            e.tabSuiv.forEach((c)->{
                CharEtEtat g;
                g=(CharEtEtat)c;
                //System.out.println(" g "+g.car+" e "+e.qc);
                if(g.car==a && !res.contains(rechEtats(autmat,g.qs))){
                    res.add(rechEtats(autmat,g.qs));
                    //System.out.println("t-----------****************************************");
                    //A.affichListList(res);
                    //System.out.println(" trans 444**********************"+res);
                }
            });
        });
       // System.out.println(" trans "+res);
        return res;
    }
    
    public List eps_fermeture(List<Etats> eps_ferm,List<Etats> autmat){
        List res=eps_ferm;
        Stack<Etats> p=new Stack();
        p=A.copyListToStack(res, p);
        while(!p.isEmpty()){
            Etats e=p.pop();
            List e1=epsTrans(e,res,autmat);
            //System.out.println("esp++++++++++++++++ "+e1);
            //A.affichListList(e1);
            if(!e1.isEmpty()){
                //System.out.println("emt++++++++++++++++ ");
                A.copyListToList(e1, res);
                //System.out.println("LL++++++++++++++++ ");
                A.copyListToStack(e1, p);
                //System.out.println("TS++++++++++++++++ ");
            }
            //System.out.println("++++++++++++++++/////////////////////////////");
        }
         //System.out.println("++++++++++++++++ /////////////////////////////+++++++++++++");
        return res;
    }
    
    public List DetermisedAutomate(List<Etats> autmat,List symbol){
        List res=new ArrayList();
        List in=new ArrayList();
        List lTrans=new ArrayList();
        Etats et1=A.getEtatInit(autmat);
        System.out.println(" et1 "+et1.qc+" et1.typ :"+et1.typ);
        in.add(et1);
        List A_init=eps_fermeture(in,autmat);
        affichEpsFer(A_init);
        List<ElemDtran> DEtatPre=new ArrayList();
        List<DetEtat> DTrans=new ArrayList();
        DEtatPre.add(new ElemDtran(A_init,false));
        
        while(nonMarque(DEtatPre)!=-1){
           // System.out.println("   DEtatsPre  : "+DEtatPre);
            ElemDtran elem=a_marquer(DEtatPre);
           // System.out.println("elem  : "+elem+" a marque ? : "+elem.marqu);
            DEtatPre=marque(DEtatPre, elem.A);
           // System.out.println("1   DEtatsPre  : "+DEtatPre);
            DetEtat f=new DetEtat();
            f.qc=(ArrayList)elem.A;
            if(f.qc.equals((ArrayList)DEtatPre.get(0).A))
                f.typ=e_init;
            if(elem.A.contains(A.getEtatFin(autmat)))
                f.typ=e_fin;
            for(Object e:symbol){
                List l2=trans(f.qc,autmat,(char)e);
                //System.out.println("5555555 l2  : "+l2+" par : "+e);
                if(!l2.isEmpty()){
                    EspTrans et=recEspTrans(lTrans,l2);
                    if(et.epsi.isEmpty() && et.tran.isEmpty()){
                        A_init=eps_fermeture(l2,autmat);
                        lTrans.add(new EspTrans(A_init,l2));
                        affichEpsFer(A_init);
                    }
                    else
                        A_init=et.epsi;
                    if(!A_init.isEmpty()){
                        if(!contain(DEtatPre,A_init)){
                            DEtatPre.add(new ElemDtran(A_init,false));
                            //System.out.println("don't containssssssssssssssssssssssssssssssss"+A_init);
                        }
                        f.suiv.add(new CharEtEtatL((char)e,A_init));
                    }
                }
            }
            DTrans.add(f);
            //System.out.println("1212121   DEtatsPre  : "+DEtatPre);
        }
        //System.out.println("dett---------------------------------------------------------------------------==  "+nonMarque(DEtatPre));
        res.add(DEtatPre);
        //System.out.println("dett---------------------------------------------------------------------------  "+DEtatPre);
        res.add(DTrans);
        //System.out.println("dett---------------------------------------------------------------------------  "+DTrans);
        return res;
    }
    
    public List convertToDetAutmat(List<Etats> autmats,List symbol){
        List res=new ArrayList();
        List autmat=DetermisedAutomate(autmats,symbol);
        //System.out.println("autttttttttttttt  first "+autmat.get(1));
        List Dtran=(ArrayList)autmat.get(1);
        Dtran.forEach((au) -> {
            DetEtat aut=(DetEtat)au;
            Etats e=new Etats();
            int pos=rechPosEtats(Dtran,aut.qc);
           //System.out.println("pos-----------"+pos+" aut "+aut.typ);
            if(pos!=-1)
                e.qc=pos;
            List<CharEtEtat> suivs=new ArrayList();
            aut.suiv.forEach((suivt)->{
                CharEtEtatL chl=(CharEtEtatL)suivt;
                int j=rechPosEtats(Dtran,chl.qs);
                //System.out.println("pos j-----------"+j+" aut "+chl+"  char : "+chl.car);
                //CharEtEtat ce=new CharEtEtat(chl.car,j);
                suivs.add(new CharEtEtat(chl.car,j));
            });
            A.copyListToList(suivs, e.tabSuiv);
            if(e_fin.equals(aut.typ))
                e.typ=e_fin;
            if(e_init.equals(aut.typ))
                e.typ=e_init;
            res.add(e);
        });
        //A.affichListList(res);
        setAutmat(res);
        return res;
    } 
}
