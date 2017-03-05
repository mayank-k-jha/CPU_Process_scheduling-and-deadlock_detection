
/*@author mayank_kumar_jha*/
import java.util.*;

class Process{
	String process_name;int burstTime;int arrivalTime=0;int compTime=0;
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

	public static void result(ArrayList<Process> ProcessScheduler,int n,boolean pre){
		
		int completion_time[]=new int [n];   //completion time array
		int turn_around_time[]=new int[n];   //Turn around time array
		int waiting_time[]=new int[n];       //Waiting time array
		
		Iterator<Process> it =ProcessScheduler.iterator();
		
		//calculating the completion time of the FCFS
		int sumer=0,counter=0;
		
		//creating total waiting time,total turn around time variables
		int total_waiting=0,total_turn_around=0,c=-8;
		System.out.println("\n                           Table : \n");
		System.out.println("Procs Exec order   Arrival TIme    Burst Time      Completion Time    Turn Around Time     Waiting Time");
		
		while(it.hasNext()){
			 Process pro =new Process();
	    	pro=(Process)it.next();if(c==-8){sumer=pro.arrivalTime;c=0;}
	    	sumer+=pro.burstTime;
	    	if(pre==false){
	    	completion_time[counter]=sumer;  
	    	
	    	}
	    	else{
	    		completion_time[counter]=pro.compTime;
	    	}
	    	//storing completion time of each process
	    	turn_around_time[counter]=completion_time[counter]-pro.arrivalTime; //storing T T for each process
	    	waiting_time[counter]=turn_around_time[counter]-pro.burstTime;//storing W T for each process
	    	total_waiting+=waiting_time[counter]; //calculating total_waiting_time
	    	total_turn_around+=turn_around_time[counter];//calculating total_turn around time
	    	System.out.print("\t  "+pro.process_name+"\t\t"+pro.arrivalTime+"\t\t"+pro.burstTime+"\t\t"+completion_time[counter]);
	    	System.out.println("\t\t"+turn_around_time[counter]+"\t\t\t"+waiting_time[counter]);
	    	++counter; // index update
	    }
		 System.out.println("\nResult :");
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
		result(ProcessScheduler,n,false);
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
		pro.process_name=in.next();
		pro.burstTime=in.nextInt();
		if(deci==true){
		pro.arrivalTime=in.nextInt();
		}
		else{
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
			Iterator<Process> it = ProcessScheduler.iterator();
			while(it.hasNext()){
				 Process pro =new Process();pro=(Process)it.next();
				 s.push(pro);
			}
		}
		
		//Setting Process sequence according to SJF in new stack s1
		
		{//finding first process
			int minArrivalTime=99999,minBurstTime=99999,loc=-9;
				for(int i=0;i<s.size();i++){
					if(s.elementAt(i).arrivalTime<=minArrivalTime){   //finding shortest arrival time process
						minArrivalTime=s.elementAt(i).arrivalTime;
						if(s.elementAt(i).burstTime<=minBurstTime){  //if two process have same arrival time
							minBurstTime=s.elementAt(i).burstTime;loc=i;//choosing the one with shortest burst time
						}
					}
				}
				s1.push(s.elementAt(loc));s.removeElementAt(loc);
			}
		
		//setting remaining process other than first
		int minArrivalTime=s1.peek().arrivalTime+s.peek().burstTime,minn=99999,loc=-9;
		
		while(!s.isEmpty()){ 
			for(int i=0;i<s.size();i++){
				if(s.elementAt(i).arrivalTime<=minArrivalTime){
						if(s.elementAt(i).burstTime<=minn){//selecting process with shortest burst time up to completion of
						minn=s.elementAt(i).burstTime;loc=i;//the previous process completion
					}
				}
			}minArrivalTime+=s.elementAt(loc).burstTime;minn=99999;//updating the minArrivalTime and minn
			s1.push(s.elementAt(loc));s.removeElementAt(loc);
			
		}	
	ProcessScheduler.clear();               //clearing previous Process order list
	
	for(int i=0;i<s1.size();i++){
		ProcessScheduler.add(s1.elementAt(i));  //assigning new process order to list
	}}
	
	//calling result function 
	result(ProcessScheduler,n,false);
}







