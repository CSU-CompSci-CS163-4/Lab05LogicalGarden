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
    }

}
