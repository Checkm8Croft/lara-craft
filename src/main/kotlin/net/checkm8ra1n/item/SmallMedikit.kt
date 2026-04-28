package net.checkm8ra1n.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class SmallMedikitItem(settings: Settings) : Item(settings) {

    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = player.getStackInHand(hand)

        if (!world.isClient) {
            // 3.5 cuori = 7 HP
            player.heal(7.0f)

            // consuma 1 medikit
            stack.decrement(1)
        }

        return TypedActionResult.success(stack, world.isClient)
    }
}