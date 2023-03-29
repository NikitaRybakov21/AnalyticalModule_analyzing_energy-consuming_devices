package dataSourse;

public class PowerDevice {
    public int id;
    public float time;

    public float inputPower;
    public float effectivePower;

    public PowerDevice(int id,float time,float inputPower,float effectivePower) {
        this.id = id;

        this.time = time;
        this.inputPower = inputPower;
        this.effectivePower = effectivePower;
    }
}
