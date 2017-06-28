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
/*
 * Created on Mar 31, 2005
 */
package org.lobobrowser.async;

import java.util.EventObject;

/**
 * An asynchronous result event. This is a generic class with a
 * <code>TResult</code> type parameter, the type of the result.
 *
 * @author J. H. S.
 * @param <TResult>
 *            the generic type
 * @see org.lobobrowser.async.AsyncResultListener
 */
public class AsyncResultEvent<TResult> extends EventObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The result. */
	private final TResult result;

	/**
	 * Instance constructor.
	 *
	 * @param source
	 *            The event source.
	 * @param result
	 *            The asynchronous result.
	 */
	public AsyncResultEvent(Object source, TResult result) {
		super(source);
		this.result = result;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public TResult getResult() {
		return this.result;
	}
}
