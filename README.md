# Castlevania Hacked
A hacked version of Konami's 1986 video game Castlevania using Java and Scene Builder for CI245: Java Programming

<img src="https://github.com/codercarly/CastlevaniaHack/blob/master/castlevaniahackdemo.gif" width="400">

# Final Project Goals
- Work with a partner to create a game that implements a GUI using the JavaFX platform.
- The project proposal contained information about the game, its rules, and features.
- The project presentation demonstrated the rules of the game, how the code implements the rules, the GUI, and why features weren't implemented if they were proposed. 
- All code must be commented properly.
- Use of external libraries or third-party tools was not permitted.
- Code found on the internet could be used but only under certain conditions: it's not a complete or partial solution or it's a minor function in the overall scheme of things. All code must be commented with the web address of where it was found. (No external code was borrowed for this game.)

# Code Overview
- The GUI itself was created using Scene Builder, which was used in the course text book.
- The game play area was created within a scroll pane and Simon Belmont is located outside the pane (so the background moves and Simon doesn't.) The user controls the game with the keyboard arrow keys and the pane moves based on those key presses. The attack function is with the space bar.
- The biggest obstacles we faced was getting the scroll pane to move accurately and detecting collisions between Simon and the monsters.

# Features
- Simon can walk left and right, jump, crouch, and attack.
- If Simon attacks a monster or a torch, the object will disappear and the user will collect points and hearts.
- If Simon doesn't attack and gets hit by a monster, he will be knocked back to the beginning of the gamef and lose health.
- If Simon loses all of his health bars, he dies and a life is lost.

# Unimplemented Features
- The enemy health bar for the final boss at the end of the game
- The time counter
- (These features weren't implemented due to the time consuming process of making the object collision work properly. We decided that the collision was more important than those minor features, so that's what we focused on.)

# Thoughts
My partner and I truely enjoyed the process of making this game hack. We had a lot of great ideas that we wanted to implement (and still can, of course) but were very satisfied with what we had created after less than 2 weeks of work. There was very little support or guidance available online for game creation for JavaFX (and even less information for Scene Builder.) Most of what we read said it couldn't be done. 
