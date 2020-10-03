# Connect Four

This is a basic Connect Four game created to incorporate knowledge of Java classes, GUI, and decision tree mechanics.
This package contains two separate "AI." One AI is called RandomAI and makes random moves every turn. The other AI, CalvinAI,
uses a decision tree to look 4 levels deep to make the move that will give it the best opportunity to win. While CalvinAI's mechanics
are still somewhat elementary, on average it beats RandomAI 95% of the time.

## Usage

Clone the git repo and run the java file found in the main folder. By default, a game will be created between a human player and CalvinAI.

Enjoy!

## Configuration

To alter game modes, open the java file and change the Player parameters of the GUICF object.

- A game between two human players has no parameters.

- A game between one human player and one AI has an AI object as the single parameter.

- A game between two AIs has two AI objects as the two parameters.

Try out games against both RandomAI and Calvin AI.

## Author

Calvin Gee

calving314@gmail.com
