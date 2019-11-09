import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
//		String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}"; 
//		// return [2, 1, 3, 4];
		
//		String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}"; 
//		 [2, 1, 3, 4]
		
//		String s = "{{20,111},{111}}"; 
		//return [111, 20]
		
//		String s = "{{123}}"; 
		// return [123]
		
		String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}"; 
		// return 	[3, 2, 4, 1]
//				
		System.out.println(Arrays.toString(new Solution().solution(s)));
	}

}

class Solution {
    public int[] solution(String s) {
        Stack<Character> stack = new Stack<>();
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
        	char tok = s.charAt(i);
        	if (tok == '{') {
        		stack.add(tok);
        	} else if (tok == ',') { 
        		if (sb.length() > 0) {
            		nums.add(Integer.parseInt(sb.toString()));
        			sb = new StringBuilder();
        		}
        	} else if (Character.isDigit(tok)) { 
        		sb.append(tok - '0');
        	} else if (tok == '}') {
        		stack.pop();
        		if (sb.length() > 0) {
            		nums.add(Integer.parseInt(sb.toString()));
        			sb = new StringBuilder();
        		}
        		if (nums.size() > 0)
        			lists.add(nums);
        		nums = new ArrayList<>();
        	}
        }
        lists.sort(new Comparator<ArrayList<Integer>>() {

			@Override
			public int compare(ArrayList<Integer> l1, ArrayList<Integer> l2) {
				return l1.size() - l2.size();
			}
        	
        });
       
        int[] answer = new int[lists.size()];
        
        for (int aIdx = 0; aIdx < answer.length; aIdx++) {
        	HashSet<Integer> set = new HashSet<>(lists.get(aIdx));
        	for (int j = 0; j < aIdx; j++) {
        		set.remove(answer[j]);
        	}
        	answer[aIdx] = set.iterator().next();
        }
        
        return answer;
    }
}
