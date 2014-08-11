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
										{{0, 0, 0},{2,2,2}},  //  *** (生存Y/N)|  *** (周遭生物下										
										{{0, 0, 0},{2,2,2}},  //  *@* (@:自己) |. *@*  世代將進行
										{{0, 0, 0},{2,2,2}}   //  *** (*周遭)  |  ***  之狀態        )
							           }; 	
	
	//生物生衍
	public void initBorn() 
	{		
		SurBioPosStatus[1][0][1]=1; // 繁殖成功
		SurBioPosStatus[1][1][1]=2; //目前狀態 未知 設為 2
	}
	// 取得隨機生存地點
	public int[] getBornPos(int i,int j,int size)
	{
		int ss=0;
		int [] NewBioPos= new int[3];
		while(true)
		{				
			NewBioPos[2]=0;
				int RandomRow=(int)(Math.random()*3)-1;
				int RandomColumn=(int)(Math.random()*3)-1;
				
				// 1.滿足此地沒有生物   2. 此地沒有生物死亡過  3.誕生點不超過邊界 4.此地非原地				
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
					//	測試 建議用3*3 出生點被卡位!
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
		SurBioPosStatus[1][0][1]=0;		//已死亡		
	}	
    //是否生存
	public int getAlive()
	{		
		return SurBioPosStatus[1][0][1];
	}	
	//取得生物動作資訊
	public int getAction()
	{		
		return SurBioPosStatus[1][1][1];
	}
	
	//生物的遊戲規則  : nei表示周圍生物數量
	public void nextStatus(int nei)
	{		 		
		if(nei==1)			
			SurBioPosStatus[1][1][1]=1; //下一代需進行的狀態 繁衍	
		else if (nei>=3)
			SurBioPosStatus[1][1][1]=0; //死亡
		else
			SurBioPosStatus[1][1][1]=2;	//不管
	}
	
	//確認生物周遭地點
	public void SurBioPos(int Row,int Column,int status)
	{		 	
		SurBioPosStatus[Row+1][0][Column+1]=status;	
	}
	//分享給周遭生物狀態
	public void ShareStatus(int Row,int Column,int status)
	{		 	
		
		SurBioPosStatus[1-Row][1][1-Column]=status;	
	}
	
	public void socw (int a,int b,int c)
	{
			 System.out.print(SurBioPosStatus[a][b][c]);					 	
	
	}
}
