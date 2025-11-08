package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class CompteARebours implements TimerChangeListener {

    private int valeur;
    private TimerService timerService;
    private String name;

    public CompteARebours(String name, int valeur, TimerService timerService) {
        this.name = name;
        this.valeur = valeur;
        this.timerService = timerService;

        timerService.addTimeChangeListener(this);

        System.out.println("CompteARebours " + name + " initialisé à " + valeur);
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {

        if (SECONDE_PROP.equals(prop)) {

            if (valeur > 0) {
                valeur--;
                System.out.println(name + " -> " + valeur);
            }

            
            if (valeur == 0) {
                System.out.println(name + " terminé");
                timerService.removeTimeChangeListener(this);
            }
        }
    }
}
