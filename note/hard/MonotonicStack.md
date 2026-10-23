
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

```java
public static int largestRectangleArea1(int[] heights, String s) { 
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
