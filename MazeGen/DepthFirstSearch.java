
import java.util.Random;

public class DepthFirstSearch {

    private Stack stack;
    public DepthFirstSearch() {}
    public char[][] DFS (int size, char[][] maze) {

        Random myRand = new Random();
        stack = new Stack(size);
        int x = myRand.nextInt(size);
        while (x % 2 == 0)
            x = myRand.nextInt(size);
        int y = myRand.nextInt(size);
        while (y % 2 == 0)
            y = myRand.nextInt(size);

        maze[x][y] = ' ';
        int total = (size * size) / 4;
        int visited = 1;
        int random[] = new int[4];
        int totalrand;

        while (visited < total) {
            totalrand = 0;
            if (x > 1 && maze[x - 2][y] == 'X')
                random[totalrand++] = 1;
            if (x < size - 2 && maze[x + 2][y] == 'X')
                random[totalrand++] = 2;
            if (y > 1 && maze[x][y - 2] == 'X')
                random[totalrand++] = 3;
            if (y < size - 2 && maze[x][y + 2] == 'X')
                random[totalrand++] = 4;

            if (totalrand > 0) {
                switch(random[myRand.nextInt(totalrand)]) {
                    case 1: maze[x-2][y] = maze[x-1][y] = ' ';
                            x -= 2;
                            stack.push(x * size + y);
                            visited++;
                            break;
                    case 2: maze[x+2][y] = maze[x+1][y] = ' ';
                            x += 2;
                            stack.push(x * size + y);
                            visited++;
                            break;
                    case 3: maze[x][y-2] = maze[x][y-1] = ' ';
                            y -= 2;
                            stack.push(x * size + y);
                            visited++;
                            break;
                    case 4: maze[x][y+2] = maze[x][y+1] = ' ';
                            y += 2;
                            stack.push(x * size + y);
                            visited++;
                            break;
                }
            }
            else {
                int vert = stack.pop();
                x = vert / size;
                y = vert % size;
            }
        }
        return maze;
    }

}
