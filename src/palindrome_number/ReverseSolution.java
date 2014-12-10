package palindrome_number;

public class ReverseSolution {
	//�Ӻ�ǰ����һ���µ������ж��������Ƿ���ͬ
	public boolean isPalindrome(int x) {
		if(x < 0)
			return false;
		if(x < 10)
			return true;
		
		int temp = x;
		int reverse = 0;
		while(temp != 0) {
			int last = temp % 10;
			temp = temp / 10;
			reverse = reverse * 10 + last;
		}
		
		return reverse == x;
	}
	
	public static void main(String[] args) {
		ReverseSolution solution = new ReverseSolution();
		System.out.println(solution.isPalindrome(313));
		System.out.println(solution.isPalindrome(1234554321));
	}	
}
