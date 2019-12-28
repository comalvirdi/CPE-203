import processing.core.PImage;
        import java.util.List;
        import java.util.Optional;
        import java.util.function.BiPredicate;
        import java.util.function.Function;
        import java.util.function.Predicate;
        import java.util.stream.Stream;

public class MinerFull extends Miners {


    public MinerFull(
            String id,
            Point position,
            List<PImage> images,
            int imageIndex,
            int resourceLimit,
            int actionPeriod,
            int animationPeriod) {
        super(id, position, images, imageIndex, resourceLimit, actionPeriod, animationPeriod);

    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler) {
//
        Optional<Entity> fullTarget = findNearest(world, this.position, Blacksmith.class);

        if (fullTarget.isPresent() && this.moveTo(world,
                fullTarget.get(), scheduler)) {
            this.transform(world, scheduler, imageStore);
        } else {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    this.actionPeriod);
        }
    }



    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        MinerNotFull miner = Factory.createMinerNotFull(id, position, resourceLimit, actionPeriod, animationPeriod, images);

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(miner);
        (miner).scheduleActions(scheduler, world, imageStore);

        return true;
    }


    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.position.adjacent(target.getPosition())) {
            return true;
        } else {
            return super.moveTo(world, target,scheduler);
        }
    }


}



