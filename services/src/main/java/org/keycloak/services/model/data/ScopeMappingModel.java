package org.keycloak.services.model.data;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Deprecated
public class ScopeMappingModel implements Serializable
{
   private static final long serialVersionUID = 1L;
   protected String id;
   protected String userid;
   protected Set<String> roles = new HashSet<String>();
   public String getId()
   {
      return id;
   }
   public void setId(String id)
   {
      this.id = id;
   }
   public String getUserid()
   {
      return userid;
   }
   public void setUserid(String userid)
   {
      this.userid = userid;
   }
   public Set<String> getRoles()
   {
      return roles;
   }
   public void setRoles(Set<String> roles)
   {
      this.roles = roles;
   }
}
