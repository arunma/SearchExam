package com.nutraspace.search;


import com.nutraspace.search.dao.RatingRepository;
import com.nutraspace.search.json.JSONUtils;
import com.nutraspace.search.json.model.CoreSearchPageResult;
import com.nutraspace.search.json.model.SearchResult;
import com.nutraspace.search.json.model.WebSearchHit;
import com.nutraspace.search.model.Rating;
import com.nutraspace.search.model.UserRating;
import com.nutraspace.search.security.CurrentUser;
import com.nutraspace.search.security.UserDetailsImpl;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Arun
 */

@Controller
@RequestMapping(value = "/search")
public class SearchController {


    @Value("${search.url}")
    private String SEARCH_URL;

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private RatingRepository ratingRepository;




    @RequestMapping(value="searchpage", method = RequestMethod.GET)
    public String search(ModelMap model) {

        return "search/searchpage";

    }


    @RequestMapping(value = "fetch", method = RequestMethod.GET)
    public
    @ResponseBody
    String fetch(@RequestParam String query, @RequestParam Integer page, HttpSession session) {



        logger.debug("Principal : "+SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        logger.info("Incoming query Parameters : " + query + ":: " + page);

        //String username=(String)session.getAttribute("username");
        //if (username==null) username="dummyuser";


        String serverQuery = null;
        try {
            serverQuery = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }

        String start = "0";
        if (page == 1) {
            start = "0";
        } else {
            start = String.valueOf((page - 1) * 10);
        }

        ClientResponse response = getResponse(serverQuery, start, "10");
        String responseJson = response.getEntity(String.class);

        logger.info("Response JSON from pageSearch : " + responseJson);

        CoreSearchPageResult homePageResult= JSONUtils.toObject(responseJson, CoreSearchPageResult.class);
        final SearchResult<WebSearchHit> wikiHits = homePageResult.getWebHits();
        //final List<WikiSearchHit> webSearchHits = wikiHits.getResults();

        SearchResult<WebSearchHit> webHitsWithRating=populateRatingForHits(user.getUsername(), wikiHits);
        homePageResult.setWebHits(webHitsWithRating);

        //logger.debug("Wiki search Hits from pageSearch :"+webSearchHits);

        return JSONUtils.toJSON(homePageResult);
        //return responseJson;

    }

    private SearchResult<WebSearchHit> populateRatingForHits(String username, SearchResult<WebSearchHit> webSearchHits) {


        List<WebSearchHit> iterHits= webSearchHits.getResults();

        if (iterHits.isEmpty()) return webSearchHits;



        LinkedHashMap<String,WebSearchHit> lookupMap=new LinkedHashMap<String, WebSearchHit>();
        List<String> urls=new ArrayList<String>();

        for (WebSearchHit eachHit : iterHits) {

            lookupMap.put(eachHit.getUrl(), eachHit);
            urls.add(eachHit.getUrl());
        }

        System.out.println("All Urls : "+urls);

        List<UserRating> userRatings=ratingRepository.findByUrlIn(urls);

        if (!userRatings.isEmpty()){

            for (UserRating userRating : userRatings) {

                if (lookupMap.containsKey(userRating.getUrl())){
                    WebSearchHit webSearchHit = lookupMap.get(userRating.getUrl());
                    webSearchHit.setRating(userRating.getRating().toString());

                    lookupMap.put(userRating.getUrl(), webSearchHit);
                }

            }
        }

        System.out.println("All User Ratings : " + userRatings);

        List<WebSearchHit> returnResults=new ArrayList<WebSearchHit>();
        returnResults.addAll(lookupMap.values());
        webSearchHits.setResults(returnResults);

        return webSearchHits;

    }


    @RequestMapping(value = "persist", method = RequestMethod.POST)
    public
    @ResponseBody
    String persistRatings(@CurrentUser UserDetailsImpl currentUser, @RequestParam String query, @RequestParam String url, @RequestParam String ratingString, @RequestParam String comments, HttpSession session) {

        String commentsStripped= StringUtils.stripToEmpty(comments);

        UserRating userRating=new UserRating(query, url, currentUser.getUsername(), comments, Rating.valueOf(ratingString));



        logger.info ("Userrating before persisting : "+userRating);
        UserRating returnRating=ratingRepository.save(userRating);


        return returnRating.getRating().toString();



    }


    private ClientResponse getResponse(String serverQuery, String start, String size) throws ClientHandlerException, UniformInterfaceException {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        String searchUrl = SEARCH_URL.replace("<SEARCH_TERM>", serverQuery);
        searchUrl = searchUrl.replace("<START_PAGE>", start);
        searchUrl = searchUrl.replace("<SEARCH_SIZE>", size);

        logger.debug("Search url : " + searchUrl);
        WebResource queryResource = client.resource(searchUrl);
        final ClientResponse response = queryResource.type("application/json").post(ClientResponse.class);
        return response;
    }
}
