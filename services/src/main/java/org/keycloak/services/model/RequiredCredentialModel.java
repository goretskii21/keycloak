package org.keycloak.services.model;
public class RequiredCredentialModel
{
   protected String type;
   protected boolean input;
   protected boolean secret;
   public String getType()
   {
      return type;
   }
   public void setType(String type)
   {
      this.type = type;
   }
   public boolean isInput()
   {
      return input;
   }
   public void setInput(boolean input)
   {
      this.input = input;
   }
   public boolean isSecret()
   {
      return secret;
   }
   public void setSecret(boolean secret)
   {
      this.secret = secret;
   }
}
