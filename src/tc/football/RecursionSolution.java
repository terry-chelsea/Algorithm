package tc.football;

//ʹ�õݹ�ķ�ʽʵ��
public class RecursionSolution extends Solution{		
	public RecursionSolution(int[] args, int sum) {
		super(args, sum);
	}
	
	public int fetchCombinations() {
		fetchCombinationsInside();
		return super.results.size();
	}
	
	private void fetchCombinationsInside() {
		ResultItem item = new ResultItemUsePrime(minValues);
		recursionCalculate(sumValue, item);
	}
	
	private void recursionCalculate(int param, ResultItem result) {
		if(param == 0) {
			results.add(result);
//			System.out.println(result + " hashcode : " + result.hashCode());
		}
		if(param < 0) {
			return ;
		}
		
		for(int value : this.minValues) {
			ResultItem newRet = (ResultItem) result.clone();
			newRet.addItem(value);
			recursionCalculate(param - value, newRet);
		}
	}
}
