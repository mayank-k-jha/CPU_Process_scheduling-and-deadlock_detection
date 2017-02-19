
/*@author mayank_kumar_jha*/
import java.util.*;

class Process{
	String process_name;int burstTime;int arrivalTime=0;boolean state=false;
}




class comp implements Comparator<Process>{

	@Override
	public int compare(Process arg0, Process arg1) {
		if(arg0.arrivalTime>arg1.arrivalTime){return 1;}
		else if(arg0.arrivalTime<arg1.arrivalTime){return -1;}
		return 0;
	}
	
}





class comp1 implements Comparator<Process>{

	@Override
	public int compare(Process arg0, Process arg1) {
		if(arg0.burstTime>arg1.burstTime){return 1;}
		else if(arg0.burstTime<arg1.burstTime){return -1;}
		return 0;
	}
	
}




public class Solution {
	
	
	
	
	
	
	
	
/*******************************Result Display Function  *****************************************/	

	public static void result(ArrayList<Process> ProcessScheduler,int n){
		int completion_time[]=new int [n];   //completion time array
		int turn_around_time[]=new int[n];   //Turn around time array
		int waiting_time[]=new int[n];       //Waiting time array
		
		Iterator it =ProcessScheduler.iterator();
		//calculating the completion time of the FCFS
		int sumer=0,counter=0;
		//creating total waiting time,total turn around time variables
		int total_waiting=0,total_turn_around=0,c=-8;
		System.out.println("Procs Exec order   Arrival TIme    Burst Time      Completion Time    Turn Around Time     Waiting Time");
		 while(it.hasNext()){
			 Process pro =new Process();
	    	pro=(Process)it.next();if(c==-8){sumer=pro.arrivalTime;c=0;}
	    	sumer+=pro.burstTime;
	    	completion_time[counter]=sumer;               //storing completion time of each process
	    	turn_around_time[counter]=sumer-pro.arrivalTime; //storing T T for each process
	    	waiting_time[counter]=turn_around_time[counter]-pro.burstTime;//storing W T for each process
	    	total_waiting+=waiting_time[counter]; //calculating total_waiting_time
	    	total_turn_around+=turn_around_time[counter];//calculating total_turn around time
	    	System.out.print("\t  "+pro.process_name+"\t\t"+pro.arrivalTime+"\t\t"+pro.burstTime+"\t\t"+completion_time[counter]);
	    	System.out.println("\t\t"+turn_around_time[counter]+"\t\t\t"+waiting_time[counter]);
	    	++counter; // index update
	    }
		 System.out.println("Result :");
		 System.out.println("Average Waiting Time     = "+(float)total_waiting/n);
		 System.out.println("Average Turn Around Time = "+(float)total_turn_around/n);
	}
	

	
	
	
	
	
	
/************************************ Global Scanner Function ************************************/	
	
