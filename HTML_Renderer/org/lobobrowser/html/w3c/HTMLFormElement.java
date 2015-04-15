/*
    GNU LESSER GENERAL PUBLIC LICENSE
    Copyright (C) 2006 The Lobo Project. Copyright (C) 2014 - 2015 Lobo Evolution

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

    Contact info: lobochief@users.sourceforge.net; ivan.difrancesco@yahoo.it
 */
/*
 * Copyright (c) 2003 World Wide Web Consortium,
 * (Massachusetts Institute of Technology, Institut National de
 * Recherche en Informatique et en Automatique, Keio University). All
 * Rights Reserved. This program is distributed under the W3C's Software
 * Intellectual Property License. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 * See W3C License http://www.w3.org/Consortium/Legal/ for more details.
 */

package org.lobobrowser.html.w3c;


/**
 * The <code>FORM</code> element encompasses behavior similar to a collection
 * and an element. It provides direct access to the contained form controls as
 * well as the attributes of the form element. See the FORM element definition
 * in HTML 4.01.
 * <p>
 * See also the
 * Object Model (DOM) Level 2 HTML Specification</p>.
 */
public interface HTMLFormElement extends HTMLElement {
	
	/**
	 * Returns a collection of all form control elements in the form.
	 *
	 * @return the elements
	 */
	public HTMLCollection getElements();

	/**
	 * The number of form controls in the form.
	 *
	 * @return the length
	 */
	public int getLength();

	/**
	 * Names the form.
	 *
	 * @return the name
	 */
	public String getName();

	/**
	 * Names the form.
	 *
	 * @param name the new name
	 */
	public void setName(String name);

	/**
	 * List of character sets supported by the server. See the accept-charset
	 * attribute definition in HTML 4.01.
	 *
	 * @return the accept charset
	 */
	public String getAcceptCharset();

	/**
	 * List of character sets supported by the server. See the accept-charset
	 * attribute definition in HTML 4.01.
	 *
	 * @param acceptCharset the new accept charset
	 */
	public void setAcceptCharset(String acceptCharset);

	/**
	 * Server-side form handler. See the action attribute definition in HTML
	 * 4.01.
	 *
	 * @return the action
	 */
	public String getAction();

	/**
	 * Server-side form handler. See the action attribute definition in HTML
	 * 4.01.
	 *
	 * @param action the new action
	 */
	public void setAction(String action);

	/**
	 * The content type of the submitted form, generally
	 * "application/x-www-form-urlencoded". See the enctype attribute definition
	 * in HTML 4.01. The onsubmit even handler is not guaranteed to be triggered
	 * when invoking this method. The behavior is inconsistent for historical
	 * reasons and authors should not rely on a particular one.
	 *
	 * @return the enctype
	 */
	public String getEnctype();

	/**
	 * The content type of the submitted form, generally
	 * "application/x-www-form-urlencoded". See the enctype attribute definition
	 * in HTML 4.01. The onsubmit even handler is not guaranteed to be triggered
	 * when invoking this method. The behavior is inconsistent for historical
	 * reasons and authors should not rely on a particular one.
	 *
	 * @param enctype the new enctype
	 */
	public void setEnctype(String enctype);

	/**
	 * HTTP method [<a href='http://www.ietf.org/rfc/rfc2616.txt'>IETF RFC
	 * 2616</a>] used to submit form. See the method attribute definition in
	 * HTML 4.01.
	 *
	 * @return the method
	 */
	public String getMethod();

	/**
	 * HTTP method [<a href='http://www.ietf.org/rfc/rfc2616.txt'>IETF RFC
	 * 2616</a>] used to submit form. See the method attribute definition in
	 * HTML 4.01.
	 *
	 * @param method the new method
	 */
	public void setMethod(String method);

	/**
	 * Frame to render the resource in. See the target attribute definition in
	 * HTML 4.01.
	 *
	 * @return the target
	 */
	public String getTarget();

	/**
	 * Frame to render the resource in. See the target attribute definition in
	 * HTML 4.01.
	 *
	 * @param target the new target
	 */
	public void setTarget(String target);

	/**
	 * Submits the form. It performs the same action as a submit button.
	 */
	public void submit();

	/**
	 * Restores a form element's default values. It performs the same action as
	 * a reset button.
	 */
	public void reset();

	/**
	 * Gets the autocomplete.
	 *
	 * @return the autocomplete
	 */
	public boolean getAutocomplete();

	/**
	 * Sets the autocomplete.
	 *
	 * @param autocomplete the new autocomplete
	 */
	public void setAutocomplete(boolean autocomplete);

	/**
	 * Gets the no validate.
	 *
	 * @return the no validate
	 */
	public boolean getNoValidate();

	/**
	 * Sets the no validate.
	 *
	 * @param noValidate the new no validate
	 */
	public void setNoValidate(boolean noValidate);

	/**
	 * Item.
	 *
	 * @param index the index
	 * @return the object
	 */
	public Object item(int index);

	/**
	 * Named item.
	 *
	 * @param name the name
	 * @return the object
	 */
	public Object namedItem(String name);

	/**
	 * Check validity.
	 *
	 * @return true, if successful
	 */
	public boolean checkValidity();

	/**
	 * Dispatch form input.
	 */
	public void dispatchFormInput();

	/**
	 * Dispatch form change.
	 */
	public void dispatchFormChange();
}