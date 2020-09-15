package de.schneider_oliver.recipeswitcher;

import de.schneider_oliver.recipeswitcher.common.AdvancedRecipeScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.util.Identifier;

public class RecipeSwitcher implements ModInitializer{

	public static final Identifier INCREMENT_MATCH_PACKET = new Identifier("recipeswitcher", "increment_match");

	@Override
	public void onInitialize() {
		ServerSidePacketRegistry.INSTANCE.register(INCREMENT_MATCH_PACKET, (context, buf) -> {

			if(context.getPlayer() != null && buf.readableBytes() >= 4
					&& context.getPlayer().currentScreenHandler != null
					&& context.getPlayer().currentScreenHandler.syncId == buf.readInt()
					&& context.getPlayer().getEntityWorld() != null) {
				context.getTaskQueue().execute(() -> {
					if(context.getPlayer().currentScreenHandler instanceof AdvancedRecipeScreenHandler) {
						((AdvancedRecipeScreenHandler)context.getPlayer().currentScreenHandler).incrementMatch(context.getPlayer().getEntityWorld());
					}
				});
			}
		});
	}

}
