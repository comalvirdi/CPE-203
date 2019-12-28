import processing.core.PImage;

import java.util.*;

public final class WorldModel
{
    public int numRows;
    public int numCols;
    private Background background[][];
    private Entity occupancy[][];
    public Set<Entity> entities;

    private static final int PROPERTY_KEY = 0;
    private static final String BGND_KEY = "background";
    private static final String MINER_KEY = "miner";
    private static final String OBSTACLE_KEY = "obstacle";
    private static final String ORE_KEY = "ore";
    private static final String SMITH_KEY = "blacksmith";
    private static final String VEIN_KEY = "vein";
    private static final String EBGND_KEY = "eggBackground";
    private static final String DINO_KEY = "dino";
    private static final String MUT_KEY = "zom";



    private static final int ORE_REACH = 1;




    public WorldModel(int numRows, int numCols, Background defaultBackground) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.background = new Background[numRows][numCols];
        this.occupancy = new Entity[numRows][numCols];
        this.entities = new HashSet<>();

        for (int row = 0; row < numRows; row++) {
            Arrays.fill(this.background[row], defaultBackground);
        }
    }

    public void tryAddEntity(Entity entity) {
        if (this.isOccupied(entity.getPosition())) {
            // arguably the wrong type of exception, but we are not
            // defining our own exceptions yet
            throw new IllegalArgumentException("position occupied");
        }

        this.addEntity(entity);
    }

    public boolean withinBounds(Point pos) {
        return pos.y >= 0 && pos.y < this.numRows && pos.x >= 0
                && pos.x < this.numCols;
    }

    public boolean isOccupied( Point pos) {
        return this.withinBounds(pos) && this.getOccupancyCell( pos) != null;
    }

    public  Optional<Point> findOpenAround( Point pos) {
        for (int dy = -ORE_REACH; dy <= ORE_REACH; dy++) {
            for (int dx = -ORE_REACH; dx <= ORE_REACH; dx++) {
                Point newPt = new Point(pos.x + dx, pos.y + dy);
                if (this.withinBounds(newPt) && !this.isOccupied(newPt)) {
                    return Optional.of(newPt);
                }
            }
        }

        return Optional.empty();
    }


    /*
       Assumes that there is no entity currently occupying the
       intended destination cell.
    */


    public  void addEntity(Entity entity) {
        if (this.withinBounds(entity.getPosition())) {
            this.setOccupancyCell( entity.getPosition(), entity);
            this.entities.add(entity);
        }
    }

    public void moveEntity(Entity entity, Point pos) {
        Point oldPos = entity.getPosition();
        if (this.withinBounds(pos) && !pos.equals(oldPos)) {
            this.setOccupancyCell( oldPos, null);
            this.removeEntityAt(pos);
            this.setOccupancyCell( pos, entity);
            entity.setPosition(pos);
        }
    }

    public void removeEntity( Entity entity) {
        removeEntityAt(entity.getPosition());
    }

    private void removeEntityAt(Point pos) {
        if (this.withinBounds(pos) && this.getOccupancyCell( pos) != null) {
            Entity entity = this.getOccupancyCell( pos);

            /* This moves the entity just outside of the grid for
             * debugging purposes. */
            entity.setPosition(new Point(-1, -1));
            this.entities.remove(entity);
            this.setOccupancyCell(pos, null);
        }
    }

    public Optional<PImage> getBackgroundImage(Point pos)
    {
        if (this.withinBounds(pos)) {
            return Optional.of((this.getBackgroundCell( pos).getCurrentImage()));
        }
        else {
            return Optional.empty();
        }
    }

    public void setBackground(Point pos, Background background)
    {
        if (this.withinBounds(pos)) {
            this.setBackgroundCell( pos, background);
        }
    }

    public  Optional<Entity> getOccupant( Point pos) {
        if (this.isOccupied(pos)) {
            return Optional.of(this.getOccupancyCell( pos));
        }
        else {
            return Optional.empty();
        }
    }

    private  Entity getOccupancyCell( Point pos) {
        return this.occupancy[pos.y][pos.x];
    }

    private void setOccupancyCell(Point pos, Entity entity)
    {
        this.occupancy[pos.y][pos.x] = entity;
    }

    private Background getBackgroundCell( Point pos) {
        return this.background[pos.y][pos.x];
    }

    private void setBackgroundCell(
            Point pos, Background background)
    {
        this.background[pos.y][pos.x] = background;
    }


    public Set<Entity> getEntities() { return entities; }

    public  boolean processLine(
            String line, WorldModel world, ImageStore imageStore) {
        String[] properties = line.split("\\s");
        if (properties.length > 0) {
            switch (properties[PROPERTY_KEY]) {
                case BGND_KEY:
                    return Parser.parseBackground(properties, world, imageStore);
                case MINER_KEY:
                    return Parser.parseMiner(properties, world, imageStore);
                case OBSTACLE_KEY:
                    return Parser.parseObstacle(properties, world, imageStore);
                case ORE_KEY:
                    return Parser.parseOre(properties, world, imageStore);
                case SMITH_KEY:
                    return Parser.parseSmith(properties, world, imageStore);
                case VEIN_KEY:
                    return Parser.parseVein(properties, world, imageStore);
                case EBGND_KEY:
                    return Parser.parseEggBackground(properties, world, imageStore);
                case DINO_KEY:
                    return Parser.parseDino(properties, world, imageStore);
                case MUT_KEY:
                    return Parser.parseMutation(properties, world, imageStore);

            }
        }

        return false;
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


    public Optional<Entity> findNearest( Point pos, Class kind)
    {
        List<Entity> ofType = new LinkedList<>();
        for (Entity entity : getEntities()) {
            if (entity.getClass() == kind) {
                ofType.add(entity);
            }
        }

        return nearestEntity(ofType, pos);
    }

}
