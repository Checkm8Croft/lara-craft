package net.checkm8ra1n.client

import net.fabricmc.api.ClientModInitializer

object LaraCraftClient : ClientModInitializer {
	override fun onInitializeClient() {
		CustomModelManager.init()
	}
}
