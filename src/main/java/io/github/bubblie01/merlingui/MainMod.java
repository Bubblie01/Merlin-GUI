package io.github.bubblie01.merlingui;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MainMod implements ModInitializer
{
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "merlin-gui";
	public static final TestItem TEST_ITEM = new TestItem(new Item.Settings().group(ItemGroup.MATERIALS));
	@Override
	public void onInitialize()
	{
		//System.loadLibrary("renderdoc");
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "test_item"),TEST_ITEM);
	}
}
