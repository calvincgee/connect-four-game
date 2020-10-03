# Connect Four

By Calvin Gee


This is a basic Connect Four game created in PIC 20A to incorporate knowledge of Java classes, GUI, and decision tree mechanics.
This package contains two separate "AI." One AI is called RandomAI and makes random moves every turn. The other AI, CalvinAI,
uses a decision tree to look 4 levels deep to make the move that will give it the best opportunity to win. While CalvinAI's mechanics
are still somewhat elementary, on average it beats RandomAI 95% of the time.

The user can create a game between two human players (create a new instance of GUICF with no parameters), one human player and one AI
(create an instance of an AI and make that the sole parameter for a new GUICF instance), or two AIs (put two AIs as the parameters of a new GUICF instance).

## Usage

Clone the gir repo and run the java file found here.
To play a game, open command line to the main directory and run java play. This will by default create a game between a human player and CalvinAI. Enjoy! 
