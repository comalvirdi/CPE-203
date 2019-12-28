import processing.core.PImage;

import java.util.List;

public abstract class AnimationEntity extends ActiveEntity{

    int animationPeriod;

    public AnimationEntity(String id,
                           Point position,
                           List<PImage> images,
                           int imageIndex,
                           int actionPeriod,
                           int animationPeriod){

        super(id, position, images, imageIndex, actionPeriod);
        this.animationPeriod = animationPeriod;
    }


    int getAnimationPeriod(){return this.animationPeriod; }
    void nextImage(){ this.imageIndex = (this.imageIndex + 1) % this.images.size(); };

    @Override
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        super.scheduleActions(scheduler, world, imageStore);
        scheduler.scheduleEvent(this,
                Factory.createAnimationAction(this,0),
                this.getAnimationPeriod());
    }

    @Override
    public PImage getCurrentImage() { return this.images.get((this.imageIndex));}
}
