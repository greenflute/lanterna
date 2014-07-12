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
 * Copyright (C) 2010-2014 Martin
 */
package com.googlecode.lanterna.screen;

import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalPosition;
import com.googlecode.lanterna.terminal.TextColor;
import com.googlecode.lanterna.TestTerminalFactory;
import java.io.IOException;

/**
 *
 * @author martin
 */
public class ScreenTabTest {

    public static void main(String[] args) throws InterruptedException, IOException, IOException {
        new ScreenTabTest(args);
    }

    private Screen screen;

    public ScreenTabTest(String[] args) throws InterruptedException, IOException {
        screen = new TestTerminalFactory(args).createScreen();
        screen.startScreen();
        screen.setCursorPosition(new TerminalPosition(0, 0));
        putStrings("Trying out some tabs!");

        long now = System.currentTimeMillis();
        while(System.currentTimeMillis() - now < 20 * 1000) {
            Thread.sleep(1);
        }
        screen.stopScreen();
    }

    private void putStrings(String topTitle) throws IOException {
        ScreenWriter writer = new ScreenWriter(screen);
        writer.setForegroundColor(TextColor.ANSI.DEFAULT);
        writer.setBackgroundColor(TextColor.ANSI.DEFAULT);
        writer.fillScreen(' ');

        writer.setForegroundColor(TextColor.ANSI.DEFAULT);
        writer.setBackgroundColor(TextColor.ANSI.DEFAULT);
        writer.putString(0, 0, topTitle, Terminal.SGR.BLINK);
        writer.setTabBehaviour(TabBehaviour.CONVERT_TO_ONE_SPACE);
        writer.putString(10, 1, "TabBehaviour.CONVERT_TO_ONE_SPACE:    |\t|\t|\t|\t|");
        writer.setTabBehaviour(TabBehaviour.CONVERT_TO_TWO_SPACES);
        writer.putString(10, 2, "TabBehaviour.CONVERT_TO_TWO_SPACES:   |\t|\t|\t|\t|");
        writer.setTabBehaviour(TabBehaviour.CONVERT_TO_THREE_SPACES);
        writer.putString(10, 3, "TabBehaviour.CONVERT_TO_THREE_SPACES: |\t|\t|\t|\t|");
        writer.setTabBehaviour(TabBehaviour.CONVERT_TO_FOUR_SPACES);
        writer.putString(10, 4, "TabBehaviour.CONVERT_TO_FOUR_SPACES:  |\t|\t|\t|\t|");
        writer.setTabBehaviour(TabBehaviour.CONVERT_TO_EIGHT_SPACES);
        writer.putString(10, 5, "TabBehaviour.CONVERT_TO_EIGHT_SPACES: |\t|\t|\t|\t|");
        writer.setTabBehaviour(TabBehaviour.ALIGN_TO_COLUMN_4);
        writer.putString(10, 6, "TabBehaviour.ALIGN_TO_COLUMN_4:       |\t|\t|\t|\t|");
        writer.setTabBehaviour(TabBehaviour.ALIGN_TO_COLUMN_8);
        writer.putString(10, 7, "TabBehaviour.ALIGN_TO_COLUMN_8:       |\t|\t|\t|\t|");

        screen.refresh();
    }
}