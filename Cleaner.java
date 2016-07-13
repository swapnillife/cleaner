

import java.awt.AWTException;
import java.awt.Robot;
import java.text.SimpleDateFormat;
import java.util.Date;

class Cleaner implements Runnable {

	private Robot iRobot;	
	static boolean targetAchevied = false;
	static boolean switchpos = false;
	int count = 0;

	public Cleaner() throws AWTException {

		iRobot = new Robot();
	}

	public void run() {

		int x, y;
		x = 1000;
		y = 900;
		do{
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH");
			String formattedTime = sdf.format(now);
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH mm");
			String formattedTime1 = sdf1.format(now);
				count++;
				moveMouse(x, y,formattedTime1);
				moveMouse(x, y,"");
				moveMouse(x, y,"");
				moveMouse(x, y,"");
				moveMouse(x, y,"");
			if(count == 8){
				targetAchevied= true;
			}
			try {
				Thread.sleep(320000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}		
		while(!targetAchevied);
	}

	private void moveMouse(int x, int y,String time) {
		
			if(switchpos){
				iRobot.mouseMove(x+100, y+100);
				//System.out.println("moved for switchpos ="+switchpos+" at "+(x+100)+", "+(y+100));
				if(!time.equals(""))
					System.out.println(time);
				switchpos = false;
			}
			else{
				iRobot.mouseMove(x-99, y-99);
				//System.out.println("moved for switchpos ="+switchpos+" at "+(x-99)+", "+(y-99));
				if(!time.equals(""))
					System.out.println(time);
				switchpos = true;
			}
	}

	public static void main(String args[]) throws AWTException {
		System.out.println("start");
		Cleaner cleaner = new Cleaner();
		Thread cleanerThread = new Thread(cleaner);
		cleanerThread.start();
	}
}

