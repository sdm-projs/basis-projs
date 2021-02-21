

#ifndef MAZE_H
#define MAZE_H
#include <fstream>
#include <vector>

struct Coordinate
{
    double x, y;
    Coordinate(double px, double py) : x(px), y(py){}
};

class Maze
{
    public:
        Maze(std::ifstream&);

        void Print();

        Coordinate GetStartPt();

        void FindExit(int, int, bool&);

    private:

        char maze[10][10];

        int MaxRows;

        int MaxCols;

        std::vector<Coordinate> coords;
};

#endif // MAZE_H
