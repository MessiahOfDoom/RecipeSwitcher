package de.schneider_oliver.recipeswitcher.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import de.schneider_oliver.recipeswitcher.RecipeSwitcher;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.gui.screen.ingame.CraftingScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

@Mixin(CraftingScreen.class)
public abstract class CraftingScreenMixin extends HandledScreen<CraftingScreenHandler>{

	public CraftingScreenMixin(CraftingScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}

	@Inject(at = @At("TAIL"), method = "init()V")
	public void onInit(CallbackInfo info) {
		this.addButton(new ButtonWidget(this.x + 146, this.height / 2 - 39, 10, 12, new LiteralText(">"), a -> {
			PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
			buf.writeInt(this.handler.syncId);
			ClientSidePacketRegistry.INSTANCE.sendToServer(RecipeSwitcher.INCREMENT_MATCH_PACKET, buf);
		}));
	}
}
