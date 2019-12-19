## Introduction
This program aims to create a development environment that will accept commands from a user, and in turn, 
animate a design drawn by a turtle.  The program will take instructions from the user input, and parse them 
into executable instructions to move both the model/frame, and the visualization of the turtle and the design 
on the UI end.  This project focuses on Application Programming Interfaces as a design approach, and a user 
friendly IDE, which allows users to experiment with different designs and inputs, and see the result in real time.  
The primary design goals identified by our team include flexibility with new commands, expandability with the visual, 
and splitting responsibilities with APIs into multiple capabilities.  These three main areas allow for robustness 
from both a user input and a backend model standpoint, as well as highlighting on the benefits of implementing APIs.  
Our primary architecture goals are to leave parts of the backend closed, such as the maintenance of the turtle model.  
The management of the model will stay the same throughout the stages of the program, because it is only the integer 
values of the model that will be updated and fed in accordingly.  To accommodate for potential new attributes to the program, 
we also aim to keep elements such as the visual aspects and new commands open.  If UI elements are to be enhanced or altered, 
or if we want to add more complex commands, these attributes must have flexible architecture.

## Overview
In our design, we are considering four total API’s: backend external, backend internal, frontend external, and frontend internal. 
The external API of the backend consists simply of a CommandParser that will take in strings and output executable, packaged 
commands which are then sent to a BackendExecutor that executes the commands through calls to the backend internal API and a 
VisualExecutor that executes the commands through calls to the frontend internal API.  The backend internal API includes a 
ModelManager that is called by the BackendExecutor in 
response to parsed commands and updates and maintains the model, keeping track of important information like pen status, 
turtle position, turtle heading, and the visual status of the turtle (showing or not showing). In addition, 
the concept of commands are defined and structured in the backend internal API as a tree structure of inheritance, 
becoming more specific downwards in the tree ending with full implemented commands, one for each of the commands 
capable of being recognized in SLogo. The CommandParser also calls the external API of the frontend to update its 
visual assets through the VisualExecutor. The VisualExecutor updates the visual assets of the app through calls in 
the internal API of the frontend, specifically the WindowManager. The WindowManager maintains and updates its 
collection of WindowComponents; another hierarchy structure to define what types of assets will have to be displayed 
and maintained by the WindowManager. 

After discussing several different possible designs, our team settled on this structure for several reasons. 
Firstly, one of our primary focuses during this planning was to design an interpreter that could be very easily 
updated to include new commands. The hierarchical structure of commands should make it easy for a programmer to 
create a new type of command based on the organization of the command hierarchy. Another area we wanted to be very 
flexible was the visualization of the model in general. Inspired by the cell society project, we designed our visual 
representation of the model to be very flexible, employing a similar hierarchical structure for the actual visual 
assets that we will implement. This means that if a change was needed to be made to what was actually being displayed 
as a visual representation of the model, it would be simple to create a new type of visual asset and have the WindowManager 
manage the new asset. Finally, we wanted to have separation between the frontend and backend pieces of our interpreter. 
This led us to a structure where both the visual representation and backend model are being called to update from our 
CommandParser and Executors. We were inspired by the Command behavioral design pattern to employ this type of isolation 
with the executors acting as “Invokers”. 



## User Interface
The user will be presented with an interface primarily dominated by the turtle?s environment. This will be the 
background on which the turtle can move and mark up. Beneath this will be a box in which to enter commands. To the 
right will be additional information, such as a logged history of recent commands, a library of user made commands, and 
a list of variables in use. A dropdown bar can be made available for the sole purpose of changing the language, as well 
as one for setting the turtle icon. A button should also be available to bring up a help menu for the commands. The 
main interaction for the user should be with the command box itself, as this is where visual changes will be entered. 
Other small instances, such as the dropdown bars and help button, will also be added for user interaction as the 
requirements demand. Most other options necessary for the basic implementation will be made available as commands. For 
situations in which the user inputs an invalid command, this should be highlighted to the user when attempted to run. 
Similarly, in cases where data for a command is missing, the command will be highlighted and a proper error message 
will be displayed.


![GUI Concept](/GUIConcept.png "Gui Concept")

## Design Details
The first API is the internal frontend. It has two main classes split into managing the "window" or stage of the 
animation and setting up the stage with all of its components. The first simply calls a setup keeps track of/returns  
crucial components like the turtle's position. The second splits each individual component like the combobox, labels, 
hboxes, turtle, command prompt, etc. into their own classes with the super class being a generic visual component that 
belongs to a window class itself. We structured it in this way so that we could easily have multiple windows, and in 
those windows, have multiple components of our choosing that we can replicate or have one instance of, providing more 
flexibility with the visuals. We also decided that the internal frontend would have specific methods relating to 
"moving" and "rotating" because those methods deal specifically with the ImageView of those window components. We 
decided to abstract those components in the API because they were similar in setting up certain functionality and 
returning a node for the root to use while setting up the stage.

