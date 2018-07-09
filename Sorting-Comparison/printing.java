public class printing {
	public static void main(String args[]) {
		In numbers = new In("numbers1000.txt");
		for(int i = 0; i < 1000; i++) {
			System.out.print(numbers.readDouble() + ", ");
			if(i % 10 == 9) {
				System.out.print("\n");
			}
		}
	}
}
