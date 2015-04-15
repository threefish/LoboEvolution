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
 * Created on Apr 17, 2005
 */
package org.lobobrowser.html.control;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.lobobrowser.html.UserAgentContext;
import org.lobobrowser.html.dombl.ModelNode;
import org.lobobrowser.html.dombl.UINode;
import org.lobobrowser.html.domimpl.DOMNodeImpl;
import org.lobobrowser.html.renderer.BaseElementRenderable;
import org.lobobrowser.html.renderer.FrameContext;
import org.lobobrowser.html.renderer.HtmlController;
import org.lobobrowser.html.renderer.RBlockViewport;
import org.lobobrowser.html.renderer.RElement;
import org.lobobrowser.html.renderer.RenderableContainer;
import org.lobobrowser.html.renderer.RenderableSpot;
import org.lobobrowser.html.renderer.UIControl;
import org.lobobrowser.html.renderstate.RenderState;
import org.lobobrowser.util.Objects;


/**
 * The Class RUIControl.
 *
 * @author J. H. S.
 */
public class RUIControl extends BaseElementRenderable implements RElement {
	
	/** The Constant MAX_CACHE_SIZE. */
	private static final int MAX_CACHE_SIZE = 10;
	
	/** The widget. */
	public final UIControl widget;
	
	/** The model node. */
	protected final ModelNode modelNode;
	
	/** The frame context. */
	private final FrameContext frameContext;

