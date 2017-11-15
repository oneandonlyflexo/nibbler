package one.flexo.nibbler.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.flexo.nibbler.registry.*;
import one.flexo.nibbler.util.NibblerName;

public class NibblerBlock extends Block implements ModelRegisteredObject {

	public final NibblerName name;

	public NibblerBlock(String modid, String name, Material material, CreativeTabs creativeTab) {
		super(material);
		if(creativeTab != null) {
			setCreativeTab(creativeTab);
		}
		this.name = new NibblerName(modid, name);
		setRegistryName(name);
		setUnlocalizedName(this.name.getUnlocalizedName());
		if(this instanceof NibblerRegisteredObject) {
			NibblerRegistry.addBlockForRegistry(this, true);
		}
	}

	protected boolean registerInCreative() {
		return true;
	}

	@SideOnly(Side.CLIENT)
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
