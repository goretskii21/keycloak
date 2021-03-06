package org.keycloak.servlet;
import org.jboss.resteasy.plugins.server.servlet.ServletUtil;
import org.keycloak.AbstractOAuthClient;
import org.jboss.resteasy.spi.ResteasyUriInfo;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
public class ServletOAuthClient extends AbstractOAuthClient
{
   public void redirectRelative(String relativePath, HttpServletRequest request, HttpServletResponse response) throws IOException
   {
      ResteasyUriInfo uriInfo = ServletUtil.extractUriInfo(request, null);
      String redirect = uriInfo.getBaseUriBuilder().path(relativePath).toTemplate();
      redirect(redirect, request, response);
   }
   public void redirect(String redirectUri, HttpServletRequest request, HttpServletResponse response) throws IOException
   {
      String state = getStateCode();
      URI url = UriBuilder.fromUri(authUrl)
              .queryParam("client_id", clientId)
              .queryParam("redirect_uri", redirectUri)
              .queryParam("state", state)
              .build();
      String cookiePath = request.getContextPath();
      if (cookiePath.equals("")) cookiePath = "/";
      Cookie cookie = new Cookie(stateCookieName, state);
      cookie.setSecure(true);
      cookie.setPath(cookiePath);
      response.addCookie(cookie);
      response.sendRedirect(url.toString());
   }
   protected String getCookieValue(String name, HttpServletRequest request)
   {
      if (request.getCookies() == null) return null;
      for (Cookie cookie : request.getCookies())
      {
         if (cookie.getName().equals(name)) return cookie.getValue();
      }
      return null;
   }
   protected String getCode(HttpServletRequest request)
   {
      String query = request.getQueryString();
      if (query == null) return null;
      String[] params = query.split("&");
      for (String param : params)
      {
         int eq = param.indexOf('=');
         if (eq == -1) continue;
         String name = param.substring(0, eq);
         if (!name.equals("code")) continue;
         return param.substring(eq + 1);
      }
      return null;
   }
   public String getBearerToken(HttpServletRequest request) throws BadRequestException, InternalServerErrorException
   {
      String error = request.getParameter("error");
      if (error != null) throw new BadRequestException(new Exception("OAuth error: " + error));
      String redirectUri = request.getRequestURL().append("?").append(request.getQueryString()).toString();
      String stateCookie = getCookieValue(stateCookieName, request);
      if (stateCookie == null) throw new BadRequestException(new Exception("state cookie not set"));
      String state = request.getParameter("state");
      String code = request.getParameter("code");
      if (state == null) throw new BadRequestException(new Exception("state parameter was null"));
      if (!state.equals(stateCookie))
      {
         throw new BadRequestException(new Exception("state parameter invalid"));
      }
      if (code == null) throw new BadRequestException(new Exception("code parameter was null"));
      return resolveBearerToken(redirectUri, code);
   }
}
