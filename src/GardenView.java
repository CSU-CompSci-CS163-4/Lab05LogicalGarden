import java.util.Scanner;

public class GardenView {
    private final Scanner scanner = new Scanner(System.in);

    public void printMenu() {
        System.out.println("Welcome to the garden system!");
        System.out.println("While in this menu, you can type -1 to exit.");
        System.out.println("You can have up to three rows of vegetables.");
        System.out.println("[a]dd row");
        System.out.println("[p]lant");
        System.out.println("What would you like to do? ");
    }


    public int getRow() {
        try {
            return Integer.parseInt(input("Select a row of vegetables to begin planting (1, 2, or 3): "));
        }catch(NumberFormatException ft) {
            return -1;
        }
    }

    public String input(String question) {
        System.out.print(question + " ");
        return scanner.nextLine();
    }


    public String getAction() {
        return scanner.nextLine();
    }

}
