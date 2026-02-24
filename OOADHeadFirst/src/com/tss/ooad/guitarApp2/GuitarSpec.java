package com.tss.ooad.guitarApp2;

import com.tss.ooad.guitarApp2.enums.Builder;
import com.tss.ooad.guitarApp2.enums.Type;
import com.tss.ooad.guitarApp2.enums.Wood;

public class GuitarSpec extends InstrumentSpec {
    private int numStrings;

    public GuitarSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood, int numStrings) {
        super(builder, model, type, backWood, topWood);
        this.numStrings = numStrings;
    }

    public int getNumStrings() { return numStrings; }

    @Override
    public boolean matches(InstrumentSpec otherSpec) {
        if (!super.matches(otherSpec)) return false;
        if (!(otherSpec instanceof GuitarSpec)) return false;
        return numStrings == ((GuitarSpec) otherSpec).numStrings;
    }
}
