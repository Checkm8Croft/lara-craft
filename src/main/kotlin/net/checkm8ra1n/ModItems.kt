package net.checkm8ra1n

import net.checkm8ra1n.item.*
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModItems {

    // 🔫 MEDKIT
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

    // 🔫 PISTOLS
    val PISTOLS = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "pistols"),
        PistolsItem(Item.Settings().maxCount(1))
    )

    // 💣 MAGNUM AMMO
    val MAGNUM_AMMO = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "magnum_ammo"),
        MagnumAmmo(Item.Settings())
    )

    // 🔫 MAGNUMS
    val MAGNUMS = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "magnums"),
        MagnumsItem(
            Item.Settings().maxCount(1),
            MAGNUM_AMMO
        )
    )

    // 💣 SHOTGUN AMMO
    val SHOTGUN_AMMO = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "shotgun_ammo"),
        ShotgunAmmo(Item.Settings())
    )

    // 🔫 SHOTGUN
    val SHOTGUN = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "shotgun"),
        ShotgunItem(
            Item.Settings().maxCount(1),
            SHOTGUN_AMMO
        )
    )
}