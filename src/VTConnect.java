//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This class defines the social network application where individuals can 
 * connect with others and set a status of their own.
 * 
 * Every profile is unique by its ID. They are unique even if two 
 * profiles share the same name and status and list of friends.
 * Hence you cannot remove a profile by its name or status.
 * 
 * @author Ahmed (AhmedAredah)
 * @version Nov 06, 2022
 */
public class VTConnect {
    
    // define the main database of the application
    GraphInterface<Profile> database = null;
    
    /**
     * initiate the class and the database.
     */
    public VTConnect() {
        this.database = new Graph<Profile>();
    }
    
    /**
     * add a new user to the database.
     * 
     * @param p is the new profile data.
     */
    public void addUser(Profile p) {
        if (! this.exists(p)) {
            // add a new profile as a vertex to the graph db
            this.database.addVertex(p);
            // Retrieve the list of friends in the db
            LinkedList<Profile> friendsProfiles = p.getFriendProfiles();
            // if the list was greater than 0, add a connection to 
            // all these friends
            if (friendsProfiles.size() > 0) {
                // iterate all fiends list
                for (Profile otherP : friendsProfiles) {
                    // check if these friends exist in the db
                    if (exists(otherP)) {
                        // if it exists, add a new connection to that profile
                        this.database.addEdge(p, otherP);
                        otherP.addFriend(p);
                    }
                }
            }
        }
        else {
            // user with similar id already exists
        }
    }
    
    /**
     * remove/delete a user from the database.
     * 
     * @param p is the profile in the database.
     * @return the deleted profile.
     */
    public Profile removeUser(Profile p) {
        // iterate over all profiles in the friendList
        LinkedList<Profile> friendsProfiles = p.getFriendProfiles();
        if (friendsProfiles.size() > 0) {
            for (Profile otherP : friendsProfiles) {
                // remove the connection
                this.database.removeEdge(p, otherP);
                this.database.removeEdge(otherP, p);
                // remove friends from friendList
                otherP.unFriend(p);
                p.unFriend(otherP);
            }
        }
        // remove the vertex
        // what is done in this method will be done again in the following
        // method, but this is what is required in the project description.
        return this.database.removeVertex(p).getLabel();
    }
    
    /**
     * create a relationship between two profiles in the database.
     * 
     * @param a is the first profile to connect with the second profile.
     * @param b is the second profile to connect with the first profile.
     * @return true if the connection is made, false otherwise.
     */
    public boolean createFriendship(Profile a, Profile b) {
        boolean result; // holds the result of the friendship creation
        result = this.database.addEdge(a, b);  // create the edge in the graph
        if (result == true) {   // if edge is created
            a.addFriend(b);
            b.addFriend(a);
        }
        return result;  // return the result of adding the edge
    }
    
    /**
     * remove a relationship between two profiles in the database.
     * 
     * @param a is the first profile to disconnect with the second profile.
     * @param b is the second profile to disconnect with the first profile.
     * @return true if the connection is removed, false otherwise.
     */
    public boolean removeFriendship(Profile a, Profile b) {
        boolean result;  // holds the result of the friendship rm
        result = this.database.removeEdge(a, b); // rm the edge in the graph
        if (result == true) {  // if edge is removed
            a.unFriend(b);
            b.unFriend(a);
        }
        return result;  // return the result of rm the edge
    }
    
    /**
     * check if there is a connection between two profiles in the database.
     * 
     * @param a is the first profile to check connection with the 
     * second profile.
     * @param b is the second profile to check connection with the 
     * first profile.
     * @return true if there is a connection, false otherwise.
     */
    public boolean hasFriendship(Profile a, Profile b) {
        return this.database.hasEdge(a, b);
    }
    
    /**
     * this method displays each profile's information and friends, starting 
     * from the startPoint profile. See the sample run on the format of the 
     * display.
     * 
     * @param startPoint the first profile.
     */
    public void traverse(Profile startPoint) {
        Queue<Profile> ls = this.database.getBreadthFirstTraversal(startPoint);
        if (ls.size() > 0) {
            for (Profile p : ls) {
                p.display();
                System.out.println();   // add new line as per description 
            }
        }
    }
    
    /**
     * check if a user exits in VTConnect, false otherwise.
     * 
     * @param user is the given user profile.
     * @return true if the user exits in the database, false otherwise.
     */
    public boolean exists(Profile user) {
        // create new vertex for comparison
        VertexInterface<Profile> vi = new Vertex<Profile>(user);
        // check if the database has this vertex
        return this.database.getVertices().contains(vi);
    }
    
    /**
     * Returns a list of Profiles, who are friends with one or more of the 
     * profile's friends (but not currently the profile's friend). 
     * It returns null, if the user does not exist or if it does not have 
     * any friend suggestions. 
     * 
     * @param user is the given user profile.
     * @return list of Profiles, who are friends with one or more of the 
     * profile's friends (but not currently the profile's friend). 
     * Null otherwise
     */
    public LinkedList<Profile> friendSuggestion(Profile user) {
        // check if the user is already in the DB
        if (! exists(user)) {
            return null;  // return null if not found
        }
        // new suggestions list
        LinkedList<Profile> suggestions = new LinkedList<Profile>();
        // retrieve the friends list of the user
        LinkedList<Profile> friendsProfiles = user.getFriendProfiles();
        // iterate them and retrieve their friends list
        for (Profile p1 : friendsProfiles) {
            for (Profile p2 : p1.getFriendProfiles()) {
                // if the profile is not included in the friends list of the 
                // user, add it in the suggestions
                if (!friendsProfiles.contains(p2) && 
                        ! user.equals(p2)) {
                    suggestions.add(p2);
                }
            }
        }

        return suggestions;
    }
    
    /**
     * Returns the friendship distance between two profiles. A friendship 
     * distance is simply how many profiles away the two profiles are. 
     * For example, if a and b are friends their friendship distance is 1. 
     * If they have a common friend but they are not friends, their friendship
     * distance is 2. If either of the profiles are not in the social 
     * networking application, the method returns -1.
     * @param a is the first profile to check connection with the 
     * second profile.
     * @param b is the second profile to check connection with the 
     * first profile.
     * @return number of mutual friends in between.
     */
    public int friendshipDistance(Profile a, Profile b) {
        Stack<Profile> path = new Stack<Profile>();
        int distance = this.database.getShortestPath(a, b, path);
        return (distance == 0 || 
                distance == (int) Integer.MAX_VALUE)? -1 : distance;
    }
    
    
    
}
