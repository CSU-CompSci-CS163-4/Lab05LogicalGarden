public class GardenRow {
    private final String light;
    private final int pH;
    private GardenPlant plant = null;

    public GardenRow(String light, int pH) {
        this.light = light;
        this.pH = pH;
    }

    public String getLight() {
        return light;
    }

    public int getPH() {
        return pH;
    }

    public GardenPlant getPlant() {
        return plant;
    }

    public void setPlant(GardenPlant plant) {
        this.plant = plant;
    }


    public static void main(String[] args) {
        // include mains in every class for debugging purposes.
        GardenRow gardenRowObj = new GardenRow("any", 6);
        System.out.println("TESTING:");
        System.out.println("EXPECTING: any");
        System.out.println("ACTUAL   : " + gardenRowObj.getLight());
        System.out.println();

        System.out.println("EXPECTING: 6");
        System.out.println("ACTUAL   : " + gardenRowObj.getPH());
        System.out.println();
    }

}
