public class players {
    // make the static varaibles like health and the class type
    private Int attackDamage;
    private Int health;
    private String classType;
    private String spell;
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
    public int mageChangeHealth(int tempHealth) {
        return health = tempHealth;
    }

}

