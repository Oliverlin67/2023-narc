package frc.robot;

public enum Constants {
    kXbox(0),
    kMotorFL(6),
    kMotorFR(8),
    kMotorRL(2),
    kMotorRR(9),
    kMotorLifter(5);

    private int id;

    Constants(int id) {
        this.id = id;
    };

    public int getID() {
        return id;
    }
}
