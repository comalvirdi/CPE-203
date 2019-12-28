import processing.core.PImage;

import java.util.List;

public class Quake extends AnimationEntity{

    private static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;

    public Quake(String id,Point position,List<PImage> images, int imageIndex, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, imageIndex, actionPeriod, animationPeriod);
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity( this);
    }

    public void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Factory.createActivityAction( this, world, imageStore),
                this.actionPeriod);

        scheduler.scheduleEvent( this, Factory.createAnimationAction(this,
                QUAKE_ANIMATION_REPEAT_COUNT),
                this.getAnimationPeriod());
    }

}