The second API is the external frontend. This API facilitates the interaction between what the model returns after 
calculating a command and how the visual updates appropriately. Because we assume that all commands return a value and 
thus, the model returns a calculated value, all this VisualExecutor has to do is call executeVisualCommands() where it 
feeds the frontend the new position/heading/flag for it to change its view. We know that this was relatively simple for 
an API but thought this was for the best so that the updating could be done always solely in the frontend and that the 
frontend wouldn't have to do any logic that the backend should take care of. Because the controller, or the external 
APIs, handles more of the specific details, this also helped to keep the internal API of the back and frontend be as 
general as possible. 

The third API is the external backend. As the communicator between the frontend and backend, it has two classes: parsing 
commands and taking those commands and calling an execute on the backend correctly. For now, we are treating the parser 
as a black box, but we assume that it returns executable command objects that will correspond to certain classes in the 
model. We chose to have the command parser be in the backend because no logic should be handled in the frontend, and it
also allows for better flow from view to controller to model. The executor is needed as to take the tree of all the 
parsed commands and call specific classes in the backend to execute/calculate. The design strategy for this came
from our goal of having the capability to take multiple levels of commands and execute one by one. What defines the 
certain commands is a properties folder that is already provided.

The fourth API is the internal backend. Similar to the frontend structure, it takes on a manager and has a components 
portion called Command. The Command class is abstracted into Turtle and Math, which are both abstracted further into the 
more specific commands. Each command corresponds to a specific class. This was designed to better anticipate flexibility 
regarding new commands. If a coder wanted to do a new turtle command like move diagonally, all they would have to do is 
add it in a Properties folder and create a new class in the backend that corresponds to that function. The manager, from 
the backend executor, knows which method to call and directs the appropriate class. Like the frontend, we wanted this 
design so that all the logic is done in the backend with a certain flexibility for commands. We used abstraction 
because there was a similar pattern in handling math for each command, and used a hierarchical design to better improve 
readability in encapsulating types of commands going general to specific. 

## API as Code
See our source code for this. Specific commands are under the slogo.internalbackend API in their respective categories.

## Design Considerations
A major design decision that we struggled with was how to structure our overall process in an MVC model while 
integrating the four main APIs. While we agreed that the flow of the program begins with a visual/view that has a 
command prompt, then into a parser/controller with the command, and finally into the backend/model to execute that 
command, we juggled between sending the completed result from the backend straight to the frontend, or instead, having 
some intermediary step between sending the completed result from the backend to another “controller” to the visual. The 
pros of the former included: easier readability, direct functionality of the model, fewer classes to facilitate the 
interaction, and less responsibilities on the controller. The cons of the former included: possible data dependencies 
(not properly protecting data), having some logic in the frontend, and not having general methods in the model. The 
second option’s pros were the exact opposite in that it would better protect the data being passed around, keep general 
methods in the view and model, but it would cause more classes thus possibly reducing readability while placing more 
responsibilities on the controller. In general, we found that we wanted each part to have purpose and not overload the 
functionality of each class, so we decided on implementing the second design choice in favor of being able to expand 
our project once complete is due. The APIs come into play by having one internally for the view and one internally for 
the model, since the view will update its current state based on what the controller feeds it, and similarly, the model 
will execute based on what the controller feeds it. The other two stem externally from frontend to backend by way of 
the controller as well as from backend to frontend also by way of the controller. Currently, we assume that the parser 
class parses the commands perfectly for the model and that all commands return some sort of value. For our data 
dependencies, we rely on a given set of basic commands for the turtle and a tree that keeps track of the commands 
parsed from the GUI.

## Team Responsibilities
Because there are 4 main APIs, we've decided to split it accordingly with a focus on the standard MVC model. Frontend 
internal and external APIs will mainly be handled by Matthew, Jerry will take on external backend with a focus on the 
parsing of commands, and the internal backend for all of the commands and math will be taken care of by Megan and 
Samantha. Because lots of the parts communicate frequently with each other, if one team member has less to do for a 
portion of the project, we are all ready to contribute to another team and have many meetings to constantly update one 
another.

## Use Case
fd 50 -> InternalFrontEnd input to CommandPrompt -> 

slogo.externalbackend CommandParser.parseCommand() parses command -> 
slogo.externalbackend BackendExecutor.executeModelCommand() ->

slogo.internalbackend ModelManager.updateTurtleY (Uses TranslationCommand to move) ->

slogo.externalfrontend VisualExecutor.executeVisualCommands() ->

slogo.internalfrontend WindowManager.moveTurtle() sets new Turtle position, leaves trail if pen is down