public class Activity extends Action {


    private WorldModel world;
    private ImageStore imageStore;
    private ActiveEntity entity;

    public Activity(ActiveEntity entity, WorldModel world, ImageStore imageStore) {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }

    public void executeAction(EventScheduler scheduler) {
        (entity).executeActivity(this.world, this.imageStore, scheduler);
    }


    public WorldModel getWorld() {return world;}
    public ImageStore getImageStore() {return imageStore;}
}

