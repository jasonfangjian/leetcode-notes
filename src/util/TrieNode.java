package util;

import notes0.SubstringWithConcatenationOfAllWords;

public class TrieNode {
    TrieNode[] children = new TrieNode[26];

    // isEndOfWord is true if the node represents
    // end of a word
    boolean isEndOfWord;

    public TrieNode(){
        isEndOfWord = false;
        for (int i = 0; i < 26; i++)
            children[i] = null;
    }

    TrieNode root = new TrieNode();

    public void insert(String str){
        int level;
        int length = str.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            index = str.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isEndOfWord = true;
    }


    public boolean search(String key)
    {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }

        return (pCrawl.isEndOfWord);
    }

    public int specialSearch(String key){
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;
        for(level = 0; level < length; level++){
            index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null)
                return -1;
            pCrawl = pCrawl.children[index];
        }
        return  pCrawl.isEndOfWord ? 1 : 0;
    }
}
