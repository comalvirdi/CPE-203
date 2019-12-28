import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class Mutation extends Movement{

    public Mutation(
    String id,
    Point position,
    List<PImage> images,
    int imageIndex,
    int actionPeriod,
    int animationPeriod)
    {
        super(id, position, images, imageIndex, actionPeriod, animationPeriod);

    }



    public boolean moveTo(
            WorldModel world,
            Entity target,
            EventScheduler scheduler)
    {
        if (this.position.adjacent(target.getPosition())) {
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;
        }
        else {
            return super.moveTo(world, target, scheduler);
        }
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> blobTarget = findNearest(world, this.position, OreBlob.class);
        long nextPeriod = this.actionPeriod;

        if (blobTarget.isPresent()) {
            Point targetPosition = blobTarget.get().getPosition();

            if (this.moveTo(world, blobTarget.get(), scheduler)) {

                scheduler.unscheduleAllEvents(blobTarget.get());
                world.removeEntity(blobTarget.get());
                nextPeriod += this.actionPeriod;

            }
        }

        scheduler.scheduleEvent(this, Factory.createActivityAction(this, world, imageStore), nextPeriod);
    }

}





