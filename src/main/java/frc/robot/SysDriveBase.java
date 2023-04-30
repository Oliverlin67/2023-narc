package frc.robot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class SysDriveBase {
    private RobotContainer container;

    private XboxController controller;

    private MotorControllerGroup leftGroup;
    private MotorControllerGroup rightGroup;

    private DifferentialDrive drive;

    private int speedIndex;
    private double[] maxSpeed;

    public void robotInit() {
        container = RobotContainer.getInstances();
        
        controller = container.getController();

        leftGroup = container.getLeftMotorGroup();
        rightGroup = container.getRightMotorGroup();

        drive = new DifferentialDrive(rightGroup, leftGroup);
    }

    private void resetSpeed() { // reset speed
        speedIndex = 2; // set speed index to default(2)
    }

    public void teleopInit() { // init teleop
        maxSpeed = new double[] {0.3, 0.4, 0.5, 0.6, 0.7, 0.8}; // create max speeds array
        resetSpeed(); // reset speed
    }

    public void teleopPeriodic() {
        double leftProp = controller.getLeftY(),
                rightProp = controller.getRightY(); // get left and right prop of controller

        if(controller.getLeftBumperPressed()) { // check if left bumper pressed 
            speedIndex -= (speedIndex > 0) ? 1 : 0; // check if speed index is greater than 0
        }
        if(controller.getRightBumperPressed()) { // check if right bumper pressed
            speedIndex += (speedIndex < maxSpeed.length - 1) ? 1 : 0; // check if speed index is less than max speed length - 1
        }

        if(controller.getAButtonPressed()) { // check if x button pressed
            resetSpeed(); // reset speed
        }

         // check if left or right trigger axis is greater than 0.1
        if(controller.getLeftTriggerAxis() > 0.1) {
            leftProp = controller.getLeftTriggerAxis();  // set right prop to left trigger axis
            rightProp = 0; // 0
        }
        if(controller.getRightTriggerAxis() > 0.1) {
            leftProp = 0; // 0
            rightProp = controller.getRightTriggerAxis(); // set right prop to right trigger axis
        }

        drive.tankDrive(leftProp * maxSpeed[speedIndex], rightProp * maxSpeed[speedIndex]); // drive with tank drivev
    }
}
