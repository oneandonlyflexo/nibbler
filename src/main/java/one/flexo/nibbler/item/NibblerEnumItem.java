/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.item;

import javax.annotation.Nonnull;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import one.flexo.nibbler.INibbleEnum;

public class NibblerEnumItem<T extends Enum<T> & INibbleEnum> extends NibblerItem {


	final Class<T> enumType;
	final T[] types;

	public NibblerEnumItem(String modid, String name, Class<T> enumType) {
		this(modid, name, enumType, null);
	}

	public NibblerEnumItem(String modid, String name, Class<T> enumType, CreativeTabs creativeTab) {
		super(modid, name, creativeTab);
		this.enumType = enumType;
		this.types = enumType.getEnumConstants();
		setHasSubtypes(true);

		nibble().withMeta(meta -> (meta < 0 || meta >= types.length ? "unknown" : types[meta].name()));
	}

	@Override
	public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> stacks) {
		if(isInCreativeTab(tab)) {
			for(int i = 0; i < types.length; i++) {
				stacks.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Nonnull
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getItemDamage();
		if(meta < 0 || meta >= types.length) {
			return "unknown";
		}
		return "item." + types[meta].nibble().getUnlocalizedName();
	}

	@Override
	public void registerModels() {
		for (int i = 0; i < types.length; i++) {
			ModelLoader.setCustomModelResourceLocation(this, i,
					new ModelResourceLocation(types[i].nibble().toString(), "inventory"));
		}
	}
}
