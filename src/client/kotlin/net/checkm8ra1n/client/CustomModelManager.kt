package net.checkm8ra1n.client

import net.fabricmc.fabric.api.client.model.loading.v1.FabricBakedModelManager
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.model.BakedModel
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

object CustomModelManager {

    val FLARE_3D_ID: Identifier = Identifier.of("lara-craft", "item/flare_3d")
    val PISTOLS_3D_ID: Identifier = Identifier.of("lara-craft", "item/pistols_3d")

    fun init() {
        ModelLoadingPlugin.register { pluginContext ->
            pluginContext.addModels(FLARE_3D_ID, PISTOLS_3D_ID)
        }
    }

    fun get3DModel(stack: ItemStack): BakedModel? {
        val client = MinecraftClient.getInstance() ?: return null
        val bakedModelManager = client.bakedModelManager as? FabricBakedModelManager ?: return null
        val id = Registries.ITEM.getId(stack.item)

        if (id.namespace != "lara-craft") return null

        return when (id.path) {
            "flare", "lit_flare" -> {
                val model = bakedModelManager.getModel(FLARE_3D_ID)
                if (model != null && model != client.bakedModelManager.missingModel) model else null
            }
            "pistols" -> {
                val model = bakedModelManager.getModel(PISTOLS_3D_ID)
                if (model != null && model != client.bakedModelManager.missingModel) model else null
            }
            else -> null
        }
    }
}
