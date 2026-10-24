package net.checkm8ra1n.client.mixin;

import net.checkm8ra1n.item.UzisItem;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ExampleClientMixin {
	@Shadow
	private int itemUseCooldown;

	@Inject(method = "doItemUse", at = @At("RETURN"))
	private void onDoItemUse(CallbackInfo info) {
		MinecraftClient client = (MinecraftClient) (Object) this;
		if (client.player != null && (client.player.getMainHandStack().getItem() instanceof UzisItem || client.player.getOffHandStack().getItem() instanceof UzisItem)) {
			this.itemUseCooldown = 2;
		}
	}
}
