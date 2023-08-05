package com.frogniche.foxmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab LEGEND_FOXES = new CreativeModeTab("legend_foxes") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.LEGEND_ICON.get());
        }
    };
    public static final CreativeModeTab DUNGEONS_FOXES = new CreativeModeTab("dungeons_foxes") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.DUNGEONS_ICON.get());
        }
    };
}