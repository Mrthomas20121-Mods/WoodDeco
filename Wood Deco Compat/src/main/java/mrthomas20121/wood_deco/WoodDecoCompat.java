package mrthomas20121.wood_deco;

import mrthomas20121.wood_deco.datagen.*;
import mrthomas20121.wood_deco.init.WoodDecoCompatBlocks;
import mrthomas20121.wood_deco.init.WoodDecoCompatCreativeTabs;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;

@Mod(WoodDecoCompat.MOD_ID)
@Mod.EventBusSubscriber(modid = WoodDecoCompat.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WoodDecoCompat {

	public static final String MOD_ID = "wood_deco_compat";
	public static final Logger LOGGER = LogManager.getLogger();

	public WoodDecoCompat() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		WoodDecoCompatCreativeTabs.CREATIVE_TABS.register(bus);
	}

	@SubscribeEvent
	public static void gatherData(final GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		PackOutput packOutput = gen.getPackOutput();

		// client providers
		gen.addProvider(event.includeClient(), new WoodDecoCompatLangProvider(packOutput));
		gen.addProvider(event.includeClient(), new WoodDecoCompatBlockStateProvider(packOutput, fileHelper));
		gen.addProvider(event.includeClient(), new WoodDecoCompatItemModelProvider(packOutput, fileHelper));
		// server providers
		gen.addProvider(event.includeServer(), new WoodDecoCompatLootGen(packOutput));
		gen.addProvider(event.includeServer(), new WoodDecoCompatTagsProvider(packOutput, lookupProvider, fileHelper));
		gen.addProvider(event.includeServer(), new WoodDecoCompatRecipeProvider(packOutput));
	}
}
