package com.wise.develop.IntelligentLandfill.wight.wheel;

public class WheelItem implements IWheel {

    private String label;

    WheelItem(String label) {
        this.label = label;
    }

    @Override
    public String getShowText() {
        return label;
    }
}
