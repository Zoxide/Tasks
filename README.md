# Task Management Application

## Overview
This is a Java-based task management application that allows users to register, log in, and manage their tasks. The application provides functionalities such as searching, deleting, and displaying tasks.

## Features
- **Storage**: This program uses .txt files to store data as it is a simple project for college
- **User Registration and Login**: Users can register and log in to the application.
- **Task Management**: Users can search for tasks by name or category, delete tasks, and view task details.
- **Input Validation**: Ensures that user inputs are valid and handles errors gracefully.

## Project Structure
- `src/main/Main.java`: The main entry point of the application.
- `src/auth/Login.java`: Handles user login functionality.
- `src/auth/Register.java`: Handles user registration functionality.
- `src/utils/menuUtils.java`: Contains utility methods for displaying menus and handling task-related operations.
- `src/utils/InputValidation.java`: Contains methods for validating user inputs.

## How to Run
1. **Clone the repository**:
    ```sh
    git clone https://github.com/Zoxide/Tasks.git
    cd Tasks
    ```

2. **Compile the project**:
    ```sh
    javac -d out src/**/*.java
    ```

3. **Run the application**:
    ```sh
    java -cp out main.Main
    ```

## Usage
1. **Main Menu**: The main menu allows users to choose between registering a new account or logging in.
2. **Login**: After logging in, users can manage their tasks.
3. **Task Management**: Users can search for tasks by name or category, delete tasks, and view task details.

## Dependencies
- Java Development Kit (JDK) 11 or higher

## Contributing
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.