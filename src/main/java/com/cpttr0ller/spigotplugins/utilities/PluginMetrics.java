package com.cpttr0ller.spigotplugins.utilities;

import java.io.IOException;

import org.mcstats.Metrics;
import org.mcstats.Metrics.Graph;

import com.cpttr0ller.spigotplugins.PluginUtilities;

/**
 * Handles the plug-in metrics
 * 
 * @contributor(s)	[CptTr0ller]
 * @version			ALPHA
 * @since			ALPHA
 */
public final class PluginMetrics {
	private Metrics metrics;
	private Graph[] graphs;
	
	public PluginMetrics(String... graphNames) throws IOException {
		metrics = new Metrics(PluginUtilities.getPlugin());
		metrics.start();
		graphs = new Graph[graphNames.length];
		for (int i = 0; i<=graphNames.length; i++) {
			graphs[i] = metrics.createGraph(graphNames[i]);
		}
	}
	
	public void close() throws IOException {
		metrics.disable();
		for (int i = 0; i<=graphs.length; i++) {
			graphs[i] = null;
		}
	}
	
	public Metrics getMetrics() {
		return metrics;
	}
	
	public void plot(int graphNumber, String valueName, int value) {
		graphs[graphNumber].addPlotter(new Metrics.Plotter(valueName) {
			@Override
			public int getValue() {
				return value;
			}
		});
	}
}
