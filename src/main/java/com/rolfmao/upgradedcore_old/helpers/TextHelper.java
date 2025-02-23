package com.rolfmao.upgradedcore_old.helpers;

import net.minecraft.network.chat.Component;

public class TextHelper {
   public static Component TCWO(String string, Object... object) {
      Component[] text = new Component[object.length];
      int count = 0;
      Object[] var4 = object;
      int var5 = object.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         Object obj = var4[var6];
         Object txt;
         if (obj instanceof Component) {
            txt = (Component)obj;
         } else {
            txt = Component.literal(obj.toString());
         }

         text[count] = (Component)txt;
         ++count;
      }

      return Component.translatable(string, (Object[])text);
   }
}
