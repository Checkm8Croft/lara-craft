package net.checkm8ra1n.client

import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack

object GunRenderer {

    fun render(
        stack: ItemStack,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {

        matrices.push()

        matrices.translate(0.25, -0.25, -0.4)
        matrices.scale(0.75f, 0.75f, 0.75f)

        println("Gun render: " + stack.item)

        matrices.pop()
    }
}