/*
 * Name: Ryan Christopher
 * Pawprint: rdcb2f
 * Class: CS 3330 Object Oriented Programming
 * Final Project: Movie List
 */
package rdcb2fmovielists21;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 *
 * @author Ryan Christopher
 * 
 * References:
 *  1) This class is taken from MVC Lecture
 */
public abstract class AbstractModel {
    protected PropertyChangeSupport propertyChangeSupport;
    
    public AbstractModel() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
}
