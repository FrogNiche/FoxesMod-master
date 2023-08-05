package com.frogniche.foxmod.item;

import com.frogniche.foxmod.FoxMod;

import com.frogniche.foxmod.entity.EntityInit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.example.item.PotatoArmorItem;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FoxMod.MOD_ID);
    public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, FoxMod.MOD_ID);

    // Tabs
    public static final RegistryObject<Item> LEGEND_ICON = ITEMS.register("legend_icon",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));
    public static final RegistryObject<Item> TASTY_BONE = ITEMS.register("tasty_bone",
            () -> new UnbreakableSword(Tiers.NETHERITE, 15, 9f,
                    new Item.Properties().tab(ModCreativeModeTab.DUNGEONS_FOXES).fireResistant()));
    public static final RegistryObject<Item> DUNGEONS_ICON = ITEMS.register("dungeons_icon",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_SEARCH)));

    // Dungeon Spawn Eggs / Items
    public static final RegistryObject<Item> KING_PAWS_SPAWN_EGG = SPAWN_EGGS.register("king_paws_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.KING_PAWS, 0xCB8B16, 0xDDCE17,
                    new Item.Properties().tab(ModCreativeModeTab.DUNGEONS_FOXES)));

    public static final RegistryObject<Item> SEEKER_SPAWN_EGG = SPAWN_EGGS.register("seeker_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.SEEKER, 0x867f72, 0x927671,
                    new Item.Properties().tab(ModCreativeModeTab.LEGEND_FOXES)));

    // Legend Mobs
    //Horde of the Spore
   public static final RegistryObject<ForgeSpawnEggItem> DEVOURER_SPAWN_EGG = SPAWN_EGGS.register("devourer_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.DEVOURER, 0x1bd19b, 0x582a36, new Item.Properties().tab(ModCreativeModeTab.LEGEND_FOXES)));
   public static final RegistryObject<ForgeSpawnEggItem> SPORE_MEDIC_SPAWN_EGG = SPAWN_EGGS.register("spore_medic_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.SPORE_MEDIC, 0x1f1714, 0xb99d06, new Item.Properties().tab(ModCreativeModeTab.LEGEND_FOXES)));
    /* public static final RegistryObject<ForgeSpawnEggItem> POTTED_PAWS_SPAWN_EGG = SPAWN_EGGS.register("potted_paws_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.POTTED_PAWS, 0x3c3b3c, 0xff8bfb, new Item.Properties().tab(ModCreativeModeTab.LEGEND_FOXES)));
    public static final RegistryObject<ForgeSpawnEggItem> BONE_CHIEF_SPAWN_EGG = SPAWN_EGGS.register("bone_chief_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.BONE_HORDE_CHIEF, 0xb6b3a2, 0x312a24, new Item.Properties().tab(ModCreativeModeTab.LEGEND_FOXES)));
    public static final RegistryObject<ForgeSpawnEggItem> SAW_PAWS_SPAWN_EGG = SPAWN_EGGS.register("saw_paws_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.SAW_PAWS, 0xf47d08, 0xc4ae22, new Item.Properties().tab(ModCreativeModeTab.LEGEND_FOXES)));
    public static final RegistryObject<ForgeSpawnEggItem> BONE_GRUNTER_SPAWN_EGG = SPAWN_EGGS.register("bone_grunter_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.BONE_GRUNTER, 0x1f1714, 0xb99d06, new Item.Properties().tab(ModCreativeModeTab.LEGEND_FOXES)));
*/
    public static final RegistryObject<ForgeSpawnEggItem> UNBREAKABLE_SPAWN_EGG = SPAWN_EGGS.register("unbreakable_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.UNBREAKABLE, 0xefb602, 0x935200, new Item.Properties().tab(ModCreativeModeTab.LEGEND_FOXES)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        SPAWN_EGGS.register(eventBus);
    }
}
