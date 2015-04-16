#include<cstdlib>;
#include<stack>;
#include<iostream>;
using namespace std;

// Global variables for wall, road, etc

char wall = '#';
char road = ' ';
char exitLoc = '$' 
char start = '+'


// Position struct, uses to store datapoints and enable backtracking
struct Position {
    int x;
    int y;
    Position previous;

    Position(int newX, int newY, Position newPrevious) {
        x = newX;
        y = newY;
        previous = newPrevious;
    }
};

int arrSize(int[] arr) {
    return (sizeof(arr) / sizeof(arr[0]));
}

void shuffleArr(Position[] neighbors) {
    for (int i = arrSize(neighbor) - 1 ; i > 0 ; i--) {
        int index = rand() % (i + 1);
        Position tmp = neighbor[index];
        neighbor[index] = neighbor[i];
        neighbor[i] = tmp;
    }
}

void pushNeighbors(char[][] board, stack frontier, Position current) {
    Position neighborList[4] = {NULL, NULL, NULL, NULL};

    if (sizeArr(board) - 1 - current.y) > 2 && (board[current.y + 2][x] == wall) {
        neighborList[0] = Position(current.x, current.y + 2, current);
    }

    if (current.y) > 1 && (board[current.y - 2][x] == wall) {
        neighborList[1] = Position(current.x, current.y - 2, current);
    }

    if (sizeArr(board[0]) - 1 - current.x) > 2 && (board[current.y][x + 2] == wall) {
        neighborList[2] = Position(current.x + 2, current.y, current);
    }

    if (current.x) > 1 && (board[current.y][x - 2] == wall) {
        neighborList[3] = Position(current.x - 2, current.y, current);
    }

    shuffleArr(neighborList);

    for (int i = 0 ; i < 4 ; i++) {
        if (neighborList[i] != NULL) {
            frontier.push(neighborList[i]);
        }
    }
}

void generateMaze(char[][] board, stack frontier, int total, int visited) {
    // Recursive base case
    if visited >= total {
        return;
    }
    
    // Reduction step
    Position current = frontier.pop();
    board[current.y][current.x] = board[current.previous.y][current.previous.x] = road;
    visited++;

    pushNeighbors(board, frontier, current);

    // Recursive Call
    generate(board, frontier, total, visited);
}


int main(int argc, char *argv[]) {
    
    if (argc != 3) { // Should feed 2 arguments alonside the function
        cout << "USAGE: " << argv[0] << " <x-len> <y-len>";
        return 1;
    }

    stack<Position> frontier;

    // Initialize the board with walls
    char board[atoi(argv[1])][atoi(argv[2])];
    for (int i = 0 ; i < arrSize(board) ; i++) {
        for (int j = 0 ; j < arrSize(board[0]) ; j++) {
            board[i][j] = wall;
        }
    }

    // Determines random exitLoc
    Position exitPt;
    do {
        exitPt.x = rand() % arrSize(board[0]);
    } while (exitPt.x % 2 != 0);

    do {
        exitPt.y = rand() % arrSize(board);
    } while (exitPt.y % 2 != 0);

    board[exitPt.y][exitPt.x] = exitLoc;
    frontier.push(exitPt);

    int total = arrSize(board) * arrSize(board[0]) / 4;
    int visited = 1;

    generateMaze(board, frontier, total, visited);

    do {
        int x = rand() % arrSize(board[0]);
        int y = rand() % arrSize(board);
    } while (board[y][x] != road);

    board[y][x] = start;

    cout << "\033\143";

    for (int i = 0 ; i < arrSize(board) ; i++) {
        for (int j = 0 ; j < arrSize(board[0]) ; i++) {
            cout << board[i][j];
        }
        cout << "\n";
    }

    return 0;
}
