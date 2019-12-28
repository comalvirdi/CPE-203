public class GridNode{

       private Point current;
       private int g;
       private double h;
       private double f;
       private Point prior;


    public GridNode(Point current, int g, double h, Point prior) {
        this.current = current;
        this.g = g;
        this.h = h;
        this.f = g+h;
        this.prior = prior;
    }



    public int getG(){return g;}
    public double getH(){return h;}
    public double getF(){return f;}
    public Point getPrior(){return prior;}
    public Point getCurrent() { return current;}

    @Override
    public String toString() {
        return "Point: " + current + " g: " + g + " h: " + h + " f: " + f + " previous: " +prior;
    }
}
