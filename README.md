# Modern Chess Game / 现代国际象棋游戏

[English](#english) | [中文](#中文)

---

## English

### 📖 Overview

A modern, feature-rich chess game built with Java Swing, offering both Player vs Player (PvP) and Player vs AI (PvE) modes with stunning visual effects and multiple difficulty levels.

### ✨ Features

#### 🎮 Game Modes

- **Player vs Player (PvP)** - Play against a friend locally
- **Player vs AI (PvE)** - Challenge the computer with 5 difficulty levels
- **Amusement Mode** - Special mode with larger boards (2x or 4x) and dynamic fog of war

#### 🤖 AI Difficulty Levels

| Difficulty | Skill | Depth | Elo Rating | Description |
|-----------|-------|-------|------------|-------------|
| **Beginner** | 0 | 1 | ~800 | Random moves, perfect for beginners |
| **Easy** | 5 | 5 | ~1500 | Amateur hobbyist level |
| **Medium** | 10 | 10 | ~2200 | National master level |
| **Hard** | 15 | 15 | ~2800 | Grandmaster level (Carlsen-tier) |
| **God-like** | 20 | 20+ | 3500+ | Unbeatable, no human can win |

#### � Stockfish Engine Support

The game includes a **ChessAIController** class that provides full UCI (Universal Chess Interface) protocol support for external chess engines like Stockfish.

**Features:**

- **UCI Protocol** - Standard communication with any UCI-compatible engine
- **FEN Conversion** - Automatic board state conversion to FEN format
- **Async Processing** - Non-blocking AI move calculation
- **Skill Level Mapping** - Maps difficulty (0-20) to engine search depth
- **Process Management** - Automatic engine startup, shutdown, and cleanup
- **Error Handling** - Graceful handling of missing engine or crashes

**How to Use Stockfish:**

1. Download Stockfish from [stockfishchess.org](https://stockfishchess.org/)
2. Place `stockfish.exe` (Windows) or `stockfish` (Mac/Linux) in the project directory
3. The game will automatically detect and use it when available
4. Falls back to internal AI if Stockfish is not found

**Supported Engines:**

- Stockfish (recommended)
- Any UCI-compatible chess engine

#### �🎨 Visual Features

- **Modern Dark Theme** - Sleek, professional UI design
- **Player Avatars** - Dynamic avatars that change based on game mode and difficulty
  - Player avatar for human players
  - Cute robot for Beginner/Easy AI
  - Student with glasses for Medium/Hard AI
  - Mysterious master for God-like AI
- **Smooth Animations** - Piece movement animations and visual effects
- **Screen Shake** - Dramatic effect when the king is in check
- **Fog of War** - Dynamic fog effect in Amusement mode

#### ⚙️ Advanced Features

- **Checkmate Detection** - Automatic game-over when checkmate occurs
- **Move History** - Full PGN-format move recording
- **Replay System** - Review your games move by move
- **Undo Function** - Take back up to 3 moves
- **Timer Mode** - Optional chess clock for timed games
- **Resolution Options** - 5 preset resolutions plus fullscreen mode
- **Volume Control** - Adjustable sound effects volume

### 🎯 How to Play

#### Starting the Game

1. Run `java ChessGame` from the project directory
2. Choose your game mode from the main menu:
   - **PvE Chess** - Play against AI
   - **Amusement Chess** - Special mode with unique features
   - **Settings** - Adjust volume and window resolution

#### Controls

- **Mouse Click** - Select and move pieces
- **Undo Button** - Take back moves (3 maximum)
- **Replay Controls** - Navigate through move history
- **Timer Toggle** - Enable/disable chess clock
- **Fog of War Toggle** - Enable/disable fog effect (Amusement mode)

### 🛠️ Technical Details

#### Requirements

- Java 11 or higher
- Windows/Mac/Linux operating system

#### Project Structure

```text
d:\game_project\
├── ChessGame.java          # Main game file (all-in-one)
├── stockfish.exe           # Optional: Stockfish engine (Windows)
├── stockfish               # Optional: Stockfish engine (Mac/Linux)
├── assets/                 # Avatar images
│   ├── player.png         # Human player avatar
│   ├── ai_easy.png        # Easy AI avatar (robot)
│   ├── ai_medium.png      # Medium AI avatar (student)
│   └── ai_hard.png        # Hard AI avatar (master)
└── README.md              # This file
```

#### Compilation & Execution

```bash
# Compile
javac -encoding UTF-8 ChessGame.java

# Run
java ChessGame
```

### 🎨 Screenshots

The game features:

- Modern dark-themed UI with gradient backgrounds
- Circular player avatars at the top of the game screen
- Highlighted valid moves and capture indicators
- Smooth piece animations
- Professional difficulty selection screen

### 🔧 Settings

#### Resolution Options

- 900 x 700 (Small)
- 1200 x 900 (Standard)
- 1400 x 1000 (Large)
- 1600 x 1200 (Extra Large)
- Fullscreen (Maximized)

#### Sound

- Adjustable volume slider (0-100%)
- Sound effects for moves, captures, and checkmate

### 📝 Game Rules

Standard chess rules apply:

- Checkmate ends the game immediately
- Pawn promotion to Queen upon reaching the opposite end
- All standard piece movements (Pawn, Rook, Knight, Bishop, Queen, King)

### 🐛 Known Issues

- Font rendering errors may appear in console (Java internal issue, doesn't affect gameplay)
- Stockfish integration available but internal AI is primary

### 📄 License

This project is open source and available for educational purposes.

---

## 中文

### 📖 项目简介

一款使用Java Swing开发的现代化国际象棋游戏，支持玩家对战(PvP)和人机对战(PvE)模式，拥有精美的视觉效果和多个难度等级。

### ✨ 功能特性

#### 🎮 游戏模式

- **玩家对战 (PvP)** - 与好友本地对战
- **人机对战 (PvE)** - 挑战电脑，5个难度等级可选
- **娱乐模式** - 特殊模式，支持更大的棋盘(2倍或4倍)和动态战争迷雾

#### 🤖 AI难度等级

| 难度 | 技能等级 | 搜索深度 | Elo等级分 | 描述 |
|------|---------|---------|-----------|------|
| **入门** | 0 | 1 | ~800 | 随机走棋，纯新手也能赢 |
| **简单** | 5 | 5 | ~1500 | 业余爱好者水平，开始有战术 |
| **中等** | 10 | 10 | ~2200 | 国家大师级别，普通人极难获胜 |
| **高手** | 15 | 15 | ~2800 | 特级大师水平（如卡尔森），人类天花板 |
| **神级** | 20 | 20+ | 3500+ | 不可战胜，目前全球没有任何人类能赢它 |

#### � Stockfish引擎支持

游戏包含一个 **ChessAIController** 类，提供完整的UCI（通用国际象棋接口）协议支持，可与Stockfish等外部国际象棋引擎通信。

**功能特性：**

- **UCI协议** - 与任何UCI兼容引擎的标准通信
- **FEN转换** - 自动将棋盘状态转换为FEN格式
- **异步处理** - 非阻塞式AI计算
- **难度映射** - 将难度等级(0-20)映射到引擎搜索深度
- **进程管理** - 自动启动、关闭和清理引擎
- **错误处理** - 优雅处理引擎缺失或崩溃

**如何使用Stockfish：**

1. 从 [stockfishchess.org](https://stockfishchess.org/) 下载Stockfish
2. 将 `stockfish.exe`（Windows）或 `stockfish`（Mac/Linux）放到项目目录
3. 游戏会自动检测并使用它
4. 如果未找到Stockfish，会自动回退到内置AI

**支持的引擎：**

- Stockfish（推荐）
- 任何UCI兼容的国际象棋引擎

#### �🎨 视觉特性

- **现代深色主题** - 时尚专业的UI设计
- **玩家头像** - 根据游戏模式和难度动态变化的头像
  - 玩家头像用于人类玩家
  - 呆萌机器人用于入门/简单AI
  - 戴眼镜的学生用于中等/高手AI
  - 神秘大师用于神级AI
- **流畅动画** - 棋子移动动画和视觉效果
- **屏幕震动** - 将军时的震撼效果
- **战争迷雾** - 娱乐模式中的动态迷雾效果

#### ⚙️ 高级功能

- **将死检测** - 将死时自动结束游戏
- **棋谱记录** - 完整的PGN格式棋谱记录
- **回放系统** - 逐步回顾您的对局
- **悔棋功能** - 最多可悔3步棋
- **计时模式** - 可选的国际象棋计时器
- **分辨率选项** - 5种预设分辨率加全屏模式
- **音量控制** - 可调节的音效音量

### 🎯 游戏玩法

#### 开始游戏

1. 在项目目录运行 `java ChessGame`
2. 从主菜单选择游戏模式：
   - **PvE Chess** - 人机对战
   - **Amusement Chess** - 娱乐模式
   - **Settings** - 调整音量和窗口分辨率

#### 操作方式

- **鼠标点击** - 选择和移动棋子
- **悔棋按钮** - 撤销移动（最多3次）
- **回放控制** - 浏览移动历史
- **计时器开关** - 启用/禁用计时器
- **战争迷雾开关** - 启用/禁用迷雾效果（娱乐模式）

### 🛠️ 技术细节

#### 系统要求

- Java 11 或更高版本
- Windows/Mac/Linux 操作系统

#### 项目结构

```text
d:\game_project\
├── ChessGame.java          # 主游戏文件（一体化）
├── stockfish.exe           # 可选：Stockfish引擎（Windows）
├── stockfish               # 可选：Stockfish引擎（Mac/Linux）
├── assets/                 # 头像图片
│   ├── player.png         # 人类玩家头像
│   ├── ai_easy.png        # 简单AI头像（机器人）
│   ├── ai_medium.png      # 中等AI头像（学生）
│   └── ai_hard.png        # 困难AI头像（大师）
└── README.md              # 本文件
```

#### 编译与运行

```bash
# 编译
javac -encoding UTF-8 ChessGame.java

# 运行
java ChessGame
```

### 🎨 游戏截图

游戏特色：

- 现代深色主题UI，渐变背景
- 游戏界面顶部显示圆形玩家头像
- 高亮显示合法移动和吃子指示
- 流畅的棋子移动动画
- 专业的难度选择界面

### 🔧 设置选项

#### 分辨率选项

- 900 x 700（小窗口）
- 1200 x 900（标准）
- 1400 x 1000（大窗口）
- 1600 x 1200（超大）
- 全屏（最大化）

#### 音效

- 可调节音量滑块（0-100%）
- 移动、吃子和将死的音效

### 📝 游戏规则

遵循标准国际象棋规则：

- 将死立即结束游戏
- 兵到达对方底线升变为后
- 所有标准棋子移动规则（兵、车、马、象、后、王）

### 🐛 已知问题

- 控制台可能出现字体渲染错误（Java内部问题，不影响游戏）
- 支持Stockfish集成但主要使用内置AI

### 📄 许可证

本项目开源，可用于教育目的。

---

## 🎮 Enjoy the Game! / 享受游戏

Made with ❤️ using Java Swing
