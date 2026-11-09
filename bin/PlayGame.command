#!/bin/bash
# Move to the folder where this launcher script sits
cd "$(dirname "$0")"

# Clear the terminal window for a clean game look
clear

# Run your compiled Java program
java GameDriver

# Prevent the terminal from snapping shut instantly when the game ends
echo ""
read -p "Press [Enter] to exit..."
