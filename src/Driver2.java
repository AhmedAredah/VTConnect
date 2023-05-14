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


/**
 * A driver for the classes Profile and VTConnect.
 * 
 * @author Ahmed (AhmedAredah)
 * @version Nov 06, 2022
 */
public class Driver2
{
	/**
	 * the main driver for the project.
	 * @param args are the passed arguments while starting the project.
	 */
	public static void main(String[] args)
	{
		System.out.println("Creating profiles and the network.");
		VTConnect m = new VTConnect();

		Profile malcom = new Profile();
		malcom.setName("Malcom", "X");
		malcom.setStatus("My name is Malcom.");

		Profile fannie = new Profile();
		fannie.setName("Fannie-lou ", "Hamer");
		fannie.setStatus("My name is Fannie.");

		Profile brown = new Profile();
		brown.setName("John", "Brown");
		brown.setStatus("My name is John Brown!");
		
		Profile lewis = new Profile();
		lewis.setName("John", "Lewis");
		lewis.setStatus("My name is also John.");

		m.addUser(malcom);
		m.addUser(fannie);
		m.addUser(brown);
		m.addUser(lewis);
      
		malcom.display();
		fannie.display();
		brown.display();
		lewis.display();

		System.out.println("-------------------------------------\n");
		System.out.println("Creating friendships.\n");

		m.createFriendship(malcom, fannie);
		m.createFriendship(fannie, brown);
		m.createFriendship(lewis, fannie);
		m.createFriendship(brown, lewis);
     
		//displaying VTConnect
		m.traverse(malcom);

		System.out.println("-------------------------------------\n");
		System.out.println("Changing statuses / names.\n");
		
		lewis.setStatus("Just got married!");
		fannie.setStatus("Now Mrs. Smith!");
		fannie.setName("Fannie", "Smith");

		fannie.display();
        lewis.display();
       
        System.out.println("-------------------------------------\n");
		System.out.println("Checking Friendships .\n");
		
		Profile friendless1 = new Profile();
		friendless1.setName("Nameless ", " 1");
		friendless1.setStatus("My name is nameless1!");
		System.out.println(m.exists(friendless1));
		m.addUser(friendless1);
		System.out.println(m.exists(brown));
		System.out.println(m.hasFriendship(friendless1, fannie));
		System.out.println(m.hasFriendship(fannie, brown));
		System.out.println(m.hasFriendship(malcom, lewis));
		System.out.println(m.hasFriendship(friendless1, fannie));
		System.out.println(m.hasFriendship(brown, lewis));
		System.out.println(m.exists(friendless1));
		System.out.println(m.hasFriendship(lewis, fannie));
		
		System.out.println(
		        "---------------Suggestion----------------------------------");
		Profile friendless2 = new Profile();
		friendless2.setName("Nameless ", " 2");
		friendless2.setStatus("My name is nameless2!");
		m.addUser(friendless2);
		m.createFriendship(friendless1, friendless2);
		m.createFriendship(friendless2, lewis);
		System.out.println(m.friendSuggestion(friendless2));
		System.out.println(m.friendSuggestion(brown));
		
		System.out.println(m.friendSuggestion(lewis));
		
		System.out.println(
		        "---------------Distance----------------------------------");
		System.out.println(m.friendshipDistance(fannie,brown));
		//System.out.println(m.exists(brown));
		System.out.println(m.friendshipDistance(fannie,friendless1));
		System.out.println(m.friendshipDistance(fannie,lewis));
		System.out.println(m.friendshipDistance(malcom,friendless2));
	  	m.removeUser(friendless2);	  
	  	m.removeFriendship( fannie, brown);
		System.out.println(m.friendshipDistance(malcom,friendless1));
		System.out.println(m.friendshipDistance(malcom,friendless2));
		System.out.println(m.friendshipDistance(fannie,brown));
		
		
	} // end main
} // 

/*
 Creating profiles and the network.
Name: Malcom X
	Status: My name is Malcom.
	Number of friend profiles: 0
Friends:
Name: Fannie-lou  Hamer
	Status: My name is Fannie.
	Number of friend profiles: 0
Friends:
Name: John Brown
	Status: My name is John Brown!
	Number of friend profiles: 0
Friends:
Name: John Lewis
	Status: My name is also John.
	Number of friend profiles: 0
Friends:
-------------------------------------

Creating friendships.

Name: Malcom X
	Status: My name is Malcom.
	Number of friend profiles: 1
Friends:
	Fannie-lou  Hamer

Name: Fannie-lou  Hamer
	Status: My name is Fannie.
	Number of friend profiles: 3
Friends:
	Malcom X
	John Brown
	John Lewis

Name: John Brown
	Status: My name is John Brown!
	Number of friend profiles: 2
Friends:
	Fannie-lou  Hamer
	John Lewis

Name: John Lewis
	Status: My name is also John.
	Number of friend profiles: 2
Friends:
	Fannie-lou  Hamer
	John Brown

-------------------------------------

Changing statuses / names.

Name: Fannie Smith
	Status: Now Mrs. Smith!
	Number of friend profiles: 3
Friends:
	Malcom X
	John Brown
	John Lewis
Name: John Lewis
	Status: Just got married!
	Number of friend profiles: 2
Friends:
	Fannie Smith
	John Brown
-------------------------------------

Checking Friendships .

false
true
false
true
false
false
true
true
true
---------------Suggestion----------------------------------
[Name: Fannie Smith
	Status: Now Mrs. Smith!
	Number of friend profiles: 3
, Name: John Brown
	Status: My name is John Brown!
	Number of friend profiles: 2
]
[Name: Malcom X
	Status: My name is Malcom.
	Number of friend profiles: 1
, Name: Nameless   2
	Status: My name is nameless2!
	Number of friend profiles: 2
]
[Name: Malcom X
	Status: My name is Malcom.
	Number of friend profiles: 1
, Name: Nameless   1
	Status: My name is nameless1!
	Number of friend profiles: 1
]
---------------Distance----------------------------------
1
3
1
3
-1
-1
2
 */
