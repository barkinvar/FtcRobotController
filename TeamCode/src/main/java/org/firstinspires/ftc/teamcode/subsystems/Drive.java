package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

public class Drive extends SubsystemBase {
    private final MotorEx m_left, m_right;
    private final DifferentialDrive m_diffDrive;
    private final Telemetry m_telemetry;

    public Drive(HardwareMap hardwareMap, Telemetry telemetry) {
        m_left = new MotorEx(hardwareMap, "left_drive_motor", 1680, 100);
        m_right = new MotorEx(hardwareMap, "right_drive_motor", 1680, 100);
        
        m_diffDrive = new DifferentialDrive(m_left, m_right);
        m_telemetry = telemetry;
    }

    @Override
    public void periodic() {
        m_telemetry.addData("LeftSpeed", m_left.getVelocity());
        m_telemetry.addData("RightSpeed", m_right.getVelocity());
    }

    public void setCoast() {
        m_left.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        m_right.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
    }

    public void setBrake() {
        m_left.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        m_right.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void drive(double leftSpeed, double rightSpeed) {
        m_diffDrive.arcadeDrive(leftSpeed, rightSpeed);
    }

    public void stop() {
        m_left.set(0.0);
        m_right.set(0.0);
    }
}