



## How to Run on macOS

Because macOS blocks unknown scripts by default for security, other Mac users need to give the launcher permission to run the first time they download it. 

Follow these quick steps to launch the game:

### 1. Download the files in the release

### 2. Open Terminal in the Game Folder
1. Open your Mac's **Finder** and navigate to the `src` folder containing the game files.
2. Right-click (or two-finger tap) the `src` folder.
3. Select **New Terminal at Folder** (or *Services > New Terminal at Folder*).

### 3. Authorize the Launcher
Paste the following command into the Terminal window that pops up and press **Enter**:
```bash
chmod +x PlayGame.command
```
*(You will only ever need to do this step once).*

### 4. Play the Game!
You can now close the Terminal. Going forward, simply **double-click `PlayGame.command`** directly inside your Finder to launch the game instantly!


