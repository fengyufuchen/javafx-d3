package com.github.javafxd3.d3.democases.behaviors;

import com.github.javafxd3.d3.D3;
import com.github.javafxd3.d3.functions.DatumFunction;
import com.github.javafxd3.d3.wrapper.Element;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class OnDragEnd implements DatumFunction<Void> {
	
	private WebEngine webEngine;
	private D3 d3;
	
	public OnDragEnd(WebEngine webEngine, D3 d3){
		this.webEngine = webEngine;
		this.d3=d3;
	}

	@Override
	public Void apply(final Object context, final Object d, final int index) {

				
		JSObject jsContext = (JSObject) context;
		Element element = new Element(webEngine, jsContext);
		
		// remove fill attributes
		d3.select(element) //
		.attr("fill", "");
		
		return null;
	}

}