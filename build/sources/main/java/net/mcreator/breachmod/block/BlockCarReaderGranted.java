
package net.mcreator.breachmod.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.Rotation;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Mirror;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.network.NetworkManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Container;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.Block;

import net.mcreator.breachmod.ElementsBreachMod;

@ElementsBreachMod.ModElement.Tag
public class BlockCarReaderGranted extends ElementsBreachMod.ModElement {
	@GameRegistry.ObjectHolder("breachmod2:carreadergranted")
	public static final Block block = null;
	public BlockCarReaderGranted(ElementsBreachMod instance) {
		super(instance, 153);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("carreadergranted"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(TileEntityCustom.class, "breachmod2:tileentitycarreadergranted");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("breachmod2:carreadergranted", "inventory"));
	}
	public static class BlockCustom extends Block implements ITileEntityProvider {
		public static final PropertyDirection FACING = BlockDirectional.FACING;
		public BlockCustom() {
			super(Material.IRON);
			setUnlocalizedName("carreadergranted");
			setSoundType(SoundType.METAL);
			setHardness(1F);
			setResistance(10F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(null);
			setBlockUnbreakable();
			this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		}

		@Override
		public boolean isFullCube(IBlockState state) {
			return false;
		}

		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
			switch ((EnumFacing) state.getValue(BlockDirectional.FACING)) {
				case SOUTH :
				default :
					return new AxisAlignedBB(0.7D, 0.2D, 0.2D, 0.3D, 0.9D, 0D);
				case NORTH :
					return new AxisAlignedBB(0.3D, 0.2D, 0.8D, 0.7D, 0.9D, 1D);
				case WEST :
					return new AxisAlignedBB(0.8D, 0.2D, 0.7D, 1D, 0.9D, 0.3D);
				case EAST :
					return new AxisAlignedBB(0.2D, 0.2D, 0.3D, 0D, 0.9D, 0.7D);
				case UP :
					return new AxisAlignedBB(0.3D, 0.2D, 0.2D, 0.7D, 0D, 0.9D);
				case DOWN :
					return new AxisAlignedBB(0.3D, 0.8D, 0.8D, 0.7D, 1D, 0.1D);
			}
		}

		@Override
		public int tickRate(World world) {
			return 1;
		}

		@Override
		protected net.minecraft.block.state.BlockStateContainer createBlockState() {
			return new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{FACING});
		}

		@Override
		public IBlockState withRotation(IBlockState state, Rotation rot) {
			return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
		}

		@Override
		public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
			return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
		}

		@Override
		public IBlockState getStateFromMeta(int meta) {
			return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
		}

		@Override
		public int getMetaFromState(IBlockState state) {
			return ((EnumFacing) state.getValue(FACING)).getIndex();
		}

		@Override
		public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
				EntityLivingBase placer) {
			return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockCarReaderGranted.block, (int) (1));
		}

		@Override
		public TileEntity createNewTileEntity(World worldIn, int meta) {
			return new TileEntityCustom();
		}

		@Override
		public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int eventID, int eventParam) {
			super.eventReceived(state, worldIn, pos, eventID, eventParam);
			TileEntity tileentity = worldIn.getTileEntity(pos);
			return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
		}

		@Override
		public EnumBlockRenderType getRenderType(IBlockState state) {
			return EnumBlockRenderType.MODEL;
		}
	}

	public static class TileEntityCustom extends TileEntityLockableLoot {
		private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);
		@Override
		public int getSizeInventory() {
			return 9;
		}

		@Override
		public boolean isEmpty() {
			for (ItemStack itemstack : this.stacks)
				if (!itemstack.isEmpty())
					return false;
			return true;
		}

		@Override
		public boolean isItemValidForSlot(int index, ItemStack stack) {
			return true;
		}

		@Override
		public ItemStack getStackInSlot(int slot) {
			return stacks.get(slot);
		}

		@Override
		public String getName() {
			return "container.carreadergranted";
		}

		@Override
		public void readFromNBT(NBTTagCompound compound) {
			super.readFromNBT(compound);
			this.stacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
			if (!this.checkLootAndRead(compound))
				ItemStackHelper.loadAllItems(compound, this.stacks);
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound compound) {
			super.writeToNBT(compound);
			if (!this.checkLootAndWrite(compound))
				ItemStackHelper.saveAllItems(compound, this.stacks);
			return compound;
		}

		@Override
		public SPacketUpdateTileEntity getUpdatePacket() {
			return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
		}

		@Override
		public NBTTagCompound getUpdateTag() {
			return this.writeToNBT(new NBTTagCompound());
		}

		@Override
		public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
			this.readFromNBT(pkt.getNbtCompound());
		}

		@Override
		public void handleUpdateTag(NBTTagCompound tag) {
			this.readFromNBT(tag);
		}

		@Override
		public int getInventoryStackLimit() {
			return 64;
		}

		@Override
		public String getGuiID() {
			return "breachmod2:carreadergranted";
		}

		@Override
		public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
			this.fillWithLoot(playerIn);
			return new ContainerChest(playerInventory, this, playerIn);
		}

		@Override
		protected NonNullList<ItemStack> getItems() {
			return this.stacks;
		}
	}
}
