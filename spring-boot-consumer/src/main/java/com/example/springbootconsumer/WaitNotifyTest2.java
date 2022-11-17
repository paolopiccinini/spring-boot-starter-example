package com.example.springbootconsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

class Message2 {
    private String message;
    private Boolean empty = true;
    public String read() {
    	synchronized (empty) {
    		while(empty) {
    			
    			try {
    				
    				empty.wait();
    			} catch (InterruptedException e) {
    				
    			}
    		}
			
		}
        empty = true;
        empty.notifyAll();
        return message;
    }
    public void write(String message) {
    	synchronized (empty) {
    		while(!empty) {
    			try {
    				
    				empty.wait();
    			} catch (InterruptedException e) {
    				
    			}
    		}
    	}
        empty = false;
        this.message = message;
        empty.notifyAll();
    }
}


class Reader implements Runnable {
    private Message2 message;
    public Reader(Message2 message) {
        this.message = message;
    }
    public void run() {
        
        
        for(String latestMessage = message.read(); latestMessage.equals("Finished");) {    
            
            System.out.println(latestMessage);
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("hiijhgsd");
            }
            
            
        }
        
    }
}


class Writer implements Runnable {
    private Message2 message;
    public Writer(Message2 message) {
        this.message = message;
    }
 
    public void run() {
        System.out.println("hiiii");
        String messages[] = {
            "Humpty Dumpty sat on a wall",
            "Humpty Dumpty had a great fall",
            "All the king's horses and all the king's men",
            "Couldn't put Humpty together again",
        };

        for(int i=0; i<4; i ++) {
            
            message.write(messages[i]);
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("hiijhgsd");
            }
        }
        message.write("Finished");
        
    }
}
    
