package com.projectreddog.machinemod.entity;

import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;

public class EntityDrillingRig extends EntityMachineModRideable {
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public double bladeOffset = 2.0d;
	public int currentDepth = 0;

	public EntityDrillingRig(World world) {
		super(world);
		setSize(5.7F, 3F);
		inventory = new ItemStack[0];
		this.mountedOffsetY = .5D;
		this.mountedOffsetX = 3D;
		this.mountedOffsetZ = 3D;
		this.maxAngle = 170;
		this.minAngle = 0;
		this.droppedItem = ModItems.drillingrig;

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public void onUpdate() {

		if (!worldObj.isRemote) {

			if (this.Attribute1 > 89) {
				// drill is extended disable all movement controls
				this.isPlayerTurningLeft = false;
				this.isPlayerTurningRight = false;
				this.isPlayerAccelerating = false;
				this.isPlayerBreaking = false;

			}
		}
		super.onUpdate();
		if (!worldObj.isRemote) {

			if (this.Attribute1 > 90) {
				// drilling arm fully extended so now for every 10 units drill another block
				currentDepth = (int) ((this.Attribute1 - 90) / 5);
				if (currentDepth > 15) {
					// fail safe limit
					currentDepth = 15;
				}

				BlockPos bp = new BlockPos(posX + calcTwoOffsetX(5, 0, 0), posY - currentDepth - 1, posZ + calcTwoOffsetZ(5, 0, 0));
				if (worldObj.getBlockState(bp).getBlock() == Blocks.stone && worldObj.getBlockState(bp).getBlock().getMetaFromState(worldObj.getBlockState(bp)) == BlockStone.EnumType.STONE.getMetadata()) {
					// worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
					// worldObj.setBlockToAir(bp);
					worldObj.setBlockState(bp, ModBlocks.machinedrilledstone.getDefaultState());

				} else if (worldObj.getBlockState(bp).getBlock() == Blocks.stone && worldObj.getBlockState(bp).getBlock().getMetaFromState(worldObj.getBlockState(bp)) == BlockStone.EnumType.ANDESITE.getMetadata()) {
					// worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
					// worldObj.setBlockToAir(bp);
					worldObj.setBlockState(bp, ModBlocks.machinedrilledandesite.getDefaultState());

				} else if (worldObj.getBlockState(bp).getBlock() == Blocks.stone && worldObj.getBlockState(bp).getBlock().getMetaFromState(worldObj.getBlockState(bp)) == BlockStone.EnumType.DIORITE.getMetadata()) {
					// worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
					// worldObj.setBlockToAir(bp);
					worldObj.setBlockState(bp, ModBlocks.machinedrilleddiorite.getDefaultState());

				} else if (worldObj.getBlockState(bp).getBlock() == Blocks.stone && worldObj.getBlockState(bp).getBlock().getMetaFromState(worldObj.getBlockState(bp)) == BlockStone.EnumType.GRANITE.getMetadata()) {
					// worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
					// worldObj.setBlockToAir(bp);
					worldObj.setBlockState(bp, ModBlocks.machinedrilledgranite.getDefaultState());

				}
			}

		}
	}
}
