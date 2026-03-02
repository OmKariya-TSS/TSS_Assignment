package com.tss.FoodAppV3.command;

public interface Command {

    void execute();

    void undo();
}