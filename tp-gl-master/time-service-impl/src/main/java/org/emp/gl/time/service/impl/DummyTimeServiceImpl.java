/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.time.service.impl;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 *
 * @author tina
 */
public class DummyTimeServiceImpl
        implements TimerService {

    int dixiemeDeSeconde;
    int minutes;
    int secondes;
    int heures;
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Constructeur du DummyTimeServiceImpl: ici, 
     * nous nous avons utilisé un objet Timer, qui permet de
     * réaliser des tics à chaque N millisecondes
     */
    public DummyTimeServiceImpl() {
        setTimeValues();
        // initialize schedular
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
             @Override
            public void run() {
                timeChanged();
            }
        };
        timer.scheduleAtFixedRate(task, 100, 100);
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();

        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100000000);
    }

   


    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {
        pcs.addPropertyChangeListener(pl);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {
        pcs.removePropertyChangeListener(pl);
    }

    private void timeChanged() {
        setTimeValues();
    }




    public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
        if (dixiemeDeSeconde == newDixiemeDeSeconde)
            return;

        int old = dixiemeDeSeconde;
        dixiemeDeSeconde = newDixiemeDeSeconde;
        pcs.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP, old, dixiemeDeSeconde);


    }
    //On a pas besoin de ça anymore  avec PropertyChangeSupport

   /*  private void dixiemeDeSecondesChanged(int oldValue, int newValue) {
       for (TimerChangeListener l : listeners) {
           l.propertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,
                   oldValue, dixiemeDeSeconde);
       }
    }
        private void secondesChanged(int oldValue, int secondes) {

       for (TimerChangeListener l : listeners) {
           l.propertyChange(TimerChangeListener.SECONDE_PROP,
                   oldValue, secondes);
       }
    }
       
    private void minutesChanged(int oldValue, int minutes) {
       for (TimerChangeListener l : listeners) {
           l.propertyChange(TimerChangeListener.MINUTE_PROP,
                   oldValue, secondes);
       }
    }
         private void heuresChanged(int oldValue, int heures) {
       for (TimerChangeListener l : listeners) {
           l.propertyChange(TimerChangeListener.HEURE_PROP,
                   oldValue, secondes);
       }
    }

 */

    public void setSecondes(int newSecondes) {
        if (secondes == newSecondes)
            return;

        int old = secondes;
        secondes = newSecondes;

        pcs.firePropertyChange(TimerChangeListener.SECONDE_PROP, old, secondes);    }

   


    public void setMinutes(int newMinutes) {
        if (minutes == newMinutes)
            return;

        int old = minutes;
        minutes = newMinutes;

        pcs.firePropertyChange(TimerChangeListener.MINUTE_PROP, old, minutes);
    }


    public void setHeures(int newHeures) {
        if (heures == newHeures)
            return;

        int old = heures;
        heures = newHeures;

        pcs.firePropertyChange(TimerChangeListener.HEURE_PROP, old, heures);
    }

  

    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}