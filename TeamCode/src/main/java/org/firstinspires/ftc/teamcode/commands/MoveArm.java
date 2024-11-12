package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.DoubleJointedArm;

public class MoveArm extends CommandBase {
    private final DoubleJointedArm arm;
    private final double targetPositionAxis1;
    private final double targetPositionAxis2;

    public MoveArm(DoubleJointedArm arm, double targetPositionAxis1, double targetPositionAxis2) {
        this.arm = arm;
        this.targetPositionAxis1 = targetPositionAxis1;
        this.targetPositionAxis2 = targetPositionAxis2;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        arm.setArmPosition(targetPositionAxis1, targetPositionAxis2);
    }

    @Override
    public void end(boolean interrupted) {
        arm.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
