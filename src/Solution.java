
/*@author mayank_kumar_jha */
import java.util.*;

class Process{
	String process_name;float burstTime;float arrivalTime=0;float compTime=0;boolean status=false;
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
	
	
/********************************  Timer  ***********************************************/	
	public static void Timer(long t,boolean c,String Pattern){
		try{
			for(int i=0;i<20;i++){
				Thread.sleep(t);
				if(c==true){System.out.print(Pattern);
				}
				}
						
		}
		catch(InterruptedException e){
			System.out.println("Error Detected");
		}
	}
	
	
	
/*******************************Result Display Function  *****************************************/	

	public static void result(ArrayList<Process> ProcessScheduler,int n,boolean pre){
		//pre decides preemptiveness of the Scheduling Algorithm
		
		float completion_time[]=new float [n];   //completion time array
		float turn_around_time[]=new float[n];   //Turn around time array
		float waiting_time[]=new float[n];       //Waiting time array
		
		Iterator<Process> it =ProcessScheduler.iterator();
		
		//calculating the completion time of the FCFS
		float sumer=0;int counter=0;
		
		//creating total waiting time,total turn around time variables
		int total_waiting=0,total_turn_around=0,c=-8;
		System.out.println("\n                           Table : \n");
		System.out.println("Procs Exec order   Arrival TIme    Burst Time      Completion Time    Turn Around Time     Waiting Time");
		
		while(it.hasNext()){
			Timer(60,false,"");
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
			pro.burstTime=in.nextFloat();
			pro.arrivalTime=in.nextFloat();
			}
			else{
			pro.process_name=in.next();
			pro.burstTime=in.nextFloat();
			pro.arrivalTime=0;}
			ProcessScheduler.add(pro);
		}
		
		{Collections.sort(ProcessScheduler, new comp());}
        
		System.out.print("\nProcessing ");Timer(220,true,".");System.out.println("\n");
		//Calling result function
		result(ProcessScheduler,n,false);
	}
	
	
	
/**************************************  SJF  *****************************************************/	

public static void sjf(){
	System.out.println("-----------------------SJF -----------------------------");
	
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
		pro.burstTime=in.nextFloat();
	
		if(deci==true){
		pro.arrivalTime=in.nextFloat();
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
			float minArrivalTime=99999f,minBurstTime=99999f;int loc=-9;
	
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
		float minArrivalTime=s1.peek().arrivalTime+s1.peek().burstTime,minn=99999;int loc=-9;
		
		while(!s.isEmpty()){
			
			for(int i=0;i<s.size();i++){
				if(s.elementAt(i).arrivalTime<=minArrivalTime){
						if(s.elementAt(i).burstTime<=minn){//selecting process with shortest burst time up to completion of
						minn=s.elementAt(i).burstTime;loc=i;//the previous process completion
					}
				}
			}
			minArrivalTime+=s.elementAt(loc).burstTime;minn=99999;//updating the minArrivalTime and minn
			s1.push(s.elementAt(loc));
			s.removeElementAt(loc);
			
		}	
		
	ProcessScheduler.clear();               //clearing previous Process order list
	
	for(int i=0;i<s1.size();i++){
		ProcessScheduler.add(s1.elementAt(i));  //assigning new process order to list
	}}
	
	System.out.print("\nProcessing ");Timer(220,true,".");System.out.println("\n");
	//calling result function 
	result(ProcessScheduler,n,false);
}



/***********************************  Round Robin *************************************************/	
/***********************************  Preemptive  *************************************************/