	    static Scanner in=new Scanner(System.in); 
	    
	 
	    
	    
	    
	    
	    
	    
	    
	    
/********************************First Come First Serve ******************************************/
	public static void fcfs(){
		System.out.print("-----------------------   FCFS  --------------------------");
		ArrayList<Process> ProcessScheduler =new ArrayList<>();
		boolean deci=false;
		System.out.print("\nEnter y / n for Arrival time option ");
		char ch=in.next().charAt(0);
		if(ch=='y'){deci=true;}
		System.out.println("Enter number of Processes ");
		int n=in.nextInt();
		if(deci==true){
			System.out.println("Enter each Process name,CPU Burst Time and Arrival time ");
			}
			else{System.out.println("Enter each Process name and CPU Burst Time  ");
		}
		for(int i=0;i<n;i++){
			Process pro =new Process();
			if(deci==true){
			pro.process_name=in.next();
			pro.burstTime=in.nextInt();
			pro.arrivalTime=in.nextInt();
			}
			else{
			pro.process_name=in.next();
			pro.burstTime=in.nextInt();
			pro.arrivalTime=0;}
			ProcessScheduler.add(pro);
		}
		{Collections.sort(ProcessScheduler, new comp());}
        //Calling result function
		result(ProcessScheduler,n);
	}
	
	
	
	
	
	
	
	
/*****************************************About *************************************************/	
public static void about(){
	System.out.print("\nName    : Mayank Kumar Jha");
	System.out.print("\nSubject : OS (Operating System )\n");
}







	
/*************************************** Select Option ******************************************/	
public static void selectOption(){
	while(true){
		System.out.print("\nSelect an option from below one ");
		System.out.print("\n1. FCFS\n2. SJF\n4. SRTF\n3. Round Robin\n5. Exit");
		int option=in.nextInt();
		if(option==1){fcfs();}
		else if(option==2){sjf();}
		else if(option==3){srtf();}
		else if(option==4){roundRobin();}
		else if(option==5){System.out.print("\n Thankyou For using this Program,\n      ***** Good Bye ");break;}
		else{System.out.print("\nPlease select a valid Option ");}
	}
}








/**************************************  SJF  *****************************************************/	

public static void sjf(){
	System.out.println("-----------------------SJF _----------------------------");
	ArrayList<Process> ProcessScheduler =new ArrayList<>();
	boolean deci=false;
	System.out.print("\nEnter y / n for Arrival time option ");
	char ch=in.next().charAt(0);
	if(ch=='y'){deci=true;}
	System.out.println("Enter number of Processes ");
	int n=in.nextInt();
	if(deci==true){
		System.out.println("Enter each Process name,CPU Burst Time and Arrival time ");
		}
		else{System.out.println("Enter each Process name and CPU Burst Time  ");
	}
	for(int i=0;i<n;i++){
		Process pro =new Process();
		if(deci==true){
		pro.process_name=in.next();
		pro.burstTime=in.nextInt();
		pro.arrivalTime=in.nextInt();
		}
		else{
		pro.process_name=in.next();
		pro.burstTime=in.nextInt();
		pro.arrivalTime=0;}
		ProcessScheduler.add(pro);
	}
	Stack<Process> s=new Stack<>();
	Stack<Process> s1=new Stack<>();
	
	//if arrival time is not given 
	if(deci==false){
		Collections.sort(ProcessScheduler,new comp1());  //Sorting with burst time shortest basis
		}
	//If Arrival time is given 
	else{
		{
			Iterator it = ProcessScheduler.iterator();
			while(it.hasNext()){
				 Process pro =new Process();pro=(Process)it.next();
				 s.push(pro);
			}
		}
		//Setting Process sequence according to SJF in new stack s1
		{//finding first process
			int min=99999,minn=99999,loc=-9;
				for(int i=0;i<s.size();i++){
					if(s.elementAt(i).arrivalTime<=min){
						min=s.elementAt(i).arrivalTime;
						if(s.elementAt(i).burstTime<=minn){
							minn=s.elementAt(i).burstTime;loc=i;
						}
					}
				}
				s1.push(s.elementAt(loc));s.removeElementAt(loc);
			}
		//setting remaining process other than first
		int min=s1.peek().arrivalTime+s.peek().burstTime,minn=99999,loc=-9;
		while(!s.isEmpty()){ 
			for(int i=0;i<s.size();i++){
				if(s.elementAt(i).arrivalTime<=min){
						if(s.elementAt(i).burstTime<=minn){
						minn=s.elementAt(i).burstTime;loc=i;
					}
				}
			}min+=s.elementAt(loc).burstTime;minn=99999;
			s1.push(s.elementAt(loc));s.removeElementAt(loc);
			
		}	
	ProcessScheduler.clear();               //clearing previous Process order list
	for(int i=0;i<s1.size();i++){
		ProcessScheduler.add(s1.elementAt(i));  //assigning new process order to list
	}}
	//calling result function 
	result(ProcessScheduler,n);
}







/***********************************  Round Robin *************************************************/	

public static void roundRobin(){
	ArrayList<Process> ProcessScheduler =new ArrayList<>();
	boolean deci=false;
	System.out.println("********************************** Round Robin *********************************\n");
	System.out.print("\nEnter y / n for Arrival time option ");
	char ch=in.next().charAt(0);
	if(ch=='y'){deci=true;}
	System.out.println("Enter number of Processes ");
	int n=in.nextInt();
	if(deci==true){
		System.out.println("Enter each Process name,CPU Burst Time and Arrival time ");
		}
		else{System.out.println("Enter each Process name and CPU Burst Time  ");
	}
	for(int i=0;i<n;i++){
		Process pro =new Process();
		if(deci==true){
		pro.process_name=in.next();
		pro.burstTime=in.nextInt();
		pro.arrivalTime=in.nextInt();
		}
		else{
		pro.process_name=in.next();
		pro.burstTime=in.nextInt();
		pro.arrivalTime=0;}
		ProcessScheduler.add(pro);
	}
	System.out.println("Enter Quantum value ");
	int quantum=in.nextInt();
	
}








/*************************************  SRTF  *****************************************************/	

public static void srtf(){
	
}







/*******************************  DEADLOCK DETECTION  **********************************************/	

public static void deadLock(){
	
}







/***************************************************************************************************/
/***************************************************************************************************/






public static void main(String ar[]){
	System.out.println("*********************************************************************");
	System.out.println("          Process Scheduling And DeadLock Detection Algorithms       ");
	System.out.println("*********************************************************************\n");
	int option=-1;
	while(option!=4){
		System.out.print("\nSelect an Operation From Below one and  press Enter");
		System.out.print("\n1. Process Scheduling Algorithm");
		System.out.print("\n2. DeadLock Detection Algorithm");
		System.out.print("\n3. About");
		System.out.print("\n4.Exit");
		option=in.nextInt();
		if(option==1){selectOption();}
		else if(option==2){deadLock();}
		else if(option==3){about();}
		else if(option==4){System.out.print("\n Thankyou For using this Program,\n      ***** Good Bye ");break;}
		else{System.out.print("\nPlease Enter a Valid Option");}
	}
	}
}
