package org.keycloak.services.model.data;
import java.io.Serializable;
@Deprecated
public class RoleModel implements Serializable
{
   private static final long serialVersionUID = 1L;
   protected String id;
   protected String name;
   public String getId()
   {
      return id;
   }
   public void setId(String id)
   {
      this.id = id;
   }
   public String getName()
   {
      return name;
   }
   public void setName(String name)
   {
      this.name = name;
   }
}
