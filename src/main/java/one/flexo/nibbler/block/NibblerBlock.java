/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import one.flexo.nibbler.INibble;
import one.flexo.nibbler.Nibble;
import one.flexo.nibbler.registry.ModelRegisteredObject;
import one.flexo.nibbler.registry.NibblerRegisteredObject;

public class NibblerBlock extends Block implements INibble, ModelRegisteredObject {

	public final Nibble name;

	public NibblerBlock(String modid, String name, Material material, CreativeTabs creativeTab) {
		super(material);
		if(creativeTab != null) {
			setCreativeTab(creativeTab);
		}
		this.name = new Nibble(modid, name);
		setRegistryName(name);
		setUnlocalizedName(this.name.getUnlocalizedName());
		if(this instanceof NibblerRegisteredObject) {
			((NibblerRegisteredObject)this).getRegistry().addBlockForRegistry(this, true);
		}
	}

	@Override
	public Nibble nibble() {
		return this.name;
	}

	protected boolean registerInCreative() {
		return true;
	}

	@Override
	public void registerModels() {
		registerCustomItemblock(this, 1);
	}


	public static void registerCustomItemblock(NibblerBlock b, int metaCount) {
		for(int i = 0; i < metaCount; i++) {
			ModelResourceLocation mrl = new ModelResourceLocation(b.name.getItemBlockName(i), "inventory");
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), i, mrl);
		}
	}
}
