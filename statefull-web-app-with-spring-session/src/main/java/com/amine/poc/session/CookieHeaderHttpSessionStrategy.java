package com.amine.poc.session;

import org.springframework.session.Session;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nxuser on 08/02/2017.
 */
public class CookieHeaderHttpSessionStrategy implements HttpSessionStrategy
{
    private CookieHttpSessionStrategy cookieHttpSessionStrategy = new CookieHttpSessionStrategy();

    private HeaderHttpSessionStrategy headerHttpSessionStrategy = new HeaderHttpSessionStrategy();

    @Override
    public String getRequestedSessionId(HttpServletRequest request)
    {
        if(request.getHeader("x-auth-token") != null)
        {
            return headerHttpSessionStrategy.getRequestedSessionId(request);
        }
        return cookieHttpSessionStrategy.getRequestedSessionId(request);
    }

    @Override
    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response)
    {
        if(request.getHeader("x-auth-token") != null)
        {
            headerHttpSessionStrategy.onNewSession(session, request, response);
        }
        else
        {
            cookieHttpSessionStrategy.onNewSession(session, request, response);
        }
    }

    @Override
    public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response)
    {
        if(request.getHeader("x-auth-token") != null)
        {
            headerHttpSessionStrategy.onInvalidateSession(request, response);
        }
        else
        {
            cookieHttpSessionStrategy.onInvalidateSession(request, response);
        }
    }
}
