package org.keycloak;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
public class BouncyIntegration
{
   static
   {
      if (Security.getProvider("BC") == null) Security.addProvider(new BouncyCastleProvider());
   }
   public static void init()
   {
   }
}
