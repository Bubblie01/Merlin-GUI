package io.github.bubblie01.merlingui;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class MainMod implements ModInitializer
{
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "merlin-gui";
	public static final TestItem TEST_ITEM = new TestItem(new Item.Settings());
	@Override
	public void onInitialize(ModContainer modContainer)
	{
		//System.loadLibrary("renderdoc");
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "test_item"),TEST_ITEM);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.addItem(TEST_ITEM));
	}
}
