package com.frogniche.foxmod;

import com.frogniche.foxmod.entity.EntityInit;
import com.frogniche.foxmod.entity.dungeonsmobs.king_paws.KingPawsEntity;
import com.frogniche.foxmod.entity.dungeonsmobs.king_paws.KingPawsRenderer;
import com.frogniche.foxmod.entity.legendsmobs.horde_of_the_bastion.unbreakable.UnbreakableEntity;
import com.frogniche.foxmod.entity.legendsmobs.horde_of_the_bastion.unbreakable.UnbreakableModel;
import com.frogniche.foxmod.entity.legendsmobs.horde_of_the_spore.devourer.DevourerEntity;
import com.frogniche.foxmod.entity.legendsmobs.horde_of_the_spore.devourer.DevourerModel;
import com.frogniche.foxmod.entity.legendsmobs.variants.seeker.SeekerEntity;
import com.frogniche.foxmod.entity.legendsmobs.variants.seeker.SeekerRenderer;
import com.frogniche.foxmod.entity.legendsmobs.variants.spore_medic.SporeMedicEntity;
import com.frogniche.foxmod.entity.legendsmobs.variants.spore_medic.SporeMedicModel;
import com.frogniche.foxmod.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FoxMod.MOD_ID)
public class FoxMod
{
    public static final String MOD_ID = "foxmod";

    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation modLoc(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public FoxMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Add to the constructor
        ModItems.register(modEventBus);
        EntityInit.ENTITIES.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerEntityAttributes);
        modEventBus.addListener(this::clientSetup);
        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);


    }

    private void commonSetup(final FMLCommonSetupEvent event) {


    }
    private void clientSetup(FMLClientSetupEvent event) {

//Dungeons Mobs
        EntityRenderers.register(EntityInit.KING_PAWS.get(), KingPawsRenderer::new);

        EntityRenderers.register(EntityInit.SEEKER.get(), SeekerRenderer::new);
//Horde of the spore
        EntityRenderers.register(EntityInit.DEVOURER.get(), makeRenderer(new DevourerModel()));

        EntityRenderers.register(EntityInit.UNBREAKABLE.get(), makeRenderer(new UnbreakableModel()));

        EntityRenderers.register(EntityInit.SPORE_MEDIC.get(), makeRenderer(new SporeMedicModel()));
    }
    private void registerEntityAttributes(EntityAttributeCreationEvent event){
//dungeons mobs
        event.put(EntityInit.KING_PAWS.get(), KingPawsEntity.createAttributes());

        event.put(EntityInit.SEEKER.get(), SeekerEntity.createAttributes());
        //spore horde
        event.put(EntityInit.DEVOURER.get(), DevourerEntity.makeAttributes());

        event.put(EntityInit.UNBREAKABLE.get(), UnbreakableEntity.createAttributes());


        event.put(EntityInit.SPORE_MEDIC.get(), SporeMedicEntity.createAttributes());

    }

    public static <T extends LivingEntity & IAnimatable> EntityRendererProvider<T> makeRenderer(AnimatedGeoModel<T> model){
        return m -> new HelperGeoRenderer<>(m, model);
    }

    public static class HelperGeoRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T>{

        public HelperGeoRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel<T> modelProvider) {
            super(renderManager, modelProvider);
        }
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
