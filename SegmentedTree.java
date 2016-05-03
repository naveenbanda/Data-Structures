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
import java.util.*;
import java.lang.*;
import java.math.*;

public class SegmentedTree {
    
    static int arr[];
    
    SegmentedTree(int a[],int n)
    {
        int x=(int)(Math.ceil(Math.log(n)/Math.log(2)));
        int max_size=2*(int)Math.pow(2,x)-1;
        arr=new int[max_size];
        
        constructSTUtil(a,0,n-1,0);
    }
    
    int constructSTUtil(int a[],int ss,int se,int si)
    {
        if(ss==se)
        {
            arr[si]=a[ss];
            return a[ss];
        }
        int mid=ss+(se-ss)/2;
        
        arr[si]=constructSTUtil(a,ss,mid,2*si+1)+constructSTUtil(a,mid+1,se,2*si+2);
        
        return arr[si];
        
    }
    
    int getSum(int n,int qs,int qe)
    {
        if(qs<0 || qe>n-1 || qs>qe)
        {
            System.out.println("Invalid Input");
            return -1;
        }
        
        return getSumUtil(0,n-1,qs,qe,0);
    }
    
    int getSumUtil(int ss,int se,int qs,int qe,int si)
    {
        if(qs<=ss && qe>=se)
            return arr[si];
        if(se<qs || ss>qe)
            return 0;
        int mid=ss+(se-ss)/2;
        return getSumUtil(ss,mid,qs,qe,2*si+1)+getSumUtil(mid+1,se,qs,qe,2*si+2);
        
    }
    
    void updateValue(int a[],int n,int i,int new_val)
    {
        if(i<0 || i>n-1)
        {
            System.out.println("Invalid Input");
            return;
        }
        
        int diff=new_val-a[i];
        
        a[i]=new_val;
        
        UpdateValueUtil(0,n-1,i,diff,0);
        
    }
    
    void UpdateValueUtil(int ss,int se,int i,int diff,int si)
    {
        if(i<ss || i>se)
            return;
        arr[si]=arr[si]+diff;
        
        if(se!=ss)
        {
            int mid=ss+(se-ss)/2;
            UpdateValueUtil(ss,mid,i,diff,2*si+1);
            UpdateValueUtil(mid+1,se,i,diff,2*si+2);
        }
    }
    
    public static void main(String args[]){
        
        Scanner sc=new Scanner(System.in);
    
         int n,q,l,r,type;
         //SegmentedTree temp=new Se
    
         n=sc.nextInt();
         q=sc.nextInt();
         
         int a[]=new int[n];
            
         for(int i=0;i<n;i++)
         {
             a[i]=sc.nextInt();
         }
         
         SegmentedTree tree=new SegmentedTree(a,n);
         
         while(q>=1)
         {
             type=sc.nextInt();
             l=sc.nextInt();
             r=sc.nextInt();
             
             if(type==0)
                 tree.updateValue(a,n,l,r);
             else
             {
                 int val=tree.getSum(n,l,r);
                 System.out.println(val);
             }    
             
             q-=1;
         }
    
    }
     
}
