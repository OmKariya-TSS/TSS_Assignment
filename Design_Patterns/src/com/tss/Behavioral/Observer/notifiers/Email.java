package com.tss.Behavioral.Observer.notifiers;

public class Email implements INotifier {
    @Override
    public void sendAlert(String msg) {
        System.out.println(msg+"email");
    }
}
