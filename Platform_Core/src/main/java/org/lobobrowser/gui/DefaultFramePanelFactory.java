/*
    GNU GENERAL LICENSE
    Copyright (C) 2006 The Lobo Project. Copyright (C) 2014 - 2017 Lobo Evolution

    This program is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    verion 3 of the License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    General License for more details.

    You should have received a copy of the GNU General Public
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    

    Contact info: lobochief@users.sourceforge.net; ivan.difrancesco@yahoo.it
 */
package org.lobobrowser.gui;

/**
 * A factory for creating DefaultFramePanel objects.
 */
public class DefaultFramePanelFactory implements FramePanelFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.lobobrowser.gui.FramePanelFactory#createFramePanel(org.lobobrowser.
	 * gui .FramePanel)
	 */
	@Override
	public FramePanel createFramePanel(FramePanel parent) {
		return new FramePanel(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.lobobrowser.gui.FramePanelFactory#createFramePanel(java.lang.String)
	 */
	@Override
	public FramePanel createFramePanel(String windowId) {
		return new FramePanel(windowId);
	}
}
