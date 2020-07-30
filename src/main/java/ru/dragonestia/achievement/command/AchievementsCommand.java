package ru.dragonestia.achievement.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import ru.dragonestia.achievement.Achievement;
import ru.dragonestia.achievement.DAchievements;
import ru.dragonestia.achievement.manager.PlayerAchievementManager;
import ru.nukkitx.forms.elements.ImageType;
import ru.nukkitx.forms.elements.SimpleForm;

import java.util.ArrayList;

public class AchievementsCommand extends Command {


    public AchievementsCommand() {
        super("achievements", "Достижения", "/achievements");
    }

    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(commandSender instanceof Player){
            sendMainForm((Player) commandSender);
            return true;
        }

        commandSender.sendMessage("Данную команду можно использовать только в игре.");
        return false;
    }

    public static void sendMainForm(Player player){
        SimpleForm form = new SimpleForm("Достижения", "Выберите ниже из списка достижение чтобы узнать информацию о нем.");
        PlayerAchievementManager manager = new PlayerAchievementManager(player);

        for(Achievement achievement: DAchievements.getInstance().getAchievements().values()){
            form.addButton(achievement.getName(), ImageType.PATH, manager.isCompleted(achievement.getId())? achievement.getIcon() : "textures/ui/lock_color");
        }

        form.send(player, (target, targetForm, data) -> {
            if(data == -1) return;

            new ArrayList<>(DAchievements.getInstance().getAchievements().values()).get(data).sendInformationForm(player, manager);
        });
    }

}
