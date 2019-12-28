public class Animation extends Action{

    private int repeatCount;
    private AnimationEntity entity;

    public Animation(AnimationEntity entity, int repeatCount)
    {
        this.entity = entity;
        this.repeatCount = repeatCount;
    }

    public void executeAction(EventScheduler scheduler)
    {
        (this.entity).nextImage();

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity,
                    Factory.createAnimationAction( this.entity,
                            Math.max(this.repeatCount - 1,
                                    0)),
                    (this.entity).getAnimationPeriod());
        }
    }



}
