package ru.dragonestia.achievement;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import ru.dragonestia.achievement.command.AchievementsCommand;
import ru.dragonestia.achievement.listener.MainListener;

import java.util.HashMap;

public class DAchievements extends PluginBase {

    private static DAchievements instance;

    private Config database;

    private final HashMap<String, Achievement> achievements = new HashMap<>();

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        database = new Config("plugins/DAchievements/users.yml", Config.YAML);

        getServer().getPluginManager().registerEvents(new MainListener(), this);

        getServer().getCommandMap().register("", new AchievementsCommand());
    }

    @Override
    public void onDisable() {

    }

    public Config getDatabase(){
        return database;
    }

    public HashMap<String, Achievement> getAchievements(){
        return achievements;
    }

    public void registerAchievement(Achievement achievement){
        achievements.put(achievement.getId(), achievement);
    }

    public static DAchievements getInstance() {
        return instance;
    }

}
