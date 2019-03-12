package com.example.b_quest;

public class Quest {

    private String questCategory;
    private String questName;
    private String questDescription;

    public Quest() {
    }

    public Quest(String questCategory, String questName, String questDescription) {
        this.questCategory = questCategory;
        this.questName = questName;
        this.questDescription = questDescription;
    }

    public String getQuestCategory() {
        return questCategory;
    }

    public void setQuestCategory(String questCategory) {
        this.questCategory = questCategory;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }
}
