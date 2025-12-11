package utiles;

public class ShowMessages {
	public static boolean showGlobal = true;

	public static void showMessage(String classorMethod, String message) {
		if (showGlobal)
			System.out.println(classorMethod + ":" + message);
	}

	public static void showMessage(String classorMethod, String message, boolean show) {
		if (showGlobal && show)
			System.out.println(classorMethod + ":" + message);
	}
}
