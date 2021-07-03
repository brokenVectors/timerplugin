package com.brokenvectors.timerplugin;

import java.util.Hashtable;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class StartTimer implements CommandExecutor {
	private Main plugin;
	public Hashtable<Player, BukkitRunnable> runnables = new Hashtable<Player, BukkitRunnable>();
	public StartTimer(Main main) {
		this.plugin = main;
	}
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//Player player = (Player) sender;
		Player player = Bukkit.getPlayer(args[0]);
		int startMs = (int)System.currentTimeMillis();
		
		if(runnables.containsKey(player)) {
			player.sendMessage("Timer has already started!");
			return true;
		}
		BukkitRunnable runnable = new BukkitRunnable() {
		    @Override
		    public void run() {
		    	
		    	if(!player.isOnline()) { // there is probably a better way of doing this lol
		    		// all this code is taken from the StopTimer.java
		    		// i am not very good at java
		    		BukkitRunnable playerRunnable = runnables.get(player); 
		    		playerRunnable.cancel();
					runnables.remove(player);
		    	}
		    	int totalMillis = (int)System.currentTimeMillis() - startMs;
		    	
		    	
		    	int totalSeconds = (int) Math.floor(totalMillis/1000);
		    	int totalMinutes = (int) Math.floor(totalSeconds/60);
		    	
		    	int millis = totalMillis % 1000;
		    	int seconds = totalSeconds % 60;
		    	int minutes = totalMinutes;
		    	int tenths = (int) Math.floor(millis / 100);
		    	
		    	String str = String.format("%d:%02d.%d", minutes, seconds, tenths);
		    	TextComponent component = new TextComponent(str);
		        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
		        
		    }
		};
		
		runnables.put(player, runnable);
		
		runnable.runTaskTimer(this.plugin, 0L, 10L);
		return true;
	}
}
