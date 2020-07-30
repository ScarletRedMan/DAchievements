package ru.dragonestia.achievement;

public enum Difficulty {

    EASY("Легкое"),
    NORMAL("Среднее"),
    HARD("Сложное"),
    LEGENDARY("Легендарное");

    private final String name;

    Difficulty(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
