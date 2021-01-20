package xyz.vanillamc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.vanillamc.TimeIsPower;

public class MainCommand implements CommandExecutor {

	TimeIsPower plugin;
	
	public MainCommand(TimeIsPower plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
