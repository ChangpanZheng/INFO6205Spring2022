public class Question2 {
    public static void main(String[] args) {

    }

    public int numIslands(char[][] grid) {
        //TODO: Write your code here
        int row = grid.length, col = grid[0].length;

        if (row == 0 || col == 0) {
            return 0;
        }

        int count = 0;

        // traverse the grid to find every single one '1'
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    dfs(grid, r, c, row, col);
                }
            }
        }

        return count;
    }


    public void dfs(char[][]grid, int r, int c, int row, int col) {
        if (r < 0 || r >= row || c < 0 || c >= col || grid[r][c] == '0') {
            return;
        }

        // to change the pointer to '0'
        grid[r][c] = '0';

        dfs(grid, r - 1, c, row, col);
        dfs(grid, r + 1, c, row, col);
        dfs(grid, r, c - 1, row, col);
        dfs(grid, r, c + 1, row, col);
    }

}
