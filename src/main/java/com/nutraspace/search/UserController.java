package com.nutraspace.search;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Arun
 */

@Controller
@RequestMapping("/")
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


/*
    @RequestMapping(value = "authenticate", method = RequestMethod.POST)
    public
    @ResponseBody
    String login(@RequestParam String username, @RequestParam String password, HttpSession session) {

*//*        UserRating userRating=new UserRating(query, url, username, comments, Rating.valueOf(ratingString));



        logger.info ("Userrating before persisting : "+userRating);
        UserRating returnRating=ratingRepository.save(userRating);


        return returnRating.getRating().toString();*//*


        return null;


    }*/




    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcome(){
        logger.info ("On method WELCOME. YAYYYYYY");
        ModelAndView model = new ModelAndView();
        model.setViewName("login");

        return model;

    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        logger.debug("$$$$$$$$$$$$ YAYYYYYYYYYY. Login method ");
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }


}
