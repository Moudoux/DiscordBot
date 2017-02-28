package me.alexander.discordbot.Bot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

public class IMessage {

	@SuppressWarnings("unused")
	private final DiscordAPI api;
	private final Message message;
	private final Bot bot;

	public IMessage(DiscordAPI api, Message message, Bot bot) {
		this.api = api;
		this.message = message;
		this.bot = bot;
	}

	public void execute() {
		new Thread() {
			@Override
			public void run() {
				if (!message.getAuthor().isBot() && message.getContent().startsWith("!")) {
					if (bot.lastCMD.equals("")) {
						bot.lastCMD = message.getContent();
					} else {
						if (bot.lastCMD.equals(message.getContent())) {
							bot.lastCMD = message.getContent();
							message.reply("I refuse to reply to command spamming.");
							return;
						}
						bot.lastCMD = message.getContent();
					}
					String out = ICommand.doCmd(message.getContent(), message, bot);
					if (!out.equals("")) {
						message.reply(out);
					}
				}
			}
		}.start();
	}

}
