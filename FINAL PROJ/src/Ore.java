import processing.core.PImage;

import java.util.List;
import java.util.Random;

public class Ore extends ActiveEntity{


    private static final Random rand = new Random();
    private static final String BLOB_ID_SUFFIX = " -- blob";
    private static final String BLOB_KEY = "blob";
    private static final int BLOB_PERIOD_SCALE = 4;
    private static final int BLOB_ANIMATION_MIN = 50;
    private static final int BLOB_ANIMATION_MAX = 150;


    public Ore(
               String id,
               Point position,
               List<PImage> images,
               int imageIndex,
               int actionPeriod)
    {
        super(id,position,images, imageIndex, actionPeriod);

    }



    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Point position = this.position;

        world.removeEntity( this);
        scheduler.unscheduleAllEvents( this);

        OreBlob blob = Factory.createOreBlob(this.id + BLOB_ID_SUFFIX,
                this.actionPeriod / BLOB_PERIOD_SCALE, position,
                BLOB_ANIMATION_MIN + rand.nextInt(
                        BLOB_ANIMATION_MAX
                                - BLOB_ANIMATION_MIN),
                imageStore.getImageList( BLOB_KEY));
        world.addEntity(blob);
        (blob).scheduleActions( scheduler, world, imageStore);
    }






}
