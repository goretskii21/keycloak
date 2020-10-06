package org.keycloak;
import java.io.Serializable;
public class SkeletonKeySession implements Serializable
{
   protected String token;
   protected transient ResourceMetadata metadata;
   public SkeletonKeySession()
   {
   }
   public SkeletonKeySession(String token, ResourceMetadata metadata)
   {
      this.token = token;
      this.metadata = metadata;
   }
   public String getToken()
   {
      return token;
   }
   public ResourceMetadata getMetadata()
   {
      return metadata;
   }
}
