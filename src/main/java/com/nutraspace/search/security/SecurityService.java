package com.nutraspace.search.security;

import com.nutraspace.search.dao.UserRepository;
import com.nutraspace.search.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 * @author Arun
 */
@Service("securityService")
public class SecurityService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        try {
            logger.info("Incoming usename : "+username);
            User user = userRepository.findByUsername(username);

            if (user==null) throw new NoResultException("User isn't available on the database");

            UserDetailsImpl userDetails = new UserDetailsImpl(user);
            return userDetails;

        } catch (NoResultException e) {
            logger.warn("User not found: " + username, e);
            throw new UsernameNotFoundException("User " + username + " does not exist");
        } catch (PersistenceException e) {
            logger.error("Unable to query for username: " + username, e);
            throw new DataRetrievalFailureException("Unable to retrieve user details", e);
        }

    }
}
