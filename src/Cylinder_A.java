
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cavaco
 */
public class Cylinder_A extends Cylinder{
    
    public Cylinder_A(String _name, Mechanism ___mech) {
        super(_name, ___mech);
    }
    @Override
    public void run(){
        while(keepWorking){
            try {
                Integer d = (Integer) mailbox.take();
                mechanism.cylinder_A_goto(d);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cylinder_A.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
