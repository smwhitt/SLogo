# slogo

A development environment that helps users write SLogo programs.

Names: Matthew Harris, Megan Lemcke, Jerry Wang, Samantha Whitt

### Timeline

Start Date: 3 October 2019

Finish Date: 30 October 2019

Hours Spent: Matthew - 40, Megan - 30, Jerry - 35, Samantha - 30

### Primary Roles
Matthew was mainly in charge of the frontend including, but not limited to, the implementation and features of 
VisualExecutor, WindowManager, and other classes within the internalfrontend and externalfrontend packages/overall GUI. 
He also designed the displays and turtle properties visually.
Jerry took charge of CommandParser and the externalbackend package regarding tokens, CommandNode, and BackendExecutor in 
creating the overall tree and stack structure to parse commands/execute commands.
Megan and Samantha split all the commands and worked with Matthew and Jerry on the respective areas. For backend 
commands, they designed ModelManager, how to appropriately call each command, and then split the other commands into 
visual and control/parser commands. Thus, Megan worked more with Matthew in finishing out functionality of the frontend 
to carry out the new visual commands extended in complete and refactoring. Samantha then worked with Jerry to integrate 
the control commands in increasing the Parser's flexibility with the addition of a ParserManager to some responsibility 
off of WindowManager and CommandParser.

### Resources Used
Stack Overflow, TA meetings, GeeksForGeeks

### Running the Program

Main class: Main.java

Important classes: ParserManager.java, CommandParser.java, VisualExecutor.java, WindowManager.Java

Data files needed: data package and resources package in src

Features implemented: 
- Commands
    - All of basic syntax, turtle commands, turtle queries, math operations, boolean operations
    - Some of user defined commands like make and repeat (see known bugs)
    - Some of multiple turtle commands like tell, id, and turtles
- Visuals
    - choosing between which window component to see via dropdown (like command history, variables, language chooser)
    - changing the pens color
    - reacting to backend commands visually
    - having multiple turtles on the screen

Assumptions or Simplifications: We simplified the overall structure of taking commands by assuming that each time a 
command was called, it would also update visually regardless of whether or not the frontend would update visually. In 
this way if there was something like fd fd 50, it would move the turtle 50 and then another 50 rather than compiling it 
into fd 100. We also assume that commands are valid and if not, will display an error in the code rather than visually.

Known Bugs: 
Multiple advanced commands are missing due to lack of time. Error checking
was not added in a user-friendly manner due to time constraints.

### Extra Features
Instead of displaying multiple window panes across the right-hand side of the window, we featured a dropdown where the 
user can choose which feature they would like to see. For instance, their command history or a help page etc. We also 
added the capability for the user to create his/her own variables and add new turtles in setting certain ones to active. 
The user can also change the visual choices of the program by index realtime via command prompt. 

### Impressions
This was certainly a very interesting project, and I would have seen it as 
an enjoyable challenge from the perspective of the parser. Unfortunately,
it proved to be so difficult that, even with numerous days dedicated to working
on it, I ultimately still have an unfinished product. This does not reflect
on my team, who were always very helpful when I needed. But it rather
indicates my struggles in understanding the structures needed for it to work,
as well as early design assumptions that resulted in conflicts later on.
This project might have appealed more to me if I just had more time and
maybe didn't do it alone (Jerry).

Although the project seemed simple at first, I realized that because the functionality appeared so straightforward, 
the design was that much harder. I've learned the most yet about design, as I spent majority of the time just thinking 
about different design alternatives and the pros and cons to each before ever starting to code, and I really appreciated 
that this team valued the design just as much. In general though, I thought this project was challenging and extremely 
rewarding when we finally got to see our turtle respond to movements. I do however think that there was not enough time 
to truly focus on design, since the basic implementation itself required a lot of time to have a solid design plan then 
integrate a way harder complete. (Samantha)

I thought this project was more demanding, but more exciting than the last
project.  I really enjoyed starting out on the backend, then finishing out on 
the frontend.  This allowed me to really get a more detailed understanding of
both ends of the project, which is something that I sort of missed out on the last
project, where I strictly worked on backend.  This team was a lot more detail-oriented
with design than my last team, so I learned a lot in the process.  I feel pretty
pleased with what we were able to accomplish, given the extensive design planning
we did that maybe took time away from getting total functionality to work, but I
think the focus on design paid off in the end. (Megan)

This project definitely tested our ability to design a flexible structure for our code. The refactoring done
for the final implementation of the project was certainly a great improvement in our structure, but came late enough
that I think we were hindered in our ability to implement new features and ultimately didn't have the time to restructure
as much as I would have wanted. It was difficult in the end to know what to spend our time on. (Matthew)