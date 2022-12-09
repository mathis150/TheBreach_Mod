
package net.mcreator.breachmod.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

import net.mcreator.breachmod.creativetab.TabTab2;
import net.mcreator.breachmod.ElementsBreachMod;

@ElementsBreachMod.ModElement.Tag
public class BlockLamp10 extends ElementsBreachMod.ModElement {
	@GameRegistry.ObjectHolder("breachmod2:lamp10")
	public static final Block block = null;
	public BlockLamp10(ElementsBreachMod instance) {
		super(instance, 104);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("lamp10"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("breachmod2:lamp10", "inventory"));
	}
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.IRON);
			setUnlocalizedName("lamp10");
			setSoundType(SoundType.METAL);
			setHardness(1F);
			setResistance(10F);
			setLightLevel(0.6F);
			setLightOpacity(0);
			setCreativeTab(TabTab2.tab);
		}

		@Override
		public boolean isFullCube(IBlockState state) {
			return false;
		}

		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
			return new AxisAlignedBB(0D, 0.8D, 0D, 1D, 1D, 1D);
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}
	}
}
