package com.frogniche.foxmod.entity;

import com.frogniche.foxmod.FoxMod;

import com.frogniche.foxmod.entity.dungeonsmobs.king_paws.KingPawsEntity;

import com.frogniche.foxmod.entity.legendsmobs.horde_of_the_bastion.unbreakable.UnbreakableEntity;
import com.frogniche.foxmod.entity.legendsmobs.horde_of_the_spore.devourer.DevourerEntity;

import com.frogniche.foxmod.entity.legendsmobs.variants.seeker.SeekerEntity;
import com.frogniche.foxmod.entity.legendsmobs.variants.spore_medic.SporeMedicEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.
                    ENTITY_TYPES,
            FoxMod.MOD_ID);
//Dungeons Mobs
    public static final RegistryObject<EntityType<KingPawsEntity>> KING_PAWS = register("king_paws",
            EntityType.Builder.<KingPawsEntity>of(KingPawsEntity::new,  MobCategory.MONSTER));

    public static final RegistryObject<EntityType<SeekerEntity>> SEEKER = register("seeker",
            EntityType.Builder.<SeekerEntity>of(SeekerEntity::new,  MobCategory.MONSTER));

//Legend Mobs
    //Horde of the Spore
    public static final RegistryObject<EntityType<DevourerEntity>> DEVOURER = register("devourer", EntityType.Builder.of(DevourerEntity::new, MobCategory.MONSTER).sized(2f, 7f));

    public static final RegistryObject<EntityType<UnbreakableEntity>> UNBREAKABLE = register("unbreakable", EntityType.Builder.of(UnbreakableEntity::new, MobCategory.MONSTER).sized(1f, 1.5f));
    public static final RegistryObject<EntityType<SporeMedicEntity>> SPORE_MEDIC = register("spore_medic",EntityType.Builder.of(SporeMedicEntity::new, MobCategory.AMBIENT).sized(1f, 1.5f));

    public static final <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> builder){
        return ENTITIES.register(name, () -> builder.build(FoxMod.modLoc(name).toString()));
    }
}