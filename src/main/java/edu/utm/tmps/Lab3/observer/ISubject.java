package edu.utm.tmps.Lab3.observer;

import java.util.Observer;

public interface ISubject {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers(String message);
}
