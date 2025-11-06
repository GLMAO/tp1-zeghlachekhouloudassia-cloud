package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge ;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        testDuTimeService();
       // TimeService timer= new DummyTimerService();
       // Horloge h1 =new Horloge (timer);
       // timer.start(); 
    }

    private static void testDuTimeService() {
         
         TimerService timer = new DummyTimeServiceImpl();
    
        Horloge h1 = new Horloge("Num 1", timer);
        Horloge h2 = new Horloge("Num 2", timer);
        Horloge h3 = new Horloge("Num3", timer);

        
    
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
