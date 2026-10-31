package net.checkm8ra1n.item

import net.checkm8ra1n.combat.FlareLightManager
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class LitFlareItem(settings: Settings) : Item(settings) {

    companion object {
        val FLARE_BURN: SoundEvent = Registry.register(
            Registries.SOUND_EVENT,
            Identifier.of("lara-craft", "flare_burn"),
            SoundEvent.of(Identifier.of("lara-craft", "flare_burn"))
        )

        const val MAX_BURN_TICKS = 800 // 40 seconds
    }

    override fun inventoryTick(
        stack: ItemStack,
        world: World,
        entity: Entity,
        slot: Int,
        selected: Boolean
    ) {
        val isOffHand = entity is LivingEntity && entity.offHandStack == stack
        val isHeld = selected || isOffHand

        if (world.isClient) {
            if (isHeld) {
                val look = entity.rotationVector
                val right = look.crossProduct(Vec3d(0.0, 1.0, 0.0)).normalize()

                // Position particles directly at the flare in the hand (to the side and down, NOT in the face)
                val sideOffset = if (isOffHand) -0.45 else 0.45
                val spawnPos = entity.eyePos
                    .add(right.multiply(sideOffset))
                    .add(look.multiply(0.35))
                    .add(0.0, -0.38, 0.0)

                val random = world.random

                // Small flame at the flare tip
                if (random.nextFloat() < 0.40f) {
                    world.addParticle(
                        ParticleTypes.SMALL_FLAME,
                        spawnPos.x, spawnPos.y, spawnPos.z,
                        (random.nextDouble() - 0.5) * 0.01,
                        0.015,
                        (random.nextDouble() - 0.5) * 0.01
                    )
                }

                // Subtle wisp of smoke (low density, never blocking vision)
                if (random.nextFloat() < 0.15f) {
                    world.addParticle(
                        ParticleTypes.SMOKE,
                        spawnPos.x, spawnPos.y, spawnPos.z,
                        (random.nextDouble() - 0.5) * 0.01,
                        0.02,
                        (random.nextDouble() - 0.5) * 0.01
                    )
                }

                // Occasional tiny spark
                if (random.nextFloat() < 0.08f) {
                    world.addParticle(
                        ParticleTypes.LAVA,
                        spawnPos.x, spawnPos.y, spawnPos.z,
                        0.0, 0.0, 0.0
                    )
                }
            }
            return
        }

        // Server-side logic
        FlareLightManager.updateLight(world, entity, isLit = isHeld)

        if (isHeld) {
            // Loop subtle burn sound every ~30 ticks (1.5s)
            if (entity.age % 30 == 0) {
                world.playSound(
                    null,
                    entity.blockPos,
                    FLARE_BURN,
                    SoundCategory.PLAYERS,
                    0.6f,
                    1.0f
                )
            }

            // Burn down durability
            val currentDamage = stack.damage + 1
            if (currentDamage >= stack.maxDamage) {
                stack.decrement(1)
                FlareLightManager.updateLight(world, entity, isLit = false)
                world.playSound(
                    null,
                    entity.blockPos,
                    SoundEvents.BLOCK_FIRE_EXTINGUISH,
                    SoundCategory.PLAYERS,
                    0.7f,
                    1.2f
                )
            } else {
                stack.damage = currentDamage
            }
        }
    }
}
