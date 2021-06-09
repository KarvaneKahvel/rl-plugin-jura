package com.jura;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Jura"
)
public class JuraPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private JuraConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Jura started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Jura stopped!");
	}

	@Subscribe
	public void onMenuOpened(MenuOpened e) {
		if (config.jura())
		{
			Player lokaalne = client.getLocalPlayer();
			lokaalne.setAnimation(config.vaartus());
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Mega türa " + config.vaartus(), null);
		}
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied hitsplat)
	{
		Actor theDude = hitsplat.getActor();
		if (!client.getLocalPlayer().equals(theDude))
		{
			theDude.setOverheadText("Fuck my ass!");
			theDude.setAnimation(AnimationID.BURYING_BONES);
			theDude.setAnimationFrame(0);
		}
	}

	@Provides
	JuraConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(JuraConfig.class);
	}
}
