package utiles;

public class WasteTime {
    
    public static void wasteTime(int time) {
        int factor = 1000;
        StringBuilder message = new StringBuilder();
            for (int i = 0; i < time*factor; i++) {
                message.append(".");
            }
    }
}
