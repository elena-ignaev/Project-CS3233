package com.example.projectp1.Model;

public class TestTube extends Tools {
    private Layer layer1;
    private Layer layer2;
    private Layer layer3;
    public TestTube() {
        super("Test Tube");
    }
    public TestTube(Layer layer1, Layer layer2, Layer layer3) {
        this();
        this.layer1 = layer1;
        this.layer2 = layer2;
        this.layer3 = layer3;
    }
    public Layer getLayer1() {
        return this.layer1;
    }
    public Layer getLayer2() {
        return this.layer2;
    }
    public Layer getLayer3() {
        return this.layer3;
    }
    public void setLayer1(Layer layer1) {
        this.layer1 = layer1;
    }
    public void setLayer2(Layer layer2) {
        this.layer2 = layer2;
    }

    public void setLayer3(Layer layer3) {
        this.layer3 = layer3;
    }

    @Override
    public void inAction() {
        // each layer in action
    }
    @Override
    public String toString() {
        return "Test tube properties: \n" + this.getLayer1().toString() + this.getLayer2().toString() + this.getLayer3().toString();
    }
}
