package com.instra.bojan.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.backends.gwt.GwtGraphics;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.instra.bojan.InstraBojan;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                int w = Window.getClientWidth();
                int h = Window.getClientHeight();
                GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(w, h);

                Window.enableScrolling(false);
                Window.setMargin("0");
//                cfg.preferFlash=false;
                cfg.width = w;
                cfg.height = h;
//                cfg.





                return cfg;
        }




        @Override
        public ApplicationListener getApplicationListener () {
                return new InstraBojan();
        }
}