package com.tss.Behavioral.Observer.notifiers;

public class Whatsapp implements INotifier {
    @Override
    public void sendAlert(String msg) {
        System.out.println(msg+"whatsapp");
    }
}
