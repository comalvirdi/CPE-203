import processing.core.PImage;

import java.util.List;
import java.util.Optional;



public class Dino extends Movement {

    private static final String QUAKE_KEY = "quake";
    private int mutated;

    public Dino(String id,
                Point position,
                List<PImage> images,
                int imageIndex,
                int actionPeriod,
                int animationPeriod, int mutated)
    {
        super(id, position, images, imageIndex, actionPeriod, animationPeriod);
        this.mutated = mutated;

    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> minerT = findNearest(world, this.position, MinerNotFull.class);
        long nextPeriod = this.actionPeriod;

        if (minerT.isPresent()) {

            if (this.moveTo(world, minerT.get(), scheduler)) {

                mutate(world, scheduler, imageStore, minerT.get());
                nextPeriod += this.actionPeriod;

            }
        }

        scheduler.scheduleEvent(this, Factory.createActivityAction(this, world, imageStore), nextPeriod);

    }

    public boolean mutate(WorldModel world,
                          EventScheduler scheduler, ImageStore imageStore, Entity minerT)
    {
        if (mutated < 3) {
            super.mutate(world, scheduler, imageStore, minerT);
            mutated +=1;
        }
        return false;
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


}

