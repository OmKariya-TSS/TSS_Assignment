package com.tss.FoodAppV2.command;

import java.util.Stack;

public class CommandInvoker {

    private Stack<Command> history = new Stack<>();

    public void executeCommand(Command cmd) {
        cmd.execute();
        history.push(cmd);
    }

    public void undoLast() {
        if (!history.isEmpty()) {
            Command last = history.pop();
            last.undo();
        } else {
            System.out.println("⚠ Nothing to undo!");
        }
    }
}