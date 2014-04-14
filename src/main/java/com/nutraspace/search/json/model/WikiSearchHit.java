package com.nutraspace.search.json.model;

/**
 * @author Arun
 */
public class WikiSearchHit implements SearchHit{

    private String id;
    private String title;
    private String topic;
    private String teaser;
    private String url;
    private String year;
    private String month;
    private String author;
    private String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "WikiSearchHit{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", topic='" + topic + '\'' +
                ", teaser='" + teaser + '\'' +
                ", url='" + url + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
