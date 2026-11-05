public class players {
    // make the static varaibles like health and the class type
    private int attackDamage;
    private int health = 20;
    private String classType;
    private String spell;
    private int dmgMage;
    private int dmgBerzerker;
    private int dmgTank;
    private int dmgHealer;
    /*
    class types to choose from: mage, bazerker, healer, tank
    based off of hypixel skyblock dungeons
    */
    
    
    public players() {
        classType = "Mage";
    }
    public int mageGetHealth() {
        return health;
    }
    public void mageChangeHealth(int tempHealth) {
        health = tempHealth;
    }

}

