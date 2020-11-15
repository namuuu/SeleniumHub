package fr.namu.hub.menu;

import fr.namu.hub.MainHUB;

public class MenuHUB {

    public MainHUB main;

    public NavigateMenu navigate = new NavigateMenu(this);
    public LobbyListMenu lobbyList = new LobbyListMenu(this);
    public ParticleMenu particle = new ParticleMenu(this);



    public MenuHUB(MainHUB main) {
        this.main = main;
    }


}
