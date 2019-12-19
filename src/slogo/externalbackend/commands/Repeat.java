package slogo.externalbackend.commands;

import slogo.externalbackend.ParserManager;

import java.util.ArrayList;

public class Repeat implements ControlCommand {
    @Override
    public double execute(ParserManager myManager, ArrayList<String> arguments) throws Exception {
        return myManager.setRepeat(Integer.parseInt(arguments.get(0)));
    }
}
