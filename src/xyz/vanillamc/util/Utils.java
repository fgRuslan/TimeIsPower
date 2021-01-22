package xyz.vanillamc.util;

import xyz.vanillamc.TimeIsPower;

public class Utils {
	public static String GetLevelCommand(int level) {
		return TimeIsPower.pluginConfig.getString("levels." + level);
	}
	public static void SetLevelCommand(int level, String command) {
		TimeIsPower.pluginConfig.set("levels." + level, command);
	}

}
