import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Random; 
import java.util.Stack; 
import controlP5.*; 
import javax.swing.JFileChooser; 

import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class MazeGenPde extends PApplet {






Random randgen;

int myX, myY, lastX, lastY;

int road;
int wall;
int exit;
int start;
int path;

int maxX;
int maxY;

int exitX;
int exitY;
int visited;
int size;

PImage board;
PImage solution;

Stack<Position> frontier;

ControlP5 cp5;

public void setup() {
  size(700, 600);
  background(0);
  PFont font = createFont("arial", 20);
  cp5 = new ControlP5(this);

  cp5.addTextfield("width")
    .setPosition(60, 30)
    .setSize(100, 50)
    .setFont(font)
    .setFocus(true)
    .setColor(color(255, 0, 0));

  cp5.addTextfield("height")
    .setPosition(220, 30)
    .setSize(100, 50)
    .setFont(font)
    .setColor(color(255, 0, 0));

  cp5.addButton("Generate")
    .setPosition(380, 30)
    .setSize(100, 50);

  cp5.addButton("Solve")
    .setPosition(540, 30)
    .setSize(100, 50);

  cp5.addButton("Save")
    .setPosition(220, 100)
    .setSize(100, 50);

  cp5.addButton("Exit")
    .setPosition(380, 100)
    .setSize(100, 50);

  board = pictureText(650, 400, "Press generate to generate a maze!");
  image(board, 25, 175);
}

public void draw() {

}

public PImage pictureText(int width, int height, String text) {
    PGraphics g = createGraphics(width, height);
    g.beginDraw();
    g.textAlign(CENTER);
    g.text(text, width / 2, height / 2);
    g.endDraw();
    return g;
}

public void Generate() {
    int X, Y;
    try {
        X = Integer.parseInt(cp5.get(Textfield.class, "width").getText());
        Y = Integer.parseInt(cp5.get(Textfield.class, "height").getText());
    } catch (Exception e) {
        PImage cleaner = createImage(650, 400, RGB);
        cleaner.loadPixels();
        for (int i = 0 ; i < cleaner.pixels.length ; i++) {
            cleaner.pixels[i] = color(0);
        }
        image(cleaner, 25, 175);
        board = pictureText(650, 400, "Please enter valid width and height!");
        image(board, 25, 175);
        return;
    }

    mazeGen(X, Y);
}

public void Exit() {
    exit();
}

public void Save() {
  JFileChooser chooser = new JFileChooser();
  chooser.setFileFilter(chooser.getAcceptAllFileFilter());
  int retVal = chooser.showOpenDialog(null);
  if (retVal == JFileChooser.APPROVE_OPTION) {
    //println("You chose: " + chooser.getSelectedFile().getName());
    board.save(chooser.getSelectedFile().getAbsolutePath());
  }

}

public void Solve() {
//    try {
        image(solution, 25, 175);
//    }
//    catch (Exception e) {
//        PImage cleaner = createImage(650, 400, RGB);
//        cleaner.loadPixels();
//        for (int i = 0 ; i < cleaner.pixels.length ; i++) {
//            cleaner.pixels[i] = color(0);
//        }
//        image(cleaner, 25, 175);
//        board = pictureText(650, 400, "Please generate a maze first!");
//        image(board, 25, 175);
//        return;
//
//    }
}

public void mazeGen(int X, int Y) {
  randgen = new Random();
  int road = color(255);
  int wall = color(0);
  int exit = color(255, 0, 0);
  int start = color(0, 0, 255);
  int path = color(0, 255, 255);
  int maxX = X;
  int maxY = Y;
  frontier = new Stack<Position>();

  board = createImage(maxX, maxY, RGB);
  solution = createImage(maxX, maxY, RGB);
  board.loadPixels();
  solution.loadPixels();
  for (int y = 0; y < maxY; y++) {
    for (int x = 0; x < maxX; x++) {
      board.pixels[x + y * board.width] = wall;
    }
  }
  image(board, 0, 0);

  size = (maxX * maxY) / 4;

  exitX = randgen.nextInt(maxX / 2) * 2 + 1;
  exitY = randgen.nextInt(maxY / 2) * 2 + 1;

  board.pixels[exitX + exitY * board.width] = exit;
  image(board, 0, 0);

  frontier.push(new Position(exitX, exitY, null));
  lastX = exitX;
  lastY = exitY;

  visited = 1;

  Position current = null;

  while (visited < size) {
    current = frontier.pop();
    myX = current.getX();
    myY = current.getY();

    if (current.getPrevious() != null) {
      lastX = current.getPrevious().getX();
      lastY = current.getPrevious().getY();
    }

    board.pixels[myX + myY * board.width] = board.pixels[(lastX + (myX - lastX) / 2) + (lastY + (myY - lastY) / 2) * board.width] = road;
    pushAllNeighbor(myX, myY, current);
    //println(frontier.size());

    visited++;
  }
  
  board.pixels[exitX + exitY * board.width] = exit;
  board.pixels[myX + myY * board.width] = start;

  for (int i = 0 ; i < board.pixels.length ; i++) {
      solution.pixels[i] = board.pixels[i];
  }

  for (Position solutionP = current.getPrevious() ; solutionP != null ; solutionP = solutionP.getPrevious()) {
      solution.pixels[solutionP.getX() + solutionP.getY() * solution.width] = path;
  }

  solution.pixels[exitX + exitY * solution.width] = exit;

  board.updatePixels();
  solution.updatePixels();
  solution.resize(650, 400);
  board.resize(650, 400);
  image(board, 25, 175);
}

public void pushAllNeighbor(int X, int Y, Position current) {
  Position[] neighborList = new Position[4];
  if (board.height - Y > 2 && (int)brightness(board.pixels[X + (Y + 2) * board.width]) == wall) {
    neighborList[0] = new Position(X, Y + 2, current);
  }

  if (Y > 2 && (int)brightness(board.pixels[X + (Y - 2) * board.width]) == wall) {
    neighborList[1] = new Position(X, Y - 2, current);
  }

  if (board.width - X > 2 && (int)brightness(board.pixels[X + 2 + Y * board.width]) == wall) {
    neighborList[2] = new Position(X + 2, Y, current);
  }

  if (X > 2 && (int)brightness(board.pixels[Y * board.width + X - 2]) == wall) {
    neighborList[3] = new Position(X - 2, Y, current);
  }

  shuffleArray(neighborList);

  int a = 2;
  for (int i = 0; i < a && a < 4; i++) {
    if (neighborList[i] != null) {
      frontier.push(neighborList[i]);
    } else {
      a++;
    }
  }
}

public void shuffleArray(Position[] arr) {
  for (int i = arr.length - 1; i > 0; i--) {
    int index = randgen.nextInt(i + 1);
    Position tmp = arr[index];
    arr[index] = arr[i];

    arr[i] = tmp;
  }
}

class Position {
  int x;
  int y;
  Position previous;

  Position(int myX, int myY, Position myPrev) {
    x = myX;
    y = myY;
    previous = myPrev;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Position getPrevious() {
    return previous;
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "MazeGenPde" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
