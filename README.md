# Java Code Quality Evaluator
An assignment for a JetBrains Internship

## How to Run?
1. To clone the repository to your local machine, navigate to your desired directory in your terminal and run:  ``git clone https://github.com/AAcharki2311/JavaComplexityReader.git``
2. Navigate to the project directory.
3. Run the application by executing the `main` method in the `Main` class.
4. Enter the directory you would like to analyze in the console.
5. The application will display the output to the console.


## Approach
#### As a developer, my approach to this test task is to create a Java application that evaluates basic aspects of code quality within a small codebase. I will demonstrate my understanding of programming and my ability to learn and apply new knowledge throughout the development process.
#### My approach to this task involves the following steps:

- I started by thoroughly reading and analyzing the task to ensure a complete understanding of the requirements.
- I then took a short break to visualize the entire application and create a plan of action.
- My plan consisted of the following steps:
  - Create a method to retrieve all Java files from the specified directory.
  - Implement a method to read each Java file.
  - Begin with the simpler methods, such as checking if a given string is in camelCase.
- To implement this plan, I took the following steps:
  - I created the `getFiles` method to retrieve all Java files from the specified directory.
  - I started with simpler methods, such as implementing the `isCamelCase` method to check if a given string is in camelCase.
  - I created a `printResults` method to store and display the method name and corresponding complexity score using a `HashMap`.

#### Next, I had to find a way to identify each method in the Java code. I took the following steps:
  - I identified the unique syntax used to define a method:
          `method name` `(parameter list)` `{implementation}`
  - I encountered a problem where conditional statements such as `if/else/while/switch/for` also follow a similar syntax.
  - To solve this problem, I filtered out all lines containing conditional parameters.
  - I also found that the keyword `void` is commonly used in method signatures, so I checked for this keyword to further identify method signatures.

#### After identifying each method, I was able to call the `isCamelCase` method on all method names to calculate the percentage of methods that adhere to the camelCase naming convention.

#### Next, I moved on to implementing the code complexity evaluator. I took the following steps:
  - I copied the code used to identify each method.
  - I developed a method to isolate each method by checking for the end of the method, which is indicated by a closing curly brace `}`.
  - I encountered a problem where nested statements could affect the accuracy of identifying the end of a method.
  - To solve this problem, I implemented a counter to keep track of the number of open curly braces `{`.
  - I then decreased the counter each time a closing curly brace `}` was encountered.
  - I was then able to accurately identify the end of a method when the counter reached 0 and a closing curly brace `}` was encountered.

#### Finally, I implemented a method to count all conditional statements within each method and listed the top three methods with the highest complexity scores.
- To display the results, I created an `App` class where I called the `run` method in the `CodeAnalyzer` class.
- I then wrote some lines to print the output in a user-friendly way through the `Main` class.

After implementing each method, I wrote corresponding tests to ensure that every method functions correctly and meets the specified requirements.
By writing tests for each method, I was able to build a robust and reliable application that meets the needs of the user.

## Design Choices

### Use of HashMap: 
To store the method name and corresponding complexity score, I chose to use a `HashMap`. This allowed for efficient storage and retrieval of the data, as well as easy sorting and retrieval of the top three methods with the highest complexity scores.

### Use of Counter for Nested Statements: 
When isolating methods, I encountered the problem of nested statements affecting the accuracy of identifying the end of a method. To solve this problem, I implemented a counter to keep track of the number of open curly braces `{`. This allowed for accurate identification of the end of a method, even in the presence of nested statements.

### User Input for Directory Path:
To make the application more flexible and user-friendly, I chose to base the directory path for reading the Java files on user input through the console, rather than hard-coding the directory path. 
This allows the user to easily specify the directory they want to analyze, making the application more adaptable to different use cases.
This design choice improves the usability and flexibility of the application, as the user is not limited to a hard-coded directory.

## Task:
### The application performs the following tasks:
### Code Complexity Evaluator: 
The application reads Java or Kotlin files from a specified directory and analyzes the methods/functions for complexity. 
It counts the number of conditional statements in each method and outputs the names of the three methods with the highest complexity scores, along with their complexity scores.

### Basic Code Style Check: 
The application also reports the percentage of methods that do not adhere to the specified naming convention (e.g., camelCase for Java). 
