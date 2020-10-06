package org.keycloak.representations.idm;
import java.util.HashSet;
import java.util.Set;
public class RoleMappingRepresentation
{
   protected String self; 
   protected String username;
   protected Set<String> roles;
   protected Set<String> surrogates;
   public String getSelf()
   {
      return self;
   }
   public void setSelf(String self)
   {
      this.self = self;
   }
   public String getUsername()
   {
      return username;
   }
   public void setUsername(String username)
   {
      this.username = username;
   }
   public Set<String> getRoles()
   {
      return roles;
   }
   public Set<String> getSurrogates()
   {
      return surrogates;
   }
   public void setSurrogates(Set<String> surrogates)
   {
      this.surrogates = surrogates;
   }
   public RoleMappingRepresentation surrogate(String surrogate)
   {
      if (this.surrogates == null) this.surrogates = new HashSet<String>();
      this.surrogates.add(surrogate);
      return this;
   }
   public void setRoles(Set<String> roles)
   {
      this.roles = roles;
   }
   public RoleMappingRepresentation role(String role)
   {
      if (this.roles == null) this.roles = new HashSet<String>();
      this.roles.add(role);
      return this;
   }
}
