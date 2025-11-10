package io.github.plexiglasog.novillagerhitting;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class NoVIllagerHittingClient implements ClientModInitializer {
    private static KeyMapping toggleVillager;
    public static NoVillagerHittingConfig modConfig;

	@Override
	public void onInitializeClient() {
        modConfig = NoVillagerHittingConfig.load();

        toggleVillager = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.novillagerhitting.toggleVillagerHit",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                KeyMapping.Category.GAMEPLAY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleVillager.consumeClick()) {
                modConfig.blockVillagerHits = !modConfig.blockVillagerHits;
                modConfig.save();
            }
        });
	}
}