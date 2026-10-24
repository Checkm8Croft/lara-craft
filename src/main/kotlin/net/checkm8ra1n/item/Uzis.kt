package net.checkm8ra1n.item

import net.checkm8ra1n.combat.GunSystem
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class UzisItem(
    settings: Settings,
    private val ammoItem: Item? = null
) : Item(settings) {

    companion object {
        val UZIS_SHOT: SoundEvent = Registry.register(
            Registries.SOUND_EVENT,
            Identifier.of("lara-craft", "uzis"),
            SoundEvent.of(Identifier.of("lara-craft", "uzis"))
        )
    }

    private val cooldown = 90L

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

        if (ammoItem != null && !player.abilities.creativeMode && !hasAmmo(player)) {
            return TypedActionResult.fail(stack)
        }

        // 🔫 sparo rapido tramite sistema
        val success = GunSystem.shoot(
            world = world,
            player = player,
            range = 50.0,
            damage = 1.0f,
            sound = UZIS_SHOT,
            cooldownMs = cooldown
        )

        // 💥 ammo SOLO se sparo valido
        if (success && ammoItem != null && !player.abilities.creativeMode) {
            consumeAmmo(player)
        }

        return TypedActionResult.success(stack)
    }

    private fun hasAmmo(player: PlayerEntity): Boolean {
        if (ammoItem == null) return true
        for (i in 0 until player.inventory.size()) {
            val stack = player.inventory.getStack(i)
            if (stack.item == ammoItem && stack.count > 0) {
                return true
            }
        }
        return false
    }

    private fun consumeAmmo(player: PlayerEntity) {
        if (ammoItem == null) return
        for (i in 0 until player.inventory.size()) {
            val stack = player.inventory.getStack(i)

            if (stack.item == ammoItem) {
                stack.decrement(1)
                return
            }
        }
    }
}

