package net.checkm8ra1n.item

import net.checkm8ra1n.ModItems
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class FlareItem(settings: Settings) : Item(settings) {

    companion object {
        val FLARE_IGNITE: SoundEvent = Registry.register(
            Registries.SOUND_EVENT,
            Identifier.of("lara-craft", "flare_ignite"),
            SoundEvent.of(Identifier.of("lara-craft", "flare_ignite"))
        )
    }

    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = player.getStackInHand(hand)

        if (!world.isClient) {
            world.playSound(
                null,
                player.blockPos,
                FLARE_IGNITE,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f
            )

            val litStack = ItemStack(ModItems.LIT_FLARE)

            if (!player.abilities.creativeMode) {
                stack.decrement(1)
            }

            if (stack.isEmpty) {
                return TypedActionResult.success(litStack, world.isClient)
            } else {
                if (!player.inventory.insertStack(litStack)) {
                    player.dropItem(litStack, false)
                }
            }
        }

        return TypedActionResult.success(stack, world.isClient)
    }
}

