import java.util.ArrayList;
import java.util.Scanner;
public class GameDriver {
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    public static ArrayList healingItems = new ArrayList<>();
    public static ArrayList swords = new ArrayList<>();
    public static ArrayList armorPieces = new ArrayList<>();
    public static ArrayList miscItems = new ArrayList<>();
    public static ArrayList<String> inventory = new ArrayList<>();
    
    public static int gold;
    public static String currentSword = "Rogue Sword";
    public static int bossHealth;
    public static int playerModifier = -2;
    public static int playerHealth = 50; 
    public static int prevScene;
    public static String currentArmor = "Miner Armor";
    public static int healthThing;

    public static void main(String[]args) {
        players p1 = new players();
        p1.mageGetHealth();
        System.out.println(p1.mageGetHealth());
        p1.mageChangeHealth(100);
        System.out.println(p1.mageGetHealth());
        
        System.out.println(RED+ "This is a TEXT adventure game. All inputs have to have capitial letters and correct spelling (im too lazy to fix this) also red text in java is cool XD. Also, some of the text may be incredibly corny bear with me I hate making stories and dialog" + ANSI_RESET);
        System.out.println(BLUE+ANSI_UNDERLINE+"Don't forget, inside of rooms with mobs, you can look for secrets by entering \"Secrets\n\n\n\n"+ANSI_RESET);
        
        //printControls();
        openingScene(); 
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
        System.out.println("\nYou can:\n1. Look Around\t2. Change Sword\t3. Use healing item\t4. Look for Chests\n7. Attack\t8. Use item\t9. Talk\n");
    }
    public static void openingScene() {
        prevScene = 1;
        Scanner input = new Scanner(System.in);
        System.out.println("\"Are you ready?\"\n\"Yeah maybe\"\n\"Are you sure\"\n\"Kinda\"\nYou enter a large cave with a tall entrance. As you walk further, you notice all of the engravings");
        System.out.print("and wonder what this place was for. Looking ahead, you notice a guard at the door. What do you do. ");
        printControls();
        String choice1 = input.nextLine();
        if (choice1.equals("Look Around")) {
            System.out.println("You look around and notice nothing new except for the weird engravings on the wall. You try talking to the man");
            checkAction(choice1);
            secondScene();
        }
        else if (choice1.equals("Talk")) {
            System.out.println("You start talking to the man and he tells you about where you are.");
            secondScene();
        }
        else {
            checkAction(choice1);
        }
    }
     
    // literally just checks the action 
    public static void checkAction(String action) {
        if (action.equals("Change Sword")) {
            inventory("Sword");
        }
        else if (action.equals("Change Armor")) {
            inventory("Armor");
        }
        else if (action.equals("Secrets")) {
            secrets();
        }
    }
    public static void secondScene() {
        prevScene = 2;

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
        prevScene = 3;

        Scanner input = new Scanner(System.in);
        System.out.println("As you walk further into the catacombs, you see a black door. As you look around, you see a Necromancer in the corner. What do you do? ");
        System.out.println("As always, entering '2' will show you basic options. ");
        String choice = input.nextLine();
        if (choice.equals("2")) {
            printControls();
            thirdScene();
        }
        else if (choice.equals("Attack")) {
            System.out.println(RED+ "The Necromancer Wonders why you would ever attck him but you do." + ANSI_RESET);
            bossHealth = 20;
            bossFight();
        }
    }
    public static void fourthScene() {
        Scanner input = new Scanner(System.in);
        System.out.println("You killed your first boss. When he fades away, he drops a key. You walk up to the big door and unlock it to get into the next room. Here, there is a puzzle room, and a room filled with skeletons and zombies. Do you Attack or Solve Puzzle?");
        String choice = input.nextLine();
        if (choice.equals("Attack")) {
           // bezerkerRoom();
        }
        else if (choice.equals("Solve Puzzle")) {
            solvePuzzle("random");
            System.out.println("\nAs you leave the puzzle room, you turn to enter the room with mobs.\n");
        }
        else {
            System.out.println("Input was invalid");
            fourthScene();
        }

    }

