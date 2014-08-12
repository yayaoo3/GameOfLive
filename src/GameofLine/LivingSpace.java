/*******************************************************************************
 * FileName       	[ LivingSpace.java ]
 * PackageName    	[ gameOfLife ]
 * JavaProjectName	[ GameOfLife ]
 * Synopsis       	[ This file sets the environment and shows new generations.]
 * Author         	[ Cheng-Wen-Zhi (Oswin) ]
 * Copyright      	[ Copyleft(c) 2014 MITLAB, GIEE, NTUST, Taiwan ]
********************************************************************************/
package GameofLine;


public class LivingSpace {

	private int MapSize;
	public Bio [][]mapaction; // 三圍陣列合併 位置 及 動作
	private int period =0;	
	private int BornBio,DieBio,CurBio=0,NextBio=0;
	// Built living space
	public LivingSpace(int size)
	{
		this.MapSize =size;				
		Init();		
	}
	// Produce maps
	private void Init()
	{		
		mapaction = new Bio[MapSize][MapSize];
		for(int i=0 ;i<MapSize ;i++)
			for(int j=0;j<MapSize ;j++)			
				mapaction[i][j]=new Bio();//地圖上建立生物物件			
	}
	//第0代生物 隨機分布
	public void InitBio(int unmber)
	{			
		BornBio=unmber;	
		int Row,Column;		
		for(int i=0 ;i<unmber ;i++)		
		{
			Row=(int)(Math.random()*MapSize);//產生0~地圖大小的數字
			Column=(int)(Math.random()*MapSize);	
			//初始化 為全部死亡  如果隨機地點重複則重來
			if(mapaction[Row][Column].getAlive()==0)				
			mapaction[Row][Column].initBorn();
			else
				i--;
		}
		/*  測試用的點 僅限於3*3
		mapaction[0][0].initBorn();
		mapaction[0][2].initBorn();
		mapaction[1][1].initBorn();
		mapaction[2][0].initBorn();
		mapaction[2][2].initBorn();
		*/	
	}
	//下世代的改變
	public void Change()
	{		   				
		period++;
		BornBio=0;
		DieBio=0;  
		//掃描地圖生物 並給予資訊
		for(int i=0 ;i<MapSize ;i++)
			for(int j=0;j<MapSize ;j++)		
				{
				Findnei(i,j);
				ShareBioStatus(i,j);    //告訴周遭生物 進行的動作
				}
		
	   for(int i=0 ;i<MapSize ;i++)
			for(int j=0;j<MapSize ;j++)
			{			
				if(mapaction[i][j].getAction()==1) //生衍條件成立
					{
					int [] NewBioPos;
			
					Findnei(i,j);
					NewBioPos=mapaction[i][j].getBornPos(i,j,MapSize);			
					mapaction[i+NewBioPos[0]][j+NewBioPos[1]].initBorn();				
					
					if(NewBioPos[2]==0)
					BornBio++;
					}
				else if (mapaction[i][j].getAction()==0) //死亡條件成立
						{			
							if(mapaction[i][j].getAlive()==1)
								DieBio++;
							mapaction[i][j].Die();							
						
						}
				else
					continue;					
			}	  	  	  	 
	}
	
	// 對單一生物 找周圍生物數量  紀錄該生物進行之動作
	public void Findnei(int i,int j)
	{						
		int nei=-1;				
		
		for(int Row=-1;Row<=1;Row++)	
			for(int Column=-1;Column<=1;Column++)
			{				
			   if( (i+Row)>=0 && (j+Column) >=0 && (i+Row)<MapSize && (j+Column)<MapSize )
				{
				   int status=mapaction[i+Row][j+Column].getAlive(); //周圍生物判斷
					if(status==1) //尋找周遭生物(包含自己)			
					{
					nei++;					
					mapaction[i][j].SurBioPos(Row,Column,status); ///周遭生物位置紀錄					
					}	
					else
						mapaction[i][j].SurBioPos(Row,Column,status);
				}
			}		
		
		//此地有生物的話
		if(mapaction[i][j].getAlive()!=0)	
				mapaction[i][j].nextStatus(nei);  //確定 生物 下一個動作			
		
	}
	//得知周遭生物進行的動作
	public void ShareBioStatus(int i,int j)
	{
		int status =mapaction[i][j].getAction();
		for(int Row=-1;Row<=1;Row++)	
			for(int Column=-1;Column<=1;Column++)
			{	
			   if( (i+Row)>=0 && (j+Column) >=0 && (i+Row)<MapSize && (j+Column)<MapSize )
				{
				   if(mapaction[i+Row][j+Column].getAlive()==1) //尋找周遭生物(包含自己					
				  	{
					mapaction[i+Row][j+Column].ShareStatus(Row,Column,status); //取得(i,j)生物點資訊
				   	}
				   else
				   {
					   if(mapaction[i+Row][j+Column].getAction()==0)
						   mapaction[i+Row][j+Column].nextStatus(0);//死亡後清除死亡印記
				   }
				}
			}
	}
	// 展示生物分布
	public void showmap()
	{				
		System.out.println();
		for(int Row=0 ;Row<MapSize ;Row++)
		{
			for(int Col=0;Col<MapSize ;Col++)					
			{
				if(mapaction[Row][Col].getAlive()==1)					
				{
					System.out.print(" @ ");
					NextBio++;
				}
				else
				{
				System.out.print(" - ");
				}
			}
			
			System.out.println();			
		}	
		System.out.println("The "+period+" generation bio :  "+NextBio+"  , Previous generations bio :  "+CurBio);
		System.out.println("This generation borns :  "+BornBio+" and	died  :  "+ DieBio +"   ");
		
		if(BornBio>DieBio)
			System.out.println("Increase : "+(BornBio-DieBio));
		else if(BornBio<DieBio)
			System.out.println("Decrease : "+(DieBio-BornBio));
		else
			System.out.println("Balance");		
		CurBio=NextBio;
		NextBio=0;
		
		System.out.printf("\n");
		
		
	}
		
}
