package com.threeDays.Utils.SortUtils;

public abstract class AbstractSort<T> {
    public  void QuickSort(T[] array,int start,int end)
    {
        int low=start;
        int high=end;
        T key=array[low];
        while(low<high)
        {
            while(low<high&&compare(array[high],key))
                high--;//当end结点值大于基准值时，向前移动，直到找到小于基准值的值
            if(low<high)
            {
                array[low]=array[high];
                low++;
            }
            while(low<high&&compare(key,array[low]))low++;
            if(low<high)
            {
                array[high]=array[low];
                high--;
            }
        }
        array[low]=key;//此时start和end 已经指向同一元素
        if(low-1>start)QuickSort(array,start,low-1);
        if(high+1<end)QuickSort(array,high+1,end);
    }

    protected abstract boolean compare(T o1, T o2);//if(o1>=o2)  return true
}
