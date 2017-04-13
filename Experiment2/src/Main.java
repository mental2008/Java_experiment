
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean[] prime = new boolean[10000010];
		for(int i = 1; i <= 10000000; i++) {
			prime[i] = true;
		}
		int ans = 2;
		for(int i = 2; i <= 10000000; i++) {
			if(prime[i]) {
				ans = Math.max(i, ans);
				for(int j = i * 2; j <= 10000000; j += i) {
					prime[j] = false;
				}
			}
		}
		System.out.println("The biggest prime number is " + ans);
	}

}
