package Extra;
import java.util.Random;

public class DistributionSort {
	public static void main(String[] args) {
		Random rand = new Random();
		int[] A = new int[1000];
		
		for(int i = 0; i < A.length; i++)
			A[i] = rand.nextInt(0,101);
		
		System.out.println("Randomized A:");
		for(int i = 0; i < A.length; i++)
			System.out.print(A[i] + " ");
		
		A = Sort(A);
		
		System.out.println("\nSorted A:");
		for(int i = 0; i < A.length; i++)
			System.out.print(A[i] + " ");
	}
	
	public static int[] Sort(int[] A) {
		int u = Integer.MIN_VALUE;
		int l = Integer.MAX_VALUE;
		int[] S = new int[A.length];
		
		for(int i = 0; i < A.length; i++) {
			if (A[i] > u)
				u = A[i];
			else if (A[i] < l)
				l = A[i];
		}
		
		int[] D = new int[u - l + 1];
		
		for(int i = 0; i < A.length; i++)
			D[A[i] - l]++;
		for(int i = 1; i < D.length; i++)
			D[i] += D[i - 1];
		for(int i = 0; i < A.length; i++)
			S[--D[A[i] - l]] = A[i];
		
		return S;
	}
}
