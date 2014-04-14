package com.nutraspace.search.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface for use within Spring MVC controller classes to designate that a
 * method parameter should be instantiated with the current user's account
 * details.
 * For example:
 * <pre><code>
 *{@literal @}RequestMapping("/some/path")
 * public String getResource({@literal @}CurrentUser UserDetailsImpl userDetails) {
 *    log.info(userDetails.getId());
 *    return "someview";
 * }</code></pre>
 * @see UserDetailsImpl
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}

