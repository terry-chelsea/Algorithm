package word_search;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.xml.internal.bind.v2.model.core.ID;

/*
 * ��һ�ַ�����ʹ�õݹ�ķ�ʽ�����ȼ�¼ÿһ���ַ����ֵ�λ�ã�������������һ��map��
 * Ȼ���ÿһ�������ڴ������ַ����е��ַ��ҳ����ַ���������Щλ�ã��ж��Ƿ��к���һ���ַ�
 * ���ڵ�λ�ã�������ڣ����Ǹ�λ���Ѿ�ʹ�ã������ݹ�Ĳ鿴��һ���ڵ㡣
 * 
 * �÷������Եõ���ȷ�Ĵ𰸣���������ʹ�õݹ�ķ�ʽ�����ִ�������ϴ���һ����ȱ��
 * Ŀǰû��ͨ�����ԣ�ʱ�䳬�����ơ�
 */
public class Exist {
	private class Point {
		private int x;
		private int y;
		private boolean used;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.used = false;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public boolean isUsed() {
			return this.used;
		}
		
		public void setUsed(boolean used) {
			this.used = used;
		}
		
		public boolean adjacent(Point p) {
			if(p == null)
				return false;

			return (p.x - this.x == 1 && p.y == this.y) || 
					(this.x - p.x == 1 && p.y == this.y) || 
					(p.y - this.y == 1 && p.x == this.x) || 
					(this.y - p.y == 1 && p.x == this.x);
		}
		
		public String toString() {
			return "(" + x + " , " + y + ")";
		}
	}
	
	private class Unit {
		private List<Point> points;
		
		public Unit(int n) {
			points = new ArrayList<Point>();
		}
		
		public List<Point> getPoints() {
			return points;
		}
		
		public void addPoint(Point p) {
			points.add(p);
		}
		
	}
	
	public boolean exist(char[][] board, String word) {
		if(board == null || word == null)
			return false;
		if(word.isEmpty())
			return true;
		
        Map<Character, Unit> indexMap = new HashMap<Character, Unit>();
        int row = board.length;
        int col = board[0].length;
        for(int i = 0 ; i < row ; ++ i) {
        	for(int j = 0 ; j < col ; ++ j) {
        		char ch = board[i][j];
        		Unit points = indexMap.get(ch);
        		if(points == null) {
        			points = new Unit(4);
        			indexMap.put(ch, points);
        		}
        		points.addPoint(new Point(i, j));
        	}
        }
        
        return checkExist(indexMap, word, 0);
    }
	
	private boolean checkExist(Map<Character, Unit> map, String word, int index) {
		if(index >= word.length() - 1)
			return true;
		
		char ch = word.charAt(index);
		Unit unit = map.get(ch);
		char nextCh = word.charAt(index + 1);
		Unit nextUnit = map.get(nextCh);
		
		if(unit == null || nextUnit == null)
			return false;
  
		for(Point p : unit.getPoints()) {
			if(p.isUsed())
				continue;
			p.setUsed(true);
			for(Point next : nextUnit.getPoints()) {
				if(next.isUsed())
					continue;
				if(!p.adjacent(next))
					continue;
				boolean ret = checkExist(map, word, index + 1);
				if(ret) 
					return true;
			}
			p.setUsed(false);
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		/*
		char[] a = new char[]{'A','B','C','E'};
		char[] b = new char[]{'S','F','C','S'};
		char[] c = new char[]{'A','D','E','E'};
		*/
		char[] a = new char[]{'a','a','a'};
		char[] b = new char[]{'a','a','a'};
		char[] c = new char[]{'a','a','a'};
		char[][] board = new char[][]{a,b,c};
		Exist exist = new Exist();
		System.out.println(exist.exist(board , "aaaaaaaaaaaaa"));
	}
}
