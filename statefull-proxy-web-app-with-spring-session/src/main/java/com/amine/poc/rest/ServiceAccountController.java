package com.amine.poc.rest;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by nxuser on 07/02/2017.
 */
@RestController
@RequestMapping("/api/account")
public class ServiceAccountController
{
    private static String SESSION_ATTR_COUNTER = "SESSION_ATTR_COUNTER";

    @Secured("ROLE_READ_ACCOUNT")
    @GetMapping(value = "/infos", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAccountInfos(HttpSession session)
    {
        Integer counter = (Integer) session.getAttribute(SESSION_ATTR_COUNTER);

        if(counter == null)
        {
            counter = Integer.valueOf(1);
        }

        int ctr = counter.intValue();
        counter = counter + 1;
        session.setAttribute(SESSION_ATTR_COUNTER, counter);
        return "Account infos : call number " + ctr;
    }
}
