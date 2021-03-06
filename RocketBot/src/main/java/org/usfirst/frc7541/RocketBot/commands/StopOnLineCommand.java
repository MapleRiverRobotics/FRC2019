// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc7541.RocketBot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

import org.usfirst.frc7541.RocketBot.Robot;
import org.usfirst.frc7541.RocketBot.sensors.LineSensor;
import org.usfirst.frc7541.RocketBot.sensors.LineSensor.LineSensorStatus;

/**
 *
 */
public class StopOnLineCommand extends TimedCommand {

    LineSensor lineSensor = null;
    boolean lineFound = false;
    double speed;

    public StopOnLineCommand(double speed) {
        super(5);
        requires(Robot.driveTrain);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        lineSensor = LineSensor.getInstance();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.driveTrain.arcadeDrive(speed, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        LineSensorStatus lineStatus = lineSensor.eitherSideLineStatus(10);
        System.out.println(lineStatus);
        lineFound = lineStatus == LineSensorStatus.Centered;
        return super.isFinished() || lineStatus == LineSensorStatus.NoReading || lineFound;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        //Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
