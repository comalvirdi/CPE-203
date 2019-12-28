public class Parser {

    private static final String MINER_KEY = "miner";
    private static final String OBSTACLE_KEY = "obstacle";
    private static final String ORE_KEY = "ore";
    private static final String SMITH_KEY = "blacksmith";
    private static final String VEIN_KEY = "vein";
    private static final String DINO_KEY = "dino";
    private static final String MUT_KEY = "zom";


    private static final int OBSTACLE_NUM_PROPERTIES = 4;
    private static final int OBSTACLE_ID = 1;
    private static final int OBSTACLE_COL = 2;
    private static final int OBSTACLE_ROW = 3;

    private static final int MINER_NUM_PROPERTIES = 7;
    private static final int MINER_ID = 1;
    private static final int MINER_COL = 2;
    private static final int MINER_ROW = 3;
    private static final int MINER_LIMIT = 4;
    private static final int MINER_ACTION_PERIOD = 5;
    private static final int MINER_ANIMATION_PERIOD = 6;

    private static final int ORE_NUM_PROPERTIES = 5;
    private static final int ORE_ID = 1;
    private static final int ORE_COL = 2;
    private static final int ORE_ROW = 3;
    private static final int ORE_ACTION_PERIOD = 4;

    private static final int DINO_NUM_PROPERTIES = 5;
    private static final int DINO_ID = 1;
    private static final int DINO_COL = 2;
    private static final int DINO_ROW = 3;
    private static final int DINO_ACTION_PERIOD = 4;

    private static final int MUT_NUM_PROPERTIES = 5;
    private static final int MUT_ID = 1;
    private static final int MUT_COL = 2;
    private static final int MUT_ROW = 3;
    private static final int MUT_ACTION_PERIOD = 4;

    private static final int VEIN_NUM_PROPERTIES = 5;
    private static final int VEIN_ID = 1;
    private static final int VEIN_COL = 2;
    private static final int VEIN_ROW = 3;
    private static final int VEIN_ACTION_PERIOD = 4;

    private static final int SMITH_NUM_PROPERTIES = 4;
    private static final int SMITH_ID = 1;
    private static final int SMITH_COL = 2;
    private static final int SMITH_ROW = 3;

    private static final int BGND_NUM_PROPERTIES = 4;
    private static final int BGND_ID = 1;
    private static final int BGND_COL = 2;
    private static final int BGND_ROW = 3;

    private static final int EBGND_NUM_PROPERTIES = 4;
    private static final int EBGND_ID = 1;
    private static final int EBGND_COL = 2;
    private static final int EBGND_ROW = 3;

    public static boolean parseMiner(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == MINER_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[MINER_COL]),
                    Integer.parseInt(properties[MINER_ROW]));
            Entity entity = Factory.createMinerNotFull(properties[MINER_ID],pt,
                    Integer.parseInt(properties[MINER_LIMIT]),
                    Integer.parseInt(properties[MINER_ACTION_PERIOD]),
                    Integer.parseInt(properties[MINER_ANIMATION_PERIOD]),
                    imageStore.getImageList(MINER_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == MINER_NUM_PROPERTIES;
    }

    public static boolean parseObstacle(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == OBSTACLE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[OBSTACLE_COL]),
                    Integer.parseInt(properties[OBSTACLE_ROW]));
            Entity entity = Factory.createObstacle(properties[OBSTACLE_ID],pt,
                    imageStore.getImageList(OBSTACLE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == OBSTACLE_NUM_PROPERTIES;
    }

    public static boolean parseOre(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == ORE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[ORE_COL]),
                    Integer.parseInt(properties[ORE_ROW]));
            Entity entity = Factory.createOre(properties[ORE_ID], pt, Integer.parseInt(
                    properties[ORE_ACTION_PERIOD]),
                    imageStore.getImageList(ORE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == ORE_NUM_PROPERTIES;
    }

    public static boolean parseVein(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == VEIN_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[VEIN_COL]),
                    Integer.parseInt(properties[VEIN_ROW]));
            Entity entity = Factory.createVein(properties[VEIN_ID], pt,
                    Integer.parseInt(
                            properties[VEIN_ACTION_PERIOD]),
                    imageStore.getImageList(VEIN_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == VEIN_NUM_PROPERTIES;
    }

    public static boolean parseSmith(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == SMITH_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[SMITH_COL]),
                    Integer.parseInt(properties[SMITH_ROW]));
            Entity entity = Factory.createBlacksmith(properties[SMITH_ID], pt,
                    imageStore.getImageList(SMITH_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == SMITH_NUM_PROPERTIES;
    }

    public static boolean parseBackground(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == BGND_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[BGND_COL]),
                    Integer.parseInt(properties[BGND_ROW]));
            String id = properties[BGND_ID];
            world.setBackground(pt, new Background(id, imageStore.getImageList(id)));
        }

        return properties.length == BGND_NUM_PROPERTIES;
    }

    public static boolean parseEggBackground(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == EBGND_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[EBGND_COL]),
                    Integer.parseInt(properties[EBGND_ROW]));
            String id = properties[EBGND_ID];
            world.setBackground(pt, new EggBackground(id, imageStore.getImageList(id)));
        }

        return properties.length == EBGND_NUM_PROPERTIES;
    }

    public static boolean parseDino(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == DINO_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[DINO_COL]),
                    Integer.parseInt(properties[DINO_ROW]));
            Entity entity = Factory.createOre(properties[DINO_ID], pt, Integer.parseInt(
                    properties[DINO_ACTION_PERIOD]),
                    imageStore.getImageList(DINO_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == ORE_NUM_PROPERTIES;
    }

    public static boolean parseMutation(
            String[] properties, WorldModel world, ImageStore imageStore) {
        if (properties.length == MUT_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[MUT_COL]),
                    Integer.parseInt(properties[MUT_ROW]));
            Entity entity = Factory.createOre(properties[MUT_ID], pt, Integer.parseInt(
                    properties[MUT_ACTION_PERIOD]),
                    imageStore.getImageList(MUT_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == ORE_NUM_PROPERTIES;
    }
}
