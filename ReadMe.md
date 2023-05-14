# Mini Social Networking App (VTConnect)

This project is part of the Virginia Tech CS5040 course with Prof. Tessema Mengistu.

## Project Description

VTConnect is a mini social networking application developed as a part of the Virginia Tech CS5040 course. The application is designed to facilitate social connections and networking between users. It utilizes a graph data structure to represent relationships between users.

A social networking service or SNS (sometimes called a social networking site) is an online platform where people build social networks or relationships with others who share similar interests, activities, or real-life connections. VTConnect aims to provide a simple and efficient social networking experience.

## Project Components

The project consists of several classes and interfaces that implement the functionality of VTConnect. Here is an overview of the main components:

### GraphInterface

The `GraphInterface` is an interface that defines the operations and functionalities of a graph data structure. It represents an undirected graph and provides methods for adding and removing vertices and edges, checking for the existence of an edge, and performing traversals.

### VertexInterface

The `VertexInterface` is an interface that represents a vertex in the graph. It represents a user profile and provides methods for managing connections with other profiles, such as adding and removing edges, checking for neighbors, and storing information about paths and costs.

### Vertex

The `Vertex` class implements the `VertexInterface` and represents a vertex in the graph. It stores the label, visited status, previous vertex, cost, and a list of edges to neighbors.

### Graph

The `Graph` class implements the `GraphInterface` and represents the graph data structure used in VTConnect. It provides methods for adding and removing vertices and edges, checking for the existence of an edge, retrieving the number of vertices and edges, performing traversals, and clearing the graph.

### Profile

The `Profile` class represents the user profiles in VTConnect. Each profile has attributes such as name, status, and a list of friend profiles. It provides methods for managing the profile's attributes, adding and removing friends, and displaying profile information.

### VTConnect

The `VTConnect` class is the main class of the social networking app. It manages the graph of profiles and provides methods for adding and removing users, creating and removing friendships, checking for friendships, traversing the social network, and suggesting friends based on mutual connections.

## Method Complexity (Big-O)

The methods in the classes have different time complexities. Here is a summary of the Big-O complexity for each method:

[Insert the table or formatted content with the method complexity information provided earlier]

## Requirements

- Implement the required classes and interfaces according to the provided descriptions.
- Ensure that all attributes are declared as private or protected.
- Write JavaDoc comments for all classes and methods.
- Follow the specified Big-O runtime for each method implementation.
- Test your implementation using the provided main method or create your own test cases.
- Students are strictly prohibited from copying any part of the code provided in this README file. The code and descriptions are meant to guide and explain the structure and functionality of the Mini Social Networking App (VTConnect) for educational purposes. It is the responsibility of the students to implement the code on their own, ensuring academic integrity and avoiding any form of plagiarism.

Please refer to the provided descriptions and specifications for more details on each class and method.

## Testing

To test the project, you can use the provided main method in the template file or create your own test cases. The main method contains code that demonstrates the usage of various methods and functionalities of VTConnect.

You can run the application using the command java VTConnect to execute the provided test cases or your own test cases.

Note: Make sure to replace VTConnect with the appropriate class name if you have renamed the main class.


Important Note: Students are strictly prohibited from copying any part of the code provided in this README file. The code and descriptions are meant to guide and explain the structure and functionality of the Mini Social Networking App (VTConnect) for educational purposes. It is the responsibility of the students to implement the code on their own, ensuring academic integrity and avoiding any form of plagiarism.
