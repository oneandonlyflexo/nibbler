/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import one.flexo.nibbler.INibble;
import one.flexo.nibbler.Nibble;
import one.flexo.nibbler.registry.ModelRegisteredObject;
import one.flexo.nibbler.registry.NibblerRegisteredObject;

public class NibblerItem extends Item implements INibble, ModelRegisteredObject {

	private final Nibble name;

	public NibblerItem(String modid, String name) {
		this(modid, name, null);
	}

	public NibblerItem(String modid, String name, CreativeTabs creativeTab) {
		if(creativeTab != null) {
			setCreativeTab(creativeTab);
		}
		this.name = new Nibble(modid, name);
		setRegistryName(this.name);
		setUnlocalizedName(this.name.getUnlocalizedName());
		if(this instanceof NibblerRegisteredObject) {
			((NibblerRegisteredObject)this).getRegistry().addItemForRegistry(this);
		}
	}

	@Override
	public Nibble nibble() {
		return this.name;
	}

	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(name, "inventory"));
	}
}
