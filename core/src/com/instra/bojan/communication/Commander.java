package com.instra.bojan.communication;

import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.state.BojanState;
import com.instra.bojan.state.BojanStateFactory;
import com.instra.bojan.state.BojanStateType;
import com.instra.bojan.theory.Note;

import java.util.ArrayList;
import java.util.Arrays;
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
        if(type == CommanderType.LEARN){
            return new LearnCommander(circles);
        }
        throw new RuntimeException("No commander of type: "+type+" found");
    }

    protected List<BojanState> constructDefaultStates(){
        List<BojanState> result = new ArrayList<BojanState>();
        for(BojanCircle circle : circles){
            BojanState state = BojanStateFactory.getState(BojanStateType.USUAL, circle);
            result.add(state);
        }
        return result;

    }



    protected BojanState constructPlayChain(List<CommandUnit> commandUnits){


        List<BojanState> allStates = new ArrayList<BojanState>();
        for(int i=0;i<commandUnits.size();i++){
            CommandUnit unit = commandUnits.get(i);
            BojanCircle circle = getCircleFromNote(unit.getNote());
            BojanState state = BojanStateFactory.getState(BojanStateType.AUTO_PLAY, circle, unit.getDuration());
            allStates.add(state);

        }

        BojanState result = allStates.get(0);


        for(int i=0;i<allStates.size()-1;i++){
            BojanState currentState = allStates.get(i);
            BojanState nextState = allStates.get(i + 1);
            currentState.setNextStates(Arrays.asList(nextState));
        }

//        allStates.get(allStates.size()-1).setNextStates(constructDefaultStates());

        return result;
    }

    protected BojanState lastToPlayInPlayChain(BojanState playChain){
        if(playChain.getNextStates().size()==0){
            return playChain;
        }
        BojanState result= playChain.getNextStates().get(0);
        while(result.getNextStates().size()>0){
            result = result.getNextStates().get(0);
        }
        return result;

    }

    protected BojanCircle getCircleFromNote(Note note){
        for(BojanCircle circle : circles){
            if(circle.getNote() == note){
                return circle;
            }
        }
        throw new RuntimeException("No circle for note: "+note+" found");

    }
}
