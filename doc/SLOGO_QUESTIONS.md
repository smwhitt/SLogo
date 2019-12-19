# Planning Questions
* What behaviors should the turtle have?
Turtle should be able to move, place its pen (turn on/off), and set its 
location. Anything regarding its position and abilities are contained within 
the turtle's capabilities.

* What behaviors does the result of the command have to be used by the frontend?
The visual needs to display the option for commands. The command tells the 
backend what to do, which tells the visual how to update. In other words, the 
frontend handles the user interface.

* How is the GUI updated after the command has completed execution?
The result of the command is shown in the turtles' movement, which is visually 
represented in the GUI. Once the command is parsed, the backend updates, and the 
visual GUI also updates appropriately. One interesting idea is to have an in 
progress visual that shows how a command is still being completed.

# SLogo High Level Design
* When does parsing need to take place? What does it need to start properly? 
Parsing starts when the user enters a command and any commands ahead of need 
to have been executed correctly. It starts properly by checking as to whether
or not the user's command is valid and if there are no other commands being run.

* What is the result of parsing and who receives it?
If an error, the visual will show it. If valid and executable, the results will 
be passed to the backend, which then updates accordingly.

* When are errors detected and how are they reported?
Errors are detected in the parsing of the command and are reported back to the 
user for invalid syntax/input. Another possible place for error is if the user 
inputs a command that's valid but not within our simulation, which will also be 
reported back to the user.

* How is the GUI updated after a command has completed execution?
Like mentioned before, after a command has completed execution, the GUI will 
update the turtle's location, whether or not it has a pen, and its angle of 
rotation.