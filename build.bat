@echo off
setlocal enabledelayedexpansion
chcp 65001 >nul
echo ========================================
echo 构建Modern Chess游戏
echo Build Modern Chess Game
echo ========================================
echo.

REM 步骤1: 编译Java文件
echo [1/3] 编译Java文件...
echo [1/3] Compiling Java files...
javac -encoding UTF-8 ChessGame.java
if errorlevel 1 (
    echo [错误] 编译失败！
    echo [ERROR] Compilation failed!
    pause
    exit /b 1
)
echo [✓] 编译成功
echo [✓] Compilation successful
echo.

REM 步骤2: 创建JAR文件
echo [2/3] 创建JAR文件...
echo [2/3] Creating JAR file...

REM 创建build目录
if not exist "build" mkdir build

REM 创建manifest文件
(
echo Manifest-Version: 1.0
echo Main-Class: ChessGame
echo.
) > build\manifest.txt

REM 使用Java自带的jar工具
REM 首先尝试直接调用jar
where jar >nul 2>&1
if not errorlevel 1 (
    jar cfm build\ChessGame.jar build\manifest.txt *.class
) else (
    REM 如果jar不在PATH中，尝试从java.exe所在目录查找
    for /f "delims=" %%i in ('where java 2^>nul') do (
        set "JAVA_EXE=%%i"
        goto :found_java
    )
    :found_java
    if defined JAVA_EXE (
        for %%i in ("!JAVA_EXE!") do set "JAVA_DIR=%%~dpi"
        if exist "!JAVA_DIR!jar.exe" (
            "!JAVA_DIR!jar.exe" cfm build\ChessGame.jar build\manifest.txt *.class
        ) else (
            echo [错误] 找不到jar.exe！
            echo [ERROR] jar.exe not found!
            echo.
            echo 请确保Java JDK已正确安装（不是JRE）
            echo Please ensure Java JDK is properly installed (not JRE)
            pause
            exit /b 1
        )
    ) else (
        echo [错误] 找不到Java！
        echo [ERROR] Java not found!
        pause
        exit /b 1
    )
)

if errorlevel 1 (
    echo [错误] JAR创建失败！
    echo [ERROR] JAR creation failed!
    pause
    exit /b 1
)

REM 复制assets到build目录
if exist "assets" (
    if not exist "build\assets" mkdir build\assets
    xcopy /E /I /Y assets build\assets >nul 2>&1
    echo [✓] 已复制资源文件
    echo [✓] Copied asset files
)

echo [✓] JAR文件创建成功
echo [✓] JAR file created successfully
echo.

REM 步骤3: 检查Launch4j
echo [3/3] 准备创建EXE...
echo [3/3] Preparing to create EXE...
echo.

echo ========================================
echo [✓] 构建完成！
echo [✓] Build completed!
echo ========================================
echo.
echo 生成的文件 / Generated files:
echo   - build\ChessGame.jar
echo.
echo 下一步 / Next steps:
echo.
echo 方法1: 使用Launch4j GUI（推荐）
echo Method 1: Use Launch4j GUI (Recommended)
echo   1. 下载并安装Launch4j: https://launch4j.sourceforge.net/
echo   2. 打开Launch4j程序
echo   3. File → Load Config → 选择 launch4j-config.xml
echo   4. 点击齿轮图标（Build wrapper）
echo   5. 完成！会生成 ModernChess.exe
echo.
echo ========================================
echo 捆绑JRE配置已启用！
echo Bundled JRE configuration enabled!
echo ========================================
echo.
echo exe将使用项目中的jre文件夹，用户无需安装Java！
echo The exe will use the jre folder in the project, users don't need Java!
echo.
echo 分发时请包含 / Please include when distributing:
echo   - ModernChess.exe
echo   - jre\ (整个文件夹 / entire folder)
echo   - assets\ (如果有 / if exists)
echo.
pause
