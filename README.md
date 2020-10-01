# User Guide

Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). 

## Quick Start

1. Ensure you have **Java 11** or above installed in your computer
1. Download the latest duke.jar from here (insert link)
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Copy the file to the folder you want to use as the home folder for Duke.
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. Double-click the file to start the app. If the setup is correct, you should see something like the following:
   ```
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   Hey there! I'm Duke.
   How may I be of service?
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