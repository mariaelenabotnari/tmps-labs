package edu.utm.tmps.Lab3.domain.command;

public interface ICommand {
    boolean wasExecuted();
    void execute();
    void undo();
    String getDescription();
}

