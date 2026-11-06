import java.util.ArrayList;
import java.util.Scanner;
public class GameDriver {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public ArrayList<String> inventory = new ArrayList<>();
    public static int gold;
    String currentSword;
    public int bossHealth;
    public int playerModifier;

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
        else if (action.equals("Secrets")) {
            secrets();
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
            System.out.println("You look around and see nothing, so you choose to go deeper into the catacombs");
            thirdScene();
        }
        else {
            checkAction(choice);
            System.out.println("With no other choices, you go further into the catacombs. ");
            thirdScene();
        }
        
    }
    
     
    
    public static void secrets() {
        int isSecret = (int) (Math.random() *10);

        if (isSecret > 2) {
            System.out.println("No secrets found.");
        }
        else {
            System.out.println("You found 100 gold in a chest!");
            gold+=100;
        }
    }

    public static void thirdScene() {
        Scanner input = new Scanner(System.in);
        System.out.println("As you walk further into the catacombs, you see a black door. As you look around, you see a Necromancer in the corner. What do you do? ");
        System.out.println("As always, entering '2' will show you basic options. ");
        String choice = input.nextLine();
        if (choice.equals("2")) {
            printControls();
        }
        else if (choice.equals("Attack")) {
            System.out.println("The Necromancer Wonders why you would ever attck him but you do.");
            attackNecromancer();
        }
    }

    //
    public static void attackNecromancer() {
        
        System.out.println("You must roll and d20 twice and take the higher number but due to your armor stats, your modifier is -2");
    }
    
    
    // modifier is a -+ value. - means harder boss and + means easier boss. howManyRolls is obvious, highLow is the "difficulty" like the boss is hard
    // so the highLow value would take the lowest roll value. Num to roll is the value of which they need to roll. this value is normally 10 or higher. 
    public static void rolld20(int modifier, int howManyRolls, String highLow, int numToRoll) {
        int min = 1000;
        int max = 0;
        if (howManyRolls == 1) {
            int rollNum = rolld20();         
            if (rollNum + modifier > numToRoll) {
                System.out.println("You rolled a " + rollNum + " and your modifier was " + modifier + " Good job!");
            }
            else {
                System.out.println("Your attack missed! It is now the necromancers turn...");

            }   
        }
        else if (howManyRolls > 1) {
            for (int i = 0; i < howManyRolls; i++) { 
                int rollDum = rolld20();
                if (rollDum > max) {
                    max = rollDum;
                }
                else if (rollDum < min) {
                    min = rollDum;
                }
            }
        }
        else {
            System.out.println("Error");
        }
    }
    public static int rolld20() {
        int rolledNum;
        rolledNum = (int) (Math.random()*20)+1;
        return rolledNum;
    }
    public static void bossTurn() {
        
    }
} 
