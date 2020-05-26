package iota_cafe3;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel
        implements Runnable {



    private Image essen,toilet,pay,love,good,notsogood,wannaleave,eating,istFrei,selection1,selection2,selection3,selection4,selection5,selection6,selection7,selection8,selection9,selection10,people_0N,people_1O,people_2S,people_3W,people2_0N,people2_1O,people2_2S,people2_3W,people3_0N,people3_1O,people3_2S,people3_3W,need,selection100,selection101,selection102,selection103,cooking,cursor,cursorGast;
    private ImageIcon b1,b2,b3,b4;
    private Thread animator;
    private JPanel menubarRight,menubarBottom;
    private JLabel scoreLabel,menubarBlock,guestBar,statusLabel,statusLabel2;
    private JButton kellnerEinstellen,goup,godown,goleft,goright,selectBlock,buildBlock,gastEinlassen,switchGast,switchKellner,kochEinstellen;
    private int schonBesetzt=0,schlange=0,tmpTimerPosX=0,tmpTimerPosY=0,tmpWert=0,offset=0,essenZubereitet=0,bestellungAbgegeben=0,x, y, ruf=50,tmpEinlass=8000, spawnX=7,spawnY=15,blockSelector=1,tmpSelector=0, minX=3,maxX=17, minY=3,maxY=15,tischzaehler=0,gastzaehler=0,kellnerzaehler=0,kochzaehler=0;
    private double time,tmpTimer=0,tmpTimerDauer=10,gehaltsListe=0,finanzen=0;
    public static int[][] blockGridStat = new int[21][19];
    public static int[][] blockGrid = new int[21][19];
    public static int[][] besetztGrid = new int[21][19];
    private String tmpstr;
    
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 600;
    private final int INITIAL_X = minX*32;
    private final int INITIAL_Y = minY*32;
    private final int DELAY = 125;

    //Stat-Klassen initialisieren
    public class tisch
    {
    		int posX;
    		int posY;
    		int nr;	
    };
    
	String[] _firstName =  new String[] { "Adam", "Alex", "Aaron", "Ben", "Carl", "Dan", "David", "Edward", "Fred", "Frank", "George", "Hal", "Hank", "Ike", "John", "Jack", "Joe", "Larry", "Monte", "Matthew", "Mark", "Nathan", "Otto", "Paul", "Peter", "Roger", "Roger", "Steve", "Thomas", "Tim", "Ty", "Victor", "Walter"};   	
	String[] _lastName = new String[] { "Anderson", "Ashwoon", "Aikin", "Bateman", "Bongard", "Bowers", "Boyd", "Cannon", "Cast", "Deitz", "Dewalt", "Ebner", "Frick", "Hancock", "Haworth", "Hesch", "Hoffman", "Kassing", "Knutson", "Lawless", "Lawicki", "Mccord", "McCormack", "Miller", "Myers", "Nugent", "Ortiz", "Orwig", "Ory", "Paiser", "Pak", "Pettigrew", "Quinn", "Quizoz", "Ramachandran", "Resnick", "Sagar", "Schickowski", "Schiebel", "Sellon", "Severson", "Shaffer", "Solberg", "Soloman", "Sonderling", "Soukup", "Soulis", "Stahl", "Sweeney", "Tandy", "Trebil", "Trusela", "Trussel", "Turco", "Uddin", "Uflan", "Ulrich", "Upson", "Vader", "Vail", "Valente", "Van Zandt", "Vanderpoel", "Ventotla", "Vogal", "Wagle", "Wagner", "Wakefield", "Weinstein", "Weiss", "Woo", "Yang", "Yates", "Yocum", "Zeaser", "Zeller", "Ziegler", "Bauer", "Baxster", "Casal", "Cataldi", "Caswell", "Celedon", "Chambers", "Chapman", "Christensen", "Darnell", "Davidson", "Davis", "DeLorenzo", "Dinkins", "Doran", "Dugelman", "Dugan", "Duffman", "Eastman", "Ferro", "Ferry", "Fletcher", "Fietzer", "Hylan", "Hydinger", "Illingsworth", "Ingram", "Irwin", "Jagtap", "Jenson", "Johnson", "Johnsen", "Jones", "Jurgenson", "Kalleg", "Kaskel", "Keller", "Leisinger", "LePage", "Lewis", "Linde", "Lulloff", "Maki", "Martin", "McGinnis", "Mills", "Moody", "Moore", "Napier", "Nelson", "Norquist", "Nuttle", "Olson", "Ostrander", "Reamer", "Reardon", "Reyes", "Rice", "Ripka", "Roberts", "Rogers", "Root", "Sandstrom", "Sawyer", "Schlicht", "Schmitt", "Schwager", "Schutz", "Schuster", "Tapia", "Thompson", "Tiernan", "Tisler" };

    
    public class personen
    {
    		String vorname;
    		String nachname;  
    		
    		public personen()
    		{
    		vorname=_firstName[(int) (Math.random() * _firstName.length)];
    		nachname=_lastName[(int) (Math.random() * _firstName.length)];;
    		}
    }
    
    public class personKlasse extends personen
    {
    		int status;
    		int typ;
    		int nr;
    		int posX;
    		int posY;
    		int zielX;
    		int zielY;
    		int homeX;
    		int homeY;
    		double gehalt;
    		int direction;
    		int Zubereitung; //für Koch
    		int Laune; //für Gäste
    		int Hunger;
    		float Harndrang;
    		int gerichtWert;
    		
    		public personKlasse()
    		{
    			gerichtWert=20;
    			gehalt=0;
    			typ=0;
    			status=0;
    			nr=0;
    			homeX=0;
    			homeY=0;
    			posX=spawnX*32;
    			posY=spawnY*32;
    			zielX=0;
    			zielY=0;
    			direction=0; //0 = N; 1=O; 2=S;3=W
    			Zubereitung=0;
    			Laune=500;
    			Harndrang=(int) (Math.random() * 350);
    			Hunger=(int) (Math.random() * 50) +50;
    		}
    };
    
    
    public static List<tisch> lTisch = new ArrayList<>(5);
    public static List<personKlasse> lPerson = new ArrayList<>(5);

    
    public void tischHinzufuegen(int tmpX, int tmpY, int Nr)
    {
    	tisch tmpTisch = new tisch();
    	blockGrid[tmpX][tmpY]=3;
    	tmpTisch.posX=tmpX;
    	tmpTisch.posY=tmpY;
    	tmpTisch.nr=Nr;
    	tischzaehler++;
    	lTisch.add(tmpTisch);	
    }
    
    public int kollisionsAbfrage(int tmpX, int tmpY)
    {
		if(
				blockGrid[tmpX/32][tmpY/32]!=100 &&
				blockGrid[tmpX/32][tmpY/32]!=101 &&
				blockGrid[tmpX/32][tmpY/32]!=2 &&
				blockGrid[tmpX/32][tmpY/32]!=3 &&
				blockGrid[tmpX/32][tmpY/32]!=4 &&
				blockGrid[tmpX/32][tmpY/32]!=5 &&
				besetztGrid[tmpX/32][tmpY/32]!=1
				) return 0; else return 1;
    }
    
    public int[] findeFreieStelle(int tmpX,int tmpY)
    {
    	int[] Array = new int[2];
    	
		for(int ix=-32;ix<=32;ix+=32)
		{
			for(int iy=-32;iy<=32;iy+=32)
			{
				if(kollisionsAbfrage(tmpX+ix,tmpY+iy)==0)
				{
					Array[0]=tmpX+ix;
					Array[1]=tmpY+iy;
				}	
			}
		};
	return Array;
    }
    
    
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
	    	        			diffX = tmpobj.posX-tmpobj.zielX;
	    	        			diffY = tmpobj.posY-tmpobj.zielY;
    	        	        	
	    	        			if(ix>=minX*32 && ix<=maxX*32 && iy>=minY*32 && iy<=maxY*32)
	    	        			{

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
		    	        	        		
		    	        	        //TOILETTE ANGEKOMMEN
		    	        	        		if(tmpobj.Harndrang>450)
		    	        	        		{
		    	        	        			besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=1;
		    	        	        			tmpobj.direction=2;
		    	        	        			tmpobj.Harndrang=-20;
		    	        	        			tmpobj.Laune+=100;
		    	        	        		}
		    	        	        //WENN GAST ANGEKOMMEN
		    	        	        			if(tmpobj.typ==0) //Gast
		    	        	        			{
			    	        	        			if(tmpobj.status==0)
			    	        	        			{
		    	        	        				besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=1;
			    	        	        			tmpobj.status++;
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
		    	        	        					treffer=0;
		    	        	        					for (int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
			    	        	            	        {
			    	        	            	        	personKlasse tmpobj4 = lPerson.get(iPerson2);
			    	        	            	            if(tmpobj4.status==5)
			    	        	            	            {
			    	        	            	            	besetztGrid[tmpobj4.posX/32][tmpobj4.posY/32]=0;
			    	        	            	            	tmpobj4.status=6;
			    	        	            	            	tmpobj4.zielX=224;
			    	        	            	            	tmpobj4.zielY=448;
			    	        	            	            	finanzen+=tmpobj4.gerichtWert;
			    	        	            	            	tmpTimer=time+tmpTimerDauer;
			    	        	            	            	tmpTimerPosX=tmpobj.posX;
			    	        	            	            	tmpTimerPosY=tmpobj.posY;
			    	        	            	            	tmpWert=tmpobj4.gerichtWert;
			    	        	            	            	treffer=1;
			    	        	            	            	break;
			    	        	            	            }
			    	        	            	        }
		    	        	        					if(treffer==0) tmpobj.status=100;
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
		    	        	        				if(tmpobj.status==104) //Hat essen vom Koch geholt
		    	        	        				{
		    	        	        					essenZubereitet--;
		    	        	        					
		    	        	        					treffer=0;
		    	        	        					//Suche Person die Essen erwartet
		    	        	        					for (int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
			    	        	            	        {
			    	        	            	        	personKlasse tmpobj4 = lPerson.get(iPerson2);
			    	        	            	            if(tmpobj4.status==3)
			    	        	            	            {
			    	        	            	            	int[] tmpArr = findeFreieStelle(tmpobj4.homeX,tmpobj4.homeY);
			    	        	            	            	tmpobj.zielX= tmpArr[0];
			    	        	            	            	tmpobj.zielY= tmpArr[1];
			    	        	            	            	tmpobj.status=105;
			    	        	            	            	treffer=1;
			    	        	            	            	break;
			    	        	            	            }
			    	        	            	        }
		    	        	        					if(treffer==0) tmpobj.status=100;
		    	        	        				}

		    	        	        				if(tmpobj.status==100 && tmpobj.posX==tmpobj.zielX && tmpobj.posY==tmpobj.zielY)
		    	        	        				{
				    	        	        			besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=1;
				    	        	        			tmpobj.status=101;
		    	        	        				}
			    	        	        			if(tmpobj.status==103) //Hat Bestellung ausgeliefert, Suche Koch und fange an mit Zubereitung
			    	        	        			{
			    	        	        				bestellungAbgegeben++;
			    	        	        				finanzen-=tmpobj.gerichtWert/2;
			    	        	        				tmpTimer=time+tmpTimerDauer;
			    	        	        				tmpTimerPosX=tmpobj.posX;
	    	        	            	            	tmpTimerPosY=tmpobj.posY;
	    	        	            	            	tmpWert=-(tmpobj.gerichtWert/2);
				    	        	        				for (int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
				    	        	            	        {
				    	        	            	        	personKlasse tmpobj4 = lPerson.get(iPerson2);
				    	        	            	            if(tmpobj4.status==201)
				    	        	            	            {
				    	        	            	            	tmpobj4.status=202;
				    	        	            	            	bestellungAbgegeben--;
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
    
    //Finde Toilette
    public int[] findeToilette(int tmpX,int tmpY)
    {
    	int faktor = 1, treffer =0 ;
    	int[] tmpArr = new int[2];
    	while(faktor<maxX && treffer==0)
		{
			if(treffer==1) break;
        	for(int ix=tmpX-32*faktor;ix<(tmpX+32*faktor);ix+=32)
        	{
        		if(treffer==1) break;
        		for(int iy=tmpY-32*faktor;iy<(tmpY+32*faktor);iy+=32)
        		{
        			System.out.println("[Suche mit Faktor " +faktor+ " an Stelle " +ix+ "," +iy+ "]");
        			if(treffer==1) break;
        			if(ix>=minX*32 && ix<=maxX*32 && iy>=minY*32 && iy<=maxY*32)
        			{
        			if(
        					blockGrid[ix/32][iy/32]==6
        					&& besetztGrid[ix/32][iy/32]!=1
        				) 
        				{
        					System.out.println("[Freie Toilette gefunden bei" +ix+ "," +iy+ "]");
        					treffer=1;
        					tmpArr[0]=ix;
        					tmpArr[1]=iy;
        					break;
        				}
        			}
        		}
        	}; faktor++;
    	};
    	return tmpArr;
    }
    
    //Finde Tisch
    public int[] findeTisch(int tmpX,int tmpY)
    {
    	int faktor = 1, treffer =0 ;
    	int[] tmpArr = new int[2];
    	while(faktor<maxX && treffer==0)
		{
			if(treffer==1) break;
        	for(int ix=tmpX-32*faktor;ix<(tmpX+32*faktor);ix+=32)
        	{
        		if(treffer==1) break;
        		for(int iy=tmpY-32*faktor;iy<(tmpY+32*faktor);iy+=32)
        		{
        			System.out.println("[Suche mit Faktor " +faktor+ " an Stelle " +ix+ "," +iy+ "]");
        			if(treffer==1) break;
        			if(ix>=minX*32 && ix<=maxX*32 && iy>=minY*32 && iy<=maxY*32)
        			{
        			if(
        					blockGrid[ix/32][iy/32]==3
        					&& besetztGrid[ix/32][iy/32]!=1
        				) 
        				{
        					System.out.println("[Freien Tisch gefunden bei" +ix+ "," +iy+ "]");
        					treffer=1;
        					tmpArr[0]=ix;
        					tmpArr[1]=iy;
        					break;
        				}
        			}
        		}
        	}; faktor++;
    	};
    	return tmpArr;
    }
    

    

    
    
    public static String showStats(personKlasse tmpobj)
    {
    	String tmpstr="";
    	
    	switch(tmpobj.status)
    	{
    		//AKTIVE GÄSTE
	    	case 0: tmpstr="<br>Sucht Platz"; break;
	    	case 1: tmpstr="<br>Ruft Kellner"; break;
	    	case 2: tmpstr="<br>Gibt Bestellung auf"; break;
	    	case 3: tmpstr="<br>Wartet auf Essen"; break;
	    	case 4: tmpstr="<br>Genießt das Essen"; break;
	    	case 5: tmpstr="<br>Möchte Zahlen"; break;
	    	case 6: tmpstr="<br>Verlässt Restaurant"; break;
	    	case 99: tmpstr="<br>Verlässt empört das Restaurant";break;
	    	//KELLNER
	    	case 100: tmpstr="<br>Sucht Kasse"; break;
	    	case 101: tmpstr="<br>Wartet auf Order"; break;
	    	case 102: tmpstr="<br>Nimmt Bestellung auf"; break;
	    	case 103: tmpstr="<br>Bringt Bestellung zu Koch"; break;
	    	case 104: tmpstr="<br>Holt Essen"; break;
	    	case 105: tmpstr="<br>Liefert Essen"; break;
	    	case 106: tmpstr="<br>Kassiert Gäste"; break;
	    	//KOCH
	    	case 200: tmpstr="<br>Sucht Herd"; break;
	    	case 201: tmpstr="<br>Wartet auf Bestellung"; break;
	    	case 202: tmpstr="<br>Bereitet Essen zu"; break;
	    	//EHEM.GÄSTE
	    	case 1000: tmpstr="<br>Hat das Restaurant zufrieden verlassen"; break;
	    	case 1001: tmpstr="<br>Hat das Restaurant empört verlassen"; break;
    	}
    	return tmpstr;
    }
    
    public static String showType(personKlasse tmpobj)
    {
    	String tmpstr="";
    	
    	switch(tmpobj.typ)
    	{
	    	case 0: tmpstr="Gast"; break;
	    	case 1: tmpstr="Kellner"; break;
	    	case 2: tmpstr="Koch"; break;
    	}
    	return tmpstr;
    }

    public void menueZeichnen()
    {
    	
        setLayout(null);
        //setOpaque(false);
        Color Maincolor = new Color(119, 169, 185);
  
    menubarRight = new JPanel();
    menubarRight.setSize(160,600);
    menubarRight.setLocation(640,0);
    menubarRight.setBackground(new Color (119, 189, 185));
    menubarRight.setLayout(null);
    add(menubarRight); 
    
    menubarBottom = new JPanel();
    menubarBottom.setSize(B_WIDTH-160,50);
    menubarBottom.setLocation(0,B_HEIGHT-50);
    menubarBottom.setBackground(new Color (119, 189, 185));
    menubarBottom.setLayout(null);
    add(menubarBottom); 
    
    scoreLabel = new JLabel();
    scoreLabel.setSize(560,50);
    scoreLabel.setLocation(15,15);
    scoreLabel.setFont(new Font("Source Sans Pro", Font.BOLD, 14));
    scoreLabel.setLayout(null);
    scoreLabel.setForeground(Color.WHITE);
    add(scoreLabel); 
    
    statusLabel = new JLabel();
    statusLabel.setSize(560,50);
    statusLabel.setLocation(0,0);
    statusLabel.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    statusLabel.setLayout(null);
    statusLabel.setForeground(Color.WHITE);
    menubarBottom.add(statusLabel); 
    
    statusLabel2 = new JLabel();
    statusLabel2.setSize(200,50);
    statusLabel2.setLocation(560,0);
    statusLabel2.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    statusLabel2.setLayout(null);
    statusLabel2.setForeground(Color.WHITE);
    statusLabel2.setText("-");
    menubarBottom.add(statusLabel2); 
    
    menubarBlock = new JLabel();
    menubarBlock.setSize(160,64);
    menubarBlock.setLocation(15,0);
    menubarBlock.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    menubarBlock.setBackground(new Color (136, 158, 157));
    menubarBlock.setLayout(null);
    menubarBlock.setIconTextGap(-128);
    menubarBlock.setText("Auswahl:");
    menubarRight.add(menubarBlock); 
    
    guestBar = new JLabel();
    guestBar.setSize(200,104);
    guestBar.setLocation(15,48);
    guestBar.setFont(new Font("Source Sans Pro", Font.BOLD, 10));
    guestBar.setBackground(new Color (136, 158, 157));
    guestBar.setLayout(null);
    guestBar.setIconTextGap(-128);
    guestBar.setText("Gast:");
    menubarRight.add(guestBar); 
    
    goup = new JButton("+");
    goup.setSize(50,25);
    goup.setBackground(Maincolor);
    goup.setForeground(Color.WHITE);
    goup.setFocusPainted(false);
    goup.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    goup.setLocation(55,345-150);
    menubarRight.add(goup); 
    
    godown = new JButton("-");
    godown.setSize(50,25);
    godown.setBackground(Maincolor);
    godown.setForeground(Color.WHITE);
    godown.setFocusPainted(false);
    godown.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    godown.setLocation(55,395-150);
    menubarRight.add(godown);
    
    goleft = new JButton("<");
    goleft.setSize(50,25);
    goleft.setBackground(Maincolor);
    goleft.setForeground(Color.WHITE);
    goleft.setFocusPainted(false);
    goleft.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    goleft.setLocation(5,395-150);
    menubarRight.add(goleft);
    
    goright = new JButton(">");
    goright.setSize(50,25);
    goright.setBackground(Maincolor);
    goright.setForeground(Color.WHITE);
    goright.setFocusPainted(false);
    goright.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    goright.setLocation(105,395-150);       
    menubarRight.add(goright);
    
    selectBlock = new JButton("Einrichtung auswaehlen (Q)");
    selectBlock.setSize(150,25);
    selectBlock.setBackground(Maincolor);
    selectBlock.setForeground(Color.WHITE);
    selectBlock.setFocusPainted(false);
    selectBlock.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    selectBlock.setLocation(5,495);       
    menubarRight.add(selectBlock);
    
    buildBlock = new JButton("Einrichtung kaufen (E)");
    buildBlock.setSize(150,25);
    buildBlock.setBackground(Maincolor);
    buildBlock.setForeground(Color.WHITE);
    buildBlock.setFocusPainted(false);
    buildBlock.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    buildBlock.setLocation(5,520);       
    menubarRight.add(buildBlock);
    
    gastEinlassen = new JButton("Gast einlassen (G)");
    gastEinlassen.setSize(150,25);
    gastEinlassen.setBackground(Maincolor);
    gastEinlassen.setForeground(Color.WHITE);
    gastEinlassen.setFocusPainted(false);
    gastEinlassen.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    gastEinlassen.setLocation(5,545);       
    menubarRight.add(gastEinlassen);
    
    switchGast = new JButton("Person ansehen (N)");
    switchGast.setSize(150,25);
    switchGast.setBackground(Maincolor);
    switchGast.setForeground(Color.WHITE);
    switchGast.setFocusPainted(false);
    switchGast.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    switchGast.setLocation(5,470);       
    menubarRight.add(switchGast);
        
    kellnerEinstellen = new JButton("Kellner einstellen (L)");
    kellnerEinstellen.setSize(150,25);
    kellnerEinstellen.setBackground(Maincolor);
    kellnerEinstellen.setForeground(Color.WHITE);
    kellnerEinstellen.setFocusPainted(false);
    kellnerEinstellen.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    kellnerEinstellen.setLocation(5,420);    
    menubarRight.add(kellnerEinstellen);
    
    kochEinstellen = new JButton("Koch einstellen (T)");
    kochEinstellen.setSize(150,25);
    kochEinstellen.setBackground(Maincolor);
    kochEinstellen.setForeground(Color.WHITE);
    kochEinstellen.setFocusPainted(false);
    kochEinstellen.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
    kochEinstellen.setLocation(5,395);       
    menubarRight.add(kochEinstellen);
    
    };
    
    
    
    
    //PROGRAMMSTART -----------------------------------------
    public Board() {

        initBoard();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("res/selection3.png");
        cursor = ii.getImage();
    }

    private void initBoard() {

        setBackground(new Color(158, 168, 168));
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        loadImage();

        x = INITIAL_X;
        y = INITIAL_Y;
        
        
    	//statische Bl�cke zeichnen
        //Grundriss festlegen        

    	for(int ix=minX;ix<=maxX;ix++)
    	{
    		for(int iy=minY;iy<=maxY;iy++)
    		{
    			besetztGrid[ix][iy]=0;
    			blockGridStat[ix][iy]=102;
    			blockGridStat[ix][minY]=101;
    			blockGridStat[minX][iy]=101;
    			blockGridStat[ix][maxY]=101;
    			blockGridStat[maxX][iy]=101;
    			blockGridStat[maxX-6][iy]=101;
    			blockGridStat[maxX-1][maxY-5]=101;
    			blockGridStat[maxX-2][maxY-5]=101;
    			blockGridStat[maxX-3][maxY-5]=101;
    			blockGridStat[maxX-4][maxY-5]=101;
    			blockGridStat[maxX-5][maxY-5]=101;
    			
    			blockGrid[ix][iy]=102;
    			blockGrid[ix][minY]=101;
    			blockGrid[minX][iy]=101;
    			blockGrid[ix][maxY]=101;
    			blockGrid[maxX][iy]=101;
    			blockGrid[maxX-6][iy]=101;
    			blockGrid[maxX-1][maxY-5]=101;
    			blockGrid[maxX-2][maxY-5]=101;
    			blockGrid[maxX-3][maxY-5]=101;
    			blockGrid[maxX-4][maxY-5]=101;
    			blockGrid[maxX-5][maxY-5]=101;
    		}
    	}
    	//Eingang festlegen
    	blockGridStat[spawnX][spawnY]=100;
    	blockGrid[spawnX][spawnY]=100;
    	
    	//T�ren festlegen
    	blockGridStat[maxX-6][maxY-3]=103;
    	blockGridStat[maxX-6][maxY-10]=103;
    	blockGridStat[maxX-6][maxY-7]=103;
    	blockGridStat[maxX-3][maxY-5]=103;
    	blockGrid[maxX-6][maxY-3]=103;
    	blockGrid[maxX-6][maxY-10]=103;
    	blockGrid[maxX-6][maxY-7]=103;
    	blockGrid[maxX-3][maxY-5]=103;
    	
    	//Toilette zeichnen
    	blockGrid[maxX-2][maxY-4]=6;
    	blockGrid[maxX-4][maxY-4]=4;
    	
    	//K�che zeichnen
    	blockGrid[maxX-2][minY+1]=4;
    	blockGrid[maxX-3][minY+1]=2;
    	blockGrid[maxX-2][minY+4]=3;
    	blockGrid[maxX-3][minY+4]=3;
    	
    	//Kasse zeichnen
    	blockGrid[minX+6][maxY-1]=3;
    	blockGrid[minX+6][maxY-2]=5;
    	
    	//Einrichtung zeichnen
	    	//Tische
	        tischHinzufuegen(minX+7, maxY-5, tischzaehler);
	        //tischHinzufuegen(minX+7, maxY-8, tischzaehler);
	        tischHinzufuegen(5, 13, tischzaehler);
	        tischHinzufuegen(7, 10, tischzaehler);
	        //St�hle
	    	//blockGrid[minX+7][maxY-9]=7;
	    	blockGrid[minX+7][maxY-6]=7;
	    	blockGrid[6][10]=10;

    	blockGrid[5][14]=9;
    	
        //UI Elemente
        menueZeichnen();

        
        
        goup.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
            	 char c = e.getKeyChar();
                 if (c == 'W' || c=='w') goup.doClick();
                 if (c == 'A' || c=='a') goleft.doClick();
                 if (c == 'S' || c=='s') godown.doClick();
                 if (c == 'D' || c=='d') goright.doClick();
                 if (c == 'Q' || c=='q') selectBlock.doClick();
                 if (c == 'E' || c=='e') buildBlock.doClick();
                 if (c == 'G' || c=='g') gastEinlassen.doClick();
                 if (c == 'N' || c=='n') switchGast.doClick();
                 if (c == 'T' || c=='t') kochEinstellen.doClick();
                 if (c == 'L' || c=='l') kellnerEinstellen.doClick();
            }
          });
        
        goup.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(y>((minY)*32)) y-=32;
    		}
        });
        
        godown.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(y<(32*maxY))y+=32;
    		}
        });
        
        goleft.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(x>(minX*32)) x-=32;
    		}
        });
        
        goright.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if(x<(maxX*32)) x+=32;
    		}
        });
              
        gastEinlassen.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    	    	personKlasse tmpGast = new personKlasse();
    	    	tmpGast.nr=lPerson.size();
    	    	lPerson.add(tmpGast);
    	    	
    	        //N�hesten Platz ermitteln
    	        for (int iPerson=0;iPerson<lPerson.size();iPerson++)
    	        {
    	        	int treffer=0;
    	        	schonBesetzt=0;
    	        	
    	        	if(treffer==1) break;
    	            personKlasse tmpobj = lPerson.get(iPerson);
    	            if(tmpobj.status==0)
    	            {
    	            		int faktor=1;
    	            		while(faktor<maxX)
    	            		{
    	            			if(treffer==1) break;
    		    	        	for(int ix=tmpobj.posX-32*faktor;ix<(tmpobj.posX+32*faktor);ix+=32)
    		    	        	{
    		    	        		if(treffer==1) break;
    		    	        		for(int iy=tmpobj.posY-32*faktor;iy<(tmpobj.posY+32*faktor);iy+=32)
    		    	        		{
    		    	        			if(treffer==1) break;
    		    	        			if(ix>=minX*32 && ix<=maxX*32 && iy>=minY*32 && iy<=maxY*32)
    		    	        			{
    		    	        			
    		    	        			if(
    		    	        					(
    		    	        					blockGrid[ix/32][iy/32]==7 ||
    		    	        					blockGrid[ix/32][iy/32]==8 ||
    		    	        					blockGrid[ix/32][iy/32]==9 ||
    		    	        					blockGrid[ix/32][iy/32]==10
    		    	        					) &&
    		    	        					besetztGrid[ix/32][iy/32]!=1
    		    	        				) 
    		    	        				{
    		    	        					for(int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
    		    	        					{
    		    	        						personKlasse tmpobj2 = lPerson.get(iPerson2);
    		    	        						if(tmpobj2.homeX==ix && tmpobj2.homeY==iy)
    		    	        							schonBesetzt=1;
    		    	        					};
    		    	        	    	        if(schonBesetzt==0)
    		    	        	    	        {
    		    	        					treffer=1;
    		    	        					tmpobj.zielX=ix;
    		    	        					tmpobj.zielY=iy;
    		    	        					tmpobj.homeX=ix;
    		    	        					tmpobj.homeY=iy;
    		    	        					lPerson.set(iPerson, tmpobj);
    		    	        					break;
    		    	        	    	        }
    		    	        				}
    		    	        			}
    		    	        		}
    		    	        	}; faktor++;
    	    	        	};
    	    	        	if(treffer==0) 
    	    	        		{
    	    	        			lPerson.remove(iPerson);
    	    	        			if(schlange<15) schlange++;
    	    	        			 System.out.println("[Schlange erhoeht  auf" +schlange+ " ]");
    	    	        		}
    	    	        	else
    	    	        	 if(schlange>0) schlange--;
    	    	 	}
    	        };
    		}
        });
        
        kellnerEinstellen.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    	    	personKlasse tmpKellner = new personKlasse();
    	    	tmpKellner.nr=lPerson.size();
    	    	tmpKellner.typ=1;
    	    	tmpKellner.status=100;
    	    	tmpKellner.gehalt=0.0010;
    	    	gehaltsListe+=tmpKellner.gehalt;
    	    	lPerson.add(tmpKellner);
    	    	
    	    	
    	        //Freien Platz an der Kasse ermitteln
    	        for (int iPerson=0;iPerson<lPerson.size();iPerson++)
    	        {
    	        	int treffer=0;
    	        	
    	        	if(treffer==1) break;
    	        	personKlasse tmpobj = lPerson.get(iPerson);
    	            //Suche Kasse
    	            if(tmpobj.status==100)
    	            {
    	            	if(tmpobj.posX==tmpobj.zielX && tmpobj.posY==tmpobj.zielY) tmpobj.status++;

    	            	System.out.println("[Kassensuche begonnen]");	
    	            		int faktor=1;
    	            		while(faktor<maxX)
    	            		{
    	            			if(treffer==1) break;
    		    	        	for(int ix=tmpobj.posX-32*faktor;ix<(tmpobj.posX+32*faktor);ix+=32)
    		    	        	{
    		    	        		if(treffer==1) break;
    		    	        		for(int iy=tmpobj.posY-32*faktor;iy<(tmpobj.posY+32*faktor);iy+=32)
    		    	        		{
    		    	        			System.out.println("[Suche mit Faktor " +faktor+ " an Stelle " +ix+ "," +iy+ "]");
    		    	        			if(treffer==1) break;
    		    	        			if(ix>=minX*32 && ix<=maxX*32 && iy>=minY*32 && iy<=maxY*32)
    		    	        			{
    		    	        			if(
    		    	        					
    		    	        					blockGrid[ix/32][iy/32]==102 &&
    		    	        					besetztGrid[ix/32][iy/32]!=1 &&
    		    	        					
    		    	        					( //Kasse in der Nähe
    		    	        					blockGrid[(ix+32)/32][iy/32]==5 ||
    		    	        					blockGrid[(ix-32)/32][iy/32]==5 ||
    		    	        					blockGrid[(ix+32)/32][(iy+32)/32]==5 ||
    	    		    	        			blockGrid[(ix-32)/32][(iy-32)/32]==5 ||
    	    		    	        			blockGrid[(ix-32)/32][(iy+32)/32]==5 ||
    	    	    		    	        	blockGrid[(ix-32)/32][(iy+32)/32]==5 ||
    	    		    	        			blockGrid[(ix+32)/32][(iy-32)/32]==5 ||
    	    	    		    	        	blockGrid[(ix+32)/32][(iy-32)/32]==5 ||
    	    		    	        			blockGrid[(ix)/32][(iy+32)/32]==5 ||
    	    	    		    	        	blockGrid[(ix)/32][(iy-32)/32]==5 
    		    	        					)
    		    	        				) 
    		    	        				{
    		    	        					System.out.println("[Freie Stelle neben Kasse gefunden bei " +ix+ "," +iy+ "]");
    		    	        					treffer=1;
    		    	        					tmpobj.zielX=ix;
    		    	        					tmpobj.zielY=iy;
    		    	        					tmpobj.homeX=ix;
    		    	        					tmpobj.homeY=iy;
    		    	        					lPerson.set(iPerson, tmpobj);
    		    	        					break;
    		    	        				}
    		    	        			}
    		    	        		}
    		    	        	}; faktor++;
    	    	        	};
    	    	 	};
    	        };
    		}
        });
        
        kochEinstellen.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    	    	personKlasse tmpKoch = new personKlasse();
    	    	tmpKoch.nr=lPerson.size();
    	    	tmpKoch.typ=2;
    	    	tmpKoch.status=200;
    	    	tmpKoch.gehalt=0.030;
    	    	gehaltsListe+=tmpKoch.gehalt;
    	    	lPerson.add(tmpKoch);
    	    	
    	    	
    	        //Freien Platz am Herd ermitteln
    	        for (int iPerson=0;iPerson<lPerson.size();iPerson++)
    	        {
    	        	int treffer=0;
    	        	
    	        	if(treffer==1) break;
    	        	personKlasse tmpobj = lPerson.get(iPerson);
    	            if(tmpobj.status==200)
    	            {
    	            	System.out.println("[Herdsuche begonnen]");
    	        		
    	            		int faktor=1;
    	            		while(faktor<maxX)
    	            		{
    	            			if(treffer==1) break;
    		    	        	for(int ix=tmpobj.posX-32*faktor;ix<(tmpobj.posX+32*faktor);ix+=32)
    		    	        	{
    		    	        		if(treffer==1) break;
    		    	        		for(int iy=tmpobj.posY-32*faktor;iy<(tmpobj.posY+32*faktor);iy+=32)
    		    	        		{
    		    	        			System.out.println("[Suche mit Faktor " +faktor+ " an Stelle " +ix+ "," +iy+ "]");
    		    	        			if(treffer==1) break;
    		    	        			if(ix>=minX*32 && ix<=maxX*32 && iy>=minY*32 && iy<=maxY*32)
    		    	        			{
    		    	        			if(
    		    	        					
    		    	        					blockGrid[ix/32][iy/32]==102 &&
    		    	        					besetztGrid[ix/32][iy/32]!=1 &&
    		    	        					
    		    	        					( //Herd in der Nähe
    		    	        					blockGrid[(ix+32)/32][iy/32]==2 ||
    		    	        					blockGrid[(ix-32)/32][iy/32]==2 ||
    		    	        					blockGrid[(ix+32)/32][(iy+32)/32]==2 ||
    	    		    	        			blockGrid[(ix-32)/32][(iy-32)/32]==2 ||
    	    		    	        			blockGrid[(ix-32)/32][(iy+32)/32]==2 ||
    	    	    		    	        	blockGrid[(ix-32)/32][(iy+32)/32]==2 ||
    	    		    	        			blockGrid[(ix+32)/32][(iy-32)/32]==2 ||
    	    	    		    	        	blockGrid[(ix+32)/32][(iy-32)/32]==2 ||
    	    		    	        			blockGrid[(ix)/32][(iy+32)/32]==2 ||
    	    	    		    	        	blockGrid[(ix)/32][(iy-32)/32]==2 
    		    	        					)
    		    	        				) 
    		    	        				{
    		    	        					System.out.println("[Freie Stelle neben Herd gefunden bei " +ix+ "," +iy+ "]");
    		    	        					treffer=1;
    		    	        					tmpobj.zielX=ix;
    		    	        					tmpobj.zielY=iy;
    		    	        					tmpobj.homeX=ix;
    		    	        					tmpobj.homeY=iy;
    		    	        					lPerson.set(iPerson, tmpobj);
    		    	        					break;
    		    	        				}
    		    	        			}
    		    	        		}
    		    	        	}; faktor++;
    	    	        	};
    	    	        	if(treffer==0) lPerson.remove(iPerson);
    	    	 	}
    	        };
    		}
        });
        
        switchGast.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			if((gastzaehler+1)<lPerson.size()) gastzaehler++; else gastzaehler=0;
    			personKlasse tmpobj = lPerson.get(gastzaehler);
    			while(tmpobj.status==1000 || tmpobj.status==1001)
				{
    				System.out.println("[Gast switcher sucht Person ohne Status 1000 und 1001]");
    				if((gastzaehler+1)<lPerson.size()) gastzaehler++; else gastzaehler=0;
    				tmpobj= lPerson.get(gastzaehler);
				};
    						guestBar.setText("<html> #" +(tmpobj.nr+1)+ " (" +showType(tmpobj)+ ") " +tmpobj.vorname+ " " +tmpobj.nachname+ "<br>Status : (" +tmpobj.status+ ") " +showStats(tmpobj)+ "<br>Laune:" +tmpobj.Laune+ "<br> Ziel:" +tmpobj.zielX+ "," +tmpobj.zielY+ "<br>Pos:" +tmpobj.posX+ "," +tmpobj.posY+ "<br>Home:" +tmpobj.homeX+ "," +tmpobj.homeY+ "<br>Harndrang: " +tmpobj.Harndrang+ "</html>" );
    			

    		}
        });
       
        
        selectBlock.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			if(blockSelector<10)blockSelector++; else blockSelector=1;
    			
    			if(blockSelector==1)
    			{
    		        ImageIcon b1 = new ImageIcon("res/woodfloor4.png");
    		        selection1 = b1.getImage();
        	       menubarBlock.setIcon(b1);
        	       menubarBlock.setText("<html>Löschen</html>");
    			}
    			if(blockSelector==2)
    			{
        	        ImageIcon b2 = new ImageIcon("res/herd.png");
        	        selection2 = b2.getImage();
        	        menubarBlock.setText("<html>Herd <br> 120 €</html>");
	    	        menubarBlock.setIcon(b2);
    			}
    			if(blockSelector==3)
    			{
        	        ImageIcon b3 = new ImageIcon("res/zenithal.png");
        	        selection3 = b3.getImage();
        	        menubarBlock.setText("<html>Tisch<br> 50 €</html>");
	    	        menubarBlock.setIcon(b3);
    			}
    			if(blockSelector==4)
    			{
        	        ImageIcon b4 = new ImageIcon("res/waschbecken.png");
        	        selection4 = b4.getImage();
        	        menubarBlock.setText("<html>Waschbecken<br> 70 €</html>");
	          		menubarBlock.setIcon(b4);
    			}
    			if(blockSelector==5)
    			{
        	        ImageIcon b5 = new ImageIcon("res/kasse1.png");
        	        selection5 = b5.getImage();
        	        menubarBlock.setText("<html>Kasse<br> 25 €</html>");
        	        menubarBlock.setIcon(b5);
    			}
    			if(blockSelector==6)
    			{
        	        ImageIcon b6 = new ImageIcon("res/toilette.png");
        	        selection6 = b6.getImage();
        	        menubarBlock.setText("<html>Toilette<br> 75 €</html>");
        	        menubarBlock.setIcon(b6);
    			}
    			if(blockSelector==7)
    			{
        	        ImageIcon b7 = new ImageIcon("res/sthul.png");
        	        menubarBlock.setText("<html>Stuhl 1<br> 20 €</html>");
        	        selection7 = b7.getImage();
        	        menubarBlock.setIcon(b7);
    			}
    			if(blockSelector==8)
    			{
        	        ImageIcon b8 = new ImageIcon("res/sthul2.png");
        	        selection8 = b8.getImage();
        	        menubarBlock.setText("<html>Stuhl 2<br> 20 €</html>");
        	        menubarBlock.setIcon(b8);
    			}
    			if(blockSelector==9)
    			{
    		        ImageIcon b9 = new ImageIcon("res/sthul3.png");
    		        selection9 = b9.getImage();
        	       menubarBlock.setIcon(b9);
        	       menubarBlock.setText("<html>Stuhl 3<br> 20 €</html>");
    			}
    			if(blockSelector==10)
    			{
    		        ImageIcon b10 = new ImageIcon("res/sthul4.png");
    		        selection10 = b10.getImage();
        	       menubarBlock.setIcon(b10);
        	       menubarBlock.setText("<html>Stuhl 4<br> 20 €</html>");

    			}

    		}
        });
        
        buildBlock.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    				if(
    						blockGrid[(x/32)][(y/32)]!=100 && 
    						blockGrid[(x/32)][(y/32)]!=103 && 
    						blockGrid[(x/32)][(y/32)]!=101 && 
    						(x/32)>minX && 
    						(x/32)<maxX && 
    						(y/32)>minY && 
    						(y/32)<maxY
    					)
    				{
        		        if(blockSelector==1 && blockGrid[(x/32)][(y/32)]==3)
        		        {
        		        lTisch.remove(tischzaehler-1);
        		        tischzaehler--;
        		        }
    				
    				switch(blockSelector)
    				{
    				case 1: break;//Löschen
    				case 2: if(finanzen>120) {blockGrid[(x/32)][(y/32)]=blockSelector;finanzen-=120;tmpWert=-120;tmpTimer=time+tmpTimerDauer;tmpTimerPosX=x;tmpTimerPosY=y;}break;//Herd
    				case 3:	if(finanzen>50) {tischHinzufuegen(x/32,y/32,tischzaehler);blockGrid[(x/32)][(y/32)]=blockSelector;finanzen-=50;tmpWert=-50;tmpTimer=time+tmpTimerDauer;tmpTimerPosX=x;tmpTimerPosY=y;}break;//Tisch
    				case 4:	if(finanzen>70) {blockGrid[(x/32)][(y/32)]=blockSelector;finanzen-=70;tmpWert=-70;tmpTimer=time+tmpTimerDauer;tmpTimerPosX=x;tmpTimerPosY=y*32;}break;//Waschbecken
    				case 5:	if(finanzen>25) {blockGrid[(x/32)][(y/32)]=blockSelector;finanzen-=25;tmpWert=-25;tmpTimer=time+tmpTimerDauer;tmpTimerPosX=x;tmpTimerPosY=y*32;}break;//Kasse
    				case 6:	if(finanzen>75) {blockGrid[(x/32)][(y/32)]=blockSelector;finanzen-=75;tmpWert=-75;tmpTimer=time+tmpTimerDauer;tmpTimerPosX=x;tmpTimerPosY=y*32;}break;//Toilette
    				case 7:	if(finanzen>20) {blockGrid[(x/32)][(y/32)]=blockSelector;finanzen-=20;tmpWert=-20;tmpTimer=time+tmpTimerDauer;tmpTimerPosX=x;tmpTimerPosY=y*32;}break;//Stuhl
    				case 8:	if(finanzen>20) {blockGrid[(x/32)][(y/32)]=blockSelector;finanzen-=20;tmpWert=-20;tmpTimer=time+tmpTimerDauer;tmpTimerPosX=x;tmpTimerPosY=y*32;}break;//Stuhl
    				case 9:	if(finanzen>20) {blockGrid[(x/32)][(y/32)]=blockSelector;finanzen-=20;tmpWert=-20;tmpTimer=time+tmpTimerDauer;tmpTimerPosX=x;tmpTimerPosY=y*32;}break;//Stuhl
    				case 10:	if(finanzen>20) {blockGrid[(x/32)][(y/32)]=blockSelector;finanzen-=20;tmpWert=-20;tmpTimer=time+tmpTimerDauer;tmpTimerPosX=x;tmpTimerPosY=y*32;}break;//Stuhl
	            	
    				}
    				}			
    				
  
      		}
        });
        

    }

	@Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawThings(g);
    }
    

    private void drawThings(Graphics g) {
    	

    	//Timer hochzählen
    	time++;
    	//Gehälter abziehen
    	finanzen-=gehaltsListe;

    	
    	
    	Graphics2D g2d = (Graphics2D)g;
    	
    	//Präbedingungen
    	
    	if(lPerson.size()<1) switchGast.setEnabled(false); else switchGast.setEnabled(true);
    	
    	//Unteres Menü zeichnen
    	statusLabel.setText("X:" +x+ "(" +(x/32)+ ")" + " Y:" +y+ "(" +(y/32)+ ") - Block#" + blockGrid[x/32][y/32] +" BlockStat#" +blockGridStat[x/32][y/32]+ " Besetzt:" +besetztGrid[x/32][y/32]+ " G�stetische:" +lTisch.size() +"Bestellung abgegeben:"+ bestellungAbgegeben + "Schlange:" +schlange + "SB:" +schonBesetzt );
    	scoreLabel.setText("Finanzen " + (Math.round(finanzen))+  "€     Ruf " +ruf+ "        Zeit in Sek. " +Math.round(time/10)+ "    Gehalt in Cent pro Sek -"+(gehaltsListe));
    	
    	//statische Blöcke zeichnen
    	double alpha =  0.5; //draw half transparent
    			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)alpha);
    			g2d.setComposite(ac);
    	
        try
 	    {
        	for(int ix=0;ix<20;ix++)
        	{
        		for(int iy=0;iy<18;iy++)
        		{ 			
        			tmpSelector = blockGridStat[ix][iy];      			

        			if(tmpSelector==100)
        			{
            	        ImageIcon b4 = new ImageIcon("res/arrows.png");
            	        selection100 = b4.getImage();
            	        g2d.drawImage(selection100, ix*32, iy*32, this);
        			}
        			if(tmpSelector==101)
        			{
            	        ImageIcon b4 = new ImageIcon("res/wall3.png");
            	        selection101 = b4.getImage();
            	        g2d.drawImage(selection101, ix*32, iy*32, this);
        			}
        			if(tmpSelector==102)
        			{
        		        ImageIcon b1 = new ImageIcon("res/woodfloor4.png");
        		        selection102 = b1.getImage();
        		        g2d.drawImage(selection102, ix*32, iy*32, this);
        			}
        			if(tmpSelector==103)
        			{
        		        ImageIcon b1 = new ImageIcon("res/door.png");
        		        selection103 = b1.getImage();
        		        g2d.drawImage(selection103, ix*32, iy*32, this);
        			}
        		}
        	}
 	    }
 	    catch (Exception exc)
 	    {
 	        exc.printStackTrace(System.out);
 	    }
    	
    	//dynamische Blöcke zeichnen
    	alpha =  1.0; //draw half transparent
		ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)alpha);
		g2d.setComposite(ac);
        
        try
 	    {

        	for(int ix=0;ix<20;ix++)
        	{
        		for(int iy=0;iy<18;iy++)
        		{ 			
        			tmpSelector = blockGrid[ix][iy];      			

        			if(tmpSelector==1)
        			{
        				ImageIcon b1 = new ImageIcon("res/floor_grey.png");
        		        selection1 = b1.getImage();

            	        //g2d.drawImage(selection1, ix*32, iy*32, this);
        			}
        			if(tmpSelector==2)
        			{
            	        ImageIcon b2 = new ImageIcon("res/herd.png");
            	        selection2 = b2.getImage();
            	        g2d.drawImage(selection2, ix*32, iy*32, this);
        			}
        			
        			if(tmpSelector==3)
        			{
            	        ImageIcon b3 = new ImageIcon("res/zenithal.png");
            	        selection3 = b3.getImage();
            	        g2d.drawImage(selection3, ix*32, iy*32, this);
        			}
        			if(tmpSelector==4)
        			{
            	        ImageIcon b4 = new ImageIcon("res/waschbecken.png");
            	        selection4 = b4.getImage();
            	        g2d.drawImage(selection4, ix*32, iy*32, this);
        			}
        			if(tmpSelector==5)
        			{
            	        ImageIcon b5 = new ImageIcon("res/kasse1.png");
            	        selection5 = b5.getImage();
            	        g2d.drawImage(selection5, ix*32, iy*32, this);
        			}
        			if(tmpSelector==6)
        			{
            	        ImageIcon b6 = new ImageIcon("res/toilette.png");
            	        selection6 = b6.getImage();
            	        g2d.drawImage(selection6, ix*32, iy*32, this);
        			}
        			if(tmpSelector==7)
        			{
            	        ImageIcon b7 = new ImageIcon("res/sthul.png");
            	        selection7 = b7.getImage();
            	        g2d.drawImage(selection7, ix*32, iy*32, this);
        			}
        			if(tmpSelector==8)
        			{
            	        ImageIcon b8 = new ImageIcon("res/sthul2.png");
            	        selection8 = b8.getImage();
            	        g2d.drawImage(selection8, ix*32, iy*32, this);
        			}
        			if(tmpSelector==9)
        			{
        		        ImageIcon b9 = new ImageIcon("res/sthul3.png");
        		        selection9 = b9.getImage();
        		        g2d.drawImage(selection9, ix*32, iy*32, this);
        			}
        			if(tmpSelector==10)
        			{
        		        ImageIcon b10 = new ImageIcon("res/sthul4.png");
        		        selection10 = b10.getImage();
        		        g2d.drawImage(selection10, ix*32, iy*32, this);
        			}
        		}
        	}
 	    }
 	    catch (Exception exc)
 	    {
 	        exc.printStackTrace(System.out);
 	    }
        
    	//Personen zeichnen
        for (int iPerson=0;iPerson<lPerson.size();iPerson++)
        {        	
            //Gäste Grafiken
	        ImageIcon p1 = new ImageIcon("res/person1_0N.png");
	        people_0N = p1.getImage();
	        ImageIcon p2 = new ImageIcon("res/person1_1O.png");
	        people_1O = p2.getImage();
	        ImageIcon p3 = new ImageIcon("res/person1_2S.png");
	        people_2S = p3.getImage();
	        ImageIcon p4 = new ImageIcon("res/person1_3W.png");
	        people_3W = p4.getImage();

	        ImageIcon p5 = new ImageIcon("res/need.png");
	        need = p5.getImage();
	        ImageIcon p51 = new ImageIcon("res/eating.png");
	        eating = p51.getImage();
	        ImageIcon p52 = new ImageIcon("res/good.png");
	        good = p52.getImage();
	        ImageIcon p53 = new ImageIcon("res/notsogood.png");
	        notsogood = p53.getImage();
	        ImageIcon p54 = new ImageIcon("res/wannaleave.png");
	        wannaleave = p54.getImage();
	        ImageIcon p55 = new ImageIcon("res/pay.png");
	        pay = p55.getImage();
	        ImageIcon p56 = new ImageIcon("res/toilet_stat.png");
	        toilet = p56.getImage();
	        ImageIcon p57 = new ImageIcon("res/herz.png");
	        love = p57.getImage();

	        
	        //Kellner Grafiken
	        ImageIcon p6 = new ImageIcon("res/person2_0N.png");
	        people2_0N = p6.getImage();
	        ImageIcon p7 = new ImageIcon("res/person2_1O.png");
	        people2_1O = p7.getImage();
	        ImageIcon p8 = new ImageIcon("res/person2_2S.png");
	        people2_2S = p8.getImage();
	        ImageIcon p9 = new ImageIcon("res/person2_3W.png");
	        people2_3W = p9.getImage();
	        
	        ImageIcon p91 = new ImageIcon("res/dreaming.png");
	        istFrei = p91.getImage();
	        
	        //Koch Grafiken
	        ImageIcon p10 = new ImageIcon("res/person3_0N.png");
	        people3_0N = p10.getImage();
	        ImageIcon p11 = new ImageIcon("res/person3_1O.png");
	        people3_1O = p11.getImage();
	        ImageIcon p12 = new ImageIcon("res/person3_2S.png");
	        people3_2S = p12.getImage();
	        ImageIcon p13 = new ImageIcon("res/person3_3W.png");
	        people3_3W = p13.getImage();
	        
	        ImageIcon p14 = new ImageIcon("res/cooking.png");
	        cooking = p14.getImage();
	        ImageIcon p15 = new ImageIcon("res/meal.png");
	        essen = p15.getImage();
	        
            personKlasse tmpobj = lPerson.get(iPerson);
            tmpobj = findeWeg(tmpobj);
            
            //Schlange zeichnen
            for(int i=0;i<schlange;i++)
            {
            	g2d.drawImage(people_1O, 210-(i*16), 512, this);     
            };
 	      
    	    //Person mit Richtung zeichnen
            if(tmpobj.typ==0 && tmpobj.status!=1000 && tmpobj.status!=1001) //GAST
            {
            	switch(tmpobj.direction)
	        	{
	        	case 0 :g2d.drawImage(people_0N, tmpobj.posX, tmpobj.posY, this); break;
	        	case 1 :g2d.drawImage(people_1O, tmpobj.posX, tmpobj.posY, this); break;
	        	case 2 :g2d.drawImage(people_2S, tmpobj.posX, tmpobj.posY, this); break;
	        	case 3 :g2d.drawImage(people_3W, tmpobj.posX, tmpobj.posY, this); break; 	
	        	}
	        	
	        	//Statussymbol über Person zeichnen	        	
	        	if(tmpobj.status==1) g2d.drawImage(need, tmpobj.posX-6, tmpobj.posY-6, this);
	        	if(tmpobj.status==4) 
	        		{
	        			if(tmpobj.posX==tmpobj.homeX && tmpobj.posY==tmpobj.homeY) g2d.drawImage(eating, tmpobj.posX-6, tmpobj.posY-6, this);
	        			int[] tmpArr = findeTisch(tmpobj.homeX,tmpobj.homeY);
	        			g2d.drawImage(essen, tmpArr[0]+6, tmpArr[1]+9, this); 
	        		}
	        	
	        	if(tmpobj.status==5) g2d.drawImage(pay, tmpobj.posX-6, tmpobj.posY-6, this);
	        	if(tmpobj.Laune>500) g2d.drawImage(love, tmpobj.posX+22, tmpobj.posY-6, this);
	        	if(tmpobj.Laune>350 && tmpobj.Laune<=500) g2d.drawImage(good, tmpobj.posX+22, tmpobj.posY-6, this);
	        	if(tmpobj.Laune>150 && tmpobj.Laune<=350) g2d.drawImage(notsogood, tmpobj.posX+22, tmpobj.posY-6, this);
	        	if(tmpobj.Laune<=150) g2d.drawImage(wannaleave, tmpobj.posX+22, tmpobj.posY-6, this);
	        	if(tmpobj.Harndrang>450) g2d.drawImage(toilet, tmpobj.posX-6, tmpobj.posY-6, this);
            }
            if(tmpobj.typ==1) //KELLNER
            {
	        	switch(tmpobj.direction)
	        	{
	        	case 0 :g2d.drawImage(people2_0N, tmpobj.posX, tmpobj.posY, this); break;
	        	case 1 :g2d.drawImage(people2_1O, tmpobj.posX, tmpobj.posY, this); break;
	        	case 2 :g2d.drawImage(people2_2S, tmpobj.posX, tmpobj.posY, this); break;
	        	case 3 :g2d.drawImage(people2_3W, tmpobj.posX, tmpobj.posY, this); break; 	
	        	}
	        	//Statussymbol über Person zeichnen	        	
	        	if(tmpobj.status==101) g2d.drawImage(istFrei, tmpobj.posX-6, tmpobj.posY-6, this);
            }
            if(tmpobj.typ==2) //Koch
            {
	        	switch(tmpobj.direction)
	        	{
	        	case 0 :g2d.drawImage(people3_0N, tmpobj.posX, tmpobj.posY, this); break;
	        	case 1 :g2d.drawImage(people3_1O, tmpobj.posX, tmpobj.posY, this); break;
	        	case 2 :g2d.drawImage(people3_2S, tmpobj.posX, tmpobj.posY, this); break;
	        	case 3 :g2d.drawImage(people3_3W, tmpobj.posX, tmpobj.posY, this); break; 	
	        	}
	        	//Statussymbol über Person zeichnen	        	
	        	if(tmpobj.status==202) g2d.drawImage(cooking, tmpobj.posX-6, tmpobj.posY-6, this);
	        	
	        	for(int i=0; i<essenZubereitet;i++)
	        	{
	    			int[] tmpArr = findeTisch(tmpobj.posX,tmpobj.posY);
	    			g2d.drawImage(essen, tmpArr[0]+(i*15), tmpArr[1]+6, this); 
	        	}
            }
            
            //-----------STATUS dynamisch verändern
            
            if(tmpobj.status==100 && tmpobj.posX==tmpobj.homeX && tmpobj.posY==tmpobj.homeY) tmpobj.status=101;
          //Suche freien Kellner
            if(tmpobj.status==1)
            {           	
            	int treffer=0;
                for (int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
                {  
                	if(treffer==1) break;
                    personKlasse tmpobjK = lPerson.get(iPerson2);
                    if(tmpobjK.status==101)
                    {
                    	//finde freien Platz neben Gast
                    	int[] tmpArr = findeFreieStelle(tmpobj.posX,tmpobj.posY);
						besetztGrid[tmpobjK.posX/32][tmpobjK.posY/32]=0;
						tmpobjK.zielX=tmpArr[0];
						tmpobjK.zielY=tmpArr[1];
						tmpobjK.status=102;
						tmpobjK.gerichtWert=tmpobj.gerichtWert; //Kellner übernimmt Gerichtwert von Gast
						tmpobj.status=2;
						lPerson.set(iPerson2, tmpobjK);
						treffer=1;
						break;
                    }
                }
            }

            //Bringe Bestellung zu Koch
            if(tmpobj.status==102)
            {           	
            	int doOnlyOnce=0;
                for (int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
                {  
                    personKlasse tmpobjK = lPerson.get(iPerson2);
                    if(tmpobjK.status==2)
                    {
                    	tmpobjK.status++;
                    	doOnlyOnce=1;
                    }
                    if(tmpobjK.typ==2)
                    {
                    	//finde freien Platz neben Koch
                    	int[] tmpArr = findeFreieStelle(tmpobjK.posX,tmpobjK.posY);
						besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=0;
						tmpobj.zielX=tmpArr[0];
						tmpobj.zielY=tmpArr[1];
						tmpobj.status=103;
						lPerson.set(iPerson2, tmpobjK);
                    }
                }
            }
            //Zubereitungszeit Gericht
            if(tmpobj.status==202)
            {
            	if(tmpobj.Zubereitung<100)
            	tmpobj.Zubereitung++;
            	else 
            		{
            			essenZubereitet++;
            			tmpobj.Zubereitung=0;
	        			
	        			if(bestellungAbgegeben>0) {tmpobj.status=202;bestellungAbgegeben--;} else tmpobj.status=201;
            		}
            }
            
            //Fertiges Essen vom Koch holen
			if(essenZubereitet>0 && tmpobj.status==101)
			{
				
				
				for (int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
    	        {
    	        	personKlasse tmpobj4 = lPerson.get(iPerson2);
    	        	if(tmpobj4.typ==2) //Suche Koch
    	        	{
    	        	tmpobj.status=104;//Hole das essen
            		besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=0;
    	            int[] tmpArr = findeFreieStelle(tmpobj4.posX,tmpobj4.posY);
    	            tmpobj.zielX=tmpArr[0];
    	            tmpobj.zielY=tmpArr[1];
    	        	}
    	        }
			}
            //Essen des Gerichts
            if(tmpobj.status==4 && tmpobj.posX==tmpobj.homeX && tmpobj.posY==tmpobj.homeY)
            {
            	tmpobj.Laune+=3;
            	if(tmpobj.Hunger>0)
            		tmpobj.Hunger--;
            	else 
            		{
            			if(tmpobj.posX==tmpobj.homeX && tmpobj.posY==tmpobj.homeY) tmpobj.status=5;
            		}
            }
            
            //Möchte jemand zahlen
            if(tmpobj.status==5)
            {
    			//Suche Kellner
    				for (int iPerson2=0;iPerson2<lPerson.size();iPerson2++)
        	        {
        	        	personKlasse tmpobj4 = lPerson.get(iPerson2);
        	            if(tmpobj4.status==101)
        	            {
                			besetztGrid[tmpobj4.posX/32][tmpobj4.posY/32]=0;
        	            	tmpobj4.status=106; //Kassiert ab
        	            	int[] tmpArr = findeFreieStelle(tmpobj.posX,tmpobj.posY);
        	            	tmpobj4.zielX=tmpArr[0];
        	            	tmpobj4.zielY=tmpArr[1];
        	            }
        	        }
            }
            
            if(tmpobj.status==100)
            {
            	tmpobj.zielX=tmpobj.homeX;
            	tmpobj.zielY=tmpobj.homeY;
            }
            
            //Laune verändern
            if(tmpobj.typ==0 && tmpobj.Laune>0 && tmpobj.status!=1000 && tmpobj.status!=1001 && tmpobj.status!=99 && tmpobj.status!=4 && tmpobj.status!=6)
            {
            	tmpobj.Laune--;
            }
            
			//Richtung Stuhl ausrichten
			if(blockGrid[tmpobj.posX/32][tmpobj.posY/32]==7) tmpobj.direction=2;
			if(blockGrid[tmpobj.posX/32][tmpobj.posY/32]==8) tmpobj.direction=3;
			if(blockGrid[tmpobj.posX/32][tmpobj.posY/32]==9) tmpobj.direction=0;
			if(blockGrid[tmpobj.posX/32][tmpobj.posY/32]==10) tmpobj.direction=1;
                        
            //Verlässt empört das Restaurant
            if(tmpobj.Laune<50 && tmpobj.status!=1001 && tmpobj.Harndrang<450)
            {
            	tmpobj.zielX=224;
            	tmpobj.zielY=448;
            	if(tmpobj.status==3) essenZubereitet--;
            	tmpobj.status=99;
            	besetztGrid[tmpobj.homeX/32][tmpobj.homeY/32]=0;
            	tmpobj.homeX=224;
            	tmpobj.homeY=448;
            }
            
			if(tmpobj.status==99 && tmpobj.posX==tmpobj.zielX && tmpobj.posY==tmpobj.zielY)
			{
				//lPerson.remove(tmpobj.nr);
				System.out.println("[Ruf subtrahieren von " +tmpobj.nr+ " mit Status " +tmpobj.status);
				tmpobj.status=1001;
				
				besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=0;
				tmpobj.homeX=0;
				tmpobj.homeY=0;
				if(schlange>0) tmpEinlass=10000;
				ruf=ruf/2;
				System.out.println("[Ruf subtrahiert, Status auf " +tmpobj.status+ " geändert]");
			}
			if(tmpobj.status==6 && tmpobj.posX==tmpobj.zielX && tmpobj.posY==tmpobj.zielY)
			{
				System.out.println("[ Gast verabschiedet ]" );
				tmpobj.status=1000;
				tmpobj.homeX=0;
				tmpobj.homeY=0;
				
				besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=0;
				if(schlange>0) tmpEinlass=10000;
				if(tmpobj.Laune<500) 
					{
						ruf+=10;
						System.out.println("[ Ruf um 10 erhoeht ]" );
					}	
						else {
					ruf+=25; 
					System.out.println("[ Ruf um 25 erhoeht ]" );
					finanzen+=(int) (Math.random() * 10);
					};

				//lPerson.remove(tmpobj.nr);
			}
			
			if(tmpobj.Harndrang<549 && tmpobj.typ==0 && tmpobj.status!=1000 && tmpobj.status!=1001 && tmpobj.status!=6 && tmpobj.status!=99)
				tmpobj.Harndrang+=0.8;
			
			if(tmpobj.Harndrang>450)
			{
				besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=0;
				int[] tmpArr = findeToilette(tmpobj.posX,tmpobj.posY);
				tmpobj.zielX=tmpArr[0];
				tmpobj.zielY=tmpArr[1];
			}
			if(tmpobj.Harndrang>-2 && tmpobj.Harndrang<0)
			{
				besetztGrid[tmpobj.posX/32][tmpobj.posY/32]=0;
    			tmpobj.zielX=tmpobj.homeX;
    			tmpobj.zielY=tmpobj.homeY;
			}
			
			//Einnahmen / Ausgaben zeichnen
			if(tmpTimer>time)
			{
		      
				g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		      
		      if(offset%3==0)
		    	  {
		    	  	g2d.setColor(Color.black);
		    	  	g2d.drawString(""+tmpWert+"€", tmpTimerPosX+1,(tmpTimerPosY-offset+1));
		    	  	if(tmpWert>=0) g2d.setColor(Color.green);else g2d.setColor(Color.red);	
		    	  	g2d.drawString(""+tmpWert+"€", tmpTimerPosX,(tmpTimerPosY-offset));
		    	  }
		      offset++;
			} else 
			{
				tmpTimer=0;
		    	tmpWert=0;
		    	offset=0;
			}
			
	     }
        
    	//Gäste einlassen
    	if(tmpEinlass<10000)
    	{
    		tmpEinlass+=ruf;
    	} else 
    	{
    		gastEinlassen.doClick();
    		tmpEinlass=0;
    	}
    	System.out.println("[Gast Spawner :" +tmpEinlass+ "," +ruf+ "]");
    	
    	
        
        //CURSOR ZEICHNEN
        //auswahl gast        
        
        if(lPerson.size()>0) 
        	{
        	personKlasse tmpobj2 = lPerson.get(gastzaehler);
            ImageIcon sw1 = new ImageIcon("res/cursorGast.png");
            cursorGast = sw1.getImage();
            g2d.drawImage(cursorGast, tmpobj2.posX, tmpobj2.posY, this);          
        	}
        

        //auswahl 
        g2d.drawImage(cursor, x, y, this);
        Toolkit.getDefaultToolkit().sync();
        
    }

    private void cycle() {

        //x += 1;
        //y += 1;

        if (y > B_HEIGHT) {

            y = INITIAL_Y;
            x = INITIAL_X;
        }
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                
                JOptionPane.showMessageDialog(this, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }

            beforeTime = System.currentTimeMillis();
        }
    }
}


