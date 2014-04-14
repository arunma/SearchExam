package com.nutraspace.search.json.model;

/**
 * @author Arun
 */
public class CoreSearchPageResult {

    //private List<WikiSearchHit> wikiHits: SearchResult[WikiSearchHit]=new SearchResult[WikiSearchHit]()
    private SearchResult<WikiSearchHit> wikiHits;
    private SearchResult<WebSearchHit> webHits;
    private SearchResult<PmhSearchHit> pmhHits;

    public SearchResult<PmhSearchHit> getPmhHits() {
        return pmhHits;
    }

    public void setPmhHits(SearchResult<PmhSearchHit> pmhHits) {
        this.pmhHits = pmhHits;
    }

    public SearchResult<WebSearchHit> getWebHits() {
        return webHits;
    }

    public void setWebHits(SearchResult<WebSearchHit> webHits) {
        this.webHits = webHits;
    }

    public SearchResult<WikiSearchHit> getWikiHits() {
        return wikiHits;
    }

    public void setWikiHits(SearchResult<WikiSearchHit> wikiHits) {
        this.wikiHits = wikiHits;
    }



}
