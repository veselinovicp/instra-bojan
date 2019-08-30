package com.instra.bojan.elements;

public class BojanPosition {
    private float leftX;
    private float leftY;
    private float rightX;
    private float rightY;

    public BojanPosition(float leftX, float leftY, float rightX, float rightY) {
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;
        this.rightY = rightY;
    }

    public float getLeftX() {
        return leftX;
    }

    public float getLeftY() {
        return leftY;
    }

    public float getRightX() {
        return rightX;
    }

    public float getRightY() {
        return rightY;
    }
}
