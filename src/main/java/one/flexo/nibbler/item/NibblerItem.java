package one.flexo.nibbler.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.flexo.nibbler.registry.*;
import one.flexo.nibbler.util.NibblerName;

public class NibblerItem extends Item implements ModelRegisteredObject {

	public final NibblerName name;

	public NibblerItem(String modid, String name) {
		this(modid, name, null);
	}

	public NibblerItem(String modid, String name, CreativeTabs creativeTab) {
		if(creativeTab != null) {
			setCreativeTab(creativeTab);
		}
		this.name = new NibblerName(modid, name);
		setRegistryName(this.name);
		setUnlocalizedName(this.name.getUnlocalizedName());
		if(this instanceof NibblerRegisteredObject) {
			NibblerRegistry.addItemForRegistry(this);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(name, "inventory"));
	}
}
