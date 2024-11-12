package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.DoubleJointedArm;

public class DefaultArmPosition extends CommandBase {
    private final DoubleJointedArm arm;
    private static final double default_position_axis1 = 20;
    private static final double default_position_axis2 = -120;

    public DefaultArmPosition(DoubleJointedArm arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        arm.setArmPosition(default_position_axis1, default_position_axis2);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        arm.stop();
    }
}
