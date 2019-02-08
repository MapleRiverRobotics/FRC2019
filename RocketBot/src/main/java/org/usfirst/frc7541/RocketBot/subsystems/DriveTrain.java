// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc7541.RocketBot.subsystems;

import java.awt.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc7541.RocketBot.RobotMap;
import org.usfirst.frc7541.RocketBot.commands.*;
import org.usfirst.frc7541.RocketBot.sensors.GyroPigeon;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    private GyroPigeon gyro;
    private AnalogInput ultrasonic;
    private WPI_TalonSRX leftMaster;
    private WPI_TalonSRX rightMaster;
    private WPI_TalonSRX leftSlave;
    private WPI_TalonSRX rightSlave;
    private DifferentialDrive differentialDrive;

    public DriveTrain() {
        leftMaster = new WPI_TalonSRX(RobotMap.driveLeftMaster);
        leftMaster.setInverted(true);
        leftMaster.setSensorPhase(true);
        addChild("LeftMaster", leftMaster);

        rightMaster = new WPI_TalonSRX(RobotMap.driveRightMaster);
        rightMaster.setInverted(true);
        rightMaster.setSensorPhase(true);
        addChild("RightMaster", rightMaster);

        leftSlave = new WPI_TalonSRX(RobotMap.driveLeftSlave);
        leftSlave.follow(leftMaster);
        leftSlave.setInverted(true);
        addChild("LeftSlave", leftSlave);

        rightSlave = new WPI_TalonSRX(RobotMap.driveRightSlave);
        rightSlave.setInverted(true);
        rightSlave.follow(rightMaster);
        addChild("RightSlave", rightSlave);

        differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
        differentialDrive.setSafetyEnabled(false);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);
        differentialDrive.setDeadband(.02);
        addChild("Differential Drive", differentialDrive);

        gyro = new GyroPigeon(rightSlave);
        gyro.resetToZeroHeading();
        addChild(gyro);

        //ultrasonic = new AnalogInput(1);
        //addChild("Ultrasonic", ultrasonic);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveTeleopCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    /**
     * Arcade style driving for the DriveTrain.
     *
     * @param speed    Speed in range [-1,1]
     * @param rotation Rotation in range [-1,1]
     */
    public void arcadeDrive(double speed, double rotation) {
        SmartDashboard.putNumber("Speed", speed);
        SmartDashboard.putNumber("Rotation", rotation);
        SmartDashboard.putNumber("Gyro Heading", gyro.getHeading());

        differentialDrive.arcadeDrive(-speed, rotation);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        SmartDashboard.putNumber("leftSpeed", leftSpeed);
        SmartDashboard.putNumber("rightSpeed", rightSpeed);
        SmartDashboard.putNumber("Gyro Heading", gyro.getHeading());

        differentialDrive.tankDrive(leftSpeed, rightSpeed, false);
    }

    public void stop() {
        differentialDrive.arcadeDrive(0, 0);
    }

    public double getHeading() {
        return gyro.getHeading();
    }

    public double getPosition() {
        return rightMaster.getSelectedSensorPosition() * 18.8 / 4096;
    }

    public void resetPosition() {
        rightMaster.setSelectedSensorPosition(0);
    }
}
