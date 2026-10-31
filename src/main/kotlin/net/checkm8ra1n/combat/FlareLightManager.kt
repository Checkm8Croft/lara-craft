package net.checkm8ra1n.combat

import net.minecraft.block.Blocks
import net.minecraft.block.LightBlock
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.UUID

object FlareLightManager {

    private data class LightState(
        val pos: BlockPos,
        val wasWater: Boolean
    )

    private val playerLights = mutableMapOf<UUID, LightState>()

    fun updateLight(world: World, entity: Entity, isLit: Boolean) {
        if (world.isClient) return

        val id = entity.uuid
        val currentHeadPos = BlockPos.ofFloored(entity.eyePos)

        val previous = playerLights[id]

        if (!isLit) {
            if (previous != null) {
                removeLight(world, previous)
                playerLights.remove(id)
            }
            return
        }

        if (previous != null && previous.pos != currentHeadPos) {
            removeLight(world, previous)
            playerLights.remove(id)
        }

        if (playerLights[id] == null) {
            val state = world.getBlockState(currentHeadPos)
            if (state.isAir) {
                world.setBlockState(
                    currentHeadPos,
                    Blocks.LIGHT.defaultState.with(LightBlock.LEVEL_15, 15),
                    3
                )
                playerLights[id] = LightState(currentHeadPos, false)
            } else if (state.isOf(Blocks.WATER) && state.fluidState.isStill) {
                world.setBlockState(
                    currentHeadPos,
                    Blocks.LIGHT.defaultState
                        .with(LightBlock.WATERLOGGED, true)
                        .with(LightBlock.LEVEL_15, 15),
                    3
                )
                playerLights[id] = LightState(currentHeadPos, true)
            }
        }
    }

    private fun removeLight(world: World, light: LightState) {
        val current = world.getBlockState(light.pos)
        if (current.isOf(Blocks.LIGHT)) {
            if (light.wasWater) {
                world.setBlockState(light.pos, Blocks.WATER.defaultState, 3)
            } else {
                world.setBlockState(light.pos, Blocks.AIR.defaultState, 3)
            }
        }
    }
}

