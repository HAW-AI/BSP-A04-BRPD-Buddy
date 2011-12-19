
public class Application {
	public static void main(String[] args) {
		Buddy buddy = Buddy.create(128);
		buddy.alloc(10);
		System.out.println(buddy);
	}
}
