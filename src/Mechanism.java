/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cavaco
 */

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Mechanism {
    
Hardware hard;

public Mechanism(Hardware h){
	hard = h;
}
public void conveyorMove(){
     int x = hard.safe_read_port(2);
     x = hard.setBitValue(x, 2 , true);
     hard.safe_write_port(2,x);
}

public void conveyorStop(){
    int x = hard.safe_read_port(2);
    x = hard.setBitValue(x, 2 , false);
    hard.safe_write_port(2,x);    
};

public int cylind_A_getPosition() {
    int x = hard.safe_read_port(0);
    if(hard.getBitValue(x, 6))
	  return 0;
    if(hard.getBitValue(x, 5))
	  return 1;
    else
        return -1;
}

public int cylind_B_getPosition() {
    int x = hard.safe_read_port(0);
    if(!hard.getBitValue(x, 4))
	  return 0;
    if(!hard.getBitValue(x, 3))
	  return 1;
    else
        return -1;
}

public int cylind_C_getPosition() {
    
    int x = hard.safe_read_port(0);
    if(!hard.getBitValue(x, 2))
	  return 0;
    if(!hard.getBitValue(x, 1))
	  return 1;
    else
        return -1;
}

public boolean cylind_A_isAtPosition (int pos) { 
     return (pos==cylind_A_getPosition());  
}

public boolean cylind_B_isAtPosition (int pos) { 
     return (pos==cylind_B_getPosition());  
}

public boolean cylind_C_isAtPosition (int pos) { 
     return (pos==cylind_C_getPosition());  
}

public void cylinder_A_movLeft() {

    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 0, true);
        x = hard.setBitValue(x, 1, false);
        hard.safe_write_port(2, x);
    
};
public void cylinder_A_movRight() {
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 1, true);
        x = hard.setBitValue(x, 0, false);
        hard.safe_write_port(2, x);
    
};
public void cylinder_A_stop() {
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 1, false);
        x = hard.setBitValue(x, 0, false);
        hard.safe_write_port(2, x);    
    
};

public void cylinder_B_movUp(){
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 4, false);
        x = hard.setBitValue(x, 3, true);
        hard.safe_write_port(2, x);

};
public void cylinder_B_movDown() {
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 3, false);
        x = hard.setBitValue(x, 4, true);
        hard.safe_write_port(2, x);
    
};
public void cylinder_B_stop(){

    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 3, false);
        x = hard.setBitValue(x, 4, false);
        hard.safe_write_port(2, x);
    
};

public void cylinder_C_movUp(){
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 6, false);
        x = hard.setBitValue(x, 5, true);
        hard.safe_write_port(2, x);

};

public void cylinder_C_movDown() {
    
    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 5, false);
        x = hard.setBitValue(x, 6, true);
        hard.safe_write_port(2, x);
    
};
public void cylinder_C_stop(){

    int x = hard.safe_read_port(2);
        x = hard.setBitValue(x, 5, false);
        x = hard.setBitValue(x, 6, false);
        hard.safe_write_port(2, x);
    
};

public void cylinder_A_goto(int desired_pos)  //0, 1
  {
    //move only if it is callibrated
    int actual = this.cylind_A_getPosition();
    if(this.cylind_A_getPosition()!=-1){
      if(actual< desired_pos)
           this.cylinder_A_movRight();
      else if(actual > desired_pos)
           this.cylinder_A_movLeft();
      while(this.cylind_A_getPosition()!=desired_pos){
        try {        
              Thread.sleep(1);
        } catch (InterruptedException ex) {
               Logger.getLogger(Mechanism.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      this.cylinder_A_stop(); 
    }    
  }

public void cylinder_B_goto(int desired_pos){

    //move only if it is callibrated
    int actual = this.cylind_B_getPosition();
    if(this.cylind_B_getPosition()!=-1){
      if(actual< desired_pos)
           this.cylinder_B_movDown();
      else if(actual > desired_pos)
           this.cylinder_B_movUp();
      while(this.cylind_B_getPosition()!=desired_pos){
        try {        
              Thread.sleep(1);
        } catch (InterruptedException ex) {
               Logger.getLogger(Mechanism.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      this.cylinder_B_stop();      
    }    
    
};

public void cylinder_C_goto(int desired_pos){

    //move only if it is callibrated
    int actual = this.cylind_C_getPosition();
    if(this.cylind_C_getPosition()!=-1){
      if(actual< desired_pos)
           this.cylinder_C_movDown();
      else if(actual > desired_pos)
           this.cylinder_C_movUp();
      while(this.cylind_C_getPosition()!=desired_pos){
        try {        
              Thread.sleep(1);
        } catch (InterruptedException ex) {
               Logger.getLogger(Mechanism.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      this.cylinder_C_stop();      
    }    
    
};

public int partIdentify() 
  {
    boolean p00=false; 
    //not intended to return -1;    
    do{  
      // read port values  to a current buffer;
      // read current bit value
      // compare with value from previous variable 
      // detect the event (see next slide)
      // account the number of patchs in bits P1.5 and P1.6
     //set previous variable equal to current variable
      // until bit P0.0 goes to 1
      p00=true;
      
      try {Thread.sleep(1);} catch (InterruptedException ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
    }while(p00!=true);
        return 0;
}

}
