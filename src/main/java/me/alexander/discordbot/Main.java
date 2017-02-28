package me.alexander.discordbot;

import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;

import me.alexander.discordbot.Bot.Bot;

public class Main {

	public static ArrayList<Bot> bots = new ArrayList<Bot>();

	public static void main(String[] args) {
		bots.add(new Bot("<Discord bot key>", "<Bot name>", "<What game is it playing ?>"));
		for (Bot b : bots) {
			b.setup();
		}
		while (!bots.isEmpty()) {
			;
		}
		System.exit(0);
	}

	public static String getRunningJarLocation() {
		CodeSource codeSource = Main.class.getProtectionDomain().getCodeSource();
		File jarFile = null;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if (jarFile == null) {
			return "null";
		}
		String jarDir = jarFile.getParentFile().getPath();
		return jarDir;
	}

}
