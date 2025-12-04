package utiles;

public class Wasters {
	public static void wasteTime(long amount,boolean type) {
		if(type) {
			try {
				Thread.sleep(amount);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			String c="";
			for (int i = 0; i < amount*1000; i++) {
				c=c+"a";
			}
		}
	}
}
