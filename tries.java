import java.io.*;
import java.util.*;
import java.lang.*;

public class tries
{
	public static void main(String args[])throws IOException
	{
		trie root=new trie();
		root.addWord("tree",0);
		root.addWord("tree",0);
		root.addWord("trie",0);
		root.addWord("algo",0);
		root.addWord("assoc",0);
		root.addWord("also",0);
		root.addWord("all",0);

		System.out.println(root.countWords("alsoo",0));
		System.out.println(root.countWords("al",0));	
		System.out.println(root.countWords("tree",0));
		System.out.println(root.countWords("also",0));
		System.out.println(root.countWords("assoc",0));	

		System.out.println(root.countPrefixes("a",0));
		System.out.println(root.countPrefixes("t",0));	
		System.out.println(root.countPrefixes("al",0));
		System.out.println(root.countPrefixes("tr",0));
		System.out.println(root.countPrefixes("also",0));

	}
}

class trie
{
	int words;
	int prefixes;
	trie edges[];

	trie()
	{
		words=0;
		prefixes=0;
		edges=new trie[26];
		for(int i=0;i<26;i++)
		{
			edges[i]=null;
		}
	}

	void addWord(String word,int k)
	{
		if(k==word.length())
			words+=1;
		else
		{
			prefixes+=1;
			
			if(edges[word.charAt(k)-'a']==null)
			{
				edges[word.charAt(k)-'a']=new trie();
			}

			

			edges[word.charAt(k)-'a'].addWord(word,k+1);
			
		}
	}

	int countWords(String word,int k)
	{
		if(k==word.length())
			return words;
		
		else if(edges[word.charAt(k)-'a']==null)
			return 0;
		else
		{
			
			return edges[word.charAt(k)-'a'].countWords(word,k+1);
		}
	
	}

	int countPrefixes(String prefix,int k)
	{
		if(k==prefix.length())
			return prefixes;
		else if(edges[prefix.charAt(k)-'a']==null)
			return 0;
		else
		{
			
			return edges[prefix.charAt(k)-'a'].countPrefixes(prefix,k+1);
		}

	}


}
