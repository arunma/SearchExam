package com.nutraspace.search.model;


import javax.persistence.*;

/**
 * @author Arun
 */
@Entity
@Table(name = "USER_RATINGS")
public class UserRating {

    public UserRating(){}

    public UserRating(String query, String url, String username, String comments, Rating rating) {
        this.query=query;
        this.url = url;
        this.username = username;
        this.comments = comments;
        this.rating = rating;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "QUERY")
    private String query;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "URL")
    private String url;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "RATING")
    @Enumerated(EnumType.STRING)
    private Rating rating;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "UserRating{" +
                "id=" + id +
                ", query='" + query + '\'' +
                ", username='" + username + '\'' +
                ", url='" + url + '\'' +
                ", comments='" + comments + '\'' +
                ", rating=" + rating +
                '}';
    }
}
