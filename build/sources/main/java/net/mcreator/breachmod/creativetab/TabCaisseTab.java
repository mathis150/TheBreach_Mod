
package net.mcreator.breachmod.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import net.mcreator.breachmod.block.BlockCaisse1x1Bleu;
import net.mcreator.breachmod.ElementsBreachMod;

@ElementsBreachMod.ModElement.Tag
public class TabCaisseTab extends ElementsBreachMod.ModElement {
	public TabCaisseTab(ElementsBreachMod instance) {
		super(instance, 198);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tabcaissetab") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(BlockCaisse1x1Bleu.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static CreativeTabs tab;
}
