package palindrome_number;

public class Solution {
	public boolean isPalindrome(int x) {
		if(x < 0)
			return false;
		if(x == 0)
			return true;
		
		int length = 1;
		int sum = 1;
		int temp = x;
		//�����С��x������10��n�η�
		while(temp >= 10) {
			temp /= 10;
			sum *= 10;
			length ++;
		}
		
		//ǰ��ͬʱ�鿴�Ƿ���ͬ�����ֻ��Ҫ�鿴һ�����
		length = (length >> 1);
		for(int i = 0 ; i < length ; ++ i) {
			//�ж�ǰ������ֺͺ���������Ƿ���ͬ��һ�����ֲ���ͬ��˵������
			if((x / sum) % 10 != x % 10)
				return false;
			x /= 10;
			sum /= 100;
		}
		
		return true;
    }
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.isPalindrome(313));
		System.out.println(solution.isPalindrome(1234554321));
	}
}
