public class GardenHelper {
    private final GardenView view = new GardenView();

    GardenPlant daisy, carrot, rose, swissChard, begonia;
    GardenRow one, two, three;

    /**
     * Runs the Application GardenHelper
     */
    public void go() {
        view.printMenu();
        String action = view.getAction();
        while(!action.startsWith("-1")) {  // interject an error, and let student debug - it is a Tuesday lab after all.
            if(action.startsWith("a")) {
                int row = view.getRow();
                int pH = Integer.parseInt(view.input("What is the pH of the row? "));
                String light = view.input("What is the sun coverage of the row? (full sun, partial sun, any) ");
                addRow(row, pH, light);
            }
            else if(action.startsWith("p")) {
                String plantName = view.input("What plant would you like to plant? (daisy, carrot, rose, swiss chard, begonia): ");
                int row = view.getRow();
                addPlant(plantName, row);
            }
            System.out.println("What would you like to do? ");
            action = view.getAction();
        }
    }

    /**
     * Initializes GardenRow one, two, or three.
     * <p>Implementation notes:</p>
     * <ul>
     *     <li>Creates a new row object based on parameters</li>
     *     <li>Stores row object in GardenRow one, two, or three according to proper parameter</li>
     * </ul>
     * @return true if adding row was successful, return false if unsuccessful
     */
    public boolean addRow(int row, int pH, String light) {
        //TODO student
        if(row == 1){
            one = new GardenRow(light, pH);
            return true;
        }
        else if(row == 2){
            two = new GardenRow(light, pH);
            return true;
        }
        else if(row == 3){
            three = new GardenRow(light, pH);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Adds plant (based on the five options) to designated row. If plant needs meet row conditions,
     * the plant will be added. If not, then the plant will not be added to the row.
     * @return true if plant was successfully added, false if unsuccessful
     */
    // have student post self-explanation for this method? or above method?
    public boolean addPlant(String plantName, int row) {
        if(canPlant(plantName, searchRow(row))){
            searchRow(row).setPlant(searchPlant(plantName));
            System.out.println(plantName.toUpperCase() + " added to row " + row + "!");
            return true;
        }
        else{
            System.out.println(plantName.toUpperCase() + " was unable to be planted.");
            return false;
        }
    }


    /**
     * Checks if a particular plant is allowed to be planted where the user designates.
     *     <p>Conditions for planting:</p>
     *     <ul>
     *         <li>Row must have correct sun coverage (full sun, partial sun, or any)</li>
     *         <li>Row must have a pH between the plant's pH range</li>
     *         <li>At least one row must have vegetables, so if it is the third row and the other two have flowers - can't plant a flower.
     *         Also remember that if there is currently no plant in the row, {@link GardenRow#getPlant()} will throw a null pointer exception.
     *         To remedy this, make the first condition check if {@link GardenRow#getPlant()} != null</li>
     *     </ul>
     * Using the provided searchPlant() method may be helpful.
     * @param plantName name of the pant to be planted in the entire row
     * @param row row to plant plants in (1, 2, or 3)
     * @return
     */
    // two parts - first write the logic as pseudo code
    public boolean canPlant(String plantName, GardenRow row){
        //TODO student
        if(searchPlant(plantName).getAmountOfSunshine().equals(row.getLight())){
            if(searchPlant(plantName).getpHLow() <= row.getPH() && searchPlant(plantName).getpHHigh() >= row.getPH()){
                if(((one != null && (one.getPlant() != null) && (one.getPlant().getType().equals("VEG")))
                        || (two != null && two.getPlant() != null && two.getPlant().getType().equals("VEG"))
                        || (three != null && three.getPlant() != null && three.getPlant().getType().equals("VEG")))){
                    return true;
                }
                if(searchPlant(plantName).getType().equals("VEG")){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Initializes the possible GardenPlants.
     */
    public void buildOptions() {
        daisy = new GardenPlant("daisy", 6, 8, "any", "FLOWER");
        carrot = new GardenPlant("carrot", 6, 7, "full sun", "VEG");
        rose = new GardenPlant("rose", 6, 7, "full sun", "FLOWER");
        swissChard = new GardenPlant("swiss chard", 6, 7, "partial sun", "VEG");
        begonia = new GardenPlant("begonia", 5, 6, "partial sun", "FLOWER");
    }

    /**
     * Searches for a GardenPlant based on a plant name passed in
     * @param plantName name of plant
     * @return one of the GardenPlant options
     */
    public GardenPlant searchPlant(String plantName){
        if(plantName.equals(daisy.getName())){
            return daisy;
        }
        else if(plantName.equals(carrot.getName())){
            return carrot;
        }
        else if(plantName.equals(rose.getName())){
            return rose;
        }
        else if(plantName.equals(swissChard.getName())){
            return swissChard;
        }
        else{
            return begonia;
        }
    }

    /**
     * Finds the GardenRow based on an numerical input
     * @param row int that numerically represents the row
     */
    public GardenRow searchRow(int row){
        if(row == 1){
            return one;
        }
        else if(row == 2){
            return two;
        }
        else{
            return three;
        }

    }


    public static void main(String[] args) {
        GardenHelper gardenHelperObj = new GardenHelper();
        gardenHelperObj.buildOptions();
        //Test 1 for addRow():
        System.out.println("Testing addRow(4, 6, any):");
        System.out.println("EXPECTING: false");
        System.out.println("ACTUAL   : " + gardenHelperObj.addRow(4, 6, "any"));
        System.out.println();

        //Test 2 for addRow():
        System.out.println("Testing addRow(2, 6, any):");
        System.out.println("EXPECTING: true");
        System.out.println("ACTUAL   : " + gardenHelperObj.addRow(2, 6, "any"));
        System.out.println();

        //Test 1 for canPlant():
        gardenHelperObj.addRow(2, 6, "full sun");
        System.out.println("Testing canPlant(carrot, 2):");
        System.out.println("EXPECTING: true");
        System.out.println("ACTUAL   : " + gardenHelperObj.addPlant("carrot", 2));
        System.out.println();

        //Test 2 for canPlant():
        gardenHelperObj.addRow(1, 2, "full sun");
        System.out.println("Testing canPlant(carrot, 2):");
        System.out.println("EXPECTING: false");
        System.out.println("ACTUAL   : " + gardenHelperObj.addPlant("carrot", 1));
        System.out.println();

    }

}