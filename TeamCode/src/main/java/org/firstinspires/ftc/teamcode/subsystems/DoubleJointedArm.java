package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ArmFeedforward;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DoubleJointedArm extends SubsystemBase {
    private final MotorEx axis1_leftMotor, axis1_rightMotor, axis2_motor;
    private final PIDFController pidfAxis1, pidfAxis2;
    private final ArmFeedforward feedforwardAxis1, feedforwardAxis2;

    public DoubleJointedArm(HardwareMap hardwareMap) {
        axis1_leftMotor = new MotorEx(hardwareMap, "axis1_leftMotor", 1680, 100);
        axis1_rightMotor = new MotorEx(hardwareMap, "axis1_rightMotor", 1680, 100);
        axis2_motor = new MotorEx(hardwareMap, "axis2_motor", 1680, 100);

        pidfAxis1 = new PIDFController(1.0, 0.0, 0.1, 0.2);
        pidfAxis2 = new PIDFController(1.0, 0.0, 0.1, 0.2);

        feedforwardAxis1 = new ArmFeedforward(0.2, 1.0, 0.1, 0.02);
        feedforwardAxis2 = new ArmFeedforward(0.2, 1.0, 0.1, 0.02);

        axis1_leftMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        axis1_rightMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
        axis2_motor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void resetEncoders() {
        axis1_leftMotor.resetEncoder();
        axis1_rightMotor.resetEncoder();
        axis2_motor.resetEncoder();
    }

    public void setArmPosition(double targetPositionAxis1, double targetPositionAxis2) {
        double currentPosAxis1 = axis1_leftMotor.getCurrentPosition();
        double pidPowerAxis1 = pidfAxis1.calculate(currentPosAxis1, targetPositionAxis1);
        double ffPowerAxis1 = feedforwardAxis1.calculate(targetPositionAxis1, 0, 0.3);
        axis1_leftMotor.set(pidPowerAxis1 + ffPowerAxis1);
        axis1_rightMotor.set(-(pidPowerAxis1 + ffPowerAxis1));

        double currentPosAxis2 = axis2_motor.getCurrentPosition();
        double pidPowerAxis2 = pidfAxis2.calculate(currentPosAxis2, targetPositionAxis2);
        double ffPowerAxis2 = feedforwardAxis2.calculate(targetPositionAxis2, 0, 0.3);
        axis2_motor.set(pidPowerAxis2 + ffPowerAxis2);
    }

    public void stop() {
        axis1_leftMotor.stopMotor();
        axis1_rightMotor.stopMotor();
        axis2_motor.stopMotor();
    }
}
