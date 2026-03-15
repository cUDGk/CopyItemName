package com.copyitemname;

public enum CopyMode {
    ENGLISH_NAME("English Name", "英語名"),
    ITEM_ID("Item ID", "アイテムID");

    public final String english;
    public final String japanese;

    CopyMode(String english, String japanese) {
        this.english = english;
        this.japanese = japanese;
    }

    public CopyMode next() {
        CopyMode[] values = values();
        return values[(ordinal() + 1) % values.length];
    }
}
