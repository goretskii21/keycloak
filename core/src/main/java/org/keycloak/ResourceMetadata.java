package org.keycloak;
import java.security.KeyStore;
import java.security.PublicKey;
public class ResourceMetadata
{
   protected String resourceName;
   protected String realm;
   protected KeyStore clientKeystore;
   protected String clientKeyPassword;
   protected KeyStore truststore;
   protected PublicKey realmKey;
   public String getResourceName()
   {
      return resourceName;
   }
   public String getRealm()
   {
      return realm;
   }
   public KeyStore getClientKeystore()
   {
      return clientKeystore;
   }
   public String getClientKeyPassword()
   {
      return clientKeyPassword;
   }
   public void setClientKeyPassword(String clientKeyPassword)
   {
      this.clientKeyPassword = clientKeyPassword;
   }
   public KeyStore getTruststore()
   {
      return truststore;
   }
   public PublicKey getRealmKey()
   {
      return realmKey;
   }
   public void setResourceName(String resourceName)
   {
      this.resourceName = resourceName;
   }
   public void setRealm(String realm)
   {
      this.realm = realm;
   }
   public void setClientKeystore(KeyStore clientKeystore)
   {
      this.clientKeystore = clientKeystore;
   }
   public void setTruststore(KeyStore truststore)
   {
      this.truststore = truststore;
   }
   public void setRealmKey(PublicKey realmKey)
   {
      this.realmKey = realmKey;
   }
}
