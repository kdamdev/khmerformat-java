package dev.kdam.khmerformat.entity;

/**
 * AverageSun
 */
public class Sun {
    private int reasey;
    private int angsar;
    private int libda; // equal to one minute
    public Sun() {}
    public Sun(int reasey, int angsar, int libda) {
        this.reasey = reasey;
        this.angsar = angsar;
        this.libda = libda;
    }

    public int getReasey() {
        return reasey;
    }

    public void setReasey(int reasey) {
        this.reasey = reasey;
    }

    public int getAngsar() {
        return angsar;
    }

    public void setAngsar(int angsar) {
        this.angsar = angsar;
    }

    public int getLibda() {
        return libda;
    }

    public void setLibda(int libda) {
        this.libda = libda;
    }
}
