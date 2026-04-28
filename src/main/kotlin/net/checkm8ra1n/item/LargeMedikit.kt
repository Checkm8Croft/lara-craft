package net.checkm8ra1n.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Item.Settings
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class LargeMedikitItem(settings: Settings) : Item(settings) {

    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = player.getStackInHand(hand)

        if (!world.isClient) {

            val missingHealth = player.maxHealth - player.health

            if (missingHealth > 0) {
                player.heal(missingHealth)
            }

            stack.decrement(1)
        }

        return TypedActionResult.success(stack, world.isClient)
    }
}