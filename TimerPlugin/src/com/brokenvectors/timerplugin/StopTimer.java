package com.brokenvectors.timerplugin;

import java.util.Hashtable;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StopTimer implements CommandExecutor {
	StartTimer st;
	public StopTimer(StartTimer startTimer) {
		st = startTimer;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = Bukkit.getPlayer(args[0]);
		
		Hashtable<Player, BukkitRunnable> runnables = st.runnables;
		if(runnables.containsKey(player)) {
			BukkitRunnable runnable = runnables.get(player);
			runnable.cancel();
			runnables.remove(player);
		}
		return true;
	}
}
