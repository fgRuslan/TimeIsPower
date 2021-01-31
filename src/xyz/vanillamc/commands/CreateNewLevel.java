package xyz.vanillamc.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.vanillamc.TimeIsPower;
import xyz.vanillamc.util.Utils;

public class CreateNewLevel implements CommandExecutor {
	private TimeIsPower plugin;

	public CreateNewLevel(TimeIsPower plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String cmd = "";
		for(int i = 1; i < args.length; i++) {
			cmd = cmd + args[i] + " ";
		}
		System.out.println(cmd);
		String levelnumber = args[0];
		String levelcommand = cmd;

		int level = Integer.parseInt(levelnumber);

		if (plugin.pluginConfig.getString("levels." + level) == null) {
			Utils.SetLevelCommand(level, levelcommand);
			sender.sendMessage(ChatColor.GREEN + plugin.messageConfig.getString("messages.add-new").replace("%level%", levelnumber));
			plugin.saveYamls();
		}
		else {
			Utils.SetLevelCommand(level, levelcommand);
			sender.sendMessage(ChatColor.DARK_RED + plugin.messageConfig.getString("messages.overwrite").replace("%level%", levelnumber));
			plugin.saveYamls();
		}

		return true;

	}

}
