package com.tss.Behavioral.Command.invoker;

import com.tss.Behavioral.Command.Command;

public class RemoteControl {

    private Command command;
    private Command lastCommand;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
        lastCommand = command;
    }

    public void pressUndo() {
        if(lastCommand != null) {
            lastCommand.undo();
        }
    }
}

