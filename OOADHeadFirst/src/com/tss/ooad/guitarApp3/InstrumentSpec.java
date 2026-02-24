package com.tss.ooad.guitarApp3;


import java.util.Map;

public class InstrumentSpec {
    private Map<String, Object> properties;

    public InstrumentSpec(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    // Check if this spec matches another spec
    public boolean matches(InstrumentSpec otherSpec) {
        for (String key : otherSpec.getProperties().keySet()) {
            if (!properties.containsKey(key) ||
                    !properties.get(key).equals(otherSpec.getProperty(key))) {
                return false;
            }
        }
        return true;
    }
}
