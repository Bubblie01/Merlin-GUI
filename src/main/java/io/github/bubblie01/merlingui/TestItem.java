package io.github.bubblie01.merlingui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.io.IOException;


public class TestItem extends Item
{


    public TestItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            MinecraftClient.getInstance().setScreen(new MerlinScreen(Text.of("Balls")));
            ResourceManager resourceManager = MinecraftClient.getInstance().getResourceManager();
            try {
                System.out.println(resourceManager.getResource(new Identifier("merlin:textures/cross.png")).getId().getNamespace());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.use(world, user, hand);
    }
}
