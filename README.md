# 2019_KAKAO_Developer_intern_02

## 2019 카카오 개발자 겨울 인턴십 코딩테스트 2번

### 1. 문제설명

input으로 특정 튜플을 표현하는 집합이 담긴 문자열 `s`가 주어진다. `s`가 표현하는 튜플을 배열로 return하는 문제.

`(a1, a2, a3, ..., an)` 튜플은

> {{a1}, {a1, a2}, {a1, a2, a3}, {a1, a2, a3, a4}, ... {a1, a2, a3, a4, ..., an}}

로 표현할 수 있다.


ex) `(2,1,3,4)`인 튜플은

> {{2}, {2, 1}, {2, 1, 3}, {2, 1, 3, 4}}

> {{2}, {2, 1}, {2, 1, 3}, {2, 1, 3, 4}}

> {{2, 1, 3, 4}, {2}, {2, 1, 3}, {2, 1}}

> {{1, 2, 3}, {2, 1}, {1, 2, 4, 3}, {2}}

로 표현할 수 있다.


### 2. 풀이

input으로 들어오는 `s`을 튜플의 집합으로 파싱한다. `(`, `)`, `,`와 같은 기호와 숫자를 구분하여 진행해준다.튜플의 첫번째 값(`a1`)은 길이가 `1`인 튜플의 원소이다. 두번째 값(`a2`)는 길이가 `2`인 튜플에서 길이가 `a1`을 제외한 값이다. 이러한 방법으로 반복하여 `s`가 표현하는 튜플을 찾으려 하므로 이를위해 파싱한 튜플들을 길이에 따라 정렬한다.

정렬이후 길이순서대로 튜플을 꺼내 이전과 중복되지 않는 값을 찾아 추가해주며 튜플을 완성한다.

```java

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

```
