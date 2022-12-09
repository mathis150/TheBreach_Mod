
package net.mcreator.breachmod.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import net.mcreator.breachmod.item.ItemLogo;
import net.mcreator.breachmod.ElementsBreachMod;

@ElementsBreachMod.ModElement.Tag
public class TabTab2 extends ElementsBreachMod.ModElement {
	public TabTab2(ElementsBreachMod instance) {
		super(instance, 3);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tabtab2") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack getTabIconItem() {
				return new ItemStack(ItemLogo.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static CreativeTabs tab;
}
