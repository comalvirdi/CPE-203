import processing.core.PImage;
import java.util.List;
import java.util.Optional;

public class MinerNotFull extends Miners {


    private int resourceCount;

    public MinerNotFull(
            String id,
            Point position,
            List<PImage> images,
            int imageIndex,
            int resourceLimit,
            int resourceCount,
            int actionPeriod,
            int animationPeriod) {
        super(id, position, images, imageIndex, resourceLimit, actionPeriod, animationPeriod);
        this.resourceCount = resourceCount;

    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.position.adjacent(target.getPosition())) {
            this.resourceCount += 1;
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);

            return true;
        } else
            return super.moveTo(world, target, scheduler);
    }


    public boolean transform(WorldModel world,
            EventScheduler scheduler, ImageStore imageStore)
    {

        if (this.resourceCount >= this.resourceLimit) {
            MinerFull miner = Factory.createMinerFull(this.id, this.position, this.resourceLimit,
                     this.actionPeriod, this.animationPeriod, this.images);
            world.removeEntity(this);
            scheduler.unscheduleAllEvents( this);

            world.addEntity(miner);
            (miner).scheduleActions( scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {

        Optional<Entity> notFullTarget = findNearest(world, this.position, Ore.class);

        if (!notFullTarget.isPresent() || !this.moveTo( world, notFullTarget.get(), scheduler) || !this.transform( world, scheduler, imageStore))
        {
            Activity activity = new Activity(this, world, imageStore);
            scheduler.scheduleEvent( this,
                    activity,
                    this.actionPeriod);
        }
    }





}


