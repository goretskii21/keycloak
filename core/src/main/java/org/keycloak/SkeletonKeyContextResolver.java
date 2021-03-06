package org.keycloak;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
@Provider
public class SkeletonKeyContextResolver implements ContextResolver<ObjectMapper>
{
   protected ObjectMapper mapper = new ObjectMapper();
   public SkeletonKeyContextResolver()
   {
      mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);
   }
   public SkeletonKeyContextResolver(boolean indent)
   {
      mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);
      if (indent)
      {
         mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
      }
   }
   @Override
   public ObjectMapper getContext(Class<?> type)
   {
      if (type.getPackage().getName().startsWith("org.jboss.resteasy.skeleton.key")) return mapper;
      return null;
   }
}
