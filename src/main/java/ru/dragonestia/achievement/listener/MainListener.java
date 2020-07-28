package ru.dragonestia.achievement.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLoginEvent;
import ru.dragonestia.achievement.manager.PlayerAchievementManager;

public class MainListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        PlayerAchievementManager manager = new PlayerAchievementManager(player);

        if(!manager.isRegistered()) manager.reset();
    }

}
