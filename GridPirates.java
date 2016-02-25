package com.example;

/**
 * Created by Milton on 19/2/2016.
 */

/** NOTE: I implemented a lot of public methods for simplicity of implementation, but we need to consider security problems
 *  which we might have from too many public methods and variables - we might need to consider changing them to protected / private
 *  once we have a more concrete idea of the actual implementation and extending
 */


public class GridPirates {
    public static void main (String[] args) {
        // creates a pirate at tile 2,2
        Pirate pirate = new Pirate(2,2);
        System.out.println("New Coordinates: " + pirate.getX() + "," + pirate.getY());
        pirate.moveLeft();
        System.out.println("New Coordinates: " + pirate.getX() + "," + pirate.getY());
        pirate.moveUp();
        System.out.println("New Coordinates: " + pirate.getX() + "," + pirate.getY());
    }


}

// objects class: encompass everything in the game except the map itself
class Objects extends Map{
    protected int x; // x-coordinate
    protected int y; // y-coordinate
    public Objects() {

    }

    // set x-coordinate
    public void setX(int x) {
        this.x = x;
    }

    // set y-coordinate
    public void setY(int y) {
        this.y = y;
    }

    // get x-coordinate
    public int getX() {
        return x;
    }

    // get y-coordinate
    public int getY() {
        return y;
    }
}

// map class - for items dealing with the game map
class Map {
    int row; // number of rows in the map
    int col; // number of cols in the map
    Objects[][] grid; // layout of the map - each contains one object

    // default initializer
    public Map() {

    }

    // initializer with map size (row * col)
    public Map(int row, int col) {
        this.row = row;
        this.col = col;
        generateMap();
    }

    // TODO: Generate random tiles
    public void generateMap() {
        // currently generates an array of an array of Floor tiles
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                grid[i][j] = new Floor();
            }
        }

        // generate walls
        for (int j=0; j<col; j++) {
            grid[0][j] = new Wall();
            grid[row-1][j] = new Wall();
        }

        for (int i=0; i<row; i++) {
            grid[i][0] = new Wall();
            grid[i][col-1] = new Wall();
        }
    }

    // sets the grid to contain a particular "Objects" type
    public void setGrid(Objects object, int x, int y) {
        grid[x][y] = object;
    }

    // gets the particular "Objects" type on the current grid
    public Objects getGrid(int x, int y) {
        return grid[x][y];
    }

    // print map
    public void mapStatus() {
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                System.out.println(grid[i][j]);
            }
        }
    }
}

// objects that are movable
class Movable extends Objects {
    // TODO: Check if the action is possible first before moving
    public void moveLeft() {
        //previousTile(x,y);
        x--;
        //nextTile(x,y);
        System.out.println("Moved left");
    }

    public void moveRight() {
        x++;
        System.out.println("Moved right");
    }

    public void moveUp() {
        //previousTile(x,y);
        y++;
        //nextTile(x,y);
        System.out.println("Moved up");
    }

    public void moveDown() {
        y--;
        System.out.println("Moved down");
    }

    public void previousTile(int x, int y) {
        setGrid(new Floor(), x, y);
    }

    // temporary filler
    public void nextTile(int x, int y) {
        setGrid(new Pirate(x,y), x, y);
    }
}

// objects that can be destroyed
class Destroyable extends Objects {

}

// objects that can be walked on
class Walkable extends Objects {

}

// main player character, movable, cannot be destroyed(?)
class Pirate extends Movable {
    public Pirate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// reefs, unmovable, destroyable
class Reef extends Destroyable {

}

// power ups, walkable, cannot be destroyed
class PowerUp extends Walkable {

}

// floor tiles
class Floor extends Walkable {

}

class Wall extends Objects {

}