package com.nutraspace.search.json.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Arun
 */
public class SearchResult<T extends SearchHit> implements Serializable {


    private Class<T> type;
    private List<T> results;
    private long resultCount;
    private long start;

    public SearchResult(){

    }
    public SearchResult(Class<T> type) {
        this.type = type;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public long getResultCount() {
        return resultCount;
    }

    public void setResultCount(long resultCount) {
        this.resultCount = resultCount;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public Class<T> getType() {
        return type;
    }

}
