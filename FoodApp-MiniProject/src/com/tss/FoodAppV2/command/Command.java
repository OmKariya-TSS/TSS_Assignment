package com.tss.FoodAppV2.command;

public interface Command {

    void execute();

    void undo();
}