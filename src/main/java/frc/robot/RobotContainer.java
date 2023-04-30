package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class RobotContainer {
    private XboxController controller;

    private WPI_VictorSPX m_driveFL;
    private WPI_VictorSPX m_driveFR;
    private WPI_VictorSPX m_driveRL;
    private WPI_VictorSPX m_driveRR;

    private MotorControllerGroup m_leftGroup;
    private MotorControllerGroup m_rightGroup;

    private CANSparkMax m_lifter;

    private static RobotContainer container;

    public final static RobotContainer getInstances() {
        if(container == null) container = new RobotContainer();
        return container;
    }

    private RobotContainer() {
        this.controller = new XboxController(0);

        this.m_driveFL = new WPI_VictorSPX(Constants.kMotorFL.getID());
        this.m_driveFR = new WPI_VictorSPX(Constants.kMotorFR.getID());
        this.m_driveRL = new WPI_VictorSPX(Constants.kMotorRL.getID());
        this.m_driveRR = new WPI_VictorSPX(Constants.kMotorRR.getID());

        this.m_leftGroup = new MotorControllerGroup(this.m_driveFL, this.m_driveRL);
        this.m_rightGroup = new MotorControllerGroup(this.m_driveFR, this.m_driveRR);

        this.m_leftGroup.setInverted(true);
        this.m_rightGroup.setInverted(false);

        this.m_lifter = new CANSparkMax(Constants.kMotorLifter.getID(), MotorType.kBrushless);
    }

    public MotorControllerGroup getLeftMotorGroup() {
        return this.m_leftGroup;
    }

    public MotorControllerGroup getRightMotorGroup() {
        return this.m_rightGroup;
    }

    public CANSparkMax getLifterMotor() {
        return this.m_lifter;
    }

    public XboxController getController() {
        return this.controller;
    }
}
