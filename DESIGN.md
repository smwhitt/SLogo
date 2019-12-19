**What are the project's design goals, specifically what kinds of new features did you want to make easy to add?**

For our design, we attempted to follow a standard MVC model with 4 APIs: internal and external frontend as well 
as internal and external backend. In that way, we focused on making it easier to be able to add new commands to 
the backend or window/visual features. This extendability was crucial to our design process and ideaology, which is why
we attempted to make the internal APIs as general as possible and the controller/external APIs handle the specifics.

**Describe the high-level design of your project, focusing on the purpose and interaction of the core classes.**
* The frontend's design has a component-based hierarchical design using lambdas to instantiate the primary stage/scene. 
Thus, there is a main class that manages the frontend and other classes that outline the visual window components. The 
backend follows a similar design where each command has a class and a single class manages those commands. However, it 
implements reflection to choose which command to run. These two 
structures are part of our internal APIs (one for backend and one for frontend), and they communicate by the external 
backend and the external frontend as part of our external APIs. The external frontend API takes user input commands 
from the internal API and passes it to the external backend API. Here the external backend communicates with the 
internal backend to execute a command, and that result is returned to the external backend, which also calls external 
frontend to update its visuals accordingly. Thus we passed the parser manager around as a constructor so that there would 
be appropriate communication between the frontend and the backend. Furthermore in this way, only the controller (backend APIs) 
would handle the specifics and the model and view (internal APIs) would be as general as possible in only expecting one 
instance of things.
* The parser was designed to be treated as almost a black box by the rest of the APIs.
This was accomplished by minimizing the number of assumptions that needed to be made about
the parser. As such, the command were fed in as nothing more than the string that was initially
written, while the actual command classes were to be called by the parser itself. Internally,
the design of the parser was constructed to be a recursive tree of command nodes. This allowed
for the nested structure of commands that could be input. The parser would subsequently know how
to call the backend and frontend commands in the order necessary to reflect that nesting.

**What assumptions or decisions were made to simplify your project's design, especially those that affected adding required features?**
* Early on, the parser was designed to split the command string into a stack of command nodes prior
to actually constructing the tree of these nodes. While this simplified the early design
when focusing solely on commands and constants, it made implementation of lists, control commands, 
and numerous other additional features significantly more difficult.
* Because of our data dependencies regarding parameters and difficulties with popups, we assume that the input commands are valid or that the user does not need to see why their input is invalid.

**Describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline.**
* The various command types that were not able to be added, such as conditionals, loops, and
groupings, were all possible if the initial splitting of commands had been accomplished better.
With a more flexible list, these commands would then need to be handled as specific cases within the parser,
as they all play with the command tree structure and the ordering of how commands are executed.
As such, certain new considerations may need to be made with how the tree is constructed, and new logic
would be added to the parser via edge-case methods.
* Multiple turtles also didn't work out entirely as we wanted to, but this seems like a bug and an easy fix because the active states weren't updating correctly. This means that all we would have to do is either re-call the list of turtles or add an update method to check to see which turtle is active/should be acted upon.
* The design of the internal frontend was refactored to optimize flexibility with adding new Window Components, so any addition to the display would simply require a new class that extends the WindowComponent Interface, and adding it to the list of components to be displayed from the dropdown menu that the user interacts with.  Some of the features were created as Window Components but were not successfully populated, such as the PenStatus and TurtleStatus boxes.  Both would require passing values into the textArea of the node, but this upkeep of status becomes increasingly complicated with multiple turtle instances and was not something we were able to achieve before the project deadline. 