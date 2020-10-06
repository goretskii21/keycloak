package org.keycloak;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class EnvUtil
{
   private static final Pattern p = Pattern.compile("[$][{]([^}]+)[}]");
   public static String replace(String val)
   {
      Matcher matcher = p.matcher(val);
      StringBuffer buf = new StringBuffer();
      while (matcher.find())
      {
         String envVar = matcher.group(1);
         String envVal = System.getProperty(envVar);
         if (envVal == null) envVal = "NOT-SPECIFIED";
         matcher.appendReplacement(buf, envVal.replace("\\", "\\\\"));
      }
      matcher.appendTail(buf);
      return buf.toString();
   }
}
