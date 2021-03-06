/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 * 
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import one.flexo.nibbler.INibble;

public class NibblerBlockItem extends ItemBlock {

	public <B extends Block & INibble> NibblerBlockItem(B block) {
		super(block);
		setRegistryName(block.nibble());
		setUnlocalizedName(block.nibble().getUnlocalizedName());
	}
}
