package com.blazejprzyluski.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class App implements EntryPoint
{
    @Override
    public void onModuleLoad()
    {
        Button b = new Button("Hello World");
        Label l = new Label("Blank");
        b.addClickHandler(e -> l.setText("Hello world"));

        RootPanel.get().add(b);
        RootPanel.get().add(l);
    }
}
