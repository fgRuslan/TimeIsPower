package xyz.vanillamc.commands.tab;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.google.common.collect.Lists;

public class RPTimeTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		final List<String> options = Lists.newArrayList();
		options.add("get");
		options.add("set");
		return options;
	}

}
