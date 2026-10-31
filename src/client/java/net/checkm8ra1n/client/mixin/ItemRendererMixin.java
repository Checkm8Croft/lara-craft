package net.checkm8ra1n.client.mixin;

import net.checkm8ra1n.client.CustomModelManager;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @ModifyVariable(
        method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
        at = @At("HEAD"),
        argsOnly = true
    )
    private BakedModel use3DModelInHand(BakedModel model, ItemStack stack, ModelTransformationMode renderMode) {
        if (renderMode != ModelTransformationMode.GUI) {
            BakedModel model3D = CustomModelManager.INSTANCE.get3DModel(stack);
            if (model3D != null) {
                return model3D;
            }
        }
        return model;
    }
}