public class WaitNotifyTest2 {
    public static void main(String[] args) {
        Message2 message = new Message2();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
        	if(map.get(target - nums[i]) != null) {
        		result[0] = i;
        		result[1] = map.get(target - nums[i]);
        	} else {
        		map.put(nums[i], i);
        	}
        }
        return result;
    }
    
    public boolean isPalindrome(int x) {
        int xReversed = 0;
        int tmp = x;
        while(tmp > 0) {
        	xReversed = (xReversed * 10) + (tmp % 10);
        	tmp /= 10;
        }
        return x == xReversed;
    }
    
    public String longestCommonPrefix(String[] strs) {
    	StringBuilder builder = new StringBuilder();
    	builder.append("");
    	boolean flag = true;
    	String first = strs[0];
    	for(int i = 0; i < first.length() && flag; i++) {
    		for(int j = 1; j < strs.length; j++) {
    			if(i == strs[j].length()) {
    				flag = false;
    			} else if(first.charAt(i) != strs[j].charAt(i)) {
    				flag = false;
    			}
    		}
    		if(flag) {
    			builder.append(first.charAt(i));
    		}
    	}
    	return builder.toString();
    }
    
    public boolean isValid(String s) {
    	Map<Character, Character> map = new HashMap<>();
    	map.put('(', ')');
    	map.put('{', '}');
    	map.put('[', ']');
    	Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if(map.get(c) != null) {
        		stack.push(c);
        	} else if(stack.isEmpty()) {
        		return false;
        	} else if(map.get(stack.pop()) != c) {
        		return false;
        	}
        }
        return stack.isEmpty();
    }
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static class ListNode {
    	int val;
    	ListNode next;
    	ListNode() {}
    	ListNode(int val) { this.val = val; }
    	ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    	ListNode list1Tmp = list1;
    	ListNode list2Tmp = list2;
    	ListNode current = null;
    	ListNode head = null;
    	while(list1Tmp != null || list2Tmp != null) {
    		if(list1Tmp != null && list2Tmp != null) {
    			if(list1Tmp.val <= list2Tmp.val) {
    				current = new ListNode(list1Tmp.val);
    				list1Tmp = list1Tmp.next;
    			} else {
    				current = new ListNode(list2Tmp.val);
    				list2Tmp = list2Tmp.next;
    			}
    		} else if(list1Tmp != null) {
    			current = new ListNode(list1Tmp.val);
				list1Tmp = list1Tmp.next;
    		} else if(list2Tmp != null) {
    			current = new ListNode(list2Tmp.val);
    			list2Tmp = list2Tmp.next;
    		}
    		if(head == null) {
    			head = current;
    		} else {
    			ListNode tmp = head;
    			while(tmp.next != null) {
    				tmp = tmp.next;
    			}
    			tmp.next = current;
    		}
    	}
        return head;
    }
    
    public static int removeDuplicates(int[] nums) {
        int k = nums.length;
        int i = 0;
        while(i < k - 1) {
        	if(nums[i] == nums[i + 1]) {
        		for(int j = i; j < k - 1; j++) {
        			nums[j] = nums[j + 1];
        		}
        		k--;
        	} else {
        		i++;
        	}
        }
        return k;
    }
    
    public int removeElement(int[] nums, int val) {
    	int k = nums.length;
    	int i = 0;
    	while(i < k) {
    		if(nums[i] == val) {
    			nums[i] = nums[k - 1];
    			k--;
    		} else {
    			i++;
    		}
    	}
    	return k;
    }
    
    public int searchInsert(int[] nums, int target) {
    	int result = Arrays.binarySearch(nums, target);
        return result >= 0 ? result : ~result;
    }
    
    public int lengthOfLastWord(String s) {
    	int lenght = 0;
    	int start = s.length() - 1;
    	while(s.charAt(start) == ' ') {
            start--;
        }
    	while(start >= 0 && s.charAt(start--) != ' ') {
    		lenght++;
    	}
    	return lenght;
    }
    
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        int[] digitsCopy = digits.clone();
        while(i >= 0 && digitsCopy[i] == 9) {
        	digitsCopy[i] = 0;
        	i--;
        }
        if(i == -1) {
        	int[] result = new int[digitsCopy.length + 1];
        	result[0] = 1;
        	int j = 1;
        	for(int k = 0; k < digitsCopy.length; k++) {
        		result[j++] = digitsCopy[k];
        	}
        	return result;
        } else {
        	digitsCopy[i] += 1;
        	return digitsCopy;
        }
    }
    
    public static String addBinary(String a, String b) {
    	StringBuilder builder = new StringBuilder();
    	boolean rest = false;
    	String start = null, end = null;
        if(a.length() >= b.length()) {
        	start = a; 
        	end = b;
        } else {
        	start = b; 
        	end = a;
        }
        int startIndex = start.length() - 1, endIndex = end.length() - 1;
        while(endIndex >= 0) {
    		if(start.charAt(startIndex) == end.charAt(endIndex) && end.charAt(endIndex) == '1' && rest) {
    			builder.append("1");
    		} else if(start.charAt(startIndex) == end.charAt(endIndex) && end.charAt(endIndex) == '1') {
    			builder.append("0");
    			rest = true;
    		} else if((start.charAt(startIndex) == '1' || end.charAt(endIndex) == '1') && rest) {
    			builder.append("0");
    		} else if((start.charAt(startIndex) == '1' || end.charAt(endIndex) == '1')) {
    			builder.append("1");
    		} else if((start.charAt(startIndex) == '0' && end.charAt(endIndex) == '0') && rest) {
    			builder.append("1");
    			rest = false;
    		} else if((start.charAt(startIndex) == '0' && end.charAt(endIndex) == '0')) {
    			builder.append("0");
    		}
    		startIndex--;
    		endIndex--;
    	}
        while(startIndex >= 0) {
    		if(start.charAt(startIndex) == '1' && rest) {
    			builder.append("0");
    		} else if(start.charAt(startIndex) == '0' && rest) {
    			builder.append("1");
    			rest = false;
    		} else {
    			builder.append(start.charAt(startIndex));
    		}
    		startIndex--;
    	}
    	if(rest) {
    		builder.append("1");
    	}
        return builder.reverse().toString();
    }
    
    public static int mySqrt(int x) {
    	if (x == 0 || x == 1)
            return x;
        long i = 1;
 
        while (i * i <= x) {
            i++;
        }
        return (int)i - 1;
    }
    
    public int climbStairs(int n) {
    	int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
          dp[i] = -1;
        }
        fib(n, dp);
        return dp[n];
    }
    
    public int fib(int n, int dp[]) {
      if (n <= 1)
        return dp[n] = 1;
   
      if (dp[n] != -1) {
        return dp[n];
      }
      dp[n] = fib(n - 1, dp) + fib(n - 2, dp);
      return dp[n];
    }
    
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode tmp = head;
        while(tmp != null && tmp.next != null) {
        	if(tmp.val == tmp.next.val) {
        		tmp.next = tmp.next.next; 
        	} else {
        		tmp = tmp.next;
        	}
        }
        return head;
    }
    
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] minElements = null, maxElements = null;
        int min = 0, max = 0;
        int[] tmp = new int[m + n];
        if(m >= n) {
        	minElements = nums2.clone();
        	maxElements = nums1.clone();
        	min = n;
        	max = m;
        } else {
        	minElements = nums1.clone();
        	maxElements = nums2.clone();
        	min = m;
        	max = n;
        }
        int i = 0, j = 0, k = 0;
        while(i < min && j < max) {
        	if(minElements[i] > maxElements[j]) {
        		tmp[k++] = maxElements[j++];
        	} else {
        		tmp[k++] = minElements[i++];
        	}
        }
        for(int y = i; y < min; y++) {
        	tmp[k++] = minElements[y];
        }
        for(int y = j; y < max; y++) {
        	tmp[k++] = maxElements[y];
        }
        for(int y = 0; y < m + n; y++) {
        	nums1[y] = tmp[y];
        }
    }
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }
    
    public void inorder(TreeNode node, List<Integer> list) {
    	if(node == null) {
    		return;
    	}
    	inorder(node.left, list);
    	list.add(node.val);
    	inorder(node.right, list);
    }
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if((p == null && q != null) || (p != null && q == null)) {
        	return false;
        }
        if(p == null && q == null) {
        	return true;
        }
        if(p.val != q.val) {
        	return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }
    
    public boolean isSymmetric(TreeNode node1, TreeNode node2) {
    	if(node1 == null && node2 == null) {
    		return true;
    	}
    	if(node1 != null && node2 != null && node1.val == node2.val) {
    		return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
    	}
    	return false;
    }
    
    public int maxDepth(TreeNode root) {
        if(root == null) {
        	return 0;
        }
        int left = maxDepth(root.left) + 1;
        int right = maxDepth(root.right) + 1;
        return left >= right ? left : right;
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
    	return sortedArrayToBST(nums, 0, nums.length);
    }
    
    public TreeNode sortedArrayToBST(int[] nums, int low, int high) {
    	if(low >= high) {
    		return null;
    	}
    	int mid = (low + high) / 2;
    	TreeNode current = new TreeNode(nums[mid]);
    	current.left = sortedArrayToBST(nums, low, mid);
    	current.right = sortedArrayToBST(nums, mid + 1, high);
    	return current;
    }
    
    public boolean isBalanced(TreeNode root) {
    	int lh; /* for height of left subtree */
        int rh; /* for height of right subtree */
 
        /* If tree is empty then return true */
        if (root == null)
            return true;
 
        /* Get the height of left and right sub trees */
        lh = height(root.left);
        rh = height(root.right);
 
        if (Math.abs(lh - rh) <= 1 && isBalanced(root.left)
            && isBalanced(root.right))
            return true;
 
        /* If we reach here then tree is not height-balanced
         */
        return false;
    }
    
    int height(TreeNode node) {
        /* base case tree is empty */
        if (node == null)
            return 0;
 
        /* If tree is not empty then height = 1 + max of
         left height and right heights */
        return 1
            + Math.max(height(node.left),
                       height(node.right));
    }
    
    public int minDepth(TreeNode root) {
    	if (root == null)
            return 0;
 
        // Base case : Leaf Node. This accounts for height = 1.
        if (root.left == null && root.right == null)
            return 1;
 
        // If left subtree is NULL, recur for right subtree
        if (root.left == null)
            return minDepth(root.right) + 1;
 
        // If right subtree is NULL, recur for left subtree
        if (root.right == null)
            return minDepth(root.left) + 1;
 
        return Math.min(minDepth(root.left),
        		minDepth(root.right)) + 1;
    }
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
    	if (root == null)
            return false;
        boolean ans = false;
        int subSum = targetSum - root.val;
        if (subSum == 0 && root.left == null
            && root.right == null)
            return true;
        if (root.left != null) {
            ans = ans || hasPathSum(root.left, subSum);
        }
        if (root.right != null) {
            ans = ans || hasPathSum(root.right, subSum);
        }
        return ans;
    }
    
    public int maxProfit(int[] prices) {
    	int maxProfit = 0;
    	 
        // The loop starts from 1
        // as its comparing with the previous
        for (int i = 1; i < prices.length; i++)
            if (prices[i] > prices[i - 1] && maxProfit < prices[i] - prices[i - 1])
                maxProfit = prices[i] - prices[i - 1];
        return maxProfit;
    }

    public static void main(String[] args) {
    	ListNode one = new ListNode(1);
    }
    
    public Optional<Character> findCandidateWithMaxVotes(Character[] candidates, int[] times, int timeLimit) {
    	Character cadidateWithMaxVotes = null;
    	int max = 0, count = 0;
    	Map<Character, Integer> numberOvVotesForCandidate = new HashMap<>();
    	for(int i = 0; i< times.length; i++) {
    		if(times[i] <= timeLimit) {
	    		count = numberOvVotesForCandidate.merge(candidates[i], 1, Integer::sum);
	    		if(max < count) {
	    			max = count;
	    			cadidateWithMaxVotes = candidates[i];
	    		}
    		}
    	}
    	return Optional.ofNullable(cadidateWithMaxVotes);
    }
    
    public String interesction(String s1, String s2) {
    	return intersection(s1, s2, 0);
    }
    
    public String intersection(String s1, String s2, int s1Index) {
    	if(s1.length() == s1Index) {
    		return "";
    	}
    	char result = intersection(s1.charAt(s1Index), s2, 0);
    	return intersection(s1, s2, s1Index + 1) + result;
    }
    
    public char intersection(char c, String s2, int s2Index) {
    	if(s2Index == s2.length()) {
    		return 0;
    	}
    	if(c == s2.charAt(s2Index)) {
    		return c;
    	}
    	return intersection(c, s2, s2Index + 1);
    }
    
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        ListNode headATmp = headA, headBTmp = headB;
//        Set<ListNode> set = new HashSet<>();
//        while(headATmp != null) {
//            set.add(headATmp);
//            headATmp = headATmp.next;
//        }
//        while(headBTmp != null) {
//            if(set.contains(headBTmp)) {
//                return headBTmp;
//            }
//            headBTmp = headBTmp.next;
//        }
//        return null;
//    }
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	ListNode headATmp = headA, headBTmp = headB;
    	int sizeA = 0, sizeB = 0;
    	while(headATmp != null) {
    		sizeA++;
    		headATmp = headATmp.next;
    	}
    	while(headBTmp != null) {
    		sizeB++;
    		headBTmp = headBTmp.next;
    	}
    	headATmp = headA;
    	headBTmp = headB;
    	if(sizeA > sizeB) {
    		while(sizeA-- > sizeB) {
    			headATmp = headATmp.next;
    		}
    	} else if(sizeA < sizeB) {
    		while(sizeB-- > sizeA) {
    			headBTmp = headBTmp.next;
    		}
    	}
    	while(headATmp != null && headBTmp != null) {
    		if(headATmp == headBTmp) {
                return headATmp;
            } else if (headATmp.next == headBTmp.next && headATmp.next != null) {
    			return headATmp.next;
    		}
    		headATmp = headATmp.next;
    		headBTmp = headBTmp.next;
    	}
    	return null;
    }
    
}
