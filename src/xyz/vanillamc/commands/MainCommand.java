package xyz.vanillamc.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

import xyz.vanillamc.TimeIsPower;

public class MainCommand implements CommandExecutor {

	@SuppressWarnings("unused")
	private TimeIsPower plugin;

	public MainCommand(TimeIsPower plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 1) {
			sender.sendMessage(ChatColor.YELLOW + "/timeispower " + ChatColor.RESET + "- " + ChatColor.AQUA
					+ TimeIsPower.messageConfig.getString("commands.timeispower"));
			sender.sendMessage(ChatColor.YELLOW + "/rptime add <player> <time> " + ChatColor.RESET + "- "
					+ ChatColor.AQUA + TimeIsPower.messageConfig.getString("commands.add-time"));
			for (int i = 0; i < 999; i++) {
				if (TimeIsPower.pluginConfig.getString("levels." + i) != null)
					sender.sendMessage(TimeIsPower.pluginConfig.getString("levels." + i));
			}
			return true;
		}
		switch (args[0]) {
		case "reload":
			plugin.loadYamls();
			sender.sendMessage("[" + plugin.getName() + "] " + ChatColor.GREEN + "Configuration reloaded!");
			return true;
		case "nya":
			sender.sendMessage("Easter egg! :)");
			return true;
		}
		return false;
	}

}
