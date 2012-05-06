/*
 * This file is part of lanterna (http://code.google.com/p/lanterna/).
 * 
 * lanterna is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright (C) 2010-2012 mabe02
 */

package com.googlecode.lanterna.test.gui;

import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.LanternTerminal;
import com.googlecode.lanterna.gui.*;
import com.googlecode.lanterna.gui.theme.Theme.Category;
import com.googlecode.lanterna.terminal.TerminalSize;

/**
 *
 * @author mabe02
 */
public class ButtonTest
{
    public static void main(String[] args) throws LanternaException
    {
        if(args.length > 0) {
            try {
                Thread.sleep(15000);
            }
            catch(InterruptedException e) {
            }
        }

        LanternTerminal terminal = new LanternTerminal();
        final GUIScreen terminalGUIScreen = terminal.getGUIScreen();
        if(terminalGUIScreen == null) {
            System.err.println("Couldn't allocate a terminal!");
            return;
        }

        terminal.start();
        terminalGUIScreen.setTitle("GUI Test");

        final Window mainWindow = new Window("Ett fönster med paneler");
        mainWindow.addComponent(new AbstractComponent() {
            public void repaint(TextGraphics graphics)
            {
                graphics.applyThemeItem(graphics.getTheme().getItem(Category.Shadow));
                for(int y = 0; y < graphics.getHeight(); y++)
                    for(int x = 0; x < graphics.getWidth(); x++)
                        graphics.drawString(x, y, "X");
            }

            public TerminalSize getPreferredSize()
            {
                return new TerminalSize(20, 6);
            }
        });
        Panel buttonPanel = new Panel(Panel.Orientation.HORISONTAL);
        Button button1 = new Button("Button1", new Action() {
            public void doAction()
            {
                terminalGUIScreen.closeWindow(mainWindow);
            }
        });
        Button button2 = new Button("Button2");
        Button button3 = new Button("Button3");
        buttonPanel.addComponent(button1);
        buttonPanel.addComponent(button2);
        buttonPanel.addComponent(button3);
        mainWindow.addComponent(buttonPanel);

        terminalGUIScreen.showWindow(mainWindow, GUIScreen.Position.CENTER);
        terminal.stopAndRestoreTerminal();
    }
}