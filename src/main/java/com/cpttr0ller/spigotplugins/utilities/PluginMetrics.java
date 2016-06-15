package com.cpttr0ller.spigotplugins.utilities;

import java.io.IOException;

import org.mcstats.Metrics;

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
	private short metricsStatus;
	
	public PluginMetrics() {
		try {
			metrics = new Metrics(PluginUtilities.getPlugin());
			metrics.start();
		} catch (IOException e) {
			metricsStatus = 1;
		}
	}
	
	public void close() {
		try {
			metrics.disable();
		} catch (IOException e) {
			metricsStatus = 1;
		}
	}
	
	public Metrics getMetrics() {
		return metrics;
	}
	
	public short getMetricsStatus() {
		return metricsStatus;
	}
}
