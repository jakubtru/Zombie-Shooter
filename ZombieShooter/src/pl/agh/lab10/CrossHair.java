package pl.agh.lab10;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CrossHair implements MouseMotionListener, MouseListener {

    // Store a reference to the parent DrawPanel
    DrawPanel parent;

    // Constructor that sets the parent DrawPanel
    CrossHair(DrawPanel parent) {
        this.parent = parent;
    }

    // Create an ArrayList of CrossHairListeners to notify when shots are fired
    ArrayList<CrossHairListener> listeners = new ArrayList<CrossHairListener>();

    // Add a CrossHairListener to the listeners ArrayList
    void addCrossHairListener(CrossHairListener e) {
        listeners.add(e);
    }

    // Notify all listeners that shots have been fired
    void notifyListeners() {
        for (var e : listeners)
            e.onShotsFired(x, y);
    }

    // Store the x and y coordinates of the crosshair, and whether it is activated
    int x;
    int y;
    boolean activated = false;

    // Method to draw the crosshair
    void draw(Graphics g) {
        if (activated) g.setColor(Color.RED);
        else g.setColor(Color.WHITE);
        g.drawLine(x, y - 20, x, y + 20);
        g.drawLine(x - 20, y, x + 20, y);
        g.drawOval(x - 15, y - 15, 30, 30);
    }

    // Implement MouseListener methods

    // When the mouse is clicked, do nothing
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    // When the mouse is pressed, set the x and y coordinates, activate the crosshair, repaint the parent DrawPanel,
    // start a timer to deactivate the crosshair after 300ms, and notify listeners that shots have been fired
    @Override
    public void mousePressed(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        activated = true;
        parent.repaint();
        Timer timer = new Timer("Timer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                activated = false;
                parent.repaint();
            }
        }, 300);
        parent.onShotsFired(x, y);
    }

    // When the mouse is released, do nothing
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    // When the mouse enters the component, do nothing
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    // When the mouse exits the component, do nothing
    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Implement MouseMotionListener methods

    // When the mouse is dragged, do nothing
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    // When the mouse is moved, update the x and y coordinates and repaint the parent DrawPanel
    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
        parent.repaint();
    }
}