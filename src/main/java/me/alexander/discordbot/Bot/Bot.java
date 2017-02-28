package me.alexander.discordbot.Bot;

import com.google.common.util.concurrent.FutureCallback;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import me.alexander.discordbot.Main;

public class Bot {

	private DiscordAPI api = null;
	private final String token;
	protected String lastCMD = "";
	private Bot bot = this;
	protected final String botName, game;

	public Bot(String token, String botName, String game) {
		this.token = token;
		this.botName = botName;
		this.game = game;
	}

	public void disconnect() {
		api.disconnect();
		Main.bots.remove(this);
	}

	public DiscordAPI getAPI() {
		return api;
	}

	public void setup() {
		api = Javacord.getApi(token, true);
		api.connect(new FutureCallback<DiscordAPI>() {
			@Override
			public void onSuccess(DiscordAPI api) {
				api.registerListener(new MessageCreateListener() {
					@Override
					public void onMessageCreate(final DiscordAPI api, final Message message) {
						new Thread() {
							@Override
							public void run() {
								IMessage imessage = new IMessage(api, message, bot);
								imessage.execute();
							}
						}.start();
					}
				});
				api.setAutoReconnect(false);
				api.setGame(game);
			}

			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		});
	}

}
