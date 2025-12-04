package com.applydigital.application.model;

public enum NewsEnum {
    AUTHOR("author"),
    COMMENT_TEXT("comment_text"),
    STORY_TITLE("story_title"),
    STORY_URL("story_url"),
    OBJECT_ID("objectID"),
    PARENT_ID("parent_id"),
    STORY_ID("story_id"),
    TAGS("_tags"),
    HITS("hits");

    private final String key;

    NewsEnum(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}