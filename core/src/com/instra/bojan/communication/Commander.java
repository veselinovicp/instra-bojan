package com.instra.bojan.communication;

import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.state.BojanState;
import com.instra.bojan.state.BojanStateFactory;
import com.instra.bojan.state.BojanStateType;

import java.util.List;

public abstract class Commander {

    protected List<BojanCircle> circles;

    public Commander(List<BojanCircle> circles)
    {
        this.circles = circles;
    }

    protected abstract BojanState getStateChain();


    public void execute(){
        getStateChain().start();


    }

    public static Commander getCommander(CommanderType type, List<BojanCircle> circles){
        if(type == CommanderType.PLAY){
            return new PlayCommander(circles);
        }
        throw new RuntimeException("No commander of type: "+type+" found");
    }
}
