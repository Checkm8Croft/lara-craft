package net.checkm8ra1n.item

import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModItems {

    val PISTOLS: Item = register("pistols", Item(Item.Settings()))

    private fun register(name: String, item: Item): Item {
        return Registry.register(
            Registries.ITEM,
            Identifier.of("lara-craft", name),
            item
        )
    }

    fun init() {}
}