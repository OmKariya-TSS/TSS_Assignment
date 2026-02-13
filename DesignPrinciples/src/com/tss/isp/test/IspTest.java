package com.tss.isp.test;

import com.tss.isp.model.Human;
import com.tss.isp.model.Robot;

public class IspTest {
    public static void main(String[] args) {
        Human om = new Human();
        om.eat();
        om.work();
        om.sleep();
        Robot chitti = new Robot();
        chitti.charge();
        chitti.work();
    }
}
