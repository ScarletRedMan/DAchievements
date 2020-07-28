package ru.dragonestia.achievement.manager;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Sound;
import cn.nukkit.utils.Config;
import ru.dragonestia.achievement.Achievement;
import ru.dragonestia.achievement.DAchievements;
import ru.dragonestia.achievement.event.PlayerCompleteAchievementEvent;

import java.util.ArrayList;

public class PlayerAchievementManager {

    private final Player player;

    public PlayerAchievementManager(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isRegistered(){
        return DAchievements.getInstance().getDatabase().exists(player.getName().toLowerCase());
    }

    public void reset(){
        Config db = DAchievements.getInstance().getDatabase();
        String playerName = player.getName().toLowerCase();

        db.set(playerName + ".achievements", new ArrayList<String>());
        db.set(playerName + ".completed", 0);
        db.save();
    }

    public int getPoints(String achievementId){
        return DAchievements.getInstance().getDatabase().getInt(player.getName().toLowerCase() + ".achievements." + achievementId.toLowerCase());
    }

    public void addPoint(String achievementId){
        addPoints(achievementId, 1);
    }

    public void addPoints(String achievementId, int count){
        Config db = DAchievements.getInstance().getDatabase();
        String playerName = player.getName().toLowerCase();
        int points = db.getInt(playerName + ".achievements." + achievementId);

        try{
            Achievement achievement = DAchievements.getInstance().getAchievements().get(achievementId);

            if(achievement.getMaxPoints() <= points){
                return;
            }

            db.set(playerName + ".achievements." + achievementId, points + count);

            if(achievement.getMaxPoints() <= points + count) {
                PlayerCompleteAchievementEvent event = new PlayerCompleteAchievementEvent(player, achievement, this);
                Server.getInstance().getPluginManager().callEvent(event);

                achievement.action(player);
                db.set(playerName + ".completed", db.getInt(playerName + ".completed") + 1);

                player.sendTitle("§l§b::§fДостижение выполнено§b::§f", "§3-- §7" + achievement.getName() + "§3 --");
                player.getLevel().addSound(player, Sound.BEACON_POWER, 1F, 1F, player);
                for(Player player: Server.getInstance().getOnlinePlayers().values()){
                    player.sendMessage("§7§lИгрок §3" + this.player.getName() + "§7 получил достижение §b" + achievement.getName() + "§7.");
                }
            }
            db.save();
        }catch (NullPointerException ex){
            Server.getInstance().getLogger().error("Невозможно найти достижение " + achievementId + ", тк оно не существует!");
        }
    }

    public boolean isCompleted(String achievementId){
        try{
            Achievement achievement = DAchievements.getInstance().getAchievements().get(achievementId);

            return DAchievements.getInstance().getDatabase().getInt(player.getName().toLowerCase() + ".achievements." + achievementId.toLowerCase()) >= achievement.getMaxPoints();
        }catch (NullPointerException ex){
            Server.getInstance().getLogger().error("Невозможно найти достижение " + achievementId + ", тк оно не существует!");
            return false;
        }
    }

    public int getCompletedAchievementsCount(){
        return DAchievements.getInstance().getDatabase().getInt(player.getName().toLowerCase() + ".completed");
    }

}
