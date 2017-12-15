/*******************************************************************************
 * Copyright (C) 2017 Jeremy Grozavescu <oneandonlyflexo>
 * https://creativecommons.org/licenses/by-nc-sa/4.0/
 *
 * This file is part of Nibbler, which is open source:
 * https://github.com/oneandonlyflexo/nibbler
 ******************************************************************************/
package one.flexo.nibbler.item.tool;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemTool;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.flexo.nibbler.INibble;
import one.flexo.nibbler.Nibble;
import one.flexo.nibbler.registry.ModelRegisteredObject;
import one.flexo.nibbler.registry.NibblerRegisteredObject;

public class NibblerTool extends ItemTool implements INibble, ModelRegisteredObject {

	private final Nibble name;

	public NibblerTool(String modid, String name, NibblerToolData toolData) {
		this(modid, name, toolData, null);
	}

	public NibblerTool(String modid, String name, NibblerToolData toolData, CreativeTabs creativeTab) {
		super(toolData.damage, toolData.speed, toolData.material, toolData.effectiveBlocks);
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
	@SideOnly(Side.CLIENT)
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(name, "inventory"));
	}
}
