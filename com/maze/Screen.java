package com.maze;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public interface Screen {

    public int getWIDTH();

    public int getHEIGHT();

    public void displayOutput(AsciiPanel terminal);

    public Screen respondToUserInput(KeyEvent key);
}
