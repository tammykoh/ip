# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). 

## Quick Start

1. Ensure you have **Java 11** or above installed in your computer
1. Download the latest duke.jar from [here](https://github.com/tammykoh/ip/releases/tag/v1.0)
1. Copy the file to the folder you want to use as the home folder for Duke.
1. Follow the following steps to start up Duke. If the setup is correct, you should see something like the following on your first launch:
   1. Open Command Prompt
   1. Navigate to the folder with the jar file
   1. Enter `java -jar duke.jar`
   ```
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   Hey there! I'm Duke.
   ______________________________________________________________________
   Seems like you're new here. No worries! Your file is being created...
   We're set and ready to go. What would you like me to do?
   ______________________________________________________________________
   ```
1. Type the command in the command box and press Enter to execute it. Refer to the Features below for the available commands.
   
## Features

### Adding a task:

#### Adding a todo: `todo`
* Adds a todo task into the task list.
* Format:  `todo TASK`
* Example: `todo write an essay`

#### Adding a deadline: `deadline`
* Adds a deadline task into the task list.
* Format:  `deadline TASK /by DATE/TIME`
* Example: `deadline complete assignment /by 3Oct`

#### Adding an event: `event`
* Adds an event task into the task list.
* Format:  `event TASK /at DATE/TIME`
* Example: `event attend a course /at LT23 on 5Oct 2pm`

### Listing all task: `list`
* Shows a list of all the tasks in the task list.
* Format: `list`

### Marking task as done: `done`
* Marks a task as done.
* Format: `done INDEX`
* Example: `done 2`

### Deleting a task: `delete`
* Removes a task from the task list.
* Format: `delete INDEX`
* Example: `delete 3`

### Finding a task: `find`
* Finds all task containing the given keyword.
* Format: `find KEYWORD`
* Example: `find book`

### Exiting the program: `bye`
* Exits the program.
* Format: `bye`