    public static void bossFight() {
        
        for (int i = 0; playerHealth >= 0; i++) {
            Scanner input = new Scanner(System.in);
            if (!(bossHealth == 0)|| !(playerHealth == 0)) {
                if (playerHealth <= 0) {
                    System.out.println("The necromancer killed you. Ouch.");
                    break;
                }
                else if (bossHealth <= 0) {
                    System.out.println("You killed the boss!");
                    fourthScene();
                    break;
                }
                else {  
                    attackNecromancer();
                    bossTurn(1, -2, "Necromancer");
                    System.out.println("The bosses health is " + bossHealth);
                    System.out.println("Your health is: " + playerHealth);
                    System.out.println("Do you want to use a healing ability or try to escape?");
                    String choice = input.nextLine();
                    if (choice.equals("Escape")) {
                        System.out.println("You attempt to escape but fail and the boss kills you. ");
                        playerHealth = 0;
                    }
                    else if (choice.equals("Healing")) {
                        System.out.println("You try to use a healing ability but have none!");
                        bossFight(); 
                    }
                    else {
                        System.out.println("Invalid Input");
                    }
                }
            } 
        }
    }
    //
    public static void attackNecromancer() {
        System.out.println(YELLOW+"You must roll and d20 twice and take the higher number but due to your armor stats, your modifier is -2");
        rolld20(1, 2, "High", 6);
        // modifier is now high
    }
    
    
    // modifier is a -+ value. - means harder boss and + means easier boss. howManyRolls is obvious, highLow is the "difficulty" like the boss is hard
    // so the highLow value would take the lowest roll value. Num to roll is the value of which they need to roll. this value is normally 10 or higher. 
    public static void rolld20(int modifier, int howManyRolls, String highLow, int numToRoll) {
        int damage;
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
                System.out.println(rollDum);
                if (rollDum > max) {
                    max = rollDum;
                    //System.out.println(max);
                }
                else if (rollDum < min) {
                    min = rollDum;
                    //System.out.println(min);

                }
            }
            if (highLow.equals("High")) {
                if (max + modifier <= numToRoll) {
                    System.out.println("You didn't roll a high enough number (" + max + "). The Necormancer dodged your attack");
                }
                else {
                    System.out.println("You rolled a " + max + " and it was enough to do damage! You roll to find attack damage!");
                    damage = attackDamage();
                    System.out.println("You dealt " + damage + " health to the boss!");
                    bossHealth-=damage;
                }
            }
            else {
                if (min < numToRoll + modifier) {
                    System.out.println("You only rolled a " + min + " and the Necromancer dodged your attack");
                }
                else {
                    System.out.println("You rolled a lower number of " + min + " and it was just enough to attack!");
                    damage = attackDamage();
                    System.out.println("You dealt " + damage + " health to the boss!"+ANSI_RESET);
                    bossHealth-=damage;
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
    public static int rolld8() {
        int rolledNum = (int) (Math.random()*8)+1;
        return rolledNum;
    }
    // bosses turn, if the health is below 10 it will just attack. if above 10, it will do long range attacks maybe
    public static void bossTurn(int modifier, int playerModifier, String boss) {
        
        if (bossHealth > 10) {
            if (rolld20() + modifier - playerModifier > 10) {
                playerHealth-=(int)(Math.random()*10+1);
                System.out.println(RED+"The "+boss+" attacked you!. You are now at " + playerHealth + " health");
            }
            else { 
                System.out.println(RED+"You dodged the "+ boss+" attack! "); 
            } 
            
        }
        else {
            System.out.print(RED+"\nThe "+boss+" uses his long-ranged attack!");
            if (rolld20() + modifier - playerModifier > 10) {
                System.out.print(RED+"... And it hit you!");
                playerHealth-=(int)(Math.random()*8);
            }
            else {
                System.out.print(RED+"... And he missed!");
            }
        }
        System.out.println(RED+"The "+boss+"'s turn is over!"+ANSI_RESET);
    }
    // inventory tracking  
    public static void inventory(String items) {
        
        if (items.equals("Swords")) {
            if (!swords.isEmpty()) {
                System.out.println("Here are the swords you have: " + swords); 
            }
            else {
                System.out.println("You have no swords");
            }
        }
        else if (items.equals("Healing")) {
            if (!healingItems.isEmpty()) {    
                System.out.println("You have some healing items!");
            }
            else {
                System.out.println("You have no healing items");
            }
        }
        else {
            if (!armorPieces.isEmpty()) {
                System.out.println("You have these armor pieces: " + armorPieces);
            }
            else {
                System.out.println("You have no armor lol");
            }
        }
        System.out.println("Do you want to use any of these items? Yes or No");
        Scanner input = new Scanner(System.in); 
        String yn = input.nextLine();
        if (yn.equals("Yes")) {
            System.out.println("What items do you wish to swap with what you have? (Enter: Sword or Armor) ");
            String item = input.nextLine();
            if (item.equals("Sword")) {
                System.out.println("What object do you want to swap with? These are the swords in your inventory: " + swords);
                String choice = input.nextLine();
                swapInven(choice);
            }
            else if (item.equals("Armor")) {
                System.out.println("What armor do you want to swap with? This is the armor in your inventory: " + armorPieces);
                String choice = input.nextLine();
                swapInven(choice);
            }
            else {
                System.out.println("incorrect input");
            }
        }   

    }
    public static void swapInven(String item) {
        Scanner quick = new Scanner(System.in);
        if (swords.contains(item)) {
            System.out.println("You are swaping " + currentSword + " With " + item + ". Is this want you want to do? (true or false)");
            boolean tf = quick.nextBoolean();
            if (tf == true) {
                currentSword = item;
            } 
            else { 
                System.out.println("No swap was made.");
            } 
        }
        else if (armorPieces.contains(item)) {
            System.out.println("You are swaping " + currentArmor + " With " + item + ". Is this want you want to do? (true or false)");
            boolean tf = quick.nextBoolean();
            if (tf == true) {
                currentArmor = item;
            } // if they didnt want to swap then it doesnt swap the item (duh)
            else {
                System.out.println("No swap was made.");
            }
        }
        else {
            System.out.println("That item doesn't exist in ur inventory loll");
        }
    }
    public static int attackDamage() {
        System.out.println("Your current sword is " + currentSword);       
        if (currentSword.equals("Aspect of the End")) {
            int damage = rolld8() + 5;
            return damage;
        }
        else if (currentSword.equals("Rogue Sword")) {
            int damage = rolld8() -1;
            return damage;
        }
        else if (currentSword.equals("Aspect of the Dragons")) {
            int damage = rolld8() + 8;
            return damage;
        }
        else if (currentSword.equals("Livid Dagger")) {
            int damage = rolld8()+12;
            return damage;
        }
        else {
            return 1;
        }
    }
    public static void useHealing(String item) {
        if (item.equals("Wand of Healing")) {
            System.out.println("You used your Wand of Healing"); 
            playerHealth+=2;
        }
        else if (item.equals("Apple")) {
            System.out.println("You ate the apple and it healed you 4 HP!");
            playerHealth+=4;
            healingItems.remove(item);
        }
        else if (item.equals("Healing Orb")) {
            System.out.println("Your Healing Orb heals you 12 HP!");
            playerHealth+=12;
            healingItems.remove(item);
        }
    }
    public static void solvePuzzle(String puzzle) {
        int amountCorrect=0;
        Scanner input = new Scanner(System.in);
        if (puzzle.equals("random")) {
            System.out.println(BLUE+"You walk in and a wizard stands there. He asks you 1 question. If you get it wrong, you take 5 damage, answering correctly results in a reward");

            
            int pickRand = (int)(Math.random()*8)+1;
            if (pickRand > 8) {
                System.out.println("What was the name of the guy on The Big Bang Theory that went to the ISS? (First name only)");
                String answer = input.nextLine();
                if (answer.equals("Howard")) {
                    amountCorrect++;
                }
                else {
                    System.out.println("It was Howard!");
                }
            }
            else if (pickRand > 7) {
                System.out.println("This bug was also the name of a popular rock band from the 60's (singular)");
                String answer1 = input.nextLine();
                if (answer1.equals("Beatle")) {
                    System.out.println("That is correct!");
                    amountCorrect++;
                }
                else {
                    System.out.println("The answer was \"Beatle\"!");
                }
            }
            else if (pickRand > 6) {
                System.out.println("Who won streamer of the year in 2025?");
                String answer2 = input.nextLine();
                if (answer2.equals("IShowSpeed")) {
                    System.out.println("Correct!");
                    amountCorrect++;
                }
                else {
                    System.out.println("Incorrect! It was IShowSpeed");
                }
            }
            else if (pickRand > 5) {
                System.out.println("What is the name of the 2026-2027 Vex Robotics Competition? (Hint: It is also apparantly the name of a fortnite season");
                String answer3 = input.nextLine();
                if (answer3.equals("Override")) {
                    System.out.println("That is correct! (holy ball knowledge");
                    amountCorrect++;
                }
                else {
                    System.out.println("Incorrect, sorry you got this question");
                }
            }
            else if (pickRand > 4) {
                System.out.println("This is the name of a godess and NASA's current program to go back to the moon");
                String answer4 = input.nextLine();
                if (answer4.equals("Artemis")) {
                    System.out.println("Correct!");
                    amountCorrect++;
                }
                else {
                    System.out.println("Seriously? Artemis? You didn't get that?");
                }
            }
            else if (pickRand > 3) { 
                System.out.println("This is the first name of the guy who created Linux (Hint: This is also the name of a massive tech youtuber!)");
                String answer5 = input.nextLine();
                if (answer5.equals("Linus")) {
                    System.out.println("Okay, that was an easy one. Good thing you got it correct");
                    amountCorrect++;
                }
                else {
                    System.out.println("Okay, how did you get that wrong. It was Linus. you know, like Linus Torvalds?");
                }
            }
            else if (pickRand > 2) {
                System.out.println("What is the name of the math that was invented by Gottfried Wilhelm Leibniz");
                String answer6 = input.nextLine();
                if (answer6.equals("Calculus")) {
                    System.out.println("Correct!");
                    amountCorrect++;
                }
                else {
                    System.out.println("Wow, that was wrong. Damn");
                }
            }
            else {
                System.out.println("What is the name of the most used phone OS in the world?");
                String answer7 = input.nextLine();
                if (answer7.equals("Android")) {
                    System.out.println("Correct!");
                    amountCorrect++;
                }
                else {
                    System.out.println("That is incorrect! iOS is only the most popular in America");
                }
            }
            if (amountCorrect > 0) {
                System.out.println("You answered correctly! The wizard grants you 5 health and...");
                System.out.println(GREEN+"\nYou got a Wand Of Healing!! This item is added to your inventory! \n"+ANSI_RESET);
                healingItems.add("Wand of Healing");
            }
            else {
                System.out.println("You did not get a answer right, the wizard attacks you for 5 damage");
                playerHealth-=5;
                if (playerHealth < 0) {
                    System.out.println("You died. Try again?");
                    dead();
                }
                else {
                    System.out.println("You have " + playerHealth + " health left. Keep that in mind for the next room");
                }
            } 
            System.out.println(ANSI_UNDERLINE+"You have " + playerHealth + " health remaining. Don't die. "+ANSI_RESET);
            System.out.println(ANSI_RESET+"The wizard makes you leave and when you step out a door blocks the entrance to the puzzle room.");
        }
        //
    
    }
    public static void berzerkRoom() {
        Scanner input = new Scanner(System.in);
        System.out.println("You enter the room filled with mobs. To the right, there are zombies, and to the left, there are skeletons. They all have a pretty low modifier, but remeber, there are more of them than you. Do you go left or right?");
        String LorR = input.nextLine();
        if (LorR.equals("Right")) {

        }
        else {
            System.out.println("So you chose to attack the Skeletons first. They all have a long ranged attack and a modifier of 0. Your current modifier is 2");
        }
    }
    public static void dead() {
        System.out.println("you died.");
        System.exit(0);
    }
    public static ArrayList<Integer> skeletons = new ArrayList<>();
    public static void skellys() {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 3; i ++) {
            skeletons.add(10);
        }
        while (!(skeletons.size() == 0) || playerHealth > 0) {
            System.out.println("your current health is: " + playerHealth);
            System.out.println("There are " + skeletons.size() + " skeletons left");
            System.out.println("Who do you attack first? (0-" + (skeletons.size()-1));
            String choice = input.nextLine();
            if (choice.equals("0")) {
                
            }
        }
    }
    public static void mobAttack(int mobModifier, int playerModifier, int numToRoll, String name, int currentSkellHealth) {
        if (name.equals("Skeleton")) {
            if (currentSkellHealth < 0) {
                System.out.println("Current skelly is dead");
                skeletons.remove(0);
            }
        }
        else if (name.equals("Zombie")) {

        }
    }
    //
    //
    //
}


/*
the boss thing needs to be universal              DONE                       
scenes are getting long, remember the inventory   DONE                    
need to fix the choices tbh
checkAction can be fixed to go back to the previous scene
printActions is not returning back to scene, make a variable called previousScene and then go back to it

*/    