# Pong-Game  ![pong](https://github.com/enes-plt/Pong-Game/assets/152672327/b59c880f-8ae8-45a3-8918-4d3908c77e40)



Pong-Game via Java

This Java Pong game project showcases core features like object-oriented programming, Swing for GUI, and multi-threading. It uses distinct classes (PongGame, GameFrame, GamePanel, Paddle, Ball, Score) for modular design and data encapsulation. Inheritance is leveraged with Ball and Paddle extending Rectangle for shared properties.

Swing components GameFrame (extends JFrame) and GamePanel (extends JPanel) manage GUI and rendering via Graphics, employing double buffering for smooth display. Keyboard input is handled by an inner class AL (extends KeyAdapter) in GamePanel. Multi-threading ensures the game loop runs independently for real-time updates, managed by implementing Runnable and using Threads.

Random class is used for ball initialization, ensuring varied gameplay. Collision detection utilizes Rectangle's geometric methods. The project employs switch statements in Paddle for efficient event handling based on paddle ID.

In summary, this project integrates Java's object-oriented approach, GUI development with Swing, and multi-threading to deliver an engaging and dynamic Pong game.

