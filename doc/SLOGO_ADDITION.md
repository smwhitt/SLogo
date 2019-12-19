CompSci 308: SLogo Addition
===

# Before

### Estimation

 * ****How long do you think it will take you to complete this new feature?****
 I think this will take me one hour because I did not work on the frontend, so it will take me some time to navigate the
 correct files and see the implementation of our frontend.

 * How many files will you need to add or update? Why?
Because I know we use reflection in manager classes and have only one instance of a turtle that both frontend and backend
use, I predict that I will only need to add one new class, in addition to updating the corresponding properties files,
that extends the visual views we have in the dropdown/is another type of view. However because I do not know the full
intricacies of the frontend code, I feel as though theoretically I wouldn't need to update any classes but in reality,
may have to add slight changes to the manager or turtle class.


# After

### Review

 * How long did it take you to complete this new feature?
 This feature took me 30-45 minutes to complete.

 * How many files did you need to add or update? Why?
I added 2 new classes: TurtleImages.java and FileException.java, and updated 4 files: WindowParameters.properties,
IncludedWindowComponents.properties, WindowManager.java, and Turtle.java. I needed to add TurtleImages.java because each
choice in the dropdown corresponded to an entire view of that choice, which gave the user a full, less-crowded view, and
provided flexibility for coders like me to easily add new visual options/features. Thus, this class was added as another
visual for the user to have a visual list of all of their turtles and their images that, if clicked on, will allow them
to change the turtle's image immediately and without a command. I added a FileException.java class because we never
implemented a way of handling exceptions, but when choosing an image from a user's computer, there has to be a try catch
statement in case there is an error, so this class throws a better exception than printing out the stacktrace when there
is no image selected or if the selected image is not a '.png' file. I updated the two properties files as expected
because the combobox lists its choices from IncludedWindowComponents.properties, so I had to add the class name of
"TurtleImages". I also changed the WindowParameters.properties file to avoid some hard-coded numbers, but this was a
personal choice since each WindowComponent had access to that resources file, but I could have instead put the numbers
at the top as private final strings. The two classes I didn't expect to change were WindowManager.java and Turtle.java.
Because I realized that TurtleImages.java required the list of turtles the user made which was stored in the controller,
it needed to have access to the ParserManager.java, and ParserManager had an instance of the entire view as the
controller of the entire application. Thus, ParserManager would need to be passed down to this WindowComponent. Luckily,
WindowManager, that handled creating instances of each of the window components based on what the user clicked on,
already had an instance of ParserManager, but now that instance had to get passed down to those components, but none of
existing window components needed ParserManager, so it wasn't an argument passed into each of the components. Instead of
adding it and changing all of the children of WindowComponent, I used polymorphism to add another case within
WindowManager that would try to make an object with the new constructor that needed a ParserManager, and if not, use the
regular method. This was the only addition I made to WindowManager. The other update was in Turtle.java because I found
that I needed a copy of the turtle's image rather than the turtle's ImageView node itself, which I will elaborate more on
in the next question. However, you cannot do a clone on an image, so the only way to create a "copy" was to create a new
image using the same file path/image url. Thus, I had to add a getter and setter for the image file path in Turtle so
that within TurtleImages I could get the turtle via its id and change the image within the side pane and within the
turtle object itself both instantaneously and simultaneously.

 * Did you get it completely right on the first try? If not, why not?
No I did not get it right the first time. As mentioned in the last question, when I coded this added feature I found
that I was taking the turtle's visual from the map and onto the sidepane that I created. Even though the rest worked,
like the drop down, panes, and choosing a new image that replaced the turtle, I had accidentally stolen the instance of
the turtle, which was not the goal. Thus, I had to figure out how to make a "clone" of the same turtle for the sidepane
and decided on just having turtle images on the screen that corresponded to turtle ids locally, rather than having a
local copy of all the turtles. In this way, whenever an image of a turtle was clicked on, it would get the correct
turtle object that was on the path and update correctly.

### Analysis

 * What do you feel this exercise reveals about your project's design and documentation --- was it as good (or bad) as you remembered?
 Because it was relatively easy for me to jump into code that I had not touched and figure out how everything worked,
 this exercise showed that spending that much time thinking about how our frontend and backend should both use reflection
 as an attempt at better design, was well worth it. Although some of the method names were slightly misleading, IntelliJ
 has great features that can show coders usages of the given method or class, which helped me understand how something
 like WindowManager was interacting with each of the WindowComponents and how to add another type of constructor within
 the reflection part. Even though in this project we didn't get a lot of functionality completed, I was proud that our
 design was good because it made things like doing this exercise less stressful. Even though I didn't use the code I
 worked on when doing SLogo, I know that we had similar design patterns, which also made it easier to see how good this
 setup was, and liked that additional classes I added near the end of the project (like ParserManager) were helpful when
 I wanted things such as the list of turtles and getting a turtle based on ID even though turtles live in the internal
 frontend and the controller lives in the external backend.

 * What about the design or documentation could be improved?
 The only critique I have about the design/documentation would be the names of the methods themselves. For example,
 getComponentNode() was relatively confusing as a general method name. At first, I thought it was a simple getter but
 upon looking at the WindowManager reflection code, I saw that this was the method that was being called to update the
 view each time and shown. Thus any code that you wanted to be "refreshed" or displayed within clicking on the
 appropriate option from the dropdown was supposed to be in this method, but that was made unclear with the previous
 notion that it was merely supposed to return a node. Perhaps a comment that said a coder should create a private method
 with more in-depth actions or re-naming the method to setContent() would have made it easier for a coder to not have to
 look down the rabbit hole of classes in order to figure out what they have to do/see how everything interacted.

 * What would it have been like if you were not familiar with the code at all?
Because I didn't work on this code but instead gave some suggestions on the bigger picture design strategy, I was
like a person who was not familiar with the code at all. I was very surprised to see that this code was relatively
readable and easy to understand. The only problems rose as a result of so much of the code being generated by reflection
and interfaces, which meant that nearly everything was passed down. Thus, people unfamiliar with the code would truly
have to look far down into the code to figure out what properties files to change. However, these hiccups were small in
relation to the fact that it was so easy and extensible to do this exercise. Someone not familiar in the code wouldn't
be frustrated because all they would have to do is add a new class that implements a window component and then code the
specifics.