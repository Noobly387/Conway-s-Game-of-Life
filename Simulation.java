

public class Simulation {

    public int width;
    public int height;
    public int[][] board;

    public Simulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[height][width];
    }

    public void printBoard () {
        for (int y = 0; y < this.height; y++) {
            String line = "|";
            for (int x = 0; x < this.width; x++) {
                if (this.board[y][x] == 0)
                    line += ".";
                else
                    line += "*";
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("\n");
    }

    public void setAlive (int x, int y) {
        this.board[y][x] = 1;
    }
    public void setDead(int x, int y) {
        board[y][x] = 0;
    }

    public int getNeighbors(int y, int x) {
        int n = 0;

        n += isAlive(y - 1, x - 1);
        n += isAlive(y - 1, x);
        n += isAlive(y - 1, x + 1);


        n += isAlive(y, x - 1);

        n += isAlive(y, x + 1);

        n += isAlive(y + 1, x - 1);
        n += isAlive(y + 1, x);
        n += isAlive(y + 1, x + 1);

        return n;
    }

    private int isAlive(int y, int x) {
        if (x < 0 || x >= width) {
            return 0;
        }
        if (y < 0 || y >= height) {
            return 0;
        }

        return board[y][x];
    }

    public void advance () {
        int[][] nextGen = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int n = getNeighbors(y, x);
                if (board[y][x] == 1) { //If current cell is living
                    if (n == 2 || n == 3)
                        nextGen[y][x] = 1;
                    else
                        nextGen[y][x] = 0;
                } else { //If current cell is dead
                    if (n == 3)
                        nextGen[y][x] = 1;
                    else
                        nextGen[y][x] = 0;
                }

            }
        }

        board = nextGen;
    }
}
