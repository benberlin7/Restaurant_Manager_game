package iota_cafe3;

import iota_cafe3.Board.personKlasse;

public class Wegfindung {
    //Wegfindung
    public personKlasse findeWeg(personKlasse tmpobj)
    {
    	
    if(tmpobj.posX!=tmpobj.zielX || tmpobj.posY!=tmpobj.zielY)
    {
    	double diffX,diffY;
    	int treffer=0;
    	
           	for(int ix=tmpobj.posX-32;ix<=(tmpobj.posX+32);ix+=32)
	    	        	{	
	    	        		if(treffer==1) break;
	    	        		System.out.println("[---BeginnX---]");
	    	        		for(int iy=tmpobj.posY-32;iy<=(tmpobj.posY+32);iy+=32)
	    	        		{
	    	        			if(treffer==1) break;
	    	        			System.out.println("[----BeginnY----" +ix+ "," +iy+ " || Endbed: " + (tmpobj.posX+32) +"," +(tmpobj.posY+32) + "|| Akt.Pos:" +tmpobj.posX+ "," +tmpobj.posY+ "|| Ziel:" + tmpobj.zielX+ "," +tmpobj.zielY+ "]"); 
	    	        			if(tmpobj.doorX==0) diffX = tmpobj.posX-tmpobj.zielX; else diffX = tmpobj.posX-tmpobj.doorX;
	    	        			if(tmpobj.doorY==0) diffY = tmpobj.posY-tmpobj.zielY; else diffY = tmpobj.posY-tmpobj.doorY;
    	        	        	
	    	        			if(ix>=minX*32 && ix<=maxX*32 && iy>=minY*32 && iy<=maxY*32)
	    	        			{
	    	        				if(blockGrid[tmpobj.posX/32][tmpobj.posY/32]==103)
	    	        				{
	    	        					if(tmpobj.doorX==0) tmpobj.doorX=ix; else tmpobj.doorX=0;
	    	        					if(tmpobj.doorY==0) tmpobj.doorY=iy; else tmpobj.doorY=0;
	    	        				}

	    	        				{
	    	        					System.out.println("[Suche an Stelle : " +ix +"," +iy+ " Diff:" +diffX+ "," +diffY +" Block#:" +blockGrid[ix/32][iy/32] + "]");
		    	        	        	if(diffX<0) {
		    	        	        		System.out.println("[Nach rechts - Vergleich diffX<0: " +tmpobj.posX+ "-" +ix+ "]");
		    	        	        		System.out.println("[Nach rechts Bed 1 : " +tmpobj.posX+ "!=" +ix+ "]");
		    	        	        		System.out.println("[Nach rechts Bed 2 : " +tmpobj.posX+ "<" +ix+ "]");
		    	        	        		System.out.println("[Nach rechts Bed 3 : " +(tmpobj.posX-ix)+ "<=" +(tmpobj.posX+diffX)+ "]");
		    	        	        		if((tmpobj.posX!=ix) && (tmpobj.posX<ix) && ((tmpobj.posX-ix)<=(tmpobj.posX+diffX))) 
		    	        	        			{
		    	        	        			System.out.println("[Start Kollisionsabfrage :" + kollisionsAbfrage(ix,tmpobj.posY)+ "]");
		    	        	        				if(kollisionsAbfrage(ix,tmpobj.posY)==0) 
		    	        	        					{
		    	        	        					System.out.println("[Kollisionsabfrage ok]");
		    	        	        					tmpobj.posX=ix;
		    	        	        					tmpobj.direction=1;
		    	        	        					System.out.println("[X Versetzen auf : " +ix+ "]");
		    	        	        					treffer=1;
		    	        	        					}
		    	        	        			}
		    	        	        	}
		    	        	        	if(diffX>0) {
		    	        	        		System.out.println("[Nach links - Vergleich diffX>0: " +tmpobj.posX+ "+" +ix+ "=" + (tmpobj.posX+ix) + ">" + tmpobj.posX+ "+" +diffX+ "=" +(tmpobj.posX+diffX) + "?]");
		    	        	        		if((tmpobj.posX!=ix) && (tmpobj.posX>ix) &&  (tmpobj.posX+ix)>=(tmpobj.posX+diffX)) 
		    	        	        			{
		    	        	        			System.out.println("[Start Kollisionsabfrage]");
    		    	        	        			if(kollisionsAbfrage(ix,tmpobj.posY)==0) 
    		    	        	        			{
    		    	        	        				System.out.println("[Kollisionsabfrage ok]");

    		    	        	        			tmpobj.posX=ix;
    		    	        	        			tmpobj.direction=3;
    		    	        	        			System.out.println("[X Versetzen auf : " +ix+ "]");
    		    	        	        			treffer=1;
    		    	        	        			}
		    	        	        			}
		    	        	        			}
		    	        	        	if(diffY<0) {
		    	        	        		System.out.println("[Nach unten - Vergleich diffY<0: " +tmpobj.posY+ "-" +iy+ "=" + (tmpobj.posY-iy) + "<" + tmpobj.posY+ "+" +diffY+ "=" +(tmpobj.posY+diffY) + "?]");
		    	        	        		
		    	        	        		if((tmpobj.posY!=iy) && (tmpobj.posY<iy) &&(tmpobj.posY-iy)<=(tmpobj.posY+diffY)) 
		    	        	        			{
    		    	        	        			if(kollisionsAbfrage(tmpobj.posX,iy)==0) 
    		    	        	        			{
    		    	        	        				System.out.println("[Kollisionsabfrage ok]");
    		    	        	        				tmpobj.posY=iy;
    		    	        	        				tmpobj.direction=2;
	    		    	        	        			System.out.println("[Y Versetzen auf : " +iy+ "]");
	    		    	        	        			treffer=1;
    		    	        	        			}
		    	        	        			}
		    	        	        		}
		    	        	        	if(diffY>0) {
		    	        	        		System.out.println("[Nach oben - Vergleich diffY>0: " +iy+"-"+diffY+"<("+tmpobj.posY +"-" +diffY +"]");
		    	        	        		if((tmpobj.posY!=iy) && (tmpobj.posY>iy) && (tmpobj.posY+iy)>=(tmpobj.posY+diffY)) 
		    	        	        			{
		    	        	        			if(kollisionsAbfrage(tmpobj.posX,iy)==0) 
		    	        	        			{
		    	        	        				System.out.println("[Kollisionsabfrage ok]");
    		    	        	        			tmpobj.posY=iy;
    		    	        	        			tmpobj.direction=0;
    		    	        	        			System.out.println("[Y Versetzen auf : " +iy+ "]");
    		    	        	        			treffer=1;
		    	        	        			}
		    	        	        		}
		    	        	        	}
		    	        	        	System.out.println("[Treffer:" +treffer+ "Endbed.Check:" +tmpobj.posX+ "=" +tmpobj.zielX+ "&&" +tmpobj.posY+ "=" +tmpobj.zielY+ " ?]");
		    	        	        	if(tmpobj.posX==tmpobj.zielX && tmpobj.posY==tmpobj.zielY)	
		    	        	        		{
		    	        	        //WENN GAST ANGEKOMMEN
		    	        	        			if(tmpobj.typ==0) //Gast
		    	        	        			{
			    	        	        			if(tmpobj.status==0)
			    	        	        			{
		    	        	        				besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=1;
			    	        	        			tmpobj.status++;
			    	        	        			//Richtung Stuhl ausrichten
			    	        	        			if(blockGrid[tmpobj.posX/32][tmpobj.posY/32]==7) tmpobj.direction=2;
			    	        	        			if(blockGrid[tmpobj.posX/32][tmpobj.posY/32]==8) tmpobj.direction=3;
			    	        	        			if(blockGrid[tmpobj.posX/32][tmpobj.posY/32]==9) tmpobj.direction=0;
			    	        	        			if(blockGrid[tmpobj.posX/32][tmpobj.posY/32]==10) tmpobj.direction=1;
			    	        	        			System.out.println("[---Ende----- Platz genommen auf : " +ix+ "," +iy+  "]");
			    	        	        			}

		    	        	        			}
		    	        	        //WENN KELLNER ANGEKOMMEN
		    	        	        			if(tmpobj.typ==1) 
		    	        	        			{
		    	        	        				if(tmpobj.status==106) //Kassiert
		    	        	        				{
		    	        	        					tmpobj.status=100;
		    	        	        					besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=0;
		    	        	        					tmpobj.zielX=tmpobj.homeX;
		    	        	        					tmpobj.zielY=tmpobj.homeY;
		    	        	        					//Suche Gast
		    	        	        					for (int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
			    	        	            	        {
			    	        	            	        	personKlasse tmpobj4 = lPerson.get(iPerson2);
			    	        	            	            if(tmpobj4.status==5)
			    	        	            	            {
			    	        	            	            	besetztGrid[tmpobj4.posX/32][tmpobj4.posY/32]=0;
			    	        	            	            	tmpobj4.status=6;
			    	        	            	            	tmpobj4.zielX=224;
			    	        	            	            	tmpobj4.zielY=448;
			    	        	            	            	score+=tmpobj4.gerichtWert;
			    	        	            	            }
			    	        	            	        }
		    	        	        				}
		    	        	        				
		    	        	        				if(tmpobj.status==105) //Essen ausgeliefert
		    	        	        				{
		    	        	        					besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=0;
		    	        	        					tmpobj.status=100; //Zur Kasse gehen
		    	        	        					tmpobj.zielX=tmpobj.homeX;
		    	        	        					tmpobj.zielY=tmpobj.homeY;
		    	        	        					for (int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
			    	        	            	        {
			    	        	            	        	personKlasse tmpobj4 = lPerson.get(iPerson2);
			    	        	            	            if(tmpobj4.status==3)
			    	        	            	            {
			    	        	            	            	tmpobj4.status=4;
			    	        	            	            	break;
			    	        	            	            }
			    	        	            	        }
		    	        	        				}
		    	        	        				if(tmpobj.status==104) //Holt essen
		    	        	        				{
		    	        	        					
		    	        	        					//Suche Person die Essen erwartet
		    	        	        					for (int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
			    	        	            	        {
			    	        	            	        	personKlasse tmpobj4 = lPerson.get(iPerson2);
			    	        	            	            if(tmpobj4.status==3)
			    	        	            	            {
			    	        	            	            	int[] tmpArr = findeFreieStelle(tmpobj4.posX,tmpobj4.posY);
			    	        	            	            	tmpobj.zielX= tmpArr[0];
			    	        	            	            	tmpobj.zielY= tmpArr[1];
			    	        	            	            	tmpobj.status=105;
			    	        	            	            	break;
			    	        	            	            }
			    	        	            	        }
		    	        	        				}

		    	        	        				if(tmpobj.status==100 && tmpobj.posX==tmpobj.zielX && tmpobj.posY==tmpobj.zielY)
		    	        	        				{
				    	        	        			besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=1;
				    	        	        			tmpobj.status=101;
		    	        	        				}
			    	        	        			if(tmpobj.status==103) //Hat Bestellung ausgeliefert, Suche Koch und fange an mit Zubereitung
			    	        	        			{
			    	        	        				bestellungAbgegeben++;
				    	        	        				for (int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
				    	        	            	        {
				    	        	            	        	personKlasse tmpobj4 = lPerson.get(iPerson2);
				    	        	            	            if(tmpobj4.status==201)
				    	        	            	            {
				    	        	            	            	tmpobj4.status=202;
				    	        	            	            	break;
				    	        	            	            }
				    	        	            	        }
			    	        	        				tmpobj.status=100;	
			    	        	        			}
			    	        	        			
		    	        	        			}
		    	        	        //WENN KOCH ANGEKOMMEN
		    	        	        			if(tmpobj.typ==2) //Koch
		    	        	        			{
		    	        	        			besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=1;
		    	        	        			tmpobj.status++;
		    	        	        			}
		    	        	        		}
		    	        	        	
	    	        				}
	    	        			}
	    	        		}
	    	        	}; 
	    	        	//Zufallsbewegung falls keine Bewegung möglich
	    	        	if(treffer==0)
	    	        	{
	    	        		int randX,randY;
	    	        		do
	    	        		{
	    	        		randX =(int)(Math.random() * 3) -1;
	    	        		randY =(int)(Math.random() * 3) -1;
	    	        		System.out.println("Rand:" +randX+ "," +randY);
	    	        		} while (kollisionsAbfrage(tmpobj.posX+(randX*32),tmpobj.posY+(randY*32))==1);
	    	        		tmpobj.posX+=randX*32;
	    	        		tmpobj.posY+=randY*32;
	    	        	}
    	      };
    	      return tmpobj;
    }

}
