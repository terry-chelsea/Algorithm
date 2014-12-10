package word_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * �ǵݹ�˼·�����Ȼ��ǽ�ÿһ���ַ������ֵ�λ�ñ�����һ��map�У����ұ����Щλ�ö���δʹ�ù���
 * Ȼ�����α��������ҵ��ַ���������ÿһ���ַ������鿴���ַ������ֵ�����λ�ã������λ���ܹ�������
 * ��λ�����ҵ���һ���ַ�����ô�ͱ�Ǹ�λ����ʹ�ò���ջ������ܹ����������һ���ַ�����ô˵�����ҵ���·��
 * �����ø�λ�ó�ջ������������һ��λ�ã����ĳ���ַ����е�λ�ö��Ѿ������ԣ���˵�������ڸ�·����
 * �走������Accepted�ˣ�Ҫ��ס������һ����ջ��Ϣ��Ҫ��¼��һ���ڵ��������һ���ˡ�
 */

public class Solution {
	private class Point {
		private int x;
		private int y;
		private boolean used;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.used = false;
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
	
	private class Element {
		private char ch;
		private int index;
		private int nextIndex;
		
		public Element(char ch, int index) {
			this.ch = ch;
			this.index = index;
			this.nextIndex = -1;
		}
		
		public int getIndex(){
			return this.index;
		}
		
		public char getChar() {
			return this.ch;
		}
		
		public int getNextIndex() {
			return this.nextIndex;
		}
		
		public void setNextIndex(int index) {
			this.nextIndex = index;
		}
	}
	
	private class Stack {
		private Element[] stack;
		private int top;
		public Stack(int size) {
			stack = new Element[size];
			this.top = 0;
		}
		
		public void push(Element e) {
			stack[top ++] = e;
		}
		
		public Element pop() {
			top --;
			if(top < 0)
				return null;
			return stack[top];
		}
		
		public int getTop() {
			return top;
		}
		
		public Element top() {
			return stack[top - 1];
		}
	}
	
	public boolean exist(char[][] board, String word) {
		if(board == null || word == null)
			return false;
		if(word.isEmpty())
			return true;
		
        Map<Character, List<Point>> indexMap = new HashMap<Character, List<Point>>();
        int row = board.length;
        int col = board[0].length;
        for(int i = 0 ; i < row ; ++ i) {
        	for(int j = 0 ; j < col ; ++ j) {
        		char ch = board[i][j];
        		List<Point> points = indexMap.get(ch);
        		if(points == null) {
        			points = new ArrayList<Point>();
        			indexMap.put(ch, points);
        		}
        		points.add(new Point(i, j));
        	}
        }
        
        int length = word.length();
        Stack stack = new Stack(length);
        List<Point> head = indexMap.get(word.charAt(0));
        if(head == null)
        	return false;
        for(int i = 0 ; i < head.size() ; ++ i) {
        	stack.push(new Element(word.charAt(0), i));
        	head.get(i).setUsed(true);
        	while(stack.getTop() > 0) {
        		int index = stack.getTop() - 1;
        		if(index == length - 1)
        			return true;
        		Element top = stack.top();
        		Element next = getNextElement(indexMap, word, index, top);
        		if(next == null) {
        			Element e = stack.pop();
        			indexMap.get(e.getChar()).get(e.getIndex()).setUsed(false);
        		}
        		else {
        			stack.push(next);
        		}
        	}
        }
        return false;
	}
	
	private Element getNextElement(Map<Character, List<Point>> map, String word, int index, Element prev) {
		if(index >= word.length() - 1)
			return null;
		
		char first = word.charAt(index);
		char second = word.charAt(index + 1);
		
		List<Point> cur = map.get(first);
		List<Point> next = map.get(second);
		if(cur == null || next == null)
			return null;
		
		Point point = cur.get(prev.getIndex());
		int size = next.size();
		for(int i = prev.getNextIndex() + 1 ; i < size ; ++ i) {
			Point nextPoint = next.get(i);
			if(nextPoint.isUsed())
				continue;
			if(point.adjacent(nextPoint)) {
				nextPoint.setUsed(true);
				prev.setNextIndex(i);
				return new Element(second, i);
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		/*
		char[] a = new char[]{'A','B','C','E'};
		char[] b = new char[]{'S','F','C','S'};
		char[] c = new char[]{'A','D','E','E'};
		*/
		/*
		char[] a = new char[]{'a','a','a'};
		char[] b = new char[]{'a','a','a'};
		char[] c = new char[]{'a','a','a'};
		*/
		char[] a = new char[]{'a'};
		char[][] board = new char[][]{a};
		Solution exist = new Solution();
		System.out.println(exist.exist(board , "b"));
	}
}
