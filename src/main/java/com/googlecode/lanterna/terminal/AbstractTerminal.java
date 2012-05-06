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

package com.googlecode.lanterna.terminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Containing a some very fundamental implementations that should be common for
 * all terminals
 * @author mabe02
 */
public abstract class AbstractTerminal implements Terminal
{
    private final List<ResizeListener> resizeListeners;
    private TerminalSize lastKnownSize;

    public AbstractTerminal()
    {
        this.resizeListeners = new ArrayList<ResizeListener>();
        this.lastKnownSize = null;
    }

    public void addResizeListener(ResizeListener listener)
    {
        if(listener != null)
            resizeListeners.add(listener);
    }

    public void removeResizeListener(ResizeListener listener)
    {
        if(listener != null)
            resizeListeners.remove(listener);
    }

    protected synchronized void onResized(TerminalSize newSize)
    {
    }

    protected void onResized(int columns, int rows) 
    {
        TerminalSize newSize = new TerminalSize(columns, rows);
        if(lastKnownSize == null || !lastKnownSize.equals(newSize)) {
            lastKnownSize = newSize;
            for(ResizeListener resizeListener: resizeListeners)
                resizeListener.onResized(lastKnownSize);
        }
    }
    
    protected TerminalSize getLastKnownSize() 
    {
        return lastKnownSize;
    }
}