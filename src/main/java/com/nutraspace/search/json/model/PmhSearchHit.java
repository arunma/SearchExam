package com.nutraspace.search.json.model;

/**
 * @author Arun
 */
public class PmhSearchHit implements SearchHit{
    private String title;
    private String displayTitle;
    private String category;
    private String definition;

    private String[] altNames;
    private String[] headerUrls;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String[] getAltNames() {
        return altNames;
    }

    public void setAltNames(String[] altNames) {
        this.altNames = altNames;
    }

    public String[] getHeaderUrls() {
        return headerUrls;
    }

    public void setHeaderUrls(String[] headerUrls) {
        this.headerUrls = headerUrls;
    }
}
