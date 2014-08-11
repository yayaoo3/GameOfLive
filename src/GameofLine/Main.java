/*************************************************************************
 * FileName       	[ Main.java ]
 * PackageName    	[ GameOfLife ]
 * JavaProjectName	[ GameOfLife ]
 * Synopsis       	[ This file initials all the settings.]
 * Author         	[ Cheng-Wen-Zhi (Oswin)  ]
 * Copyright      	[ Copyleft(c) 2014 MITLAB, GIEE, NTUST, Taiwan ]
**************************************************************************/
package GameofLine;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException{
		
		LivingSpace ls = new LivingSpace(30);//環境大小	
		ls.InitBio(450);//亂數生成生物
		 
	       for(int i=0;i<100;i++)       
	       {	    	
	       ls.showmap();	 //展示環境 生物      
	       ls.Change();	     // 變遷
	       WaitEnter();	//等待ENTER
	      // Thread.sleep(1000);
	       }	      
	}
	 public static void  WaitEnter()
	 {	 
		  Scanner input = new Scanner(System.in);  
		  System.out.print("Press enter to continue....");
		  input.hasNextLine();	  
      /*  此方法Enter 會偵測2次
      System.out.print("Press enter to exit....");	  
	  try
	  {
	   System.in.read();		  
	  }	  
	  catch(Exception e){}; 
	  */
	 }
	
	
	 
}

