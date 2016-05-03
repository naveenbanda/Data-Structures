/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author Naveen
 */

import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

public class RangeMinimumQuery {
    
    int st[];
    
    int minVal(int x,int y)
    {
        return (x<y)?x:y;
    }
    
    int getMid(int s,int e)
    {
        return s+(e-s)/2;
    }
    
    void constructST(int []arr,int n)
    {
        int x=(int)(Math.ceil(Math.log(n)/Math.log(2)));
        
        int max_size=2*(int)Math.pow(2,x)-1;
        st=new int[max_size];
        
        constructSTUtil(arr,0,n-1,0);
    }
    
    int constructSTUtil(int []arr,int start,int end,int index)
    {
        if(start==end)
        {
            st[index]=arr[start];
            return arr[start];
        }
        
        int mid=getMid(start,end);
        
        st[index]=minVal(constructSTUtil(arr,start,mid,2*index+1),constructSTUtil(arr,mid+1,end,2*index+2));
        
        return st[index];
    }
    
    int RMQ(int n,int start,int end)
    {
        if(start<0 || end>n-1 || start>end)
        {
            System.out.println("Invalid Input");
            return -1;
        }
        
        return RMQUtil(0,n-1,start,end,0);
    }
    
    int RMQUtil(int start,int end,int qstart,int qend,int index)
    {
        if(qstart<=start && qend>=end)
            return st[index];
        
        if(end<qstart || start>qend)
            return Integer.MAX_VALUE;
        
        int mid=getMid(start,end);
        
        return minVal(RMQUtil(start,mid,qstart,qend,2*index+1),RMQUtil(mid+1,end,qstart,qend,2*index+2));
        
    }
    
    public static void main(String args[])
    {
        int arr[]={1,3,2,7,9,11};
        int n=arr.length;
        
        RangeMinimumQuery tree=new RangeMinimumQuery();
        
        tree.constructST(arr,n);
        
        int qs=1;
        int qe=5;
        
        System.out.println("Minimum value in the range ["+qs+", "+qe+"] is : "+tree.RMQ(n,qs,qe));
        
    }
}
