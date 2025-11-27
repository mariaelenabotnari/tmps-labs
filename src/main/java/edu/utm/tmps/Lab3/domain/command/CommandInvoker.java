package edu.utm.tmps.Lab3.domain.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {

    private final Map<String, List<ICommand>> historyPerUser = new HashMap<>();

    public void executeCommand(String userId, ICommand command) {
        command.execute();

        if (command.wasExecuted()) {
            historyPerUser
                    .computeIfAbsent(userId, k -> new ArrayList<>())
                    .add(command);
        }
    }

    public void undoLast(String userId) {
        List<ICommand> history = historyPerUser.get(userId);

        if (history == null || history.isEmpty()) {
            System.out.println("No recent activity to undo.");
            return;
        }

        ICommand last = history.removeLast();
        last.undo();
    }

    public void showHistory(String userId) {
        List<ICommand> history = historyPerUser.get(userId);

        if (history == null || history.isEmpty()) {
            System.out.println("No recent activity to show.");
            return;
        }

        System.out.println("\nRecent Liking Activity: ");
        for (ICommand c : history) {
            System.out.println("- " + c.getDescription());
        }
    }
}
