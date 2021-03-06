import processing.core.PImage;

import java.util.List;

public class Factory {


    private static final String QUAKE_ID = "quake";
    private static final int QUAKE_ACTION_PERIOD = 1100;
    private static final int QUAKE_ANIMATION_PERIOD = 100;

    public static MinerNotFull createMinerNotFull(String id, Point position, int resourceLimit, int actionPeriod,
                                                    int animationPeriod,
                                                    List<PImage> images) {
        return new MinerNotFull(id, position, images, 0,
                resourceLimit, 0, actionPeriod, animationPeriod);

    }

    public static Mutation createMutation(String id, Point position, int actionPeriod,
                                                  int animationPeriod,
                                                  List<PImage> images) {
        return new Mutation(id, position, images, 0,
                 actionPeriod, animationPeriod);

    }



    public static MinerFull createMinerFull(String id, Point position, int resourceLimit, int actionPeriod, int animationPeriod,
                                      List<PImage> images) {
        return new MinerFull( id, position, images, 0,
                resourceLimit, actionPeriod,
                animationPeriod);
    }

    public static Obstacle createObstacle(String id, Point position, List<PImage> images) {
        return new Obstacle(id, position, images,0);
    }

    public static Ore createOre(
            String id, Point position, int actionPeriod, List<PImage> images)
    {
        return new Ore(id, position, images, 0, actionPeriod);
    }


    public static OreBlob createOreBlob(
            String id,
            int actionPeriod,
            Point position,
            int animationPeriod,
            List<PImage> images)
    {
        return new OreBlob(id, position, images, 0, actionPeriod, animationPeriod);

    }

    public static Quake createQuake(Point position, List<PImage> images) {
        return new Quake(QUAKE_ID, position, images,0, QUAKE_ACTION_PERIOD, QUAKE_ANIMATION_PERIOD);
    }

    public static Entity createVein(
            String id, Point position, int actionPeriod, List<PImage> images) {
        return new Vein(id, position, images, 0, actionPeriod);
    }

    public static Entity createBlacksmith(String id, Point position,List<PImage> images) {
        return new Blacksmith(id, position, images,0);
    }

    public static Action createActivityAction(ActiveEntity entity, WorldModel world, ImageStore imageStore) {
        return new Activity(entity, world, imageStore);}


    public static Animation createAnimationAction(AnimationEntity entity, int repeatCount) {
        return new Animation(entity, repeatCount); }

}
