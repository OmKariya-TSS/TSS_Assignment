package com.tss.ooad.guitarApp2;

import com.tss.ooad.guitarApp2.enums.Builder;
import com.tss.ooad.guitarApp2.enums.Style;
import com.tss.ooad.guitarApp2.enums.Type;
import com.tss.ooad.guitarApp2.enums.Wood;

public class MandolinSpec extends InstrumentSpec {
    private Style style; // e.g., A-style, F-style

    public MandolinSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood, Style style) {
        super(builder, model, type, backWood, topWood);
        this.style = style;
    }

    public Style getStyle() { return style; }

    @Override
    public boolean matches(InstrumentSpec otherSpec) {
        if (!super.matches(otherSpec)) return false;
        if (!(otherSpec instanceof MandolinSpec)) return false;
        return style == ((MandolinSpec) otherSpec).style;
    }
}
