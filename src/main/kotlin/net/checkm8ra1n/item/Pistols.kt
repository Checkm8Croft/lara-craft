package net.checkm8ra1n.item

import net.checkm8ra1n.combat.GunSystem
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

class PistolsItem(settings: Settings) : Item(settings) {

    companion object {
        val PISTOLS_SHOT: SoundEvent = Registry.register(
            Registries.SOUND_EVENT,
            Identifier.of("lara-craft", "pistols"),
            SoundEvent.of(Identifier.of("lara-craft", "pistols"))
        )
    }

    private val cooldown = 200L

    override fun use(
        world: World,
        player: PlayerEntity,
        hand: Hand
    ): TypedActionResult<ItemStack> {

        val stack = player.getStackInHand(hand)

        // 🎮 client = niente logica
        if (world.isClient) {
            return TypedActionResult.success(stack)
        }

        // 🔫 sparo unico tramite sistema
        GunSystem.shoot(
            world = world,
            player = player,
            range = 50.0,
            damage = 1.4f,
            sound = PISTOLS_SHOT,
            cooldownMs = cooldown
        )

        return TypedActionResult.success(stack)
    }
}