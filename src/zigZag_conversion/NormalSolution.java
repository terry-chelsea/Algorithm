package zigZag_conversion;

import java.util.ArrayList;
import java.util.List;

public class NormalSolution {
	public String solution(String s, int nRows) {
		if(s == null || nRows <= 0)
			return null;
		StringBuffer result = new StringBuffer();
		if(s.isEmpty())
			return result.toString();
		if(nRows == 1)
			return s;
		//�ַ�������
		int length = s.length();
		//�����ÿһ�������ܴ��ڶ��ٸ��ַ���ֻ����������List����ָ��capacity�����Բ���
		int maxLengthPerLine = (length / nRows + 1) << 1;
		List<List<Character>> allChars = new ArrayList<List<Character>>(nRows);
		for(int i = 0 ; i < nRows ; ++ i) {
			allChars.add(new ArrayList<Character>(maxLengthPerLine));
		}
		
		//��һ����һƲ(�������ϣ����������һ���ڵ�)���ϵ����нڵ㵱��һ����Ԫ������nRows=N����ôһ�������Ľڵ���
		//����N,һƲ�ĳ�������N - 2(�����������յ�)���ܹ��Ľڵ���Ϊ2 * N - 2
		int len = (nRows << 1) - 2;
		//������lines����Ԫ���������һ����Ԫ��һ������������һ����һƲ������һ��������ȫ��
		int lines = length / nRows;
		//����ÿһ����Ԫ������������Ԫ�������ĵ��������ͬλ��(����֮��)�����ڵ���ֵ�λ�á�
		for(int i = 0 ; i < lines + 1 ; ++ i) {
			for(int j = 0 ; j < nRows ; ++ j) {
				//�����ÿһ���ַ�Ӧ�ó��ڵĶ�ά�����λ��
				//����ÿһ����Ԫ��һ����Ҫ�����ÿһ���ڵ�ĳ����ڶ�ά�����λ��
				int next = i * len + j;
				if(next < length) {
					allChars.get(j).add(s.charAt(next));
				}
				//����һƲ�ϵ�ÿһ���㣬��������ͷ�ͽ�β�����㣬��������������ڵ�λ��
				if(j > 0 && j < nRows - 1) {
					next = (i + 1) * len - j;
					if(next < length)
						allChars.get(j).add(s.charAt(next));
				}
			}
		}
		
		//����Z���������������ַ���
		for(int i = 0 ; i < nRows ; ++ i) {
			List<Character> chars = allChars.get(i);
			StringBuffer buf = new StringBuffer();
			if(i == 0 || i == nRows - 1) {				
				int space = nRows - 2;				
				for(Character character : chars) {
					buf.append(character);
					for(int j = 0 ; j < space ; ++ j)
						buf.append(' ');
				}
			} else {
				int prevSpace = nRows - (i + 2);
				int nextSpace = i - 1;
				int half = chars.size() % 2;
				int units = (half == 0) ? chars.size() / 2 : chars.size() / 2 + 1;
				
				for(int j = 0 ; j < units ; ++ j) {
					buf.append(chars.get(2 * j));
					for(int k = 0 ; k < prevSpace ; ++ k)
						buf.append(' ');
					if(2 * j + 1 < chars.size()) {
						buf.append(chars.get(2 * j + 1));
						for(int k = 0 ; k < nextSpace ; ++ k)
							buf.append(' ');
					}
				}
			}
			System.out.println(buf);
		}
		
		//�õ��Ķ�ά����֮��Ϳ��԰��������ת��֮����ַ����ˡ�
		for(List<Character> chars : allChars) {
			for(Character ch : chars)
				result.append(ch);
		}
		
		return result.toString();
	}
	
	public static void main(String[] args) {
		String test = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		NormalSolution solution = new NormalSolution();
		System.out.println(solution.solution(test, 5));
	}
}
