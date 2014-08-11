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
	public Bio [][]mapaction; // �T��}�C�X�� ��m �� �ʧ@
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
				mapaction[i][j]=new Bio();//�a�ϤW�إߥͪ�����			
	}
	//��0�N�ͪ� �H������
	public void InitBio(int unmber)
	{			
		BornBio=unmber;	
		int Row,Column;		
		for(int i=0 ;i<unmber ;i++)		
		{
			Row=(int)(Math.random()*MapSize);//����0~�a�Ϥj�p���Ʀr
			Column=(int)(Math.random()*MapSize);	
			//��l�� ���������`  �p�G�H���a�I���ƫh����
			if(mapaction[Row][Column].getAlive()==0)				
			mapaction[Row][Column].initBorn();
			else
				i--;
		}
		/*  ���եΪ��I �ȭ���3*3
		mapaction[0][0].initBorn();
		mapaction[0][2].initBorn();
		mapaction[1][1].initBorn();
		mapaction[2][0].initBorn();
		mapaction[2][2].initBorn();
		*/	
	}
	//�U�@�N������
	public void Change()
	{		   				
		period++;
		BornBio=0;
		DieBio=0;  
		//���y�a�ϥͪ� �õ�����T
		for(int i=0 ;i<MapSize ;i++)
			for(int j=0;j<MapSize ;j++)		
				{
				Findnei(i,j);
				ShareBioStatus(i,j);    //�i�D�P�D�ͪ� �i�檺�ʧ@
				}
		
	   for(int i=0 ;i<MapSize ;i++)
			for(int j=0;j<MapSize ;j++)
			{			
				if(mapaction[i][j].getAction()==1) //�ͭl���󦨥�
					{
					int [] NewBioPos;
			
					Findnei(i,j);
					NewBioPos=mapaction[i][j].getBornPos(i,j,MapSize);			
					mapaction[i+NewBioPos[0]][j+NewBioPos[1]].initBorn();				
					
					if(NewBioPos[2]==0)
					BornBio++;
					}
				else if (mapaction[i][j].getAction()==0) //���`���󦨥�
						{			
							if(mapaction[i][j].getAlive()==1)
								DieBio++;
							mapaction[i][j].Die();							
						
						}
				else
					continue;					
			}	  	  	  	 
	}
	
	// ���@�ͪ� ��P��ͪ��ƶq  �����ӥͪ��i�椧�ʧ@
	public void Findnei(int i,int j)
	{						
		int nei=-1;				
		
		for(int Row=-1;Row<=1;Row++)	
			for(int Column=-1;Column<=1;Column++)
			{				
			   if( (i+Row)>=0 && (j+Column) >=0 && (i+Row)<MapSize && (j+Column)<MapSize )
				{
				   int status=mapaction[i+Row][j+Column].getAlive(); //�P��ͪ��P�_
					if(status==1) //�M��P�D�ͪ�(�]�t�ۤv)			
					{
					nei++;					
					mapaction[i][j].SurBioPos(Row,Column,status); ///�P�D�ͪ���m����					
					}	
					else
						mapaction[i][j].SurBioPos(Row,Column,status);
				}
			}		
		
		//���a���ͪ�����
		if(mapaction[i][j].getAlive()!=0)	
				mapaction[i][j].nextStatus(nei);  //�T�w �ͪ� �U�@�Ӱʧ@			
		
	}
	//�o���P�D�ͪ��i�檺�ʧ@
	public void ShareBioStatus(int i,int j)
	{
		int status =mapaction[i][j].getAction();
		for(int Row=-1;Row<=1;Row++)	
			for(int Column=-1;Column<=1;Column++)
			{	
			   if( (i+Row)>=0 && (j+Column) >=0 && (i+Row)<MapSize && (j+Column)<MapSize )
				{
				   if(mapaction[i+Row][j+Column].getAlive()==1) //�M��P�D�ͪ�(�]�t�ۤv					
				  	{
					mapaction[i+Row][j+Column].ShareStatus(Row,Column,status); //���o(i,j)�ͪ��I��T
				   	}
				   else
				   {
					   if(mapaction[i+Row][j+Column].getAction()==0)
						   mapaction[i+Row][j+Column].nextStatus(0);//���`��M�����`�L�O
				   }
				}
			}
	}
	// �i�ܥͪ�����
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
