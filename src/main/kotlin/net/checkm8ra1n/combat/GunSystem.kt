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
        pitch: Float = 1.0f
    ) {

        // ❌ evita doppio lato
        if (world.isClient) return

        // ⏱ cooldown
        if (!canShoot(cooldownMs)) return

        // 🎯 direzione sparo (centro camera)
        val start: Vec3d = player.eyePos
        val direction: Vec3d = player.rotationVector.multiply(range)
        val end: Vec3d = start.add(direction)

        // 📦 “linea di fuoco” ingrandita per non perdere hit ravvicinati
        val box = Box(start, end).expand(0.5)

        // 🔍 trova entità lungo il colpo
        val entities = world.getOtherEntities(player, box)

        val target = entities
            .filterIsInstance<LivingEntity>()
            .minByOrNull { it.squaredDistanceTo(player) }

        if (target != null) {
            target.damage(
                world.damageSources.playerAttack(player),
                damage
            )
        }

        // 🔊 suono sparo
        world.playSound(
            null,
            player.blockPos,
            sound,
            SoundCategory.PLAYERS,
            1.0f,
            pitch
        )

        registerShot()
    }
}