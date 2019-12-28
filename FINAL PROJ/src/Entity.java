import processing.core.PImage;

import java.util.List;

public abstract class Entity{

    String id;
    Point position;
    List<PImage> images;
    int imageIndex;

    public Entity(String id, Point position, List<PImage> images, int imageIndex){

        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = imageIndex;
    }

    public void setPosition(Point position) { this.position = position;}
    public Point getPosition() { return position; }
    public PImage getCurrentImage() { return this.images.get((0));}
    public List<PImage> getImages() { return images; }
}



