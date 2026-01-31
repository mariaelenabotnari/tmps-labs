package edu.utm.tmps.Lab3.domain.observer;

public interface ISubject {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers(String message);
}
