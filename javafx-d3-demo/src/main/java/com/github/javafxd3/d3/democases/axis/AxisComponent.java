package com.github.javafxd3.d3.democases.axis;

import java.net.URL;

import com.github.javafxd3.d3.AbstractDemoCase;
import com.github.javafxd3.d3.D3;
import com.github.javafxd3.d3.DemoCase;
import com.github.javafxd3.d3.DemoFactory;
import com.github.javafxd3.d3.core.Selection;
import com.github.javafxd3.d3.scales.LinearScale;
import com.github.javafxd3.d3.svg.Area;
import com.github.javafxd3.d3.svg.Axis;
import com.github.javafxd3.d3.svg.Line;
import com.github.javafxd3.d3.svg.Axis.Orientation;
import com.github.javafxd3.d3.time.TimeFormat;
import com.github.javafxd3.d3.time.TimeScale;

import javafx.scene.layout.VBox;

@SuppressWarnings("javadoc")
public class AxisComponent extends AbstractDemoCase {
	
	//#region CONSTRUCTORS

	/**
	 * 
	 * Constructor
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 */
	public AxisComponent(D3 d3, VBox demoPreferenceBox) {
		super(d3, demoPreferenceBox);		
	}

	//#end region

	//#region METHODS

	/**
	 * Factory provider
	 * 
	 * @param d3
	 * @param demoPreferenceBox
	 * @return
	 */
	public static DemoFactory factory(D3 d3, VBox demoPreferenceBox) {
		return new DemoFactory() {
			@Override
			public DemoCase newInstance() {
				return new AxisComponent(d3, demoPreferenceBox);
			}
		};
	}

	@Override
	public void start() {

		final int[] m = new int[] { 80, 80, 80, 80 };
		final int w = 960 - m[1] - m[3];
		final int h = 500 - m[0] - m[2];

		final TimeFormat format = d3.time().format("%b %Y");

		// Scales and axes. Note the inverted domain for the y-scale: bigger is
		// up!

		final TimeScale x = d3.time().scale().range(0, w);

		final LinearScale y = d3.scale().linear().range(h, 0);
		final Axis xAxis = d3.svg().axis().scale(x).tickSize(-h);
		// removed .tickSubdivide(1);
		final Axis yAxis = d3.svg().axis().scale(y).orient(Orientation.RIGHT).ticks(4);

		// An area generator, for the light fill.
		final Area area = d3.svg().area().interpolate(Area.InterpolationMode.MONOTONE)
				// .x(function(d) { return x(d.date); })
				.x(new XAxisDatumFunction(webEngine, x)).y0(h)
				// .y1(function(d) { return y(d.price); });
				.y1(new YAxisDatumFunction(webEngine, y));

		// A line generator, for the dark stroke.
		final Line line = d3.svg().line().interpolate(Line.InterpolationMode.MONOTONE)
				// .x(function(d) { return x(d.date); })
				.x(new XAxisDatumFunction(webEngine, x))
				// // .y(function(d) { return y(d.price); });
				.y(new YAxisDatumFunction(webEngine, y));
		
		String relativeUrl = "readme.csv";
		
		URL url = getClass().getClassLoader().getResource(relativeUrl);
		String fileUrl = url.toString();		
		Selection svg = d3.select("svg");
		
		//String remoteUrl = "https://github.com/gwtd3/gwt-d3/blob/master/gwt-d3-demo/src/main/webapp/demo-data/readme.csv";

		d3.csv(fileUrl, new DataDsvObjectAccessor(webEngine, format), new DataDsvCallback(webEngine, svg, x, y, xAxis, yAxis, line, area, m, w, h));

	}

	@Override
	public void stop() {
	}

	//#end region	

}