	/**
	 * Instantiates a new RUI control.
	 *
	 * @param me the me
	 * @param widget the widget
	 * @param container the container
	 * @param frameContext the frame context
	 * @param ucontext the ucontext
	 */
	public RUIControl(ModelNode me, UIControl widget,
			RenderableContainer container, FrameContext frameContext,
			UserAgentContext ucontext) {
		super(container, me, ucontext);
		this.modelNode = me;
		this.widget = widget;
		this.frameContext = frameContext;
		widget.setRUIControl(this);
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BaseRCollection#focus()
	 */
	public void focus() {
		super.focus();
		Component c = this.widget.getComponent();
		c.requestFocus();
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BaseElementRenderable#invalidateLayoutLocal()
	 */
	public final void invalidateLayoutLocal() {
		// Invalidate widget (some redundancy)
		super.invalidateLayoutLocal();
		this.widget.invalidate();
		// Invalidate cached values
		this.cachedLayout.clear();
		this.lastLayoutKey = null;
		this.lastLayoutValue = null;
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.RElement#getVAlign()
	 */
	public int getVAlign() {
		return this.widget.getVAlign();
	}

	/**
	 * Checks for background.
	 *
	 * @return true, if successful
	 */
	public boolean hasBackground() {
		return this.backgroundColor != null || this.backgroundImage != null
				|| this.lastBackgroundImageUri != null;
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BaseElementRenderable#paint(java.awt.Graphics)
	 */
	public final void paint(Graphics g) {
		RenderState rs = this.modelNode.getRenderState();		
		if (rs != null && rs.getVisibility() != RenderState.VISIBILITY_VISIBLE) {
			// Just don't paint it.
			return;
		}
		// Prepaint borders, background images, etc.
		this.prePaint(g);
		// We need to paint the GUI component.
		// For various reasons, we need to do that
		// instead of letting AWT do it.
		Insets insets = this.getInsets(false, false);
		g.translate(insets.left, insets.top);
		try {
			this.widget.paint(g);
		} finally {
			g.translate(-insets.left, -insets.top);
		}
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BoundableRenderable#onMouseClick(java.awt.event.MouseEvent, int, int)
	 */
	public boolean onMouseClick(MouseEvent event, int x, int y) {
		ModelNode me = this.modelNode;
		if (me != null) {
			return HtmlController.getInstance().onMouseClick(me, event, x, y);
		} else {
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BoundableRenderable#onDoubleClick(java.awt.event.MouseEvent, int, int)
	 */
	public boolean onDoubleClick(MouseEvent event, int x, int y) {
		ModelNode me = this.modelNode;
		if (me != null) {
			return HtmlController.getInstance().onDoubleClick(me, event, x, y);
		} else {
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BoundableRenderable#onMousePressed(java.awt.event.MouseEvent, int, int)
	 */
	public boolean onMousePressed(MouseEvent event, int x, int y) {
		ModelNode me = this.modelNode;
		if (me != null) {
			return HtmlController.getInstance().onMouseDown(me, event, x, y);
		} else {
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BoundableRenderable#onMouseReleased(java.awt.event.MouseEvent, int, int)
	 */
	public boolean onMouseReleased(MouseEvent event, int x, int y) {
		ModelNode me = this.modelNode;
		if (me != null) {
			return HtmlController.getInstance().onMouseUp(me, event, x, y);
		} else {
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BoundableRenderable#onMouseDisarmed(java.awt.event.MouseEvent)
	 */
	public boolean onMouseDisarmed(MouseEvent event) {
		ModelNode me = this.modelNode;
		if (me != null) {
			return HtmlController.getInstance().onMouseDisarmed(me, event);
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.lobobrowser.html.render.BoundableRenderable#invalidateState(org.xamjwg
	 * .html.renderer.RenderableContext)
	 */
	/**
	 * Invalidate render style.
	 */
	public void invalidateRenderStyle() {
		// NOP - No RenderStyle below this node.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.lobobrowser.html.dombl.UINode(org.lobobrowser.html.dombl.ModelNode modelNode);
	 */
	public void repaint(ModelNode modelNode) {
		Object widget = this.widget;
		if (widget instanceof UINode) {
			((UINode) widget).repaint(modelNode);
		} else {
			this.repaint();
		}
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BaseRCollection#updateWidgetBounds(int, int)
	 */
	public void updateWidgetBounds(int guiX, int guiY) {
		// Overrides
		super.updateWidgetBounds(guiX, guiY);
		Insets insets = this.getInsets(false, false);
		this.widget.setBounds(guiX + insets.left, guiY + insets.top, this.width
				- insets.left - insets.right, this.height - insets.top
				- insets.bottom);
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BaseBoundableRenderable#getBlockBackgroundColor()
	 */
	public Color getBlockBackgroundColor() {
		return this.widget.getBackgroundColor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.lobobrowser.html.render.BoundableRenderable#paintSelection(java.awt.
	 * Graphics, boolean, org.lobobrowser.html.render.RenderablePoint,
	 * org.lobobrowser.html.render.RenderablePoint)
	 */
	public boolean paintSelection(Graphics g, boolean inSelection,
			RenderableSpot startPoint, RenderableSpot endPoint) {
		inSelection = super
				.paintSelection(g, inSelection, startPoint, endPoint);
		if (inSelection) {
			Color over = new Color(0, 0, 255, 50);
			if (over != null) {
				Color oldColor = g.getColor();
				try {
					g.setColor(over);
					g.fillRect(0, 0, this.width, this.height);
				} finally {
					g.setColor(oldColor);
				}
			}
		}
		return inSelection;
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BaseRCollection#extractSelectionText(java.lang.StringBuffer, boolean, org.lobobrowser.html.renderer.RenderableSpot, org.lobobrowser.html.renderer.RenderableSpot)
	 */
	public boolean extractSelectionText(StringBuffer buffer,
			boolean inSelection, RenderableSpot startPoint,
			RenderableSpot endPoint) {
		// No text here
		return inSelection;
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BoundableRenderable#getLowestRenderableSpot(int, int)
	 */
	public RenderableSpot getLowestRenderableSpot(int x, int y) {
		// Nothing draggable - return self
		return new RenderableSpot(this, x, y);
	}

	/** The declared width. */
	private int declaredWidth = -1;
	
	/** The declared height. */
	private int declaredHeight = -1;
	
	/** The last layout key. */
	private LayoutKey lastLayoutKey = null;
	
	/** The last layout value. */
	private LayoutValue lastLayoutValue = null;
	
	/** The cached layout. */
	private final Map<LayoutKey, LayoutValue> cachedLayout = new HashMap<LayoutKey, LayoutValue>(
			5);

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.BaseElementRenderable#doLayout(int, int, boolean)
	 */
	public void doLayout(int availWidth, int availHeight, boolean sizeOnly) {
		Map<LayoutKey, LayoutValue> cachedLayout = this.cachedLayout;
		RenderState rs = this.modelNode.getRenderState();
		int whitespace = rs == null ? RenderState.WS_NORMAL : rs
				.getWhiteSpace();
		Font font = rs == null ? null : rs.getFont();
		LayoutKey layoutKey = new LayoutKey(availWidth, availHeight,
				whitespace, font);
		LayoutValue layoutValue;
		if (sizeOnly) {
			layoutValue = (LayoutValue) cachedLayout.get(layoutKey);
		} else {
			if (Objects.equals(this.lastLayoutKey, layoutKey)) {
				layoutValue = this.lastLayoutValue;
			} else {
				layoutValue = null;
			}
		}
		if (layoutValue == null) {
			this.applyStyle(availWidth, availHeight);
			RenderState renderState = this.modelNode.getRenderState();
			Insets paddingInsets = this.paddingInsets;
			if (paddingInsets == null) {
				paddingInsets = RBlockViewport.ZERO_INSETS;
			}
			Insets borderInsets = this.borderInsets;
			if (borderInsets == null) {
				borderInsets = RBlockViewport.ZERO_INSETS;
			}
			Insets marginInsets = this.marginInsets;
			if (marginInsets == null) {
				marginInsets = RBlockViewport.ZERO_INSETS;
			}
			int actualAvailWidth = availWidth - paddingInsets.left
					- paddingInsets.right - borderInsets.left
					- borderInsets.right - marginInsets.left
					- marginInsets.right;
			int actualAvailHeight = availHeight - paddingInsets.top
					- paddingInsets.bottom - borderInsets.top
					- borderInsets.bottom - marginInsets.top
					- marginInsets.bottom;
			Integer dw = this.getDeclaredWidth(renderState, actualAvailWidth);
			Integer dh = this.getDeclaredHeight(renderState, actualAvailHeight);
			int declaredWidth = dw == null ? -1 : dw.intValue();
			int declaredHeight = dh == null ? -1 : dh.intValue();
			this.declaredWidth = declaredWidth;
			this.declaredHeight = declaredHeight;

			UIControl widget = this.widget;
			widget.reset(availWidth, availHeight);
			Insets insets = this.getInsets(false, false);
			int finalWidth = declaredWidth == -1 ? -1 : declaredWidth
					- insets.left - insets.right;
			int finalHeight = declaredHeight == -1 ? -1 : declaredHeight
					- insets.top - insets.bottom;
			if (finalWidth == -1 || finalHeight == -1) {
				Dimension size = widget.getPreferredSize();
				if (finalWidth == -1) {
					finalWidth = size.width + insets.left + insets.right;
				}
				if (finalHeight == -1) {
					finalHeight = size.height + insets.top + insets.bottom;
				}
			}
			layoutValue = new LayoutValue(finalWidth, finalHeight);
			if (sizeOnly) {
				if (cachedLayout.size() > MAX_CACHE_SIZE) {
					// Unlikely, but we should ensure it's bounded.
					cachedLayout.clear();
				}
				cachedLayout.put(layoutKey, layoutValue);
				this.lastLayoutKey = null;
				this.lastLayoutValue = null;
			} else {
				this.lastLayoutKey = layoutKey;
				this.lastLayoutValue = layoutValue;
			}
		}
		this.width = layoutValue.width;
		this.height = layoutValue.height;
	}

	/**
	 * May be called by controls when they wish to modifiy their preferred size
	 * (e.g. an image after it's loaded). This method must be called in the GUI
	 * thread.
	 */
	public final void preferredSizeInvalidated() {
		int dw = RUIControl.this.declaredWidth;
		int dh = RUIControl.this.declaredHeight;
		if (dw == -1 || dh == -1) {
			this.frameContext.delayedRelayout((DOMNodeImpl) this.modelNode);
		} else {
			RUIControl.this.repaint();
		}
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.RCollection#getRenderables()
	 */
	public Iterator<?> getRenderables() {
		// No children for GUI controls
		return null;
	}

	/* (non-Javadoc)
	 * @see org.lobobrowser.html.renderer.RenderableContainer#getPaintedBackgroundColor()
	 */
	public Color getPaintedBackgroundColor() {
		return this.container.getPaintedBackgroundColor();
	}

	/**
	 * Gets the foreground color.
	 *
	 * @return the foreground color
	 */
	public Color getForegroundColor() {
		RenderState rs = this.modelNode.getRenderState();
		return rs == null ? null : rs.getColor();
	}

	/**
	 * The Class LayoutKey.
	 */
	public static class LayoutKey {
		
		/** The avail width. */
		public final int availWidth;
		
		/** The avail height. */
		public final int availHeight;
		
		/** The whitespace. */
		public final int whitespace;
		
		/** The font. */
		public final Font font;

		/**
		 * Instantiates a new layout key.
		 *
		 * @param availWidth the avail width
		 * @param availHeight the avail height
		 * @param whitespace the whitespace
		 * @param font the font
		 */
		public LayoutKey(int availWidth, int availHeight, int whitespace,
				Font font) {
			this.availWidth = availWidth;
			this.availHeight = availHeight;
			this.whitespace = whitespace;
			this.font = font;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof LayoutKey)) {
				return false;
			}
			LayoutKey other = (LayoutKey) obj;
			return other.availWidth == this.availWidth
					&& other.availHeight == this.availHeight
					&& other.whitespace == this.whitespace
					&& Objects.equals(other.font, this.font);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		public int hashCode() {
			Font font = this.font;
			return (this.availWidth * 1000 + this.availHeight)
					^ (font == null ? 0 : font.hashCode());
		}
	}

	/**
	 * The Class LayoutValue.
	 */
	public static class LayoutValue {
		
		/** The width. */
		public final int width;
		
		/** The height. */
		public final int height;

		/**
		 * Instantiates a new layout value.
		 *
		 * @param width the width
		 * @param height the height
		 */
		public LayoutValue(int width, int height) {
			this.width = width;
			this.height = height;
		}
	}
}