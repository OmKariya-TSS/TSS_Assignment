package com.tss.FoodApp.command;

public interface Command {

    void execute();

    void undo();
}