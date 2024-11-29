public class Space {

    int dimension;
    char[][] Maze;
    Rat Rat;
    Space(int dimension){
        this.dimension = dimension;
        this.Maze = Tools.MatrixGenerator(dimension);
        Rat = new Rat();
    }
}
