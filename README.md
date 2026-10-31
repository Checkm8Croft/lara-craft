# 🧭 Lara Craft

A Minecraft 1.21.1 Fabric mod inspired by classic Tomb Raider gameplay.

This mod brings a small set of iconic survival and combat elements into Minecraft, focused on recreating the feeling of old Tomb Raider games inside a blocky world.

---

## 🧰 Features

### ❤️ Medikit System
- Small Medikit
- Large Medikit  
  Used to restore health during exploration and combat.

### 🔫 Weapons
- Pistols (fast and reliable)
- Magnum Pistols (high damage, slower fire rate)
- Shotgun (close-range burst damage)
- Uzis (rapid-fire submachine guns)

### 🧨 Exploration & Lighting
- **Flares (Bengala)**: Iconic Tomb Raider 2 flare. 
  - 2D sprite in inventory & hotbar.
  - Full 3D model in hand (with dynamic light, sparks, smoke and original audio).
  - Burns for 40 seconds, works underwater just like TR2!

### 💣 Ammo System
- Each weapon uses its own ammo type
- Ammo is required to fire weapons (Magnums, Shotgun, Uzis)

---

## 🎮 Goal

Lara Craft is not a full conversion mod, but a lightweight tribute to classic Tomb Raider gameplay inside Minecraft, mixing exploration survival with simple weapon mechanics.

---

## ⚙️ Requirements

- Minecraft 1.21.1
- Fabric Loader
- Fabric API
- Fabric Language Kotlin

---

## 🛠 Usage

Use `/give` to obtain items:

```mcfunction
/give @p lara-craft:pistols
/give @p lara-craft:magnums
/give @p lara-craft:magnum_ammo
/give @p lara-craft:shotgun
/give @p lara-craft:shotgun_ammo
/give @p lara-craft:uzis
/give @p lara-craft:uzi_ammo
/give @p lara-craft:flare
/give @p lara-craft:small_medikit
/give @p lara-craft:large_medikit
```
