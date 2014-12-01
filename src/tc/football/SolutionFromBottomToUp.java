package tc.football;

import java.util.HashSet;
import java.util.Set;

import sun.awt.SunHints.Value;

public class SolutionFromBottomToUp extends RecursionSolutionWithCache {
	private int maxValue = 0;
	public SolutionFromBottomToUp(int[] args, int sum) {
		super(args, sum);
	}

	public int fetchCombinations() {
		initCache();
		//�õ����ֵ�������ͷŲ���Ҫ���ڴ�
		maxValue = minValues[0];
		for(int value : minValues) {
			if(value > maxValue)
				maxValue = value;
		}
		fetchCombinationsInside();
		if(results == null)
			return -1;
		return results.size();
	}
	
	private void fetchCombinationsInside() {
		int index = 0;
		for( ; index < cache.size() && cache.get(index) != null ; ++ index);
		//��ÿһ�����м���
		for(int i = index ; i < cache.size() ; ++ i) {
			//����Ѿ�������ˣ�����Ҫ�ظ�����,��ֹ��ʼ���Ľ�������ǣ����³���
			if(cache.get(i) != null)
				continue;
			Set<ResultItem> newResult = new HashSet<ResultItem>();
			for(int value : minValues) {
				int sub = i - value;
				if(sub < 0)
					continue;
				Set<ResultItem> result = cache.get(sub);
				if(result == null)
					continue;
				for(ResultItem item : result) {
					ResultItem newItem = (ResultItem) item.clone();
					newItem.addItem(value);
					newResult.add(newItem);
				}
			}
			cache.set(i, newResult);
			//������Ҫ���ڴ�
			if(i - maxValue >= 0)
				cache.set(i - maxValue, null);
		}
		results = cache.get(sumValue);
	}
}
