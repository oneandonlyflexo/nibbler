package one.flexo.nibbler.item;

import net.minecraft.item.ItemBlock;
import one.flexo.nibbler.block.NibblerBlock;

public class NibblerBlockItem extends ItemBlock {

	public NibblerBlockItem(NibblerBlock block) {
		super(block);
		setRegistryName(block.name);
		setUnlocalizedName(block.name.getUnlocalizedName());
	}
}
