/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.util;

import java.lang.ref.WeakReference;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

public final class NibblerUtil {

	public static boolean notnull(WeakReference<?> weakRef) {
		return weakRef != null && weakRef.get() != null;
	}

	public static <V> V nnod(V value, V defaultValue) {
		if(value == null) {
			return defaultValue;
		}
		return value;
	}

	public static <O, V> V nnod(O value, Function<O, V> function, V defaultValue) {
		if(value == null) {
			return defaultValue;
		}
		V functionValue = function.apply(value);
		if(functionValue == null) {
			return defaultValue;
		}
		return functionValue;
	}

	public static final class Math {
		private Math() { }

		public static int clamp(int value, int min, int max) {
			return (value < min) ? min : (value > max) ? max : value;
		}
		public static float clamp(float value, float min, float max) {
			return (value < min) ? min : (value > max) ? max : value;
		}
		public static double clamp(double value, double min, double max) {
			return (value < min) ? min : (value > max) ? max : value;
		}

	}

	public static final class Inventory {
		private Inventory() { }

		@SafeVarargs
		public static <T> NonNullList<T> asNonnullList(T... array) {
			NonNullList<T> nonNullList = NonNullList.create();
			if (array != null)
				nonNullList.addAll(Arrays.stream(array).filter(e -> e != null).collect(Collectors.toList()));
			return nonNullList;
		}

		public static <T> NonNullList<T> asNonnullList(List<T> list) {
			NonNullList<T> nonNullList = NonNullList.create();
			if (list != null)
				nonNullList.addAll(list.stream().filter(e -> e != null).collect(Collectors.toList()));
			return nonNullList;
		}
	}

	public static final class Entity {
		private Entity() { }

		public static float getYaw(EnumFacing currentFacing) {
			switch (currentFacing) {
			case DOWN:
			case UP:
			case SOUTH:
			default:
				return 0;
			case WEST:
				return 90f;
			case NORTH:
				return 180f;
			case EAST:
				return 270f;
			}
		}

		public static EnumFacing getFacing(float yaw) {
			yaw %= 360;
			if (yaw < 0) {
				yaw += 360;
			}
			yaw += 45;
			yaw %= 360;
			return EnumFacing.getHorizontal(((int)yaw) / 90);
		}

		public static float getAttackDamage(ItemStack mainHand, EntityLivingBase entity) {
			IAttributeInstance dmgAttr = new AttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			if (!mainHand.isEmpty()) {
				Collection<AttributeModifier> modifiers = mainHand
						.getAttributeModifiers(EntityEquipmentSlot.MAINHAND)
						.get(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
				for (AttributeModifier modifier : modifiers) {
					dmgAttr.applyModifier(modifier);
				}
			}
			float damage = (float) dmgAttr.getAttributeValue();
			float enchantDamage = EnchantmentHelper.getModifierForCreature(mainHand, entity.getCreatureAttribute());
			return damage + enchantDamage;
		}
	}
}
