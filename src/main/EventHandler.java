package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    public EventHandler(GamePanel gp)
    {
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x=23;
        eventRect.y=23;
        eventRect.width=3;
        eventRect.height=2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }
}
