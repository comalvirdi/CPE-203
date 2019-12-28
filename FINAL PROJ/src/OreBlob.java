import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class OreBlob extends Movement{

    private static final String QUAKE_KEY = "quake";



    public OreBlob(
               String id,
               Point position,
               List<PImage> images,
               int imageIndex,
               int actionPeriod,
               int animationPeriod)
    {
        super(id, position, images, imageIndex, actionPeriod, animationPeriod);

    }


    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> blobTarget = findNearest(world, this.position, Vein.class);
        long nextPeriod = this.actionPeriod;

        if (blobTarget.isPresent()) {
            Point targetPosition = blobTarget.get().getPosition();

            if (this.moveTo(world, blobTarget.get(), scheduler)) {
                Quake quake = Factory.createQuake(targetPosition, imageStore.getImageList(QUAKE_KEY));

                world.addEntity(quake);
                nextPeriod += this.actionPeriod;
                (quake).scheduleActions( scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this, Factory.createActivityAction(this, world, imageStore), nextPeriod);
    }

//    public Point nextPosition( WorldModel world, Point destPos)
//    {
//        int horiz = Integer.signum(destPos.x - this.position.x);
//        Point newPos = new Point(this.position.x + horiz, this.position.y);
//
//        Optional<Entity> occupant = world.getOccupant( newPos);
//
//        if (horiz == 0 || (occupant.isPresent() && !(occupant.get() instanceof Ore)))
//        {
//            int vert = Integer.signum(destPos.y - this.position.y);
//            newPos = new Point(this.position.x, this.position.y + vert);
//            occupant = world.getOccupant( newPos);
//
//            if (vert == 0 || (occupant.isPresent() && !(occupant.get() instanceof Ore)))
//            {
//                newPos = this.position;
//            }
//        }
//
//        return newPos;
//    }

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
