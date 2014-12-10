package zigZag_conversion;

public class MathSolution {
	public String solution(String s , int nRows) {
		if(s == null || nRows <= 0)
			return null;
		StringBuffer result = new StringBuffer();
		if(s.isEmpty())
			return result.toString();
		if(nRows == 1)
			return s;
		int length = s.length();
		
		//ÿһ����Ԫ���ַ���
		int len = (nRows << 1) - 2;
		//��Ԫ�������������һ������δ��ĵ�Ԫ
		int counter = length / len;
		//���һ������δ��ĵ�Ԫ
		int last = length % len;
		
		for(int i = 0 ; i < nRows ; ++ i) {
			//����last����Ŀ�жϸ������δ��ĵ�Ԫ�ڸ����Ƿ����ַ�
			int count = counter + (last > i ? 1 : 0);
			for(int j = 0 ; j < count ; ++ j) {
				//ÿһ����Ԫ���ֵĵ�һ���ַ����϶����ڣ�ֱ�Ӽ��뵽����У��������ǰ����������
				result.append(s.charAt(j * len + i));
				//��һ�к����һ�в�����Ʋ·���ϵĽڵ�
				if(i > 0 && i < nRows - 1) {
					//���� һƲ�ϵĵ㣬�ж��Ƿ����
					int next = (j + 1) * len - i;
					if(next < length)
						result.append(s.charAt(next));
				}
			}
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		String test = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		MathSolution solution = new MathSolution();
		System.out.println(solution.solution(test, 5));
	}
}
