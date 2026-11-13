/*
first couple of choices are mandatory but after that (after the zombie and skeleton fight) then you can choose where to go. (maybe)


*/
import java.util.ArrayList;
import java.util.Scanner;
public class GameDriver {
    
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    public static ArrayList<String> healingItems = new ArrayList<>();
    public static ArrayList<String> swords = new ArrayList<>();
    public static ArrayList<String> armorPieces = new ArrayList<>();
    public static ArrayList<String> miscItems = new ArrayList<>();
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
        
        System.out.println(RED+ "This is a TEXT adventure game. All inputs have to have correct spelling (im too lazy to fix this) also red text in java is cool XD. Also, some of the text may be incredibly corny bear with me I hate making stories and dialog" + ANSI_RESET);
        System.out.println(BLUE+ANSI_UNDERLINE+"Don't forget, inside of rooms with mobs, you can look for secrets by entering \"Secrets\n\n\n\n"+ANSI_RESET);
        
        // solvePuzzle("riddle");
        
        //printControls();
        //openingScene(); 
        berzerkRoom();
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
        if (caseSensitive(choice1).equals("look around")) {
            System.out.println("You look around and notice nothing new except for the weird engravings on the wall. You try talking to the man");
            secondScene();
        }
        else if (choice1.equals("talk")) {
            System.out.println("You start talking to the man and he tells you about where you are.");
            secondScene();
        }
        else {
            checkAction(caseSensitive(choice1));
            secondScene();
        }
    }
     
    // literally just checks the action 
    public static void checkAction(String action) {
        Scanner input = new Scanner(System.in);
        if (action.equals("change sword")) {
            inventory("Sword");
        }
        else if (action.equals("change armor")) {
            inventory("Armor");
        }
        else if (action.equals("secrets")) {
            secrets();
        }
        else if (action.equals("use healing item")) {
            System.out.println(BLUE+"What item do you want to use (enter the name of the item you want to use): " + healingItems);
            String choice = input.nextLine();
            useHealing(choice);
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
        else if (caseSensitive(choice).equals("look around")) {
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
        else if (caseSensitive(choice).equals("attack")) {
            System.out.println(RED+ "The Necromancer Wonders why you would ever attck him but you do." + ANSI_RESET);
            bossHealth = 20;
            bossFight();
        }
        else {
            checkAction(caseSensitive(choice));
            System.out.println("You have no choice but to fight the Necromancer");
            bossHealth = 20;
            bossFight();
        }
    }
    public static void fourthScene() {
        Scanner input = new Scanner(System.in);
        System.out.println("You killed your first boss. When he fades away, he drops a key. You walk up to the big door and unlock it to get into the next room. Here, there is a "+BLUE+"puzzle"+ANSI_RESET+" room, and a "+RED+"room filled with skeletons and zombies."+ANSI_RESET+" Do you Attack or Solve Puzzle?");
        String choice = input.nextLine();
        if (caseSensitive(choice).equals("attack")) {
           berzerkRoom();
           System.out.println("As you finish killing all of the mobs, you go into the puzzle room");
           solvePuzzle("random");
        }
        else if (caseSensitive(choice).equals("solve puzzle")) {
            solvePuzzle("random");
            System.out.println("\nAs you leave the puzzle room, you turn to enter the room with mobs.\n");
            berzerkRoom();
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
                    bossTurn(-1, -2, "Necromancer");
                    System.out.println("The bosses health is " + bossHealth);
                    System.out.println("Your health is: " + playerHealth);
                    System.out.println("Do you want to use a healing ability or try to escape? PRESS ENTER TO KEEP GOING");
                    String choice = input.nextLine();
                    if (choice.equals("Escape")) {
                        System.out.println("You attempt to escape but fail and the boss kills you\n. ");
                        playerHealth = 0;
                    }
                    else if (choice.equals("Healing")) {
                        System.out.println("You try to use a healing ability but have none!");
                        bossFight(); 
                    }
                    else if (choice.equals("")) {
                        System.out.println("You keep fighting");
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
    public static void fifthScene() {
        Scanner input = new Scanner(System.in);
        System.out.println(YELLOW+"Congrats on making this far, now, to save the villagers and the realm of skyblock, you must defeat the Witherlord himself.");
        System.out.println("\tBUT, you are WAY under geard for this fight. If you wish to proceed, you may. But attacking him now is certain death.");
        System.out.println("\t\tYou have the option to either, Attack Witherlord, Do puzzle room, Look for armor, look for swords, or carry on with story");
        String choice = input.nextLine();
        if (caseSensitive(choice).equals("attack witherlord")) {
            System.out.println("What a dumb choice");
            bossHealth = 100;
            attackNecromancer();
        }
        else if (caseSensitive(choice).equals("do puzzle room")) {
            System.out.println("You choose to solve a puzzle\n");
        }
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
                playerHealth-=(int)(Math.random()*6);
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
                playerHealth-=(int)(Math.random()*6);
            }
            else {
                System.out.print(RED+"... And he missed!");
            }
        }
        System.out.println(RED+"The "+boss+"'s turn is over!"+ANSI_RESET);
    }
    // inventory tracking  
    public static void inventory(String items) {
        System.out.println(BLUE + ".");
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
        System.out.println("."+ANSI_RESET);   

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
        System.out.println(YELLOW+"Your current sword is " + currentSword);       
        if (currentSword.equals("Aspect of the End"+ANSI_RESET)) {
            int damage = rolld8() + 5;
            return damage;
        }
        else if (currentSword.equals("Rogue Sword"+ANSI_RESET)) {
            int damage = rolld8() -1;
            return damage;
        }
        else if (currentSword.equals("Aspect of the Dragons"+ANSI_RESET)) {
            int damage = rolld8() + 8;
            return damage;
        }
        else if (currentSword.equals("Livid Dagger"+ANSI_RESET)) {
            int damage = rolld8()+12;
            return damage;
        }
        else {
            System.out.println(ANSI_RESET);
            return 1;
        }
    }
    public static void useHealing(String item) {
        System.out.println(BLUE);
        if (item.equals("Wand of Healing")) {
            System.out.println("You used your Wand of Healing"); // this is for healing or sum okease
            playerHealth+=5; 
        }
        else if (item.equals("Apple")) {
            System.out.println("You ate the apple and it healed you 4 HP!");
            playerHealth+=10;
            
            healingItems.remove(item);
        } 
        else if (item.equals("Healing Orb")) {
            System.out.println("Your Healing Orb heals you 12 HP!");
            playerHealth+=15;
            healingItems.remove(item);
        }
        System.out.println(ANSI_RESET);
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
                if (caseSensitive(answer).equals("howard")) {
                    amountCorrect++;
                }
                else {
                    System.out.println("It was Howard!");
                }
            }
            else if (pickRand > 7) {
                System.out.println("This bug was also the name of a popular rock band from the 60's (singular)");
                String answer1 = input.nextLine();
                if (caseSensitive(answer1).equals("beatle")) {
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
                if (caseSensitive(answer2).equals("ishowspeed")) {
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
                if (caseSensitive(answer3).equals("override")) {
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
                if (caseSensitive(answer4).equals("artemis")) {
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
                if (caseSensitive(answer5).equals("linus")) {
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
                if (caseSensitive(answer6).equals("calculus")) {
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
                if (caseSensitive(answer7).equals("android")) {
                    System.out.println("Correct!");
                    amountCorrect++;
                }
                else {
                    System.out.println("That is incorrect! iOS is only the most popular in America");
                }
            }
            if (amountCorrect > 0) {
                System.out.println("You answered correctly! The wizard grants you 5 health and...");
                playerHealth+=5;
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
        else if (puzzle.equals("riddle")) {
            int chooseRiddle = (int) (Math.random()*4);
            if (chooseRiddle >= 4) {
                System.out.println("What has one eye but cannot see? (Answer form is: A _______");
                String choice = input.nextLine();
                if (caseSensitive(choice).equals("a needle")) {
                    System.out.println("That was correct!");
                    amountCorrect+=1;
                }
                else {
                    System.out.println("That is incorrect. The answer was: A Needle");
                }
            }
            else if (chooseRiddle >= 3) {
                System.out.println("What can you catch, but not throw (answer = A _____");
                String choice = input.nextLine();
                if (caseSensitive(choice).equals("a cold")) {
                    System.out.println("That is correct!");
                    amountCorrect++;
                }
                else {
                    System.out.println("Wrong, the correct answer was: A Cold");
                }
            }
            else if (chooseRiddle >= 2) {
                System.out.println("What needs to be broken in order to use it? ");
                String choice = input.nextLine();
                if (caseSensitive(choice).equals("egg")) {
                    System.out.println("That is correct!");
                    amountCorrect++;
                }
                else {
                    System.out.println("That was wrong. It was an egg!");
                }
            }
            else {
                System.out.println("What is full of holes, but can hold water? (Answer form: A _____)");
                String choice = input.nextLine();
                if (caseSensitive(choice).equals("a sponge")) {
                    System.out.println("That is correct!");
                    amountCorrect+=1;
                }
                else {
                    System.out.println("That was incorrect, it was: A Sponge");
                }
            }
            // if they got correct, then get new armor
            if (amountCorrect > 0) {
                System.out.println("After answering the question correctly, a hole in the wall opens and a new Armor set is revealed. When you go collect it, you see it's called \"Glacite Armor.\" This armor has a modifier of + 3");
                armorPieces.add(currentArmor);
                currentArmor = "Glacite Armor";
                armorPieces.add("Glacite Armor");
            }
            else {
                System.out.println("You answered incorrectly. Now, because the boss is so hard (and you need better armor), you can choose to go back to the last scene and get the answer correct (Yes or No)");
                String YorN = input.nextLine();
                if (caseSensitive(YorN).equals("yes")) {
                    fifthScene();
                }
                else {
                    System.out.println("Suit youself ig ¯\\_(ツ)_/¯");
                }
            }
        }
        else if (puzzle.equals("math")) {

        }
     
    }
      
    public static void berzerkRoom() {
        Scanner input = new Scanner(System.in);
        System.out.println("You enter the room filled with mobs. To the right, there are zombies, and to the left, there are skeletons. They all have a pretty low modifier, but remeber, there are more of them than you. Do you go left or right?");
        String LorR = input.nextLine();
        if (caseSensitive(LorR).equals("right")) {
            zombies(3);
            System.out.println("You defeated all of the zombies!");
            System.out.println("Now it's time for the skeletons"); 
            System.out.println("You probably need to heal so lets see what you have");
            checkAction("Use Healing Item");
            skellys(3); 
        }
        else if (caseSensitive(LorR).equals("left")){
            System.out.println("So you chose to attack the Skeletons first. They all have a long ranged attack and a modifier of 0. Your current modifier is 2");
            skellys(3);
            System.out.println("You defeated the skeletons!");
            System.out.println("Lets see if you can heal before you fight the zombies");
            checkAction("Use Healing Item");
            zombies(3);
        }
        else {
            System.out.println("Invalid input. Left or Right");
            berzerkRoom();
        }
        System.out.println("After defeating the skeletons and zombies, the last one you kill drops what looks to be a sword. When you pick it up, you realize that it it is a"+BLUE+" Livid Dagger! "+ANSI_RESET+"Do you want to keep it in your inventory or equip it? It is better than a rogue sword because it has a roll value of +2 and a damage modifier of +2 (Equip or Keep In Inventory?");
        String choice = input.nextLine();
        if (caseSensitive(choice).equals("")) {

        }
    }
    public static void dead() {
        System.out.println("you died.");
        System.exit(0);
    }

    
    // for fighting skeletons
    public static ArrayList<Integer> skeletons = new ArrayList<>();
    public static void skellys(int amount) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < amount; i++) {
            skeletons.add(4);
        }
        while(!(skeletons.isEmpty()) && playerHealth > 0) {
            if (playerHealth < 0 ) {
                System.out.println("Player has died");
                break;
            }
            System.out.println("Your current health is " + playerHealth);
            System.out.println("There are " + skeletons.size() + " skeletons left");
            System.out.println("Which one do you attack? (0-" + (skeletons.size()-1));
            int choice = input.nextInt();
            mobAttack(0, 2, 11, "Zombie", choice);
            int playerDamage = attackDamage();
            System.out.println("You deal " + playerDamage + " damage to the zombie");
            int newHealth = skeletons.get(choice) - playerDamage;
            skeletons.set(choice, newHealth);
            if (skeletons.get(choice) < 0) {
                skeletons.remove(choice);
            }
            System.out.println(RED+ANSI_UNDERLINE+"Do you want to heal?"+ANSI_RESET);
            input.nextLine();
            String choice12123 = input.nextLine();
            if (caseSensitive(choice12123).equals("yes")) {
                checkAction("use healing item");
            }
            else if (caseSensitive(choice12123).equals("no")) {
                System.out.println("Okay, keep fighting");
            } 
            else {
                System.out.println("Invalid input. it is Yes or No");
            }
        }
        System.out.println(GREEN+ANSI_UNDERLINE+"all the skeltons are dead!!"+ANSI_RESET);
    }
    
    // for fighting zombies 
    public static ArrayList<Integer> zombies = new ArrayList<>();
    public static void zombies(int amount) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < amount; i++) {
            zombies.add(5);
        }
        while(!(zombies.isEmpty()) && playerHealth > 0) {
            if (playerHealth < 0 ) {
                System.out.println("Player has died");
                break;
            }
            System.out.println(RED+ANSI_UNDERLINE+"Do you want to heal? You may either only heal or attack this turn (Yes or No)"+ANSI_RESET);
            input.nextLine();
            String choice12123 = input.nextLine();
            if (caseSensitive(choice12123).equals("yes")) {
                checkAction("use healing item");
            }
            // whether they heal or not, this has to run 
            //System.out.println("Okay, keep fighting");
            
            
            System.out.println("Your current health is " + playerHealth);
            System.out.println("There are " + zombies.size() + " zombies left");
            System.out.println("Which one do you attack? (0-" + (zombies.size()-1));
            int choice = input.nextInt();
            mobAttack(0, 2, 10, "Zombie", choice);
            int playerDamage = attackDamage();
            System.out.println("You deal " + playerDamage + " damage to the zombie");
            int newHealth = zombies.get(choice) - playerDamage;
            //zombies.set(choice, newHealth);
            if (zombies.get(choice) < 0) {
                zombies.remove(choice);
            }
            zombies.set(choice, newHealth);
        }
        System.out.println(GREEN+ANSI_UNDERLINE+"all the zombies are dead!!"+ANSI_RESET);
    }

    public static void mobAttack(int mobModifier, int playerModifier, int numToRoll, String name, int currentMob) {
        if (name.equals("Skeleton")) {
            if (skeletons.get(currentMob) < 0) {
                System.out.println(RED+"Current skelly is dead"+ANSI_RESET);
                skeletons.remove(currentMob);
            } 
            else {
                int roll = rolld20();
                if ((roll + mobModifier) - playerModifier >= numToRoll) {
                    int skellyDamage = (((rolld8() + mobModifier) - playerModifier)-2);
                    System.out.println(RED+"The skeleton did " + skellyDamage + " damage to you. "+ANSI_RESET);
                    playerHealth-=skellyDamage;
                }
                else {
                    System.out.println(RED+"You dodged its attack!"+ANSI_RESET);
                }
            }
        }
        else if (name.equals("Zombie")) {
            if (zombies.get(currentMob) > 0) {
                int roll = rolld20(); 
                if (roll + mobModifier - playerModifier > numToRoll) {
                    int zomDamage = (((rolld8() + mobModifier) - playerModifier) - 2);
                    playerHealth-=zomDamage;
                    System.out.println(RED+"The zombie attacks you and deals " + zomDamage+ANSI_RESET);
                }
            }
            else {
                System.out.println(RED+"Current zombie is dead"+ANSI_RESET);
                //zombies.remove(currentMob);
            }
        }
    }
    // 
    //
    //Look Around
    public static String caseSensitive(String word) {
        String lowerCase = word.toLowerCase();
        return lowerCase;
    }
}

// this project will go into week 4
/*
make riddles for solzePuzzle 1                          DONE
make the math thing for solvePuzzle 2
go on with story
more rooms to fight in
check for secrets for armor
get armor and new sword fom zombie and skeletons room 3
*/ 