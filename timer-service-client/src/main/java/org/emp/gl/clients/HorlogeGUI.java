package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HorlogeGUI extends JFrame implements TimerChangeListener {
    private final JLabel timeLabel;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public HorlogeGUI(TimerService timerService) {
        setTitle("Horloge Graphique - TP Observer");
        setSize(320, 160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        timeLabel = new JLabel("Heure : --:--:--", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(timeLabel, BorderLayout.CENTER);

        // Inscription correcte via addTimeChangeListener
        timerService.addTimeChangeListener(this);

        // Affichage initial
        updateTime();
    }

    // Méthode EXACTE de TimerChangeListener (String, Object, Object)
    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            SwingUtilities.invokeLater(this::updateTime);
        }
    }

    private void updateTime() {
        timeLabel.setText("Heure : " + sdf.format(new Date()));
    }

    // Test (ou dans App.java)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TimerService timer = new org.emp.gl.time.service.impl.DummyTimeServiceImpl();
            new HorlogeGUI(timer).setVisible(true);
        });
    }
}