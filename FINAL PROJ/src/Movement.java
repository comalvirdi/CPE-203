import processing.core.PImage;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class Movement extends AnimationEntity{

    public PathingStrategy newPath = new AStarPathingStrategy();

    public Movement(String id,
                    Point position,
                    List<PImage> images,
                    int imageIndex,
                    int actionPeriod,
                    int animationPeriod)
    {
        super(id, position, images, imageIndex, actionPeriod, animationPeriod);
    }



    public Optional<Entity> nearestEntity(
            List<Entity> entities, Point pos)
    {
        if (entities.isEmpty()) {
            return Optional.empty();
        }
        else {
            Entity nearest = entities.get(0);
            double nearestDistance = nearest.getPosition().distanceSquared(pos);

            for (Entity other : entities) {
                double otherDistance = other.getPosition().distanceSquared(pos);

                if (otherDistance < nearestDistance) {
                    nearest = other;
                    nearestDistance = otherDistance;
                }
            }

            return Optional.of(nearest);
        }
    }


    public Optional<Entity> findNearest(WorldModel world, Point pos, Class kind)
    {
        List<Entity> ofType = new LinkedList<>();
        for (Entity entity : world.getEntities()) {
            if (entity.getClass() == kind) {
                ofType.add(entity);
            }
        }

        return nearestEntity(ofType, pos);
    }


    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {

            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.position.equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant( nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
    }
    public Point nextPosition(WorldModel world, Point destPos)
    {
        List<Point> nextPoss;
        nextPoss = newPath.computePath(getPosition(), destPos, canPassThrough(world), withinReach() , PathingStrategy.CARDINAL_NEIGHBORS);
        if (nextPoss.size() == 0)
            return getPosition();
        return nextPoss.get(0);
    }

    public Predicate<Point> canPassThrough(WorldModel world) {
        return point -> world.withinBounds(point) && !world.isOccupied(point);
    }

    public BiPredicate<Point, Point> withinReach(){
        return (p1, p2) -> p1.adjacent(p2);
    }

    public boolean mutate(WorldModel world,
                          EventScheduler scheduler, ImageStore imageStore, Entity ent)
    {

        Mutation mutant = Factory.createMutation("zom", this.position,
                this.actionPeriod / 2 , this.animationPeriod / 2 , imageStore.getImageList("zom"));
        world.removeEntity(ent);
        scheduler.unscheduleAllEvents(ent);

        world.addEntity(mutant);
        (mutant).scheduleActions(scheduler, world, imageStore);


        return true;

    }
}


