<div align="center">

[日本語](README.md) | **English**

# Copy Item Name

### Minecraft Item Name Copy Mod

<img src="https://img.shields.io/badge/Minecraft-1.21.11-62B47A?style=for-the-badge&logo=minecraft&logoColor=white" alt="Minecraft">
<img src="https://img.shields.io/badge/Fabric-Mod_Loader-DBD0B4?style=for-the-badge" alt="Fabric">
<img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
<img src="https://img.shields.io/badge/License-PolyForm_NC-blue?style=for-the-badge" alt="License">

**Middle-click any item in your inventory to copy its English name or ID to clipboard.**

---

</div>

## Overview

When playing Minecraft in a non-English language, you often need item names in English for commands, wikis, or trading. This mod lets you middle-click any item in your inventory to instantly copy its English name or item ID to your clipboard.

## Features

| Feature | Description |
|:---:|---|
| **Middle-click to Copy** | Just press the mouse wheel button on any item in inventory |
| **Three Modes** | Switch between `English Name`, `Item ID` (`minecraft:trident`), and `Component` (full component data) |
| **Enchanted Book Support** | Copies all enchantment names with levels (e.g., `Sharpness V, Unbreaking III`) |
| **ModMenu Integration** | Toggle modes from the in-game settings screen |
| **Action Bar Notification** | Shows a confirmation message when copied |
| **Persistent Config** | Settings saved to `config/copyitemname.json` |

## Requirements

- **Minecraft 1.21.11**
- **[Fabric Loader](https://fabricmc.net/) 0.18.0+**
- **Fabric API**
- **Java 21**
- [ModMenu](https://modrinth.com/mod/modmenu) (optional — for settings screen)

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/)
2. Download [`copy-item-name-1.0.0.jar`](https://github.com/cUDGk/CopyItemName/releases/download/v1.0.0/copy-item-name-1.0.0.jar)
3. Place in `.minecraft/mods/` and launch

## Usage

1. Open your inventory (`E` key)
2. Hover over the item you want to copy
3. Press **middle mouse button** (mouse wheel click)
4. Done — the name is in your clipboard

### Mode Switching

With ModMenu installed: `Mods` → `Copy Item Name` → Settings button.

| Mode | Copies | Example |
|:---:|---|---|
| **English Name** | Item's English display name | `Trident` |
| **Item ID** | Namespaced item ID | `minecraft:trident` |
| **Component** | Item ID + component data | `minecraft:trident[enchantments={...}]` |

## License

[PolyForm Noncommercial 1.0.0](https://polyformproject.org/licenses/noncommercial/1.0.0/) — Free for non-commercial use, modification, and redistribution. Commercial use is prohibited.
