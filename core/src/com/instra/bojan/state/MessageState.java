package com.instra.bojan.state;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.Pool;
import com.instra.bojan.elements.BojanCircle;

public class MessageState extends BojanState {

    private String message;
    public MessageState(BojanCircle bojanCircle, String message) {
        super(bojanCircle);
        this.message = message;
    }

    class MessageAction extends TemporalAction {

        final Dialog dialog = new Dialog(message,bojanCircle.getSkin());

        public MessageAction(float duration) {
            super(duration);
        }

        @Override
        protected void begin() {
            super.begin();
            dialog.show(bojanCircle.getStage());
        }

        @Override
        protected void update(float percent) {


        }

        @Override
        protected void end() {
            super.end();
            dialog.hide();
            startNextStates();
        }
    }

    Pool<MessageAction> messageActionPool = new Pool<MessageAction>(){
        protected MessageAction newObject(){
            return new MessageAction(1);
        }
    };

    @Override
    public void start() {
        bojanCircle.addAction(messageActionPool.obtain());

    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

    }
}
