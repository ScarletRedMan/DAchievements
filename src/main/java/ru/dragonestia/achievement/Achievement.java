package ru.dragonestia.achievement;

import cn.nukkit.Player;
import cn.nukkit.Server;
import ru.dragonestia.achievement.command.AchievementsCommand;
import ru.dragonestia.achievement.manager.PlayerAchievementManager;
import ru.nukkitx.forms.elements.SimpleForm;

public abstract class Achievement {

    public abstract String getId();

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getIcon();

    public abstract Difficulty getDifficulty();

    public abstract int getMaxPoints();

    public abstract void action(Player player);

    public void sendInformationForm(Player player, PlayerAchievementManager manager){
        new SimpleForm("Информация о достижении")
                .addContentLine("Название: §e" + getName() + "§f")
                .addContentLine("Прогресс: " + (isCompleted(manager)?
                        ("§2Выполнено§f") :
                        ("§e" + getPoints(manager) + "§f/§e" + getMaxPoints() + "§f")
                        ))
                .addContentLine("Сложность: §b" + getDifficulty().getName() + "§f")
                .addContentLine("Описание: §3" + getDescription() + "§f")
                .addButton("Назад")
                .send(player, (target, form, data) -> {
                    if(data == -1) return;

                    AchievementsCommand.sendMainForm(target);
                });
    }

    public int getPoints(PlayerAchievementManager manager){
        return manager.getPoints(getId());
    }

    public void addPoint(PlayerAchievementManager manager){
        manager.addPoint(getId());
    }

    public void addPoints(PlayerAchievementManager manager, int count){
        manager.addPoints(getId() ,count);
    }

    public boolean isCompleted(PlayerAchievementManager manager){
        return manager.isCompleted(getId());
    }

}
