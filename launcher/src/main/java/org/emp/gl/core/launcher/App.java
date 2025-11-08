package org.emp.gl.core.launcher;


import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.Horloge ;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;
import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {

        testDuTimeService();
    
    }

    private static void testDuTimeService() {
         
         TimerService timer = new DummyTimeServiceImpl();
    
        Horloge h1 = new Horloge("Num 1", timer);
        Horloge h2 = new Horloge("Num 2", timer);
        Horloge h3 = new Horloge("Num3", timer);
        
        new CompteARebours("C1", 5, timer);

       

        for (int i = 1; i <= 10; i++)
        {
            int j=i+10;
          new CompteARebours("C" + i, j, timer);
        }
        SwingUtilities.invokeLater(() -> {
            HorlogeGUI gui = new HorlogeGUI(timer);
            gui.setTitle("Horloge Graphique - TP Observer");
            gui.setVisible(true);
        });
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}