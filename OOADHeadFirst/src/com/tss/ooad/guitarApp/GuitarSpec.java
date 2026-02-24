package com.tss.ooad.guitarApp;

public class GuitarSpec {

    private Builder builder;
    private String model;
    private Type type;
    private int numStrings;
    private Wood backWood;
    private Wood topWood;

    public GuitarSpec(Builder builder, String model,
                      Type type, int numStrings,
                      Wood backWood, Wood topWood) {

        this.builder = builder;
        this.model = model;
        this.type = type;
        this.numStrings = numStrings;
        this.backWood = backWood;
        this.topWood = topWood;
    }

    public Builder getBuilder() { return builder; }
    public String getModel() { return model; }
    public Type getType() { return type; }
    public int getNumStrings() { return numStrings; }
    public Wood getBackWood() { return backWood; }
    public Wood getTopWood() { return topWood; }


    public boolean matches(GuitarSpec otherSpec) {

        if (builder != otherSpec.getBuilder())
            return false;

        if (model != null && !model.isEmpty() &&
                !model.equalsIgnoreCase(otherSpec.getModel()))
            return false;

        if (type != otherSpec.getType())
            return false;

        if (numStrings != otherSpec.getNumStrings())
            return false;

        if (backWood != otherSpec.getBackWood())
            return false;

        if (topWood != otherSpec.getTopWood())
            return false;

        return true;
    }
}
