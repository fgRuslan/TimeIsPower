package xyz.vanillamc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import xyz.vanillamc.TimeIsPower;

public class Utils {
	public static String GetLevelCommand(int level) {
		return TimeIsPower.pluginConfig.getString("levels." + level);
	}
	public static void SetLevelCommand(int level, String command) {
		TimeIsPower.pluginConfig.set("levels." + level, command);
	}
	
	public static void copy(InputStream in, File file) {
	    try {
	        OutputStream out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0){
	            out.write(buf,0,len);
	        }
	        out.close();
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
