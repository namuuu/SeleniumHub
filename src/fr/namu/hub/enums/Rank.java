package fr.namu.hub.enums;

public enum Rank {
    PLAYER("player", 0, "§7", "§7[Joueur] "),
    BUILDER("builder", 600, "§aBUILD §7» ", "§7[§aBuild§7] §f"),
    HELPER("helper", 700, "§5HELP §7» ", "§7[§5Helper§7] §f"),
    MOD("modo", 800, "§2MOD §7» ", "§7[§2Modérateur§7] §f"),
    DEV("dev", 900, "§6DEV §7» ", "§7[§6Dev§7] §f"),
    ADMIN("admin", 1000, "§cADMIN §7» ", "§7[§cAdmin§7] §f"),

    ;



    private String name;
    private int weight;
    private String prefix;
    private String fullname;


    Rank(String name, int weight, String prefix, String fullname) {
        this.name = name;
        this.weight = weight;
        this.prefix = prefix;
        this.fullname = fullname;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }
    public void setWeight(String permission) {
        this.weight = weight;
    }

    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
