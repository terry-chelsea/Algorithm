package tc.football;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sun.tools.jar.resources.jar;

//ʹ������������ж��������飨���صĽ�����Ƿ������ͬ��Ԫ�أ���������һ�������ĳ˻�ֻ����Ψһ�ı�ʾ��ʽ��ԭ��
//���ԣ�ÿ���������м���һ��Ԫ�ص�ʱ�����˻��г��Ը�Ԫ�ض�Ӧ����������������ܵĳ˻��ж����������Ƿ������ͬ��Ԫ�ء�
//���ƣ�������int����ȫ���ĳ˻������ܴ���long��������������ʹ�ñȽ�С������������ˣ������Ƿ����ȡ��������ĳ��ȡ�
//int�����������Σ�գ�Ӧ�ö���ȷ��û��ʲôӰ�죬��Ϊ����һ�������������֮�������Ҳ�ܱ�֤����һ��Ψһ������������˵Ľ��
public class ResultItemUsePrime extends ResultItem {
	private int[] args;
	private int sumPrime;
	private Map<Integer, Integer> valueToPrime;
	private static List<Integer> primes;
	public ResultItemUsePrime(int[] args) {
		super();
		this.args = args;
		sumPrime = 1;
		valueToPrime = getPrimes(this.args);
	}
	
	public ResultItemUsePrime(ResultItemUsePrime prev) {
		result = new LinkedList<Integer>(prev.result);
		this.args = prev.args;
		sumPrime = prev.sumPrime;
		valueToPrime = getPrimes(this.args);
	}
	
	public void addItem(Integer value) {
		if(value == null) {
			return ;
		}
		result.add(value);
		Integer prime = valueToPrime.get(value);
		if(prime == null) {
			System.err.println("never happen : can not find prime");
			return ;
		}
		sumPrime *= prime;
	}
	
	//��Ҫ�����Է��ԡ������ԡ������Ժ�һ���ԣ����಻����һ���ԣ���һ��ֻ�Ƚ�һ�Σ����Զ�̬�ı䡣
	public boolean equals(Object obj) {
		if(!(obj instanceof ResultItemUsePrime))
			return false;
		ResultItemUsePrime item = (ResultItemUsePrime)obj;
		if(this.result.size() != item.result.size())
			return false;
		
		boolean result = (item.sumPrime == this.sumPrime);
		if(result) {
			Integer[] curList = this.result.toArray(new Integer[0]);
			Arrays.sort(curList);
			Integer[] itemList = item.result.toArray(new Integer[0]);
			Arrays.sort(itemList);
			if(curList.length != curList.length) {
				System.err.println(item + " equals " + this);
				return false;
			}
			for(int i = 0 ; i < curList.length ; ++ i) {
				if(curList[i] != itemList[i])
				{
					System.err.println(item + " equals " + this);
					return false;
				}
			}
		}
		return result;
	}
	
	//��Ҫ��֤equals����true��ʱ�����������hashCode��ͬ
	//��Ҫ��longת����int���أ�long���ܴ��������ת����int��һ���ܱ�֤Ψһ�ԣ����Կ��ܴ���bug��
	public int hashCode() {
		return this.sumPrime;
	}
	
	private static int getNextPrime(int start) {
		for(int i = start + 1 ; true ; i ++) {
			int j = 2;
			for( ; (j <= (i >> 1)) && (i % j != 0); ++ j);
			if(j > (i >> 1))
				return i;			
		}
	}
	
	private static Map<Integer, Integer> getPrimes(int[] args) {
		Map<Integer, Integer> valueToPrimeMap = new HashMap<>();
		if(args == null)
			return valueToPrimeMap;
		if(primes == null) {
			primes = new ArrayList<>(args.length);
			//��һ��������3,֮����ʹ��3��ʼ����Ϊ���ʹ��2���ܵ������֮������0
			primes.add(3);
		}
		//��������������Ѱ������
		if(primes.size() < args.length) {
			int need = args.length - primes.size();
			for(int i = 0 ; i < need ; ++ i) {
				primes.add(getNextPrime(primes.get(primes.size() - 1)));
			}
		}
		
		int i = 0;
		for(int value : args) {
			valueToPrimeMap.put(value, primes.get(i));
			i ++;
		}
		return valueToPrimeMap;
	}
	
	public static void main(String[] args) {
		System.out.println(getPrimes(new int[] {1, 2, 3}));
	}
}
