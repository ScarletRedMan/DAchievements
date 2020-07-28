package ru.dragonestia.achievement.event;

import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;
import ru.dragonestia.achievement.Achievement;
import ru.dragonestia.achievement.manager.PlayerAchievementManager;

public class PlayerCompleteAchievementEvent extends PlayerEvent {

    private final Achievement achievement;

    private final PlayerAchievementManager manager;

    private static final HandlerList handlers = new HandlerList();

    public PlayerCompleteAchievementEvent(Player player, Achievement achievement, PlayerAchievementManager manager){
        this.player = player;
        this.achievement = achievement;
        this.manager = manager;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public PlayerAchievementManager getManager() {
        return manager;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }

}