public static void roundRobin(){
	
	ArrayList<Process> ProcessScheduler =new ArrayList<>();
	boolean deci=false;
	System.out.println("---------------------------------- Round Robin ----------------------------------\n");
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
		pro.burstTime=in.nextFloat();
		
		if(deci==true){
		pro.arrivalTime=in.nextFloat();
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
	HashMap<String,Float> map=new HashMap<>();
	
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
	float TimeCounter=s.elementAt(0).arrivalTime;
	System.out.println("                   Gantt Chart \n");
	
	while(!s.isEmpty()){
	
		for(int i=0;i<s.size();i++){Timer(50,false,"");
		
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
		
	}
	System.out.println("\n\n\n                    Table            \n\n");
	//copying the cpuBurstTime into all the process after the further modification
   
	for(int i=0;i<s2.size();i++){
    	s2.elementAt(i).burstTime=map.get(s2.elementAt(i).process_name);
    	
    	ProcessScheduler.add(s2.elementAt(i));
    }
    
    System.out.print("\nProcessing ");Timer(220,true,".");System.out.println("\n");
	
	//calling result function by passing the updated and arranged process scheduler
    result(ProcessScheduler,n,true);

}


/*************************************  SRTF  *****************************************************/	
/***********************************  Preemptive  *************************************************/



public static void srtf(){
	
	ArrayList<Process> ProcessScheduler =new ArrayList<>();
	boolean deci=false;
	System.out.println("---------------------------------- SRTF ----------------------------------\n");
	System.out.print("\nEnter y / n for Arrival time option ");
	char ch=in.next().charAt(0);
	
	if(ch=='y'){deci=true;}
	System.out.println("Enter number of Processes ");
	int n=in.nextInt();                         //total number of processes
	
	if(deci==true){
		System.out.println("Enter each Process name,CPU Burst Time and Arrival time ");
		}
		else{System.out.println("Enter each Process name and CPU Burst Time  ");
	}
	
	Stack<Process> s=new Stack<>();             //Operational container
	Stack<Process> s1=new Stack<>();            //SRTF algo performer
	Stack<Process> s2=new Stack<>();            //SRTF scheduled container
	
	for(int i=0;i<n;i++){
		Process pro =new Process();
		pro.process_name=in.next();
		pro.burstTime=in.nextFloat();
		
		if(deci==true){
		pro.arrivalTime=in.nextFloat();
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
	
	//copying stack s into Copy to restore the original value ogf burst time after 
	//operations on its contents
	//for Process Scheduling
	
	HashMap<String,Float> Copy=new HashMap<>();
	
	for(int i=0;i<s.size();i++){
		Copy.put(s.elementAt(i).process_name,s.elementAt(i).burstTime);
	}
	
	
	ProcessScheduler.clear();
	float maxArr=-9,count=0f;
	float minArr=999999f;float secMinArr=999999f;
	int run=0,lock=0,lck=0,key=0;
	
	while(!s.isEmpty() && lock==0){
		
		if(run==0){
			run=-9;
		
		//Filtering shortest Arrival Time
		for(int i=0;i<s.size();i++){
			if(s.elementAt(i).arrivalTime<minArr){
				minArr=s.elementAt(i).arrivalTime;     //minimum arrival time
			}
			if(s.elementAt(i).arrivalTime>maxArr){
				maxArr=s.elementAt(i).arrivalTime;  //maximum arrival time
			}
		}}
		
		//Collecting all process with shortest arrival time
		float ShortBurst=9999;if(lck==-9){minArr=secMinArr;}
		
		for(int i=0;i<s.size();i++){
			if(s.elementAt(i).arrivalTime<=minArr){
				s1.push(s.elementAt(i));
			}
		}
		
		//Collecting process with shortest burst time from s1
		int loc=-9;
		
		for(int i=0;i<s1.size();i++){
			if(s1.elementAt(i).burstTime<ShortBurst){
				ShortBurst=s1.elementAt(i).burstTime;loc=i;
			}
		}
		s1.clear();                //clearing processing stack 
		
		if(lock==0){
		//finding second minArr
		secMinArr=99999;
		
		for(int i=0;i<s.size();i++){
			if(s.elementAt(i).arrivalTime>minArr && s.elementAt(i).arrivalTime<secMinArr){
				secMinArr=s.elementAt(i).arrivalTime;
			}
		}}
		lck=-9;                       //lock for 2nd secSuc running
		if(secMinArr==maxArr){
			lock=-9;              //lock for breaking the while loop after all process arrival
		}
		
		//Initializing count
		if(key==0){count=minArr;key=-9;}
		
		//updating count to current passed processing time for further assigning 
		count+=(secMinArr-minArr);
		
		//checking for the particular selected process completion
		
		if(s.elementAt(loc).burstTime-(secMinArr-minArr)>0){
			s.elementAt(loc).burstTime-=(secMinArr-minArr)  ;
			s.elementAt(loc).compTime=count;
		}
		else if(s.elementAt(loc).burstTime-(secMinArr-minArr)<=0){
			if(s.elementAt(loc).burstTime-(secMinArr-minArr)==0){
				s.elementAt(loc).compTime = count;
				s.elementAt(loc).status=true;
				s2.push(s.elementAt(loc));    //completed Processes got collected in s2
				s.removeElementAt(loc);
			}
			else{
			s.elementAt(loc).compTime = count+s.elementAt(loc).burstTime;
			s.elementAt(loc).status=true;
			s2.push(s.elementAt(loc));    //completed Processes got collected in s2
			s.removeElementAt(loc);
			}
			}
		}
	
	//now s2 contains all those processes which are completed
	//sorting remaining process with their burst time and applying SJF 
	    Collections.sort(s,new comp1());
		
	    while(!s.isEmpty()){
			s.elementAt(0).compTime=count+s.elementAt(0).burstTime;
			count+=s.elementAt(0).burstTime;
			s2.push(s.elementAt(0));s.removeElementAt(0);
		}
		
		
		//Now s2 is ready with all process in order of their completion
		
		//Restoring the original burst time from copy stack to s2 stack
		for(int i=0;i<s2.size();i++){
			s2.elementAt(i).burstTime=Copy.get(s2.elementAt(i).process_name);
		}
		
		System.out.print("\nProcessing ");Timer(220,true,".");System.out.println("\n");
		
		//Getting Result
		result(new ArrayList<Process>(s2),n,true);
		
		}



/*******************************  DEADLOCK DETECTION  **********************************************/	

public static void deadLock(){
	
	System.out.println("********************************** Deadlock Detection *********************************\n");
	System.out.println("Enter number of Processes ");
	int n=in.nextInt();
	
	System.out.println("Enter number of resources");
	int r=in.nextInt();
	int AllocationTable[][]=new int[n][r];
	
	//Allocation Table
	System.out.println("Enter Allocation Table for each Process");
	
	for(int i=0;i<n;i++){
		for(int j=0;j<r;j++){
			AllocationTable[i][j]=in.nextInt();
		}
	}
	
	//Request/Max Table Entry
    int Request[][]=new int[n][r];
    System.out.println("Enter Max/Request Table for each Process");
	
    for(int i=0;i<n;i++){
		for(int j=0;j<r;j++){
			Request[i][j]=in.nextInt();
		}
	}
	
	//Input Available or Total Resource Instance
	
	System.out.println("Select from below Option :");
	System.out.println("1.Available");
	System.out.println("2.Total Resource");
	
	int option=in.nextInt();
	int Available[]=new int [r];
	int Total[]=new int [r];
	
	if(option==1){
		System.out.println("Enter Available Resource Instance : ");
		for(int i=0;i<r;i++){
			Available[i]=in.nextInt();
		}
	}
	else if(option==2){
		System.out.println("Enter Total Resource Instance : ");
		for(int i=0;i<r;i++){
			Total[i]=in.nextInt();
		}		
	}
	else System.out.println("Invalid Option ");

	/***************  Input Need Table              */
	int Need[][]=new int[n][r];
	
	for(int i=0;i<n;i++){
		for(int j=0;j<r;j++){
			Need[i][j]=Request[i][j]-AllocationTable[i][j];
		}
	}
	
	/***************  Calculating Need Table              */
	
	System.out.print("Calculating Need ,Please wait");Timer(130,true,".");
	System.out.println("\n");
	
	for(int i=0;i<n;i++){
		System.out.print("P"+(i)+"  ");
		for(int j=0;j<r;j++){
			System.out.print(Need[i][j]+" ");Timer(20,false,"");
		}System.out.println();
	}
	
	/***************  Calculating Total/Available Resource Instance      */
	
	if(option==1){System.out.println("Total Resource Instance : ");
	
	for(int i=0;i<r;i++){
		int sum=0;
		sum+=Available[i];
		for(int j=0;j<n;j++){sum+=AllocationTable[j][i];}
		Total[i]=sum;                             //Filling Total Resource Instance
		System.out.print(sum+" ");
	}
	System.out.println("\n");
	}
	else{motion("Available Resource Instance : ",5);
		for(int i=0;i<r;i++){
			int sum=0;
			for(int j=0;j<n;j++){
				sum+=AllocationTable[j][i];
			}
			System.out.print((Total[i]-sum)+" ");
			Available[i]=(Total[i]-sum);          //Filling Available Resource Instance
		}System.out.println("\n");
	}

	/***************  Running Deadlock Detection      */
	
	System.out.print("\n\nRunning Deadlock Detection ");Timer(150,true,".");
	System.out.println("\n");
	
	Stack <Integer>s=new Stack<>();             //Operational Container
	Stack <String>s1=new Stack<>();             //Safe Sequence Collector
	boolean status[]=new boolean[n];            //Status flag
	
	for(int i=0;i<n;i++){s.push(i);}
	int counter=0,ke=0;
	
	while(!s.isEmpty()){
		++counter;
		for(int i=0;i<n;i++){
			int key=0;
			for(int j=0;j<r;j++){
				if(status[i]==true || Need[i][j]>Available[j]){key=-9;break;}
			}
			if(key==-9){continue;}
			else{
			for(int j=0;j<r;j++){Need[i][j]=0;
				Available[j]+=AllocationTable[i][j];
			}
			status[i]=true;                //Assigning status update
			if(s.isEmpty()){break;}
			s1.push("P"+i);
			s.pop();}
			}
		
		if(counter>(n*r)){System.out.print("           ");motion("*** WARNING ***",3);System.out.print("   Deadlock Detected,Exiting  ");
		Timer(120,true,"X");System.out.println("\n");ke=-9;
		break;}
	}
	/*        Result      */
	
	if(ke==0){
	motion("Safe State Found\n",3);
	motion("Safe Sequence is : ",2);
	for(int i=0;i<s1.size();i++){
		System.out.print(s1.elementAt(i)+" ");Timer(60,false,"");
	}
	System.out.println("\n");
	}

	System.out.print("                   ");	
	motion("Thank You For using DeadLock Detection ",3);
	System.out.print("                      ");
	motion("***** Good Bye",5);
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
		else if(option==5){
			System.out.print("                   ");	
			motion("Thank You For using Process Scheduling",3);
			System.out.print("                      ");
			motion("***** Good Bye",5);break;
		}
		else{System.out.print("\nPlease select a valid Option \n");}
	}
}



/*****************************************About *************************************************/	
public static void about(){
	System.out.print("\nName    : Mayank Kumar Jha");
	System.out.print("\nSubject : OS (Operating System )\n");
}




/******************************** Graphical/Timed Text Representation ******************************/
public static void motion(String s,long t){
	for(int i=0;i<s.length();i++){
		System.out.print(s.charAt(i));Timer(t,false,"");
	}System.out.println();
}



/***************************************************************************************************/
/***************************************************************************************************/


public static void main(String ar[]){
	motion("*********************************************************************",1);
	
	//creating timed representation of texts
	System.out.print("            ");
	motion("Process Scheduling And DeadLock Detection Algorithms",4);
	motion("*********************************************************************",1);
	System.out.println();
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
		else if(option==4){
		System.out.print("                   ");	
		motion("Thank You For using this Program",2);System.out.print("                      ");
		motion("***** Good Bye",2);break;}
		else{System.out.print("\nPlease Enter a Valid Option\n");}
	}
	}
}