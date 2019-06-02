/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestautomates;

/**
 *
 * @author KENFACK TAGUEZEM BORIS
 */
public class CharEtEtat{
    public char car;
    public int qs;

    public CharEtEtat(char d, int c) {
        car=d;qs=c;
    }

    public CharEtEtat() {
        car=' ';qs=-1;
    }
    public char getCar(){
        return car;
    }
    public int getEtat(){
        return qs;
    }
    public void setCar(char c){
        car=c;
    }
    public void setEtat(int e){
        qs=e;
    }
}