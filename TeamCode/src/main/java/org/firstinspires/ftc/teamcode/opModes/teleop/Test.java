package org.firstinspires.ftc.teamcode.opModes.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.TeleopDrive;
import org.firstinspires.ftc.teamcode.subsystems.Drive;

@TeleOp
public class Test extends CommandOpMode {
    @Override
    public void initialize() {
        GamepadEx gamepad = new GamepadEx(gamepad1);

        Drive driveSystem = new Drive(hardwareMap, telemetry);
        driveSystem.setCoast();

        driveSystem.setDefaultCommand(new TeleopDrive(driveSystem, gamepad::getLeftY, gamepad::getRightX));

        schedule(new RunCommand(telemetry::update));
    }
}
