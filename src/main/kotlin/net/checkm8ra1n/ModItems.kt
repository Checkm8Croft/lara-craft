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

    // 💣 UZI AMMO
    val UZI_AMMO = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "uzi_ammo"),
        UziAmmo(Item.Settings())
    )

    // 🔫 UZIS
    val UZIS = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "uzis"),
        UzisItem(
            Item.Settings().maxCount(1),
            UZI_AMMO
        )
    )

    // 🧨 FLARE (Unlit)
    val FLARE = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "flare"),
        FlareItem(Item.Settings().maxCount(64))
    )

    // 🔥 LIT FLARE (Active)
    val LIT_FLARE = Registry.register(
        Registries.ITEM,
        Identifier.of("lara-craft", "lit_flare"),
        LitFlareItem(Item.Settings().maxCount(1).maxDamage(LitFlareItem.MAX_BURN_TICKS))
    )
}
