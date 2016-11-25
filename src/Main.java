/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cavaco
 */
public class Main {
    
    /**
     *
     * @param args
     */
    public static void main(String args[]){
      Hardware h = new Hardware();
      h.create_di(0);
      h.create_di(1);
      h.create_di(2);
    Mechanism m = new Mechanism(h);
    Cylinder_A a = new Cylinder_A("cil a", m);
    a.mailbox.add(0);
    a.mailbox.add(1);
    }
}