/***********************************  Round Robin *************************************************/	
/***********************************  Preemptive  *************************************************/

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
		pro.process_name=in.next();
		pro.burstTime=in.nextInt();
		if(deci==true){
		pro.arrivalTime=in.nextInt();
		}
		else{
		pro.arrivalTime=0;}
		ProcessScheduler.add(pro);
	}
	System.out.println("Enter Quantum value ");
	int quantum=in.nextInt();
	Collections.sort(ProcessScheduler,new comp());  //sorting with arrival time
	
	//distribution of quantum among processes on the basis of their arrival time
	Stack<Process> s=new Stack<>();
	Stack<Process> s2=new Stack<>();
	HashMap<String,Integer> map=new HashMap<>();
	{//filling s stack with Processes
		Iterator<Process> it = ProcessScheduler.iterator();
		while(it.hasNext()){
			 Process pro =new Process();pro=(Process)it.next();
			 map.put(pro.process_name,pro.burstTime);
			 s.push(pro);
		}
	}
	
	
	//clearing process list after filling stack s
	ProcessScheduler.clear();
	
	//arranging Processes in Stack s
	int TimeCounter=s.elementAt(0).arrivalTime;
	System.out.println("                   Gantt Chart \n");
	while(!s.isEmpty()){
		for(int i=0;i<s.size();i++){
			if(s.elementAt(i).burstTime-quantum>=0){
				s.elementAt(i).burstTime=s.elementAt(i).burstTime-quantum;
				TimeCounter+=quantum;
				s.elementAt(i).compTime=TimeCounter;
			}
			else if(s.elementAt(i).burstTime-quantum<0){
				TimeCounter+=(s.elementAt(i).burstTime%quantum);
				s.elementAt(i).burstTime-=(s.elementAt(i).burstTime%quantum);
				s.elementAt(i).compTime=TimeCounter;
			}
			
			System.out.print("("+s.elementAt(i).process_name+",");int key=-7;
			if(s.elementAt(i).burstTime==0){
				s2.add(s.elementAt(i));System.out.print("Completed,"+s.elementAt(i).compTime+") ");key=0;
				s.removeElementAt(i);--i;//decrementing i for applying update as removal
			}
			System.out.print(key==-7?(s.elementAt(i).compTime+") "):"");
		}
		
	}System.out.println("\n\n\n                    Table            \n\n");
	//copying the cpuBurstTime into all the process after the further modification
    for(int i=0;i<s2.size();i++){
    	s2.elementAt(i).burstTime=map.get(s2.elementAt(i).process_name);
    	
    	ProcessScheduler.add(s2.elementAt(i));
    }
	
	//calling result function by passing the updated and arranged process scheduler
    result(ProcessScheduler,n,true);

}








/*************************************  SRTF  *****************************************************/	

public static void srtf(){
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
	Stack<Process> s=new Stack<>();
	Stack<Process> s1=new Stack<>();
	
	for(int i=0;i<n;i++){
		Process pro =new Process();
		pro.process_name=in.next();
		pro.burstTime=in.nextInt();
		if(deci==true){
		pro.arrivalTime=in.nextInt();
		}
		else{
		pro.arrivalTime=0;}
		ProcessScheduler.add(pro);
	}
	Collections.sort(ProcessScheduler,new comp());
	Iterator<Process> it =ProcessScheduler.iterator();
	while(it.hasNext()){
		Process p=new Process();
		p=(Process)it.next();
		s.add(p);
	}
	ProcessScheduler.clear();
	int minSuc=9999999;
	{//finding first process
		int minArrivalTime=99999,minBurstTime=99999,loc=-9;
			for(int i=0;i<s.size();i++){
				if(s.elementAt(i).arrivalTime<=minArrivalTime){   //finding shortest arrival time process
					minArrivalTime=s.elementAt(i).arrivalTime;
					if(s.elementAt(i).burstTime<=minBurstTime){  //if two process have same arrival time
						minBurstTime=s.elementAt(i).burstTime;loc=i;//choosing the one with shortest burst time
					}
					
				}
				if(s.elementAt(i).arrivalTime!=minBurstTime && s.elementAt(i).arrivalTime<minSuc){
					minSuc=s.elementAt(i).arrivalTime;System.out.println(minSuc);
				}
			}
			s1.push(s.elementAt(loc));s.removeElementAt(loc);
		}
	s1.peek().compTime=s.peek().arrivalTime;
	
	
}







/*******************************  DEADLOCK DETECTION  **********************************************/	

public static void deadLock(){
	
}






/*************************************** Select Option ******************************************/	
public static void selectOption(){
	
	while(true){
		System.out.print("\nSelect an option from below one ");
		System.out.print("\n1. FCFS\n2. SJF\n3. SRTF\n4. Round Robin\n5. Exit\n");
		int option=in.nextInt();
		if(option==1){fcfs();}
		else if(option==2){sjf();}
		else if(option==3){srtf();}
		else if(option==4){roundRobin();}
		else if(option==5){System.out.print("\n Thankyou For using this Program,\n      ***** Good Bye \n");break;}
		else{System.out.print("\nPlease select a valid Option \n");}
	}
}






/*****************************************About *************************************************/	
public static void about(){
	System.out.print("\nName    : Mayank Kumar Jha");
	System.out.print("\nSubject : OS (Operating System )\n");
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
		System.out.print("\n4.Exit\n");
		option=in.nextInt();
		if(option==1){selectOption();}
		else if(option==2){deadLock();}
		else if(option==3){about();}
		else if(option==4){System.out.print("\n Thankyou For using this Program,\n      ***** Good Bye \n");break;}
		else{System.out.print("\nPlease Enter a Valid Option\n");}
	}
	}
}
