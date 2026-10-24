
# Q3. Largest Rectangle in Histogram

### LIFO , Stack , _O(n)_
> Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
> return the area of the largest rectangle in the histogram.

```shell
# TestCase
input :
  [2,1,5,6,2,3]
Output :
  10
```

> [!NOTE]
> - 알고리즘 구현 정리 :
> 아래 두 가지 구현 기본 알고리즘과 객체 구현 알고리즘의 메모리,속도의 차이는 미미함

## 기본 알고리즘

```java
public static int largestRectangleArea1(int[] heights) { 
    int n = heights.length;                                        
    int[] stack = new int[n];                                      
    int top = -1;                                                  
    int maxArea = 0;                                               
    for (int i = 0; i <= n; i++) {                                 
        int currHeight = (i == n) ? 0 : heights[i];                
        while (top >= 0 && heights[stack[top]] > currHeight) {     
            int height = heights[stack[top--]];                    
            int left = (top >= 0) ? stack[top] : -1;               
            int width = i - left - 1;                              
            int computeArea=height * width;                        
            maxArea = Math.max(maxArea, computeArea);              
        }                                                          
        if (i < n) {                                               
            top++;                                                 
            stack[top] = i;                                        
        }                                                          
    }                                                              
    return maxArea;                                                
}                                                                  
```

## 객체 구현 알고리즘

```java

    public static int largestRectangleArea(int[] data) {
        final int length = data.length;
        final StackH stackH = new StackH(data);
        int maxArea = 0;
        for (int i = 0; i <= length; i++) {
            while (!stackH.isEmpty() && stackH.isDecrease(i)) {
                maxArea = Math.max(maxArea, stackH.getArea(i));
            }
            stackH.push(i);
        }
        return maxArea;
    }


   static class StackH {
        private int[] stack;
        private final int[] data;
        private int top = -1;
        private final int length;

        public StackH(int[] data) {
            this.length = data.length ;
            this.data = data;
            this.stack = new int[length+1];
        }

        public boolean isDecrease(int i) {
            return dataPeek() > ((i == length) ? 0 : data[i]);
        }

        public int getArea(int i) {
            return pop() * getWid…(int i) {
            if (top == -1) {
                return i;
            }
            return i - peek() - 1;
        }

        public int size() {
            return top;
        }

        public int dataPeek() {
            return data[stack[top]];
        }

        public int peek() {
            return stack[top];
        }

        public int pop() {
            return data[stack[top--]];
        }

        public void push(int i) {
            this.stack[++top] = i;
        }

    }

```
