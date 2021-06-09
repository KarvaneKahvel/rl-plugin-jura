package com.jura;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface JuraConfig extends Config
{
	@ConfigItem(
		keyName = "vaartus",
		name = "Vaartus",
		description = "Jura, kirjeldus"
	)
	default int vaartus()
	{
		return 0;
	}

	@ConfigItem(
		keyName = "kontroll",
		name = "Kontroll",
		description = "Jura, kirjeldus"
	)
	default boolean jura()
	{
		return false;
	}
}
