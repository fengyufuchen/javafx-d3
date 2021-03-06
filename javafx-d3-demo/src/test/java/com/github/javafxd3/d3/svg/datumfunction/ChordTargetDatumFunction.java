package com.github.javafxd3.d3.svg.datumfunction;

import com.github.javafxd3.d3.svg.ChordDef;
import com.github.javafxd3.d3.functions.DatumFunction;

/**
 * A datum function that creates a target chord definition 
 *  
 */
public class ChordTargetDatumFunction implements DatumFunction<ChordDef> {
	
		
	//#region CONSTRUCTORS
	
	/**
	 * @param webEngine
	 */
	public ChordTargetDatumFunction(){			
	}
	
	//#end region
	
	//#region METHODS

	@Override
	public ChordDef apply(Object context, Object d, int index) {
		System.out.println("target: " + d);
		return new ChordDef(index * 5, index * 5, index * 5);
	}
	
	//#end region

}
