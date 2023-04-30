package frc.robot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.XboxController;

public class SysLifter {
    private RobotContainer container;

    private XboxController controller;

    private CANSparkMax m_lifter;

    public void robotInit() {
        container = RobotContainer.getInstances();
        
        controller = container.getController();

        m_lifter = container.getLifterMotor();
    }

    public void teleopPeriodic() {
        m_lifter.set(0);

        if(controller.getXButton() || controller.getPOV() == 180) {
            m_lifter.set(0.3);
        }

        if(controller.getYButton() || controller.getPOV() == 0) {
            m_lifter.set(-0.3);
        }
    }
}
