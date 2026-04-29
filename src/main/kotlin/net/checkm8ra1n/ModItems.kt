package net.checkm8ra1n

import net.checkm8ra1n.item.SmallMedikitItem
import net.checkm8ra1n.item.LargeMedikitItem
import net.checkm8ra1n.item.PistolsItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModItems {

    val SMALL_MEDIKIT = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "small_medikit"),
        SmallMedikitItem(Item.Settings().maxCount(16))
    )

    val LARGE_MEDIKIT = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "large_medikit"),
        LargeMedikitItem(Item.Settings().maxCount(8))
    )
    val PISTOLS = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "pistols"),
        PistolsItem(Item.Settings().maxCount(1))
    )

    fun register() {
        println("ModItems registrati")
    }
}