package com.brokenvectors.timerplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable() {
		StartTimer startTimer = new StartTimer(this);
		StopTimer stopTimer = new StopTimer(startTimer);
		
		getLogger().info("TimerPlugin is working!(hopefully)");
		this.getCommand("startTimer").setExecutor(startTimer);
		this.getCommand("stopTimer").setExecutor(stopTimer);
	}
}
