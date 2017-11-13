package one.flexo.nibbler.item.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import one.flexo.nibbler.NibblerInfo;
import one.flexo.nibbler.util.IModelRegister;
import one.flexo.nibbler.util.ModIdType;

public abstract class NibblerItem extends Item implements IModelRegister {

	public NibblerItem(String name) {
		//TODO: setup nibbler creative tab
		//setCreativeTab(BotaniaCreativeTab.INSTANCE);
		setRegistryName(new ResourceLocation(NibblerInfo.modid, name));
		setUnlocalizedName(ModIdType.DEFAULT.getId(name));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
