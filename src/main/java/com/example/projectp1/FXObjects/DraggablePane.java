package com.example.projectp1.FXObjects;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public abstract class DraggablePane extends Pane {
    public abstract void setDraggable(Pane parent, MouseEvent e);
}
