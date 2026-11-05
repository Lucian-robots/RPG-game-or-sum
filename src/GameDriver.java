import java.util.ArrayList;
import java.util.Scanner;
public class GameDriver {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public ArrayList<String> inventory = new ArrayList<>();
    int gold;
    String currentSword;

    public static void main(String[]args) {
        players p1 = new players();
        p1.mageGetHealth();
        System.out.println(p1.mageGetHealth());
        p1.mageChangeHealth(100);
        System.out.println(p1.mageGetHealth());



        printControls();
        openingScene();
    }
    public void gotHealth() {

    }
    public void attack(players p1, players p2) {
       
    }
    public String openChest() {
        int random = (int)(Math.random() * 10) +1;
        if (random > 5) {
            gold+=5;
            return "You got 5 gold!";
        }
        else if (random > 3) {
            gold+=10;
            return "You got 10 gold!";
        } 
        else if (random > 1) {
            gold+=20;
            return "You got 20 Gold!";
        }
        else {
            inventory.add("Longsword (20 Attck Dmg)");
            return "You found a long sword! Sweet!";
        }
    }
    public static void printControls() {
        System.out.println("\nYou can:\n1. Look Around\t2. Change Sword\t3. Use healing item\t4. Look for Chests\n5. Look for traps\t6. Enter new room\t7. Attack\t8. Use item\t9. Talk\n");
    }
    public static void openingScene() {
        Scanner input = new Scanner(System.in);
        System.out.println("\"Are you ready?\"\n\"Yeah maybe\"\n\"Are you sure\"\n\"Kinda\"\nYou enter a large cave with a tall entrance. As you walk further, you notice all of the en");
        System.out.print("gravings and wonder what this place was for. As you walk further, you notice a guard at the door. What do you do. ");
        printControls();
        String choice1 = input.nextLine();
        if (choice1.equals("Look Around")) {
            System.out.println("You look around and notice nothing new except for the weird engravings on the wall. You try talking to the man");
            checkAction(choice1);
            secondScene();
        }
        else if (choice1.equals("Talk")) {
            System.out.println("You start talking to the man and he tells you about where you are.");

        }
        else {
            checkAction(choice1);
        }
    }
     
    // literally just checks the action 
    public static void checkAction(String action) {
        if (action.equals("Talk")) {

        }
        else if (action.equals("Change Sword")) {

        }
    }

    // annoying logic to add down here to make stuff easier
    public void changeSword(String tempSword) {
        currentSword = tempSword; 
    }
    public static void secondScene() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nHe begins to explain you are in a place called the \"The catacombs.\" This place is where the Necromancers and Withers train the dead for their armies.");
        System.out.println("You are now needed to make sure they don't grow any stronger. You say: \nBut won't we die instantly?\nHe says:\nThat's up to you\nThen, he makes you go in and shuts the door behind you");
        System.out.println("As you enter, what do you do? Enter 2 to see your options.");
        String choice = input.nextLine();
        if (choice.equals("2")) {
            printControls();
        }
        else if (choice.equals("Look Around")) {

        }
        else if (choice.equals("Secrets")) {
            
        }
        
    } 
}
