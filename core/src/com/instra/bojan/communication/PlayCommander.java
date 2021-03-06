package com.instra.bojan.communication;

import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.state.BojanState;


import java.util.List;

public class PlayCommander extends Commander {


    public PlayCommander(List<BojanCircle> circles, String notation) {
        super(circles, notation);
    }

    @Override
    protected BojanState getStateChain() {


       /* BojanState state1 = BojanStateFactory.getState(BojanStateType.AUTO_PLAY, circles.get(0),  Duration.QUARTER);
        BojanState state2 = BojanStateFactory.getState(BojanStateType.AUTO_PLAY, circles.get(1),  Duration.EIGHT);
        state1.setNextStates(Arrays.asList(state2));
        BojanState state3 = BojanStateFactory.getState(BojanStateType.AUTO_PLAY, circles.get(2), Duration.WHOLE);
        state2.setNextStates(Arrays.asList(state3));

        BojanState state6 = BojanStateFactory.getState(BojanStateType.AUTO_PLAY, circles.get(3),  Duration.EIGHT);
        BojanState state4 = BojanStateFactory.getState(BojanStateType.LISTEN, circles.get(2), Note.MI);
        state4.setNextStates(Arrays.asList(state6));
        BojanState state5 = BojanStateFactory.getState(BojanStateType.LISTEN, circles.get(1), Note.MI);
        state3.setNextStates(Arrays.asList(state4, state5));*/

       /* List<CommandUnit> katarinaBarbara = Arrays.asList(new CommandUnit(Note.MI, Duration.QUARTER),
                new CommandUnit(Note.DO, Duration.QUARTER),
                new CommandUnit(Note.MI, Duration.QUARTER),
                new CommandUnit(Note.DO, Duration.QUARTER),
                new CommandUnit(Note.FA, Duration.QUARTER),
                new CommandUnit(Note.RE, Duration.QUARTER),
                new CommandUnit(Note.RE, Duration.QUARTER),
                new CommandUnit(Note.RE, Duration.QUARTER),
                new CommandUnit(Note.SO, Duration.QUARTER),
                new CommandUnit(Note.SO, Duration.QUARTER),
                new CommandUnit(Note.SO, Duration.QUARTER),
                new CommandUnit(Note.SO, Duration.QUARTER),
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
                new CommandUnit(Note.SO, Duration.QUARTER),
                new CommandUnit(Note.SO, Duration.QUARTER),
                new CommandUnit(Note.SO, Duration.QUARTER),
                new CommandUnit(Note.SO, Duration.QUARTER),
                new CommandUnit(Note.DO, Duration.WHOLE)
        );*/




        List<CommandUnit> textNotation = readTextNotation(notation);


        BojanState playChain = constructPlayChain(textNotation);
        lastToPlayInPlayChain(playChain).setNextStates(constructDefaultStates());
        return playChain;
    }
}
