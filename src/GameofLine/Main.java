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
		
		LivingSpace ls = new LivingSpace(30);//���Ҥj�p	
		ls.InitBio(450);//�üƥͦ��ͪ�
		
	       for(int i=0;i<100;i++)       
	       {	    	
	       ls.showmap();	 //�i������ �ͪ�      
	       ls.Change();	     // �ܾE
	       WaitEnter();	//����ENTER
	      // Thread.sleep(1000);
	       }	      
	}
	 public static void  WaitEnter()
	 {	 
		  Scanner input = new Scanner(System.in);  
		  System.out.print("Press enter to continue....");
		  input.hasNextLine();	  
      /*  ����kEnter �|����2��
      System.out.print("Press enter to exit....");	  
	  try
	  {
	   System.in.read();		  
	  }	  
	  catch(Exception e){}; 
	  */
	 }
	
	
	 
}

