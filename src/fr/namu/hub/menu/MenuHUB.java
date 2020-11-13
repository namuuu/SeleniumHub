package fr.namu.hub.menu;

import fr.namu.hub.MainHUB;

public class MenuHUB {

    public MainHUB main;

    public NavigateMenu navigate = new NavigateMenu(this);



    public MenuHUB(MainHUB main) {
        this.main = main;
    }


}
