# Budget Tracker

### Achieve financial independence with just the tips of you fingers!

Cool features that it provides:
- Keep track of you goals (cars, traveling, etc).
- Daily reports and month reports of financial progress.
- Categorize purchases to help eliminate unnecessary spendings.
- Take your salary into account.
- Also helps you manages your mortgages/debts

This application helps people make smarter financial decision by categorizing all the spending into different categories 
so it’s easy for them to visualize where they can save money on. With the included goal tracker feature people are also 
more motivated so save up towards their financial goals. This application can be used by essentially anyone who has the 
desired to learn more about their spending habits and saving habits.

This project is interesting to me because the existing budget trackers place heavy focus on just the **spending part** 
of finance and completely neglected the **saving part**. To me an application that can take all aspects of our 
financial life into account including mortgages, cars, stock, and more is exciting. With every wider use of **apple pay/
google pay** this application can eventually be designed to take data from those paying methods so people don’t have to
manually enter their spending.  



### User Stories

- As a user, I want to be able to add a purchase to a categories (needs, wants, regrets)
- As a user, I want to be able to see the total sum of money spend in one spending Category
- As a user, I want to be able to add money to savings and my saving account can grow according to interest rate
- As a user, I want to be able to add a goal to a list of goals that I have for things I want to buy
- As a user, I want to be able to save my purchase history (in respective categories) and my goals when I quit
- As a user, I want to be able to load all the previous purchases and goal when I start up the application

### Instructions for grader
***Run the "MainFrame" class under the folder gui to start
***There are also tool tips for every button and error messages for almost all the incorrect inputs that I could think 
of. 
- You can generate the first required event by going into manage purchases fill in all three required fields
the first one can be any string, second can be any number, third has to be one of the required categories. 
After you have done so hit the "add to the category" button you add such purchase to given category You can
choose to input the wrong type for example input random words for the cost instead of a number, then an error message
would pop up and alert you. Similarly you can go into manage purchase or manage savings they both have components
that let you add stuff to a list too.
- You can generate the second required event by going ing manage purchases again. You can delete ONE purchase in a 
category by just filling in the name of the item you want to delete and the category it's in and hit the "delete a 
purchase" button. This will delete the said item with said them in the given category. Or you could just input a category
that you want to clear 
and hit the "delete all in given category" button this way everything in the category will be deleted. There are similar
functionality in both the manage savings and manage goals tabs.
- You can trigger my audio component by clicking on any one of the three buttons in the main menu or 
anytime you click a on the grey space. I have lost of pop up error messages when you input the wrong type of data for
different field for example if you tye "four" instead of "4" for cost. The visual component can be found 
in the menu. When you hit the button "pop up a random cute photo" a random cute photo will pop up and 
it will be a different photo each time.
- You can save the state of my application by using the "save to file" menu at the upper left corner. There are 
two options under that menu. If you click "save to file" the application will just automatically save progress 
to the current file that you just loaded. If you click "save as a new file" you can create a new txt file that is 
formatted correctly for this app to the location of your desire.
- You can reload the state of my application by using the "load a file" menu at beside the "save to file" menu.
Every time you open the app the app will automatically load from the default file which is \data\budget.txt in the 
project starter. However, if you use this function you can load a file anywhere as long as it's properly formatted 
to this app. If you try to load a file that is not formatted for this app a message will pop up and let you know it's
not the right file.
### Phase4:Task2
Since all four of the designs can only minimally fit my app I just decided to incorporate 2 of them.
- The entire Category class and all the subclasses under it are now robust with no requires clause in 
any of the methods. The tests to test out the respective exception thrown are also put in test.
- Also the three subclasses under the category class now have their own overridden methods from Category class
(although very minimally different because these classes in theory are not that different but it is easier to read the 
code and design if they are separated).
### Phase 4: Task 3
- One of the places in my project that has poor cohesion is in the MainFrame class of my GUI. This class should only
be accounting for constructing a Frame that has a proper size, and when trigger it loads on a new given panel without
knowing the anything or operation about those panels. However, I was designing I accidentally designed the menu bars
inside of the MainFrame class. The MainFrame class should not be responsible for menu bar, so I refactored the menu bar 
and functionality that come with it out into their own class called MenuBar.
- There is also too much coupling(Instead of downward coupling there is too much upward coupling) between all the GUIs
in terms of the fields that they operate on. For example right now if I initiate the app will load the default file and
the values contain in it will be the fields in MainFrame. The implication of this is that, whenever there is an 
operation within the panels that wants to change the value in the file they have to access back up to the MainFrame.
This also means that instead of letting the MainFrame be at
the top of the class chain and the panels below it have no knowledge of anything inside it, the panels now have to 
refer back up to the MainFrame class to access the values every time.

To deal with this coupling issue, I completed overhauled the way my GUI stores data by first creating a class called
GuiData that store data as static fields and remove the data fields in the MainFrame class. I also changed every
operation relating changing the data to happen with in the GuiData class, so the MainFrame class now has no knowledge
of what happens to the data.

***The UML class diagram for the overall project is added to the root of this project starter