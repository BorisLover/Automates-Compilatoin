/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestautomates;
//import java.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 *
 * @author KENFACK TAGUEZEM BORIS
 */
public class StringOpperation {
   public boolean opperator(char a){
       if(a=='+'||a=='|'||a=='.'||a=='*')
           return true;
       else return false;
   }
    public int priorite(char a){
        if(a=='+'||a=='|')
            return 1;
        else if(a=='.')
            return 2;
        else if(a=='*')
            return 3;
        else return 0;
   }
   public Stack listToStackEtasQc(List<Etats> l){
       Stack res=new Stack();
       for (Etats h : l) {
            res.add(h.qc+"");
       }
       System.out.println(res);
       return res;
   }
    public Stack listToStack(List l){
       Stack res=new Stack();
       for (Object h : l) {
            res.add(h);
       }
       System.out.println(res);
       return res;
   }
    public String listToString(List l){
       String res="";
       for (Object h : l) {
            res=res+h;
       }
       System.out.println(res);
       return res;
   }
   public List stringToList(String string){
       List res=new ArrayList();
       int i=0;
       while(i<string.length()){
           res.add(string.charAt(i));
           i++;
       }
       return res;
   }
   public List getSymbols(String word){
        List symbol=new ArrayList();
        int i=0;
        while(i<word.length()){
            char ch=word.charAt(i);
            if(!opperator(ch) && ch!=')' && ch!='(')
                if(!symbol.contains(ch) && ch!='$'){
                    symbol.add(ch);
                }
            i++;
        }
        return symbol;
    }
   public String traitString(String chaine){
       if(!chaine.isEmpty()){
           String err="erreur";
       String result="";
       int j=1;
       int n=chaine.length();
       Stack chain=new Stack();
       char ch=chaine.charAt(0);
       if(opperator(ch))
           return err;
       else{
           chain.add(ch);
           while(j<n){
               ch=chaine.charAt(j);
               //tete de pile n'est pas opperateur  et  charactere entrant pas opperateur
                if(!opperator((char)chain.peek()) && !opperator(ch) && (char)chain.peek()=='(')
                    chain.add(ch);
                
                else if(!opperator((char)chain.peek()) && !opperator(ch) && (char)chain.peek()==')' && ch!=')'){
                    chain.add('.');
                    chain.add(ch);
                }
                else if(!opperator((char)chain.peek()) && !opperator(ch) && (char)chain.peek()==')' && ch==')')
                    chain.add(ch);
                
                else if(!opperator((char)chain.peek()) && !opperator(ch) &&  ch==')' && (char)chain.peek()!='(' && (char)chain.peek()!=')')
                    chain.add(ch);
                else if(!opperator((char)chain.peek()) && !opperator(ch) && ch!=')' && (char)chain.peek()!='(' && (char)chain.peek()!=')'){
                    chain.add('.');
                    chain.add(ch);
                }
                
                //tete de pile n'est pas un opperateur  et  charactere entrant est opperateur 
               else if(!opperator((char)chain.peek()) && opperator(ch) && (char)chain.peek()=='(' )
                    return err;
               else if(opperator(ch) && !opperator((char)chain.peek())&& (char)chain.peek()!='(')
                   chain.add(ch);
               
               //tete de pile opperateur  et character entrant opperateur
               else if(opperator(ch) && opperator((char)chain.peek()) && (char)chain.peek()!='*')
                   return err;
               else if(opperator(ch)&& opperator((char)chain.peek()) && (char)chain.peek()=='*' )
                   chain.add(ch);
               
                // tete de pile opperateur charactere entrant pas opperateur
               else if(opperator((char)chain.peek()) && !opperator(ch) &&  (char)chain.peek()!='*')
                   chain.add(ch);
               else if(opperator((char)chain.peek()) && !opperator(ch) &&  (char)chain.peek()=='*'){
                   chain.add('.');
                   chain.add(ch);
               }
               j++;
           }
       }
       while(chain.size()>0){
            result=result+chain.firstElement();
            chain.remove(0);
       }
        return result;
       }
       return "";
   }
   public String postfixe(String chaine){
       int n=chaine.length();
       int i=0;
       List post;
       post=new ArrayList();
       Stack opp=new Stack();
       while(i<n){
           char ch=chaine.charAt(i);
           if(!opperator(ch)&& ch!='('&&ch!=')')
               post.add(ch);
           else if(ch=='(')
               opp.add(ch);
           else if(ch==')'){
               while(!opp.isEmpty()&& (char)opp.peek()!='(')
                   post.add(opp.pop());
               if((char)opp.peek()=='(')
                   opp.pop();
                   }
           else if(opperator(ch))
               if(opp.empty())
                   opp.add(ch);
               else if(priorite(ch)>=priorite((char)opp.peek()))
                   opp.add(ch);
               else{
                   while(!opp.empty() && priorite((char)opp.peek()) > priorite(ch)){
                       post.add(opp.pop());
                    }
                    opp.add(ch);
               }
           i++;
       }
       while(!opp.empty())
           post.add(opp.pop());
       return listToString(post);
   }
}
