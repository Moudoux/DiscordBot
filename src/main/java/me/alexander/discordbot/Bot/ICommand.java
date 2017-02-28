package me.alexander.discordbot.Bot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import de.btobastian.javacord.entities.message.Message;
import me.alexander.discordbot.Main;

public class ICommand {

	public static ArrayList<String> getHelp() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("!who - Who am i ?");
		list.add("!ping - Pong");
		list.add("!help - Commands");
		return list;
	}

	public static String doCmd(String in, Message message, Bot bot) {
		String cmd = in;
		if (cmd.contains(" ")) {
			cmd = cmd.split(" ")[0];
		}
		if (!cmd.startsWith("!")) {
			return "";
		}
		String result = "";
		switch (cmd.toLowerCase()) {
		case "!help":
			for (String s : getHelp()) {
				result += s + "\n";
			}
			result = result.substring(0, result.length() - 1);
			break;
		case "!ping":
			result = "Am online";
			break;
		case "!who":
			result = "Discord java bot";
			break;
		default:
			result = "No can do, type !help";
			break;
		}
		return result;
	}

}
