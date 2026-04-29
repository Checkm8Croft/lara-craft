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

class MagnumsItem(
    settings: Settings,
    private val ammoItem: Item
) : Item(settings) {

    companion object {
        val MAGNUM_SHOT: SoundEvent = Registry.register(
            Registries.SOUND_EVENT,
            Identifier.of("lara-craft", "magnums"),
            SoundEvent.of(Identifier.of("lara-craft", "magnums"))
        )
    }

    private val cooldown = 350L

    override fun use(
        world: World,
        player: PlayerEntity,
        hand: Hand
    ): TypedActionResult<ItemStack> {

        val stack = player.getStackInHand(hand)

        if (world.isClient) {
            return TypedActionResult.success(stack)
        }

        if (!hasAmmo(player)) {
            return TypedActionResult.fail(stack)
        }

        val success = GunSystem.shoot(
            world = world,
            player = player,
            range = 50.0,
            damage = 4.0f,
            sound = MAGNUM_SHOT,
            cooldownMs = cooldown,
            pitch = 1.0f
        )

        // 💥 ammo SOLO se sparo valido
        if (success) {
            consumeAmmo(player)
        }

        return TypedActionResult.success(stack)
    }

    private fun hasAmmo(player: PlayerEntity): Boolean {
        for (i in 0 until player.inventory.size()) {
            val stack = player.inventory.getStack(i)
            if (stack.item == ammoItem && stack.count > 0) {
                return true
            }
        }
        return false
    }

    private fun consumeAmmo(player: PlayerEntity) {
        for (i in 0 until player.inventory.size()) {
            val stack = player.inventory.getStack(i)

            if (stack.item == ammoItem) {
                stack.decrement(1)
                return
            }
        }
    }
}