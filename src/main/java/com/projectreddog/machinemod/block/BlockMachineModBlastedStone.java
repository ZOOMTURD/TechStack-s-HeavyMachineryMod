package com.projectreddog.machinemod.block;

import net.minecraft.block.BlockStone;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

import com.projectreddog.machinemod.reference.Reference;

public class BlockMachineModBlastedStone extends BlockMachineModBlastedStoneBase {
	public static final PropertyEnum PROPERTYORE = PropertyEnum.create("ore", EnumVanillaOres.class);

	public BlockMachineModBlastedStone() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeStone);
		this.setHardness(1.5f);

	}

	@Override
	/**
	 * Get the damage value that this Block should drop
	 */
	public int damageDropped(IBlockState state) {

		return this.getMetaFromState(state);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumVanillaOres ore = EnumVanillaOres.byMetadata(meta);
		return this.getDefaultState().withProperty(PROPERTYORE, ore);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumVanillaOres ore = (EnumVanillaOres) state.getValue(PROPERTYORE);

		return ore.getMetadata();
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { PROPERTYORE });
	}

	public static enum EnumVanillaOres implements IStringSerializable {
		STONE(0, "STONE"), GRANITE(1, "GRANITE"), DIORITE(2, "DIORITE"), ANDESITE(3, "ANDESITE"), GOLD(4, "GOLD"), IRON(5, "IRON"), COAL(6, "COAL"), LAPIS(7, "LAPIS"), DIAMOND(8, "DIAMOND"), REDSTONE(9, "REDSTONE"), EMERALD(10, "EMERALD");

		public int getMetadata() {
			return this.meta;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static EnumVanillaOres byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public String getName() {
			return this.name;
		}

		private final int meta;
		private final String name;
		private static final EnumVanillaOres[] META_LOOKUP = new EnumVanillaOres[values().length];

		private EnumVanillaOres(int i_meta, String i_name) {
			this.meta = i_meta;
			this.name = i_name;
		}

		static {
			for (EnumVanillaOres ore : values()) {
				META_LOOKUP[ore.getMetadata()] = ore;
			}
		}
	}

}
