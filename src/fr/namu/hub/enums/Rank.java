package fr.namu.hub.enums;

public enum Rank {
    PLAYER("player", "rank.player", "§7", "§7[Joueur] "),
    DEV("dev", "group.dev", "§6DEV §7»", "§7[§6Dev§7] §f"),

    ;



    private String name;
    private String permission;
    private String prefix;
    private String fullname;


    Rank(String name, String permission, String prefix, String fullname) {
        this.name = name;
        this.permission = permission;
        this.prefix = prefix;
        this.fullname = fullname;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }
    public void setPermission(String permission) {
        this.permission = permission;
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
