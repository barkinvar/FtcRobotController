package org.firstinspires.ftc.teamcode.opModes.teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DefaultArmPosition;
import org.firstinspires.ftc.teamcode.commands.TeleopDrive;
import org.firstinspires.ftc.teamcode.commands.MoveArm;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.DoubleJointedArm;

@TeleOp
public class Test extends CommandOpMode {
    @Override
    public void initialize() {
        GamepadEx gamepad = new GamepadEx(gamepad1);

        Drive driveSystem = new Drive(hardwareMap, telemetry);
        driveSystem.setCoast();
        driveSystem.setDefaultCommand(new TeleopDrive(driveSystem, gamepad::getLeftY, gamepad::getRightX));

        DoubleJointedArm arm = new DoubleJointedArm(hardwareMap);
        arm.resetEncoders();
        arm.setDefaultCommand(new DefaultArmPosition(arm));
        new GamepadButton(gamepad, GamepadKeys.Button.A)
                .whenPressed(new MoveArm(arm, 90, -90));

        new GamepadButton(gamepad, GamepadKeys.Button.B)
                .whenPressed(new MoveArm(arm, 160, -180));

        schedule(new RunCommand(telemetry::update));
    }
}
