package com.oreo.website.until;

public class Article {
    int aId;
    int aAuthorId;
    String aTitle;
    String aText;
    int aAuthority;

    public int getaAuthority() {
        return aAuthority;
    }

    public void setaAuthority(int aAuthority) {
        this.aAuthority = aAuthority;
    }

    public int getaAuthorId() {
        return aAuthorId;
    }

    public int getaId() {
        return aId;
    }

    public String getaText() {
        return aText;
    }

    public String getaTitle() {
        return aTitle;
    }

    public void setaAuthorId(int aAuthorId) {
        this.aAuthorId = aAuthorId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public void setaText(String aText) {
        this.aText = aText;
    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
    }
}
