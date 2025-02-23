package com.rolfmao.upgradedcore_old.helpers;

import java.util.List;
import net.minecraft.network.chat.Component;

public class TooltipHelper {
   public static void addTWO(List<Component> list, String string, Object... object) {
      Component[] text = new Component[object.length];
      int count = 0;
      Object[] var5 = object;
      int var6 = object.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Object obj = var5[var7];
         Object txt;
         if (obj instanceof Component) {
            txt = (Component)obj;
         } else {
            txt = Component.literal(obj.toString());
         }

         text[count] = (Component)txt;
         ++count;
      }

      list.add(Component.translatable(string, (Object[])text));
   }
}
