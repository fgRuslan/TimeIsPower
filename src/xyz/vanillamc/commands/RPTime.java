package xyz.vanillamc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import xyz.vanillamc.TimeIsPower;

public class RPTime implements CommandExecutor {

	private TimeIsPower plugin;

	public RPTime(TimeIsPower plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 2) {
			sender.sendMessage(ChatColor.RED + "Not enough arguments!");
			return true;
		}
		String function = args[0].toLowerCase();
		String player = args[1].toLowerCase();
		String value = args[2];

		switch (function) {
		case "set":
			if (args.length < 3) {
				sender.sendMessage(ChatColor.RED + "Not enough arguments!");
				return true;
			}
			plugin.playerData.set(player, Integer.parseInt(value));
			sender.sendMessage(ChatColor.GREEN + "Successfully set " + player + "'s RPTime to " + value);
			break;
		case "get":
			int rptime = plugin.playerData.getInt(player);
			sender.sendMessage(ChatColor.GREEN + player + "'s RPTime is " + rptime);
			break;
		}
		return true;
	}

}
