package com.tss.Behavioral.Observer.notifiers;

public class SMS implements INotifier {
    String msg;

    @Override
    public void sendAlert(String msg) {
        System.out.println(msg+"sms");
    }
}
