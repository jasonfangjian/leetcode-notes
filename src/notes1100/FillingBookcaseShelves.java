package notes1100;


/*
1105. Filling Bookcase Shelves

You are given an array books where books[i] = [thicknessi, heighti] indicates the thickness and height of the ith book.
You are also given an integer shelfWidth.

We want to place these books in order onto bookcase shelves that have a total width shelfWidth.

We choose some of the books to place on this shelf such that the sum of their thickness is less than or equal to shelfWidth,
then build another level of the shelf of the bookcase so that the total height of the bookcase has increased
by the maximum height of the books we just put down. We repeat this process until there are no more books to place.

Note that at each step of the above process, the order of the books we place is the same order as the given sequence of books.

For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf,
the third book on the second shelf, and the fourth and fifth book on the last shelf.
Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.



Example 1:


Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
Output: 6
Explanation:
The sum of the heights of the 3 shelves is 1 + 3 + 2 = 6.
Notice that book number 2 does not have to be on the first shelf.
Example 2:

Input: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
Output: 4


Constraints:

1 <= books.length <= 1000
1 <= thicknessi <= shelfWidth <= 1000
1 <= heighti <= 1000
 */
public class FillingBookcaseShelves {
    public static void main(String[] args){
        System.out.println(new FillingBookcaseShelves().minHeightShelves(new int[][]{{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}},4));
    }


    /*

     */
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int[] dp = new int[books.length+1];

        dp[0] = 0;
        dp[1] = books[0][1];

        for(int i = 2; i <= books.length; i++){
            int width = 0;
            int min_high = Integer.MAX_VALUE;
            int max_high_cur = Integer.MIN_VALUE;
            for(int j = i; width < shelfWidth && j>0; j--){
                max_high_cur  =Math.max(max_high_cur,books[j-1][1]);
                width += books[j-1][0];
                if(width > shelfWidth)
                    break;
                min_high = Math.min(min_high,dp[j-1] + max_high_cur);
            }
            dp[i] = min_high;
        }

        return dp[books.length];
    }

}
