public class GardenPlant implements Plant {
    private final String name;
    private final String type;
    private int pHLow;
    private int pHHigh;
    private String amountOfSunshine;

    public GardenPlant(String name, int pHLow, int pHHigh, String amountOfSunshine, String type){
        this.name = name;
        this.pHLow = pHLow;
        this.pHHigh = pHHigh;
        this.amountOfSunshine = amountOfSunshine;
        this.type = type;
    }

    public String getType() {
        return type;
    }



    public String getName() {
        return name;
    }



    public int getpHLow() {
        return pHLow;
    }

    public void setpHLow(int pHLow) {
        this.pHLow = pHLow;
    }

    public int getpHHigh() {
        return pHHigh;
    }

    public void setpHHigh(int pHHigh) {
        this.pHHigh = pHHigh;
    }

    public String getAmountOfSunshine() {
        return amountOfSunshine;
    }

    public void setAmountOfSunshine(String amountOfSunshine) {
        this.amountOfSunshine = amountOfSunshine;
    }


    // write tests!
    public static void main(String[] args) {
        // include mains in every class for debugging purposes.
        GardenPlant petunia = new GardenPlant("petunia", 5, 6, "partial sun", "FLOWER");
        System.out.println("TESTING:");
        System.out.println("EXPECTING: petunia");
        System.out.println("ACTUAL   : " + petunia.getName());
        System.out.println();

        System.out.println("EXPECTING: FLOWER");
        System.out.println("ACTUAL   : " + petunia.getType());
        System.out.println();
        /*
        Can build an object table for the new GardenPlant and compare your expected results with a debugger,
        and/or can call the different methods in GardenPlant.
         */
    }
}
