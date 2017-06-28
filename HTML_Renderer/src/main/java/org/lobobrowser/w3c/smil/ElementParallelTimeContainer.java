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

package org.lobobrowser.w3c.smil;

import org.w3c.dom.DOMException;

/**
 * A <code>parallel</code> container defines a simple parallel time grouping in
 * which multiple elements can play back at the same time. It may have to
 * specify a repeat iteration. (?)
 */
public interface ElementParallelTimeContainer extends ElementTimeContainer {
	/**
	 * Controls the end of the container. Need to address thr id-ref value.
	 * 
	 * @exception DOMException
	 *                NO_MODIFICATION_ALLOWED_ERR: Raised if this attribute is
	 *                readonly.
	 */
	public String getEndSync();

	public void setEndSync(String endSync) throws DOMException;

	/**
	 * This method returns the implicit duration in seconds.
	 * 
	 * @return The implicit duration in seconds or -1 if the implicit is unknown
	 *         (indefinite?).
	 */
	public float getImplicitDuration();

}
