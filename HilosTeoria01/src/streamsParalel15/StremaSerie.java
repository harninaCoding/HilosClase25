package streamsParalel15;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;


public class StremaSerie {

	public static void main(String[] args) {
		ArrayList<Element> elements = new ArrayList<>();
		int size = 100;
		TransformerSingle single=new TransformerSingle();
		for (int i = 0; i < size; i++) {
			elements.add(new Element());
		}
		Instant now = Instant.now();
		elements.stream().forEach((element)->{single.change(element);});
		Instant later = Instant.now();
		Duration duration = Duration.between(now, later);
		System.out.println("la diferencia monohilo " + duration.toMillis());

	}

}
