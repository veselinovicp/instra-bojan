package com.instra.bojan.communication;

import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.state.BojanState;
import com.instra.bojan.state.BojanStateFactory;
import com.instra.bojan.state.BojanStateType;
import com.instra.bojan.theory.Duration;
import com.instra.bojan.theory.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LearnCommander extends Commander{
    public LearnCommander(List<BojanCircle> circles) {
        super(circles);
    }


    @Override
    protected BojanState getStateChain() {
        List<CommandUnit> katarinaBarbara = Arrays.asList(new CommandUnit(Note.MI, Duration.QUARTER),
                new CommandUnit(Note.DO, Duration.QUARTER),
                new CommandUnit(Note.MI, Duration.QUARTER),
                new CommandUnit(Note.DO, Duration.QUARTER),
                new CommandUnit(Note.FA, Duration.QUARTER),
                new CommandUnit(Note.RE, Duration.QUARTER),
                new CommandUnit(Note.RE, Duration.QUARTER),
                new CommandUnit(Note.RE, Duration.QUARTER),
                new CommandUnit(Note.SOL, Duration.QUARTER),
                new CommandUnit(Note.SOL, Duration.QUARTER),
                new CommandUnit(Note.SOL, Duration.QUARTER),
                new CommandUnit(Note.SOL, Duration.QUARTER),
                new CommandUnit(Note.MI, Duration.QUARTER),
                new CommandUnit(Note.DO, Duration.QUARTER),
                new CommandUnit(Note.DO, Duration.HALF),
                new CommandUnit(Note.MI, Duration.QUARTER),
                new CommandUnit(Note.DO, Duration.QUARTER),
                new CommandUnit(Note.MI, Duration.QUARTER),
                new CommandUnit(Note.DO, Duration.QUARTER),
                new CommandUnit(Note.FA, Duration.QUARTER),
                new CommandUnit(Note.RE, Duration.QUARTER),
                new CommandUnit(Note.RE, Duration.QUARTER),
                new CommandUnit(Note.RE, Duration.QUARTER),
                new CommandUnit(Note.SOL, Duration.QUARTER),
                new CommandUnit(Note.SOL, Duration.QUARTER),
                new CommandUnit(Note.SOL, Duration.QUARTER),
                new CommandUnit(Note.SOL, Duration.QUARTER),
                new CommandUnit(Note.DO, Duration.WHOLE)
        );

       /* List<CommandUnit> katarinaBarbara = Arrays.asList(new CommandUnit(Note.MI, Duration.QUARTER),
                new CommandUnit(Note.DO, Duration.QUARTER),
                new CommandUnit(Note.RE, Duration.QUARTER)
        );*/
        return constructLearnChain(katarinaBarbara);
    }

    private BojanState constructLearnChain(List<CommandUnit> commandUnits){

        List<BojanState> parts = new ArrayList<BojanState>();
        for(int i=1; i<=commandUnits.size();i++){
            List<CommandUnit> part = commandUnits.subList(0, i);
            BojanState partState = constructLearnChainPart(part);
            parts.add(partState);
        }


        //TODO here lies the problem
        /*for(int i=0; i<parts.size()-1;i++){
            List<BojanState> states = parts.get(i).getNextStates();
            Note note = commandUnits.get(i).getNote();
            for(int j=0;j<states.size();j++){
                if(note == circles.get(j).getNote()){
                    states.get(j).setNextStates(Arrays.asList(parts.get(i+1)));
                }
            }
        }*/

        for(int i=0; i<parts.size()-1;i++){
            BojanState state = parts.get(i);
            BojanState lastToPlayInLearnChain = lastToPlayInLearnChain(state, commandUnits);
            lastToPlayInLearnChain.setNextStates(Arrays.asList(parts.get(i+1)));
        }


        return parts.get(0);


    }

    private BojanState lastToPlayInLearnChain(BojanState playChain, List<CommandUnit> commandUnits){



        List<BojanState> nextPlayStates = playChain.getNextStates();



        while(nextPlayStates.size()==1){
            nextPlayStates = nextPlayStates.get(0).getNextStates();
            if(nextPlayStates.size()>1){

                break;
            }
        }
        int index = 0;
        while(true) {
            boolean carryOn = false;
            for(int i=0; i<nextPlayStates.size();i++){
                List<BojanState> successors = nextPlayStates.get(i).getNextStates();
                if(successors.size()>1){
                    nextPlayStates = successors;
                    carryOn = true;
                    index++;
                    break;
                }
            }
            if(!carryOn){
                break;
            }

        }

        Note note = commandUnits.get(index).getNote();
        for(BojanState state : nextPlayStates){
            if(state.getBojanCircle().getNote() == note){
                return state;
            }
        }
        throw new RuntimeException("lastToPlayInLearnChain not found (is it the end of chain?)");



    }

    private BojanState constructLearnChainPart(List<CommandUnit> commandUnits){
        BojanState playChain = constructPlayChain(commandUnits);
        List<BojanState> listenChain = constructListenChain(commandUnits);
        lastToPlayInPlayChain(playChain).setNextStates(listenChain);

        return playChain;
    }

    private BojanState lastToPlayInPlayChain(BojanState playChain){
        if(playChain.getNextStates().size()==0){
            return playChain;
        }
        BojanState result= playChain.getNextStates().get(0);
        while(result.getNextStates().size()>0){
            result = result.getNextStates().get(0);
        }
        return result;

    }


    private List<BojanState> constructListenChain(List<CommandUnit> commandUnits){


        List<List<BojanState>> allStates = new ArrayList<List<BojanState>>();
        for(int i=0;i<commandUnits.size();i++){
            List<BojanState> singleNoteListeners = new ArrayList<BojanState>();
            CommandUnit unit = commandUnits.get(i);
//            BojanCircle circle = getCircleFromNote(unit.getNote());

            for(int j=0;j<circles.size();j++) {
                BojanState state = BojanStateFactory.getState(BojanStateType.LISTEN, circles.get(j), unit.getNote());

                singleNoteListeners.add(state);
            }

            allStates.add(singleNoteListeners);

        }

        /*if(circle.getNote() == unit.getNote()){
            if(i<commandUnits.size()-1){
                state.
            }
        }*/
        for(int i=0; i< allStates.size()-1;i++){
            Note note = commandUnits.get(i).getNote();
            for(int j=0;j<circles.size();j++) {
                BojanCircle circle = circles.get(j);
                if(circle.getNote() == note){
                    allStates.get(i).get(j).setNextStates(allStates.get(i+1));
                }
            }
        }


        return allStates.get(0);
    }
}
