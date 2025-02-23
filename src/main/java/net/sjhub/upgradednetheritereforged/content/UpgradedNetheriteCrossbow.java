package net.sjhub.upgradednetheritereforged.content;

import com.google.common.collect.Lists;
import com.rolfmao.upgradedcore_old.helpers.TooltipHelper;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.init.ModItems;
import net.sjhub.upgradednetheritereforged.utils.ToolUtil;
import net.sjhub.upgradednetheritereforged.utils.check.CorruptUtil;
import net.sjhub.upgradednetheritereforged.utils.check.EchoUtil;
import net.sjhub.upgradednetheritereforged.utils.check.EnderUtil;
import net.sjhub.upgradednetheritereforged.utils.check.FeatherUtil;
import net.sjhub.upgradednetheritereforged.utils.check.FireUtil;
import net.sjhub.upgradednetheritereforged.utils.check.GoldUtil;
import net.sjhub.upgradednetheritereforged.utils.check.PhantomUtil;
import net.sjhub.upgradednetheritereforged.utils.check.PoisonUtil;
import net.sjhub.upgradednetheritereforged.utils.check.WaterUtil;
import net.sjhub.upgradednetheritereforged.utils.check.WitherUtil;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class UpgradedNetheriteCrossbow extends CrossbowItem {
   private boolean startSoundPlayed = false;
   private boolean midLoadSoundPlayed = false;

   public UpgradedNetheriteCrossbow(Properties properties) {
      super(properties);
   }

   private static boolean tryLoadProjectiles(LivingEntity livingEntity, ItemStack itemStack) {
      int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MULTISHOT, itemStack);
      int j = i == 0 ? 1 : 3;
      boolean flag = livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild;
      ItemStack itemstack = livingEntity.getProjectile(itemStack);
      ItemStack itemstack1 = itemstack.copy();

      for(int k = 0; k < j; ++k) {
         if (k > 0) {
            itemstack = itemstack1.copy();
         }

         if (itemstack.isEmpty() && flag) {
            itemstack = new ItemStack(Items.ARROW);
            itemstack1 = itemstack.copy();
         }

         if (!loadProjectile(livingEntity, itemStack, itemstack, k > 0, flag)) {
            return false;
         }
      }

      return true;
   }

   private static boolean loadProjectile(LivingEntity livingEntity, ItemStack itemStack, ItemStack itemStack1, boolean p_220023_3_, boolean p_220023_4_) {
      if (itemStack1.isEmpty()) {
         return false;
      } else {
         boolean flag = p_220023_4_ && itemStack1.getItem() instanceof ArrowItem;
         ItemStack itemstack;
         if (!flag && !p_220023_4_ && !p_220023_3_) {
            itemstack = itemStack1.split(1);
            if (itemStack1.isEmpty() && livingEntity instanceof Player) {
               ((Player)livingEntity).getInventory().removeItem(itemStack1);
            }
         } else {
            itemstack = itemStack1.copy();
         }

         addChargedProjectile(itemStack, itemstack);
         return true;
      }
   }

   public static boolean isCharged(ItemStack itemStack) {
      CompoundTag compoundnbt = itemStack.getTag();
      return compoundnbt != null && compoundnbt.getBoolean("Charged");
   }

   public static void setCharged(ItemStack itemStack, boolean p_220011_1_) {
      CompoundTag compoundnbt = itemStack.getOrCreateTag();
      compoundnbt.putBoolean("Charged", p_220011_1_);
   }

   private static void addChargedProjectile(ItemStack itemStack, ItemStack itemStack1) {
      CompoundTag compoundnbt = itemStack.getOrCreateTag();
      ListTag listnbt;
      if (compoundnbt.contains("ChargedProjectiles", 9)) {
         listnbt = compoundnbt.getList("ChargedProjectiles", 10);
      } else {
         listnbt = new ListTag();
      }

      CompoundTag compoundnbt1 = new CompoundTag();
      itemStack1.save(compoundnbt1);
      listnbt.add(compoundnbt1);
      compoundnbt.put("ChargedProjectiles", listnbt);
   }

   private static List<ItemStack> getChargedProjectiles(ItemStack itemStack) {
      List<ItemStack> list = Lists.newArrayList();
      CompoundTag compoundnbt = itemStack.getTag();
      if (compoundnbt != null && compoundnbt.contains("ChargedProjectiles", 9)) {
         ListTag listnbt = compoundnbt.getList("ChargedProjectiles", 10);
         if (listnbt != null) {
            for(int i = 0; i < listnbt.size(); ++i) {
               CompoundTag compoundnbt1 = listnbt.getCompound(i);
               list.add(ItemStack.of(compoundnbt1));
            }
         }
      }

      return list;
   }

   private static void clearChargedProjectiles(ItemStack itemStack) {
      CompoundTag compoundnbt = itemStack.getTag();
      if (compoundnbt != null) {
         ListTag listnbt = compoundnbt.getList("ChargedProjectiles", 9);
         listnbt.clear();
         compoundnbt.put("ChargedProjectiles", listnbt);
      }

   }

   private static void shootProjectile(Level level, LivingEntity livingEntity, InteractionHand interactionHand, ItemStack itemStack, ItemStack itemStack1, float p_220016_5_, boolean p_220016_6_, float p_220016_7_, float p_220016_8_, float p_220016_9_) {
      if (livingEntity instanceof Player) {
         Player playerentity = (Player)livingEntity;
         if (!level.isClientSide) {
            boolean flag = itemStack1.getItem() == Items.FIREWORK_ROCKET;
            Object projectileentity;
            if (flag) {
               projectileentity = new FireworkRocketEntity(level, itemStack1, livingEntity, livingEntity.getX(), livingEntity.getEyeY() - 0.15000000596046448D, livingEntity.getZ(), true);
            } else {
               projectileentity = getArrow(level, livingEntity, itemStack, itemStack1);
               if (p_220016_6_ || p_220016_9_ != 0.0F) {
                  ((AbstractArrow)projectileentity).pickup = Pickup.CREATIVE_ONLY;
               }
            }

            if (livingEntity instanceof CrossbowAttackMob) {
               CrossbowAttackMob icrossbowuser = (CrossbowAttackMob)livingEntity;
               icrossbowuser.shootCrossbowProjectile(icrossbowuser.getTarget(), itemStack, (Projectile)projectileentity, p_220016_9_);
            } else {
               Vec3 vec31 = livingEntity.getUpVector(1.0F);
               Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double)(p_220016_9_ * 0.017453292F), vec31.x, vec31.y, vec31.z);
               Vec3 vec3 = livingEntity.getViewVector(1.0F);
               Vector3f vector3f = vec3.toVector3f().rotate(quaternionf);
               ((Projectile)projectileentity).shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), p_220016_7_, p_220016_8_);
            }

            itemStack.hurtAndBreak(flag ? 3 : 1, livingEntity, (p_220017_1_) -> {
               p_220017_1_.broadcastBreakEvent(interactionHand);
            });
            if (itemStack.getItem() == ModItems.GOLD_UPGRADED_NETHERITE_CROSSBOW.get()) {
               ((Projectile)projectileentity).addTag("GoldUpgradedNetheriteBow");
               if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MOB_LOOTING, itemStack) > 0) {
                  ((Projectile)projectileentity).getPersistentData().putInt("LootingGoldUpgradedNetheriteBow", EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MOB_LOOTING, itemStack));
               }
            }

            if (itemStack.getItem() == ModItems.FIRE_UPGRADED_NETHERITE_CROSSBOW.get()) {
               ((Projectile)projectileentity).addTag("FireUpgradedNetheriteBow");
               if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemStack) > 0) {
                  ((Projectile)projectileentity).addTag("FlameFireUpgradedNetheriteBow");
                  ((Projectile)projectileentity).setSecondsOnFire(100);
               }
            }

            if (itemStack.getItem() == ModItems.ENDER_UPGRADED_NETHERITE_CROSSBOW.get()) {
               ((Projectile)projectileentity).addTag("EnderUpgradedNetheriteBow");
               if (itemStack.getOrCreateTag().getBoolean("UpgradedNetherite_Tagged")) {
                  ((Projectile)projectileentity).getPersistentData().putIntArray("UpgradedNetherite_Position", itemStack.getOrCreateTag().getIntArray("UpgradedNetherite_Position"));
                  ((Projectile)projectileentity).getPersistentData().putString("UpgradedNetherite_Dimension", itemStack.getOrCreateTag().getString("UpgradedNetherite_Dimension"));
                  ((Projectile)projectileentity).getPersistentData().putBoolean("UpgradedNetherite_Tagged", itemStack.getOrCreateTag().getBoolean("UpgradedNetherite_Tagged"));
               }
            }

            if (itemStack.getItem() == ModItems.WATER_UPGRADED_NETHERITE_CROSSBOW.get()) {
               ((Projectile)projectileentity).addTag("WaterUpgradedNetheriteBow");
            }

            if (itemStack.getItem() == ModItems.WITHER_UPGRADED_NETHERITE_CROSSBOW.get()) {
               ((Projectile)projectileentity).addTag("WitherUpgradedNetheriteBow");
            }

            if (itemStack.getItem() == ModItems.POISON_UPGRADED_NETHERITE_CROSSBOW.get()) {
               ((Projectile)projectileentity).addTag("PoisonUpgradedNetheriteBow");
            }

            if (itemStack.getItem() == ModItems.PHANTOM_UPGRADED_NETHERITE_CROSSBOW.get()) {
               ((Projectile)projectileentity).addTag("PhantomUpgradedNetheriteBow");
            }

            if (itemStack.getItem() == ModItems.FEATHER_UPGRADED_NETHERITE_CROSSBOW.get()) {
               ((Projectile)projectileentity).addTag("FeatherUpgradedNetheriteBow");
            }

            if (itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_CROSSBOW.get()) {
               ((Projectile)projectileentity).addTag("CorruptUpgradedNetheriteBow");
               ((Projectile)projectileentity).getPersistentData().putInt("LootingCorruptUpgradedNetheriteBow", CorruptUtil.intWearingCorrupt(playerentity, true));
            }

            if (itemStack.getItem() == ModItems.ECHO_UPGRADED_NETHERITE_CROSSBOW.get()) {
               ((Projectile)projectileentity).addTag("EchoUpgradedNetheriteBow");
            }

            level.addFreshEntity((Entity)projectileentity);
            level.playSound((Player)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, p_220016_5_);
         }
      }

   }

   private static AbstractArrow getArrow(Level level, LivingEntity livingEntity, ItemStack itemStack, ItemStack itemStack1) {
      ArrowItem arrowitem = (ArrowItem)(itemStack1.getItem() instanceof ArrowItem ? itemStack1.getItem() : Items.ARROW);
      AbstractArrow abstractarrowentity = arrowitem.createArrow(level, itemStack1, livingEntity);
      if (livingEntity instanceof Player) {
         abstractarrowentity.setCritArrow(true);
      }

      abstractarrowentity.setSoundEvent(SoundEvents.CROSSBOW_HIT);
      abstractarrowentity.setShotFromCrossbow(true);
      int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PIERCING, itemStack);
      if (i > 0) {
         abstractarrowentity.setPierceLevel((byte)i);
      }

      return abstractarrowentity;
   }

   public static void performShooting(Level level, LivingEntity livingEntity, InteractionHand interactionHand, ItemStack itemStack, float p_220014_4_, float p_220014_5_) {
      List<ItemStack> list = getChargedProjectiles(itemStack);
      float[] afloat = getShotPitches(livingEntity.getRandom());

      for(int i = 0; i < list.size(); ++i) {
         ItemStack itemstack = (ItemStack)list.get(i);
         boolean flag = livingEntity instanceof Player && ((Player)livingEntity).getAbilities().instabuild;
         if (!itemstack.isEmpty()) {
            if (i == 0) {
               shootProjectile(level, livingEntity, interactionHand, itemStack, itemstack, afloat[i], flag, p_220014_4_, p_220014_5_, 0.0F);
            } else if (i == 1) {
               shootProjectile(level, livingEntity, interactionHand, itemStack, itemstack, afloat[i], flag, p_220014_4_, p_220014_5_, -10.0F);
            } else if (i == 2) {
               shootProjectile(level, livingEntity, interactionHand, itemStack, itemstack, afloat[i], flag, p_220014_4_, p_220014_5_, 10.0F);
            }
         }
      }

      onCrossbowShot(level, livingEntity, itemStack);
   }

   private static float[] getShotPitches(RandomSource random) {
      boolean flag = random.nextBoolean();
      return new float[]{1.0F, getRandomShotPitch(flag, random), getRandomShotPitch(!flag, random)};
   }

   private static float getRandomShotPitch(boolean p_220032_0_, RandomSource random) {
      float f = p_220032_0_ ? 0.63F : 0.43F;
      return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
   }

   private static void onCrossbowShot(Level level, LivingEntity livingEntity, ItemStack itemStack) {
      if (livingEntity instanceof ServerPlayer) {
         ServerPlayer serverplayerentity = (ServerPlayer)livingEntity;
         if (!level.isClientSide) {
            CriteriaTriggers.SHOT_CROSSBOW.trigger(serverplayerentity, itemStack);
         }

         serverplayerentity.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
      }

      clearChargedProjectiles(itemStack);
   }

   public static int getChargeDuration(ItemStack p_220026_0_) {
      int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, p_220026_0_);
      return i == 0 ? 25 : 25 - 5 * i;
   }

   private static float getPowerForTime(int p_220031_0_, ItemStack itemStack) {
      float f = (float)p_220031_0_ / (float)getChargeDuration(itemStack);
      if (f > 1.0F) {
         f = 1.0F;
      }

      return f;
   }

   private static float getShootingPower(ItemStack itemStack) {
      return itemStack.getItem() == Items.CROSSBOW && containsChargedProjectile(itemStack, Items.FIREWORK_ROCKET) ? 2.4F : 4.725F;
   }

   public boolean m_6832_(ItemStack toRepair, ItemStack repair) {
      return Items.NETHERITE_INGOT.equals(repair.getItem());
   }

   public boolean canApplyAtEnchantingTable(ItemStack itemStack, Enchantment enchantment) {
      if (itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_CROSSBOW.get() && enchantment == Enchantments.MENDING) {
         return false;
      } else if ((itemStack.getItem() == ModItems.GOLD_UPGRADED_NETHERITE_CROSSBOW.get() || itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_CROSSBOW.get()) && enchantment == Enchantments.MOB_LOOTING) {
         return true;
      } else {
         return itemStack.getItem() == ModItems.FIRE_UPGRADED_NETHERITE_CROSSBOW.get() && enchantment == Enchantments.FLAMING_ARROWS ? true : enchantment.category.canEnchant(itemStack.getItem());
      }
   }

   public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slot, boolean p_77663_5_) {
      if (itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_CROSSBOW.get() && itemStack.isEnchanted() && EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MENDING, itemStack) > 0) {
         Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
         if (enchantments.containsKey(Enchantments.MENDING)) {
            enchantments.remove(Enchantments.MENDING);
            EnchantmentHelper.setEnchantments(enchantments, itemStack);
         }
      }

   }

   public InteractionResult useOn(UseOnContext context) {
      InteractionResult iResult = InteractionResult.PASS;
      if (context.getPlayer() != null && context.getPlayer().isCrouching() && EnderUtil.isEnderRangedWeapon(context.getItemInHand()) && !context.getPlayer().getCooldowns().isOnCooldown(context.getItemInHand().getItem())) {
         iResult = ToolUtil.EnderSetTag(context);
      }

      return iResult == InteractionResult.PASS ? super.useOn(context) : iResult;
   }

   public Predicate<ItemStack> getSupportedHeldProjectiles() {
      return ARROW_OR_FIREWORK;
   }

   public Predicate<ItemStack> getAllSupportedProjectiles() {
      return ARROW_ONLY;
   }

   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
      ItemStack itemstack = player.getItemInHand(interactionHand);
      if (isCharged(itemstack)) {
         performShooting(level, player, interactionHand, itemstack, getShootingPower(itemstack), 1.0F);
         setCharged(itemstack, false);
         return InteractionResultHolder.consume(itemstack);
      } else if (!player.getProjectile(itemstack).isEmpty()) {
         if (!isCharged(itemstack)) {
            this.startSoundPlayed = false;
            this.midLoadSoundPlayed = false;
            player.startUsingItem(interactionHand);
         }

         return InteractionResultHolder.consume(itemstack);
      } else {
         return InteractionResultHolder.fail(itemstack);
      }
   }

   public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int timeLeft) {
      int i = this.getUseDuration(itemStack) - timeLeft;
      float f = getPowerForTime(i, itemStack);
      if (f >= 1.0F && !isCharged(itemStack) && tryLoadProjectiles(livingEntity, itemStack)) {
         setCharged(itemStack, true);
         SoundSource soundcategory = livingEntity instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
         level.playSound((Player)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundcategory, 1.0F, 1.0F / (level.random.nextFloat() * 0.5F + 1.0F) + 0.2F);
      }

   }

   public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int p_219972_4_) {
      if (!level.isClientSide) {
         int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, itemStack);
         SoundEvent soundevent = this.getStartSound(i);
         SoundEvent soundevent1 = i == 0 ? SoundEvents.CROSSBOW_LOADING_MIDDLE : null;
         float f = (float)(itemStack.getUseDuration() - p_219972_4_) / (float)getChargeDuration(itemStack);
         if (f < 0.2F) {
            this.startSoundPlayed = false;
            this.midLoadSoundPlayed = false;
         }

         if (f >= 0.2F && !this.startSoundPlayed) {
            this.startSoundPlayed = true;
            level.playSound((Player)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), soundevent, SoundSource.PLAYERS, 0.5F, 1.0F);
         }

         if (f >= 0.5F && soundevent1 != null && !this.midLoadSoundPlayed) {
            this.midLoadSoundPlayed = true;
            level.playSound((Player)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), soundevent1, SoundSource.PLAYERS, 0.5F, 1.0F);
         }
      }

   }

   public int getUseDuration(ItemStack itemStack) {
      return getChargeDuration(itemStack) + 3;
   }

   public UseAnim getUseAnimation(ItemStack itemStack) {
      return UseAnim.CROSSBOW;
   }

   private SoundEvent getStartSound(int p_220025_1_) {
      switch(p_220025_1_) {
      case 1:
         return SoundEvents.CROSSBOW_QUICK_CHARGE_1;
      case 2:
         return SoundEvents.CROSSBOW_QUICK_CHARGE_2;
      case 3:
         return SoundEvents.CROSSBOW_QUICK_CHARGE_3;
      default:
         return SoundEvents.CROSSBOW_LOADING_START;
      }
   }

   @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
      List<ItemStack> list = getChargedProjectiles(itemStack);
      if (!UpgradedNetheriteConfig.DisableTooltips) {
         Item crossbow = itemStack.getItem();
         if (crossbow != ModItems.NETHERITE_CROSSBOW.get()) {
            if (Screen.hasShiftDown()) {
               float EnchantBonus;
               Map enchantments;
               int EnchantLevel;
               if (GoldUtil.isGoldRangedWeapon(itemStack) && (UpgradedNetheriteConfig.EnableDamageBonusGoldWeapon || UpgradedNetheriteConfig.EnableLootingBonus)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnableDamageBonusGoldWeapon) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusGoldWeapon + "%"});
                  }

                  if (UpgradedNetheriteConfig.EnableLootingBonus) {
                     EnchantBonus = 0.0F;
                     enchantments = EnchantmentHelper.getEnchantments(itemStack);
                     if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.MOB_LOOTING)) {
                        EnchantLevel = (Integer)enchantments.get(Enchantments.MOB_LOOTING);
                        EnchantBonus = (float)EnchantLevel;
                     }

                     if (EnchantBonus >= 3.0F) {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon2.TT", new Object[]{"§6" + (UpgradedNetheriteConfig.LootingBonus + UpgradedNetheriteConfig.LootingEnchantBonus)});
                     } else {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.LootingBonus});
                        if (!(EnchantBonus > 0.0F)) {
                           tooltip.add(Component.translatable("upgradednetherite.Gold_Bow.TT"));
                        }

                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon3.TT", new Object[]{"§d" + UpgradedNetheriteConfig.LootingEnchantBonus});
                     }
                  }
               } else if (!FireUtil.isFireRangedWeapon(itemStack) || !UpgradedNetheriteConfig.EnableDamageBonusFireWeapon && !UpgradedNetheriteConfig.EnableAutoSmelt) {
                  if (EnderUtil.isEnderRangedWeapon(itemStack) && (UpgradedNetheriteConfig.EnablePreventTeleport || UpgradedNetheriteConfig.EnableTeleportChest)) {
                     if (UpgradedNetheriteConfig.EnablePreventTeleport) {
                        tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                        tooltip.add(Component.translatable("upgradednetherite.OnHit.TT"));
                        tooltip.add(Component.translatable("upgradednetherite.Ender_Weapon.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableTeleportChest) {
                        tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                        tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                        tooltip.add(Component.translatable("upgradednetherite.Ender_Tool.TT"));
                        if (itemStack.getTag() != null && itemStack.getTag().contains("UpgradedNetherite_Tagged") && itemStack.getTag().getBoolean("UpgradedNetherite_Tagged")) {
                           String world = level.dimension().location().getPath();
                           String var10001;
                           if (!world.equals(itemStack.getTag().getString("UpgradedNetherite_Dimension"))) {
                              tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                              tooltip.add(Component.translatable("upgradednetherite.Target.TT"));
                              tooltip.add(Component.translatable("upgradednetherite.Ender_Dim.TT"));
                              var10001 = itemStack.getTag().getString("UpgradedNetherite_Dimension");
                              tooltip.add(Component.literal("§7• §c" + var10001 + "§7 : §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[0] + "§7, §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[1] + "§7, §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[2] + "§7."));
                           } else {
                              tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                              tooltip.add(Component.translatable("upgradednetherite.Target.TT"));
                              var10001 = itemStack.getTag().getString("UpgradedNetherite_Dimension");
                              tooltip.add(Component.literal("§7• §9" + var10001 + "§7 : §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[0] + "§7, §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[1] + "§7, §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[2] + "§7."));
                           }
                        }
                     }
                  } else if (!WaterUtil.isWaterRangedWeapon(itemStack) || !UpgradedNetheriteConfig.EnableDamageBonusWaterWeapon && !UpgradedNetheriteConfig.EnableMiningSpeedUnderwater) {
                     if (WitherUtil.isWitherRangedWeapon(itemStack)) {
                        if (UpgradedNetheriteConfig.EnableWitherEffect) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.OnHit.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Wither_Weapon.TT"));
                        }

                        if (UpgradedNetheriteConfig.EnableDamageBonusWitherWeapon) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Wither_Weapon2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusWitherWeapon + "%"});
                        }
                     } else if (PoisonUtil.isPoisonRangedWeapon(itemStack)) {
                        if (UpgradedNetheriteConfig.EnablePoisonEffect) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.OnHit.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Poison_Weapon.TT"));
                        }

                        if (UpgradedNetheriteConfig.EnableDamageBonusPoisonWeapon) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Poison_Weapon2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusPoisonWeapon + "%"});
                        }
                     } else if (!PhantomUtil.isPhantomRangedWeapon(itemStack) || !UpgradedNetheriteConfig.EnableDamageBonusPhantomWeapon && !UpgradedNetheriteConfig.EnableGlowingEffect && !UpgradedNetheriteConfig.EnableReachEffect) {
                        if (FeatherUtil.isFeatherRangedWeapon(itemStack)) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.OnHit.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Feather_Bow.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Feather_Tool.TT", new Object[]{"§7• "});
                        } else if (CorruptUtil.isCorruptRangedWeapon(itemStack)) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Malus.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Corrupt_Bonus2.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Corrupt_Enchant.TT"));
                           if (UpgradedNetheriteConfig.EnableDamageBonusCorruptWeapon || UpgradedNetheriteConfig.EnableLootingBonusCorruptWeapon) {
                              tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                              tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                           }

                           if (Minecraft.getInstance().player != null && CorruptUtil.intWearingCorrupt(Minecraft.getInstance().player, true) > 0) {
                              Object[] var10002;
                              int var10005;
                              if (UpgradedNetheriteConfig.EnableDamageBonusCorruptWeapon) {
                                 var10002 = new Object[1];
                                 var10005 = CorruptUtil.intWearingCorrupt(Minecraft.getInstance().player, true);
                                 var10002[0] = "§6" + var10005 * UpgradedNetheriteConfig.DamageBonusCorruptWeapon + "%";
                                 TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Weapon3.TT", var10002);
                              }

                              if (UpgradedNetheriteConfig.EnableLootingBonusCorruptWeapon) {
                                 var10002 = new Object[1];
                                 var10005 = CorruptUtil.intWearingCorrupt(Minecraft.getInstance().player, true);
                                 var10002[0] = "§6" + var10005 * UpgradedNetheriteConfig.LootingBonusCorruptWeapon;
                                 TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Weapon4.TT", var10002);
                              }
                           }

                           EnchantBonus = 0.0F;
                           enchantments = EnchantmentHelper.getEnchantments(itemStack);
                           if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.MOB_LOOTING)) {
                              EnchantLevel = (Integer)enchantments.get(Enchantments.MOB_LOOTING);
                              EnchantBonus = (float)EnchantLevel;
                           }

                           if (!(EnchantBonus > 0.0F)) {
                              tooltip.add(Component.translatable("upgradednetherite.Gold_Bow.TT"));
                           }

                           if (UpgradedNetheriteConfig.EnableDamageBonusCorruptWeapon) {
                              TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Weapon2.TT", new Object[]{"§d" + UpgradedNetheriteConfig.DamageBonusCorruptWeapon + "%"});
                           }

                           if (UpgradedNetheriteConfig.EnableLootingBonusCorruptWeapon) {
                              TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Weapon.TT", new Object[]{"§d" + UpgradedNetheriteConfig.LootingBonusCorruptWeapon});
                           }
                        } else if (EchoUtil.isEchoRangedWeapon(itemStack) && (UpgradedNetheriteConfig.EnableDamageBonusEchoWeapon || UpgradedNetheriteConfig.EnableBonusExpEcho)) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                           if (UpgradedNetheriteConfig.EnableDamageBonusEchoWeapon) {
                              TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusEchoWeapon + "%"});
                           }

                           if (UpgradedNetheriteConfig.EnableBonusExpEcho) {
                              TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Tool2.TT", new Object[0]);
                           }
                        } else {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Disabled.TT"));
                        }
                     } else {
                        tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                        tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                        if (UpgradedNetheriteConfig.EnableDamageBonusPhantomWeapon) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Phantom_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusPhantomWeapon + "%"});
                        }

                        if (UpgradedNetheriteConfig.EnableGlowingEffect) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Phantom_Tool.TT", new Object[]{"§7• "});
                        }

                        if (UpgradedNetheriteConfig.EnableReachEffect) {
                           tooltip.add(Component.translatable("upgradednetherite.Phantom_Tool2.TT"));
                        }
                     }
                  } else {
                     tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                     tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                     if (UpgradedNetheriteConfig.EnableDamageBonusWaterWeapon) {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Water_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusWaterWeapon + "%"});
                     }

                     if (UpgradedNetheriteConfig.EnableMiningSpeedUnderwater) {
                        tooltip.add(Component.translatable("upgradednetherite.Water_Tool.TT"));
                     }
                  }
               } else {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnableDamageBonusFireWeapon) {
                     EnchantBonus = 0.0F;
                     enchantments = EnchantmentHelper.getEnchantments(itemStack);
                     if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.FLAMING_ARROWS)) {
                        EnchantLevel = (Integer)enchantments.get(Enchantments.FLAMING_ARROWS);
                        EnchantBonus = (float)EnchantLevel;
                     }

                     if (EnchantBonus >= 1.0F) {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Weapon.TT", new Object[]{"§6" + (UpgradedNetheriteConfig.DamageBonusFireWeapon + UpgradedNetheriteConfig.DamageBonusFireEnchantWeapon) + "%"});
                     } else {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusFireWeapon + "%"});
                        tooltip.add(Component.translatable("upgradednetherite.Fire_CrossBow.TT"));
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Bow.TT", new Object[]{"§d" + UpgradedNetheriteConfig.DamageBonusFireEnchantWeapon + "%"});
                     }
                  }

                  if (UpgradedNetheriteConfig.EnableAutoSmelt) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Tool.TT", new Object[]{"§7• "});
                  }
               }
            } else {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               tooltip.add(Component.translatable("upgradednetherite.HoldShift.TT"));
               if (EnderUtil.isEnderRangedWeapon(itemStack)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  if (itemStack.getTag() != null && itemStack.getTag().contains("UpgradedNetherite_Tagged") && itemStack.getTag().getBoolean("UpgradedNetherite_Tagged")) {
                     tooltip.add(Component.translatable("upgradednetherite.Ender_ToolTar.TT"));
                  } else {
                     tooltip.add(Component.translatable("upgradednetherite.Ender_ToolReq.TT"));
                  }
               }
            }
         }
      }

      if (isCharged(itemStack) && !list.isEmpty()) {
         ItemStack itemstack = (ItemStack)list.get(0);
         tooltip.add(Component.translatable("item.minecraft.crossbow.projectile").append(" ").append(itemstack.getDisplayName()));
         if (tooltipFlag.isAdvanced() && itemstack.getItem() == Items.FIREWORK_ROCKET) {
            List<Component> list1 = Lists.newArrayList();
            Items.FIREWORK_ROCKET.appendHoverText(itemstack, level, list1, tooltipFlag);
            if (!list1.isEmpty()) {
               for(int i = 0; i < list1.size(); ++i) {
                  list1.set(i, Component.literal("  ").append((Component)list1.get(i)).withStyle(ChatFormatting.GRAY));
               }

               tooltip.addAll(list1);
            }
         }
      }

   }

   public int getDefaultProjectileRange() {
      return 8;
   }
}
