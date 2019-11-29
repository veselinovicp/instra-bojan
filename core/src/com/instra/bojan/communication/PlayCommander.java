package com.instra.bojan.communication;

import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.state.BojanState;
import com.instra.bojan.state.BojanStateFactory;
import com.instra.bojan.state.BojanStateType;
import com.instra.bojan.theory.Note;


import java.util.Arrays;
import java.util.List;

public class PlayCommander extends Commander {


    public PlayCommander(List<BojanCircle> circles) {
        super(circles);
    }

    @Override
    protected BojanState getStateChain() {


        BojanState state1 = BojanStateFactory.getState(BojanStateType.AUTO_PLAY, circles.get(0), null);
        BojanState state2 = BojanStateFactory.getState(BojanStateType.AUTO_PLAY, circles.get(1), null);
        state1.setNextStates(Arrays.asList(state2));
        BojanState state3 = BojanStateFactory.getState(BojanStateType.AUTO_PLAY, circles.get(2), null);
        state2.setNextStates(Arrays.asList(state3));

        BojanState state4 = BojanStateFactory.getState(BojanStateType.LISTEN, circles.get(2), Note.MI);
        BojanState state5 = BojanStateFactory.getState(BojanStateType.LISTEN, circles.get(1), Note.MI);
        state3.setNextStates(Arrays.asList(state4, state5));


        return state1;
    }
}
