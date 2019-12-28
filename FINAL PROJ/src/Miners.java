import processing.core.PImage;

import java.util.List;

public abstract class Miners extends Movement {

    int resourceLimit;


    public Miners(String id,
                  Point position,
                  List<PImage> images,
                  int imageIndex,
                  int resourceLimit,
                  int actionPeriod,
                  int animationPeriod){

        super(id, position, images, imageIndex, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;

    }



    abstract boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);


}
