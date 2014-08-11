/*********************************************************************
 * FileName       	[ Bio.java ]
 * PackageName    	[ gameOfLife ]
 * JavaProjectName	[ GameOfLife ]
 * Synopsis       	[ This file generates new bio.]
 * Author         	[ Cheng-Wen-Zhi (Oswin) ]
 * Copyright      	[ Copyleft(c) 2014 MITLAB, GIEE, NTUST, Taiwan ]
*********************************************************************/
package GameofLine;
import java.util.Scanner;

public class Bio { 
	 // 0:die  1:Born   2:Nothing
	private int [][][] SurBioPosStatus={
										{{0, 0, 0},{2,2,2}},  //  *** (�ͦsY/N)|  *** (�P�D�ͪ��U										
										{{0, 0, 0},{2,2,2}},  //  *@* (@:�ۤv) |. *@*  �@�N�N�i��
										{{0, 0, 0},{2,2,2}}   //  *** (*�P�D)  |  ***  �����A        )
							           }; 	
	
	//�ͪ��ͭl
	public void initBorn() 
	{		
		SurBioPosStatus[1][0][1]=1; // �c�ަ��\
		SurBioPosStatus[1][1][1]=2; //�ثe���A ���� �]�� 2
	}
	// ���o�H���ͦs�a�I
	public int[] getBornPos(int i,int j,int size)
	{
		int ss=0;
		int [] NewBioPos= new int[3];
		while(true)
		{				
			NewBioPos[2]=0;
				int RandomRow=(int)(Math.random()*3)-1;
				int RandomColumn=(int)(Math.random()*3)-1;
				
				// 1.�������a�S���ͪ�   2. ���a�S���ͪ����`�L  3.�ϥ��I���W�L��� 4.���a�D��a				
				if(SurBioPosStatus[RandomRow+1][0][RandomColumn+1]==0 && 
						SurBioPosStatus[RandomRow+1][1][RandomColumn+1]!=0 &&
						(i+RandomRow)>=0 && (j+RandomColumn) >=0 &&
						 (i+RandomRow)<size && (j+RandomColumn)<size &&
						 (RandomRow!=0 || RandomColumn!=0) )			
				{											
					NewBioPos[0]=RandomRow;
					NewBioPos[1]=RandomColumn;			
					break;
				}	
				
				
			  	ss++;
				if(ss>=50)				
				{		
					NewBioPos[2]=1;
					//	���� ��ĳ��3*3 �X���I�Q�d��!
					/*  Scanner input = new Scanner(System.in);  
					  System.out.print("shit.");
					  input.hasNextLine();
					*/
					break;
				}	
							
		}
		return NewBioPos;
		
	}
	public void Die()
	{
		SurBioPosStatus[1][0][1]=0;		//�w���`		
	}	
    //�O�_�ͦs
	public int getAlive()
	{		
		return SurBioPosStatus[1][0][1];
	}	
	//���o�ͪ��ʧ@��T
	public int getAction()
	{		
		return SurBioPosStatus[1][1][1];
	}
	
	//�ͪ����C���W�h  : nei��ܩP��ͪ��ƶq
	public void nextStatus(int nei)
	{		 		
		if(nei==1)			
			SurBioPosStatus[1][1][1]=1; //�U�@�N�ݶi�檺���A �c�l	
		else if (nei>=3)
			SurBioPosStatus[1][1][1]=0; //���`
		else
			SurBioPosStatus[1][1][1]=2;	//����
	}
	
	//�T�{�ͪ��P�D�a�I
	public void SurBioPos(int Row,int Column,int status)
	{		 	
		SurBioPosStatus[Row+1][0][Column+1]=status;	
	}
	//���ɵ��P�D�ͪ����A
	public void ShareStatus(int Row,int Column,int status)
	{		 	
		
		SurBioPosStatus[1-Row][1][1-Column]=status;	
	}
	
	public void socw (int a,int b,int c)
	{
			 System.out.print(SurBioPosStatus[a][b][c]);					 	
	
	}
}
