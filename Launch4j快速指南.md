# 🎮 Launch4j 快速打包指南

## ✅ 已准备好的文件

- `build/ChessGame.jar` - 游戏JAR文件（已创建）
- `icon.png` - 游戏图标（已创建）
- `launch4j-config.xml` - Launch4j配置文件（已配置好）
- `assets/` - 游戏资源

## 📝 操作步骤（只需3步！）

### 第1步：下载Launch4j

1. 访问：<https://launch4j.sourceforge.net/>
2. 下载Windows版本
3. 安装（默认设置即可）

### 第2步：转换图标为ICO格式

Launch4j需要.ico格式的图标。

**在线转换（最简单）：**

1. 访问：<https://convertio.co/png-ico/>
2. 点击"Choose Files"按钮
3. 选择项目中的 `icon.png`
4. 点击"Convert"
5. 下载 `icon.ico` 并保存到项目目录（与icon.png同一位置）

**或使用其他在线工具：**

- <https://www.icoconverter.com/>
- <https://cloudconvert.com/png-to-ico>

### 第3步：使用Launch4j创建EXE

1. 打开Launch4j程序
2. 点击菜单：**File → Load Config**
3. 选择项目中的 `launch4j-config.xml`
4. 界面会自动填充配置：
   - Output file: `ModernChess.exe`
   - Jar: `build/ChessGame.jar`
   - Icon: `icon.ico`
5. 点击工具栏的 **齿轮图标**（Build wrapper）
6. 等待3-5秒
7. 完成！在项目目录会生成 `ModernChess.exe`

## 🎮 测试

双击 `ModernChess.exe` 测试游戏！

## 📦 分发给朋友

打包这些文件/文件夹：

```
Modern Chess/
├── ModernChess.exe     ← 主程序
├── build/              ← 包含ChessGame.jar
└── assets/             ← 游戏资源
```

用户只需：

1. 解压文件夹
2. 双击 `ModernChess.exe`
3. 开始游戏！

**要求**：需要安装Java 11+

## ❓ 常见问题

**Q: 双击exe没反应？**
A: 确保Java已安装。命令行运行：`java -version`

**Q: 提示找不到jar？**
A: 确保build文件夹和ModernChess.exe在同一目录

**Q: Launch4j报错？**
A: 检查icon.ico是否存在，路径是否正确

## 🎉 完成

exe文件很小（几KB），启动游戏时会自动调用Java运行jar文件。
这是Java程序最常用的打包方式！
