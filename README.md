<div align="center">

**日本語** | [English](README_en.md)

# Copy Item Name

### Minecraft アイテム名コピー Mod

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11-62B47A?style=for-the-badge&logo=minecraft&logoColor=white)](https://www.minecraft.net/)
[![Fabric](https://img.shields.io/badge/Fabric-Mod_Loader-DBD0B4?style=for-the-badge)](https://fabricmc.net/)
[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://adoptium.net/)
[![License](https://img.shields.io/badge/License-PolyForm_NC-blue?style=for-the-badge)](LICENSE)

**インベントリでミドルクリックするだけで、アイテムの英語名やIDをコピー。**

---

</div>

## Overview

日本語環境でマイクラをプレイしていると、コマンドやWikiでアイテムの英語名が必要になる場面がよくあります。このModを入れておけば、インベントリを開いてミドルクリックするだけで英語名またはアイテムIDがクリップボードにコピーされます。

## Features

| 機能 | 説明 |
|:---:|---|
| **ミドルクリックでコピー** | インベントリ画面でマウスホイールクリックするだけ |
| **2つのモード** | `English Name`（英語名）と `Item ID`（`minecraft:trident` 形式）を切り替え可能 |
| **エンチャント本対応** | エンチャント名 + レベルをまとめてコピー（例: `Sharpness V, Unbreaking III`） |
| **ModMenu対応** | ゲーム内の設定画面からモード切り替え |
| **アクションバー通知** | コピー完了時に画面下部にメッセージ表示 |
| **設定永続化** | `config/copyitemname.json` に設定を自動保存 |

## Demo

```
[インベントリでダイヤモンドソードをミドルクリック]
→ クリップボード: "Diamond Sword"
→ アクションバー: "Copied: [Name] Diamond Sword"

[モードをItem IDに切り替えてミドルクリック]
→ クリップボード: "minecraft:diamond_sword"
→ アクションバー: "Copied: [ID] minecraft:diamond_sword"

[エンチャント本をミドルクリック]
→ クリップボード: "Sharpness V, Unbreaking III"
```

## Requirements

- **Minecraft 1.21.11**
- **[Fabric Loader](https://fabricmc.net/) 0.18.0+**
- **Fabric API**
- **Java 21**
- [ModMenu](https://modrinth.com/mod/modmenu)（任意 — 設定画面用）

## Installation

1. [Fabric Loader](https://fabricmc.net/use/) をインストール
2. [Releases](../../releases) から `.jar` をダウンロード
3. `.minecraft/mods/` に配置して起動

### ビルドする場合

```bash
git clone https://github.com/cUDGk/CopyItemName.git
cd CopyItemName
./gradlew build
```

`build/libs/copy-item-name-1.0.0.jar` が生成されます。

## Usage

1. ゲーム内でインベントリを開く（`E` キー）
2. コピーしたいアイテムにカーソルを合わせる
3. **マウスホイール（ミドルクリック）** を押す
4. クリップボードにコピー完了

### モード切り替え

ModMenu がインストールされている場合、`Mods` → `Copy Item Name` → 設定ボタンからモードを切り替えられます。

| モード | コピー内容 | 例 |
|:---:|---|---|
| **English Name** | アイテムの英語名 | `Trident` |
| **Item ID** | アイテムの名前空間ID | `minecraft:trident` |

## How It Works

```
ミドルクリック検出 (Mixin)
    ↓
フォーカス中のスロットからItemStack取得
    ↓
モード判定
    ├─ ITEM_ID → Registries からID取得
    └─ ENGLISH_NAME
         ├─ 通常アイテム → en_us.json から翻訳キーで英語名取得
         └─ エンチャント本 → 各エンチャント名 + レベルを結合
    ↓
クリップボードにコピー + アクションバーに通知
```

英語名の取得には、Minecraft のリソースパックに含まれる `en_us.json`（英語の翻訳ファイル）をリソースリロード時に読み込んで使用しています。日本語環境でも常に英語名を返せるのはこの仕組みのおかげです。

## License

[PolyForm Noncommercial 1.0.0](https://polyformproject.org/licenses/noncommercial/1.0.0/) — 非商用目的での利用・改変・再配布が可能です。商用利用は禁止されています。
