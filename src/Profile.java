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

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Ahmed (AhmedAredah)
 * @version Nov 02, 2022
 */
public class Profile{
    // hold the total number of the profiles in the network
    private static int totalCount = 0;
    // hold the id of the current profile
    // this prevents duplicates in the network
    private int id = 0;
    // hold the name of the profile
    private String theName = null;
    // hold the current status of the profile
    private String theStatus = null;
    // hold the friends list
    private LinkedList<Profile> friendProfiles = null;
    
    /**
     * initiate the class profile with the null values.
     */
    public Profile() {
        this.theName = "";
        this.theStatus = "";
        this.friendProfiles = new LinkedList<Profile>();
        Profile.totalCount++;
        this.id = Profile.totalCount;
    }
    
    /**
     * initiate the class profile with the passed values.
     * 
     * @param name is the name of the person with this profile.
     * @param status is the status of the profile.
     * @param friendProfiles is the fiends list.
     */
    public Profile(String name, String status, 
            LinkedList<Profile> friendProfiles) {
        this.theName = name;
        this.theStatus = status;
        if (friendProfiles == null) {
            this.friendProfiles = new LinkedList<Profile>();
        }
        else {
            this.friendProfiles = friendProfiles;
        }
        Profile.totalCount++;
        this.id = Profile.totalCount;
    }
    
    /**
     * change the name of the profile based on new values.
     * 
     * @param firstName is the first name of the person.
     * @param lastName is the last name of the person.
     */
    public void setName(String firstName, String lastName) {
        this.theName = firstName + " " + lastName;
    }
    
    /**
     * get the name of the profile.
     * 
     * @return the name of the profile.
     */
    public String getName() {
        return this.theName;
    }
    
    /**
     * change the profile status.
     * 
     * @param status is the new status to be saved in the profile.
     */
    public void setStatus(String status) {
        this.theStatus = status;
    }
    
    /**
     * get the status of the profile.
     * 
     * @return the status saved for this profile.
     */
    public String getStatus() {
        return this.theStatus;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Name: " + this.theName + "\n\tStatus: " + this.theStatus + 
                "\n\tNumber of friend profiles: " + 
                this.friendProfiles.size() + "\n";
    }
    
    /**
     * print the profile to the console.
     */
    public void display() {
        String result = this.toString();
        result += "Friends:";
        Iterator<Profile> it = this.friendProfiles.iterator();
        while (it.hasNext()) {
            result += "\n\t" + it.next().getName();
        }
        System.out.println(result);
    }
    
    /**
     * get a list of friends profiles.
     * 
     * @return a list of friends profiles.
     */
    public LinkedList<Profile> getFriendProfiles() {
        return this.friendProfiles;
    }
    
    /**
     * add a new friend to this profile.
     * 
     * @param user is the new friend profile.
     */
    public void addFriend(Profile user) {
        this.friendProfiles.add(user);
    }
    
    /**
     * remove a connection between this profile and another profile.
     * 
     * @param user is the other profile to be unfriended with.
     * @return true if successfully unfriended the other profile.
     */
    public boolean unFriend(Profile user) {
        return this.friendProfiles.remove(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        // if null
        if (o == null)
            return false;
        // if the same instance
        if (this == o)
            return true;
        // cast it and compare them
        Profile other = (Profile) o;
        return (this.id == other.id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return ((Integer)this.id).hashCode();
    }
}

