package com.tss.Behavioral.Command.test;

import com.tss.Behavioral.Command.Command;
import com.tss.Behavioral.Command.Light;
import com.tss.Behavioral.Command.LightOffCommand;
import com.tss.Behavioral.Command.LightOnCommand;
import com.tss.Behavioral.Command.invoker.RemoteControl;

public class Main {
    public static void main(String[] args) {

        Light light = new Light();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(lightOn);
        remote.pressButton();

        remote.pressUndo();

        remote.setCommand(lightOff);
        remote.pressButton();

        remote.pressUndo();
    }
}
