package org.emp.gl.core.launcher;


import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.Horloge ;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

import org.emp.gl.Lookup;

import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {

       
            // 1. Création du TimerService
            TimerService ts1 = new DummyTimeServiceImpl();

    // 2. Enregistrement dans le Lookup
            Lookup.getInstance().subscribeService(TimerService.class, ts1);

    // 3. Création d'une Horloge (qui va chercher elle-même le TimerService)
            Horloge h = new Horloge("h1");
           // ts1 est perdu et remplace  par ts2
            TimerService ts2 = new DummyTimeServiceImpl();
            // ça sera pas crée a cause de singleton
            Lookup.getInstance().subscribeService(TimerService.class, ts2);
            Horloge h2= new Horloge("h2") ;
   
        } 

    private static void testDuTimeService() {
         
         TimerService timer = new DummyTimeServiceImpl();
    
        /*Horloge h1 = new Horloge("Num 1", timer);
        Horloge h2 = new Horloge("Num 2", timer);
        Horloge h3 = new Horloge("Num3", timer);*/
        
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