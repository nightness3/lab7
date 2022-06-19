package common;

import common.commands.Command;
import common.vehicle.Vehicle;

import java.io.Serializable;

public class Request implements Serializable {

    private User reqUser;
    private Vehicle reqVehicle;
    private Command reqCommand;
    private String rspAnswer = "request";

    public User getReqUser() {
        return reqUser;
    }

    public void setReqUser(User reqUser) {
        this.reqUser = reqUser;
    }

    public Vehicle getReqVehicle() {
        return reqVehicle;
    }

    public void setReqVehicle(Vehicle reqVehicle) {
        this.reqVehicle = reqVehicle;
    }

    public Command getReqCommand() {
        return reqCommand;
    }

    public void setReqCommand(Command reqCommand) {
        this.reqCommand = reqCommand;
    }

    public String getRspAnswer() {
        return rspAnswer;
    }

    public void setRspAnswer(String rspAnswer) {
        this.rspAnswer = rspAnswer;
    }

}
