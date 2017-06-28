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
package org.lobobrowser.html.svgimpl;

import org.lobobrowser.html.HtmlAttributeProperties;
import org.lobobrowser.html.style.AbstractCSS2Properties;
import org.lobobrowser.w3c.svg.SVGAnimatedLength;
import org.lobobrowser.w3c.svg.SVGAnimatedTransformList;
import org.lobobrowser.w3c.svg.SVGLineElement;

public class SVGLineElementImpl extends SVGSVGElementImpl implements SVGLineElement {

	public SVGLineElementImpl(String name) {
		super(name);
	}

	@Override
	public SVGAnimatedTransformList getTransform() {
		return new SVGAnimatedTransformListImpl(this.getAttribute(HtmlAttributeProperties.TRANSFORM));
	}

	@Override
	public SVGAnimatedLength getX1() {
		return new SVGAnimatedLengthImpl(new SVGLengthImpl(this.getAttribute(HtmlAttributeProperties.X1)));
	}

	@Override
	public SVGAnimatedLength getY1() {
		return new SVGAnimatedLengthImpl(new SVGLengthImpl(this.getAttribute(HtmlAttributeProperties.Y1)));
	}

	@Override
	public SVGAnimatedLength getX2() {
		return new SVGAnimatedLengthImpl(new SVGLengthImpl(this.getAttribute(HtmlAttributeProperties.X2)));
	}

	@Override
	public SVGAnimatedLength getY2() {
		return new SVGAnimatedLengthImpl(new SVGLengthImpl(this.getAttribute(HtmlAttributeProperties.Y2)));
	}

	@Override
	public AbstractCSS2Properties getSVGStyle() {

		AbstractCSS2Properties style = this.getStyle();

		if (style.getStroke() == null) {
			style.setStroke(this.getStroke());
		}

		if (style.getStrokeDashArray() == null) {
			style.setStrokeDashArray(this.getStrokeDashArray());
		}

		if (style.getStrokeLineCap() == null) {
			style.setStrokeLineCap(this.getStrokeLineCap());
		}

		if (style.getStrokeMiterLimit() == null) {
			style.setStrokeMiterLimit(this.getStrokeMiterLimit());
		}

		if (style.getStrokeOpacity() == null) {
			style.setStrokeOpacity(this.getStrokeOpacity());
		}

		if (style.getStrokeWidth() == null) {
			style.setStrokeWidth(this.getStrokeWidth());
		}

		if (style.getFill() == null) {
			style.setFill(this.getFill());
		}

		return style;
	}

}
