package ru.dragonestia.achievement;

public enum Difficulty {

    EASY("Легкое", "textures/blocks/glazed_terracotta_green"),
    NORMAL("Среднее", "textures/blocks/glazed_terracotta_cyan"),
    HARD("Сложное", "textures/blocks/glazed_terracotta_black"),
    LEGENDARY("Легендарное", "textures/blocks/glazed_terracotta_yellow");

    private final String name, icon;

    Difficulty(String name, String icon){
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

}
