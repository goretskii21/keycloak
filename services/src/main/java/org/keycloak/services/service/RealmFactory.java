package org.keycloak.services.service;
import org.keycloak.services.model.RealmManager;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.services.model.RealmModel;
import org.picketlink.idm.model.User;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
@Path("/realmfactory")
public class RealmFactory
{
   protected RealmManager adapter;
   @Context
   protected UriInfo uriInfo;
   public RealmFactory(RealmManager adapter)
   {
      this.adapter = adapter;
   }
   @POST
   @Consumes("application/json")
   public Response importDomain(RealmRepresentation rep)
   {
      RealmModel realm = createRealm(rep);
      UriBuilder builder = uriInfo.getRequestUriBuilder().path(realm.getId());
      return Response.created(builder.build())
              .type(MediaType.APPLICATION_JSON_TYPE).build();
   }
   protected RealmModel createRealm(RealmRepresentation rep)
   {
      RealmModel realm = adapter.create(rep.getRealm());
      KeyPair keyPair = null;
      try
      {
         keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
      }
      catch (NoSuchAlgorithmException e)
      {
         throw new RuntimeException(e);
      }
      realm.setPrivateKey(keyPair.getPrivate());
      realm.setPublicKey(keyPair.getPublic());
      realm.setName(rep.getRealm());
      realm.setEnabled(rep.isEnabled());
      realm.setTokenLifespan(rep.getTokenLifespan());
      realm.setAccessCodeLifespan(rep.getAccessCodeLifespan());
      realm.setSslNotRequired(rep.isSslNotRequired());
      realm.updateRealm();
      Map<String, User> userMap = new HashMap<String, User>();
      return null;
   }
}
