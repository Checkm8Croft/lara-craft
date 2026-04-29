package net.checkm8ra1n.combat

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Box
import net.minecraft.world.World

object GunSystem {

    private var lastShotTime: Long = 0

    fun canShoot(cooldownMs: Long): Boolean {
        return System.currentTimeMillis() - lastShotTime >= cooldownMs
    }

    private fun registerShot() {
        lastShotTime = System.currentTimeMillis()
    }

    fun shoot(
        world: World,
        player: PlayerEntity,
        range: Double,
        damage: Float,
        sound: SoundEvent,
        cooldownMs: Long,
        pitch: Float = 1.0f,
        spread: Double = 0.0
    ): Boolean {

        if (world.isClient) return false
        if (!canShoot(cooldownMs)) return false

        val start = player.eyePos

        val baseDir = player.rotationVector

        val right = baseDir.crossProduct(Vec3d(0.0, 1.0, 0.0)).normalize()

        val spreadOffset = right.multiply(spread)

        val direction = baseDir.add(spreadOffset).normalize().multiply(range)

        val end = start.add(direction)

        val box = Box(start, end).expand(0.5)

        val entities = world.getOtherEntities(player, box)

        val target = entities
            .filterIsInstance<LivingEntity>()
            .minByOrNull { it.squaredDistanceTo(player) }

        // ❌ niente hit = sparo NON valido
        if (target == null) return false

        target.damage(
            world.damageSources.playerAttack(player),
            damage
        )

        world.playSound(
            null,
            player.blockPos,
            sound,
            SoundCategory.PLAYERS,
            1.0f,
            pitch
        )

        registerShot()
        return true
    }
}