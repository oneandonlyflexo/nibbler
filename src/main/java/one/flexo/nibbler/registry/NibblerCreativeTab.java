/*******************************************************************************
 * Copyright (C) 2017 "oneandonlyflexo"
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import one.flexo.nibbler.Nibbler;

/**
 *
 * @author "oneandonlyflexo"
 */
public class NibblerCreativeTab {

	private static CreativeTabs tab;

	public static CreativeTabs get() {
		if(tab == null) {
			tab = new CreativeTabs("nibbler") {
				@Override
				public String getTabLabel() {
					return "nibbler";
				}
				@Override
				public ItemStack getTabIconItem() {
					return new ItemStack(Nibbler.logo);
				}
			};
		}
		return tab;
	}
}
