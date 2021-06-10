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

	private Actor theDude;
	private int tickCounter = 0;

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
			lokaalne.setAnimationFrame(0);
		}
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied hitsplat)
	{
		theDude = hitsplat.getActor();
		if (theDude instanceof Player) theDude = null;
	}

	@Subscribe
	public void onGameTick(GameTick e)
	{
		if (theDude instanceof NPC && tickCounter == 0)
		{
			theDude.setOverheadText("Fuck my ass");
			theDude.setAnimation(AnimationID.BURYING_BONES);
			theDude.setAnimationFrame(0);
			tickCounter++;
		}
		else if (theDude != null && tickCounter == 3)
		{
			theDude.setOverheadText("");
			theDude = null;
			tickCounter = 0;
		}
		else if (theDude != null)
		{
			tickCounter++;
		}
	}

	@Provides
	JuraConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(JuraConfig.class);
	}
}
