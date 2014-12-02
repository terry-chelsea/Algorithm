package intersection_of_two_linked_lists;


class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }

public class Solution {
	//��һ�ֽⷨ����������ȵĲ�ֵN��Ȼ�󳤵�����N����Ȼ������ͬʱǰ����ֱ����ȡ�
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
        	return null;
        
        ListNode curA = headA;
        ListNode curB = headB;
        int gap = 0;
        
        while(curA.next != null && curB.next != null) {
    		curA = curA.next;
    		curB = curB.next;
    	}
        if(curA.next == null) {
        	while(curB.next != null) {
        		gap --;
        		curB = curB.next;
        	}
        } else {
        	while(curA.next != null) {
        		gap ++;
        		curA = curA.next;
        	}
        }

        curA = headA;
        curB = headB;
        if(gap >= 0) {
        	for(int i = 0 ; i < gap && (curA != null); ++ i) {
        		curA = curA.next;
        	}
        } else {
        	for(int i = 0 ; i < -1 * gap && (curB != null); ++ i) {
        		curB = curB.next;
        	}
        }
        while(curA != null && curB != null) {
        	if(curA == curB)
        		return curA;
        	curA = curA.next;
        	curB = curB.next;
        }
        return null;
    }
    
    //�ڶ��ֽⷨ��һֱѭ����ֱ������ָ����ȣ��������Ҳ�ͽ������Ρ�
    public ListNode getIntersectionNodeNew(ListNode headA, ListNode headB) {
    	if(headA == null || headB == null)
        	return null;
    	ListNode curA = headA;
    	ListNode curB = headB;
    	while(curA != curB) {
    		curA = curA.next;
    		curB = curB.next;
    		if(curA == curB)
    			break;
    		if(curA == null) {
    			curA = headB;
    		}
    		if(curB == null)
    			curB = headA;   		
    	}
    	return curA;
    }
    
    public ListNode firstList;
    public ListNode secondList;
    
    public void mock() {
    	ListNode[] nodes = new ListNode[3]; 
    	for(int i = 0 ; i < nodes.length - 1; ++ i){
    		nodes[i] = new ListNode(i);
    	}
    	nodes[nodes.length - 1] = null;
    	
    	for(int i = 0 ; i < nodes.length - 1 ; ++ i)
    		nodes[i].next = nodes[i + 1];
    	firstList = nodes[0];
    	
    	secondList = nodes[1];
    }
    
    public static void main(String[] args) {
    	Solution s = new Solution();
    	s.mock();
    	
    	ListNode node = s.getIntersectionNodeNew(s.firstList, s.secondList);
    	System.out.println(node.val);
    }
}