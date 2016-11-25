
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cavaco
 */
//if you see a line starting with the word “package”, as follows
//or a similar one, you should delete it and let netbeans perform refactoring.
public class Hardware
{//D:\STR\TRAB_2_STR\hardware\x64\Debug\hardware.dll
 private final java.util.concurrent.Semaphore sem = new java.util.concurrent.Semaphore(1);
 static
 {
 System.load("D:\\STR\\TRAB_2_STR\\hardware\\x64\\Debug\\hardware.dll");
 //c:\str\tttrab2\hardware\x64\Debug\hardware.dll
 }
public int safe_read_port(int port)
  {
    int x=-1;
    try {
      sem.acquire();
      x=read_port(port);
      sem.release();      
    } catch (InterruptedException ex) {Logger.getLogger(Hardware.class.getName()).log(Level.SEVERE, null, ex);
    }
    return x;
  }
  
  public void safe_write_port(int port, int value){
    try {
      sem.acquire();
      write_port(port,value);
      sem.release();
    } catch (InterruptedException ex) {
      Logger.getLogger(Hardware.class.getName()).log(Level.SEVERE, null, ex);
    }
  }



    
public int setBitValue(int var, int bit_n, boolean value) {
        int mask_on = 1<< bit_n;
        int mask_off = ~mask_on;
        if(value) 
                var = var | mask_on;
        else 
                var = var & mask_off;     
        return var;
    }

public boolean getBitValue(int var, int bit_n) {
         int result = var& (1<< bit_n) ;
         boolean bool_result = (result!=0);
         return bool_result;
    }
 native public void create_di(int port);
 native public void create_do(int port);
 native public void write_port(int port, int value);
 native public int read_port(int port);
}