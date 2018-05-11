Programming Project: p2_40354020_172

What is it?
- - - - - - 
This java project consists of implementing a simple simulation system that manages pools of arriving customers 
(individuals requesting some service), and which can measure how different policies behave on that data. We are 
interested in measuring two important statistical values for different policies. In this project, we will work 
with four waiting/serving policies for customers that arrive looking for service. They all serve the common 
scenario of a person requiring attention by a clerk in a particular location. The person waits until he/she 
reaches one of the serving clerks. How is that waiting done is defined by a waiting/serving policy that the 
particular location has implemented.  The four policies we will be exploring are: Single Line Multiple Servers 
(SLMS), Multiple Line Multiple Servers (MLMS), Multiple Lines Multiple Servers and Balanced Line Lengths (MLMSBLL), 
and Multiple Lines Multiple Servers and Balanced Waiting Times (MLMSBWT).

Getting started
- - - - - - - - 
How to compile and run in Termina? Save the project on your computer.
From the terminal window, navigate to the directory containing your .java file using the cd command. For example,

	cd eclipse-workspace/P2_#########/src

Assuming the file is in the current working directory, type the javac commands below to compile all the packages.

	javac packageName1/*.java packageName2/*.java packageName3/*.java

Now your project should be compiled in the src folder.
Since Eclipse was used, compiling is not necessary since the project is already compiled in the bin folder included.

Once you have your compiled project, the next step is to run it. First go to the directory containing your project. For example,
	
	cd eclipse-workspace/P2_#########

Now, run the project with the following command.
	If your class files are already in the bin folder:

		java –classpath bin packageName.MainClassName 

	If your class files are in the src folder as explained above:

		java –classpath src packageName.MainClassName

	
The output of this command will be saved to the folder outputFiles inside the main project folder.

 
Name and description of the projects and sub-modules
- - - - - - - - - - - - - - - - - - - - - - - - - - 
p2_40354020_172

package: classes
	ArrivalEvent.java – customer is set to arrive event
	Customer.java – class representing a customer who needs service
	EventComparator.java – 
	MLMS.java - Multiple Line Multiple Servers policy
	MLMSBLL.java - Multiple Line Multiple Servers and Balance Line Lengths policy
	MLMSBWT.java - Multiple Line Multiple Servers with Balanced Waiting Times policy
	Monitor.java - monitors the arrival and transfer of a customer
	ServiceCompletedEvent.java - customer is done receiving service event
	ServiceStartsEvent.java - customer is set to start receiving service event
	SLLQueue.java - singly linked list queue implementation
	SLMS.java - Single Line Multiples Servers policy
	TransferEvent.java - customer is transferred to a shorter line event

package: interfaces 
	Event.java – interface for event
	Queue.java – interface for queue
	WaitingServingPolicy.java - interface for waiting/serving policies

package: p2MainClasses
	theSystem.java – main class of the project

package: testers
        EventTester.java - test for the events 

Built with
- - - - - -
Eclipse – IDE used

Author
- - - 
Jean C. Merced Cádiz
