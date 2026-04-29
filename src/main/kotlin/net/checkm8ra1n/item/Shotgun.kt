package net.checkm8ra1n.item

import net.checkm8ra1n.combat.GunSystem
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import kotlin.random.Random

class ShotgunItem(
    settings: Settings,
    private val ammoItem: Item
) : Item(settings) {

    companion object {
        val SHOTGUN_SHOT: SoundEvent =
            net.minecraft.registry.Registry.register(
                net.minecraft.registry.Registries.SOUND_EVENT,
                net.minecraft.util.Identifier.of("lara-craft", "shotgun"),
                SoundEvent.of(net.minecraft.util.Identifier.of("lara-craft", "shotgun"))
            )
    }

    private val cooldown = 1000L

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

        val pellets = 6
        var anyHit = false

        repeat(pellets) {

            val success = GunSystem.shoot(
                world = world,
                player = player,
                range = 25.0,
                damage = 2.0f,
                sound = SHOTGUN_SHOT,
                cooldownMs = cooldown,
                pitch = 0.95f,
                spread = 0.25
            )

            if (success) anyHit = true
        }

        // 💥 ammo SOLO se almeno un pellet ha colpito qualcosa
        if (anyHit) {
            consumeAmmo(player)
        }

        return TypedActionResult.success(stack)
    }

    private fun hasAmmo(player: PlayerEntity): Boolean {
        for (i in 0 until player.inventory.size()) {
            val stack = player.inventory.getStack(i)
            if (stack.item == ammoItem && stack.count > 0) return true
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