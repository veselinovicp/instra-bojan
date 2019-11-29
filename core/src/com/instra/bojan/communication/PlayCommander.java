package com.instra.bojan.communication;

import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.state.BojanState;
import com.instra.bojan.state.BojanStateFactory;
import com.instra.bojan.state.BojanStateType;


import java.util.List;

public class PlayCommander extends Commander {


    public PlayCommander(List<BojanCircle> circles) {
        super(circles);
    }

    @Override
    protected BojanState getStateChain() {


        BojanState state1 = BojanStateFactory.getState(BojanStateType.AUTO_PLAY, circles.get(0));
        BojanState state2 = BojanStateFactory.getState(BojanStateType.AUTO_PLAY, circles.get(1));
        state1.setNextState(state2);
        BojanState state3 = BojanStateFactory.getState(BojanStateType.AUTO_PLAY, circles.get(2));
        state2.setNextState(state3);

        BojanState state4 = BojanStateFactory.getState(BojanStateType.LISTEN, circles.get(2));
        state3.setNextState(state4);


        return state1;
    }
}
