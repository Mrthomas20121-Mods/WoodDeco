package mrthomas20121.wood_deco;

import mrthomas20121.wood_deco.datagen.*;
import mrthomas20121.wood_deco.init.WoodDecoBlocks;
import mrthomas20121.wood_deco.init.WoodDecoCreativeTabs;
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

@Mod(WoodDeco.MOD_ID)
@Mod.EventBusSubscriber(modid = WoodDeco.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WoodDeco {

	public static final String MOD_ID = "wood_deco";
	public static final Logger LOGGER = LogManager.getLogger();

	public WoodDeco() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		WoodDecoBlocks.BLOCKS.register(bus);
		WoodDecoBlocks.ITEMS.register(bus);
		WoodDecoCreativeTabs.CREATIVE_TABS.register(bus);
	}

	@SubscribeEvent
	public static void gatherData(final GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper fileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		PackOutput packOutput = gen.getPackOutput();

		// client providers
		gen.addProvider(event.includeClient(), new WoodDecoLangProvider(packOutput));
		gen.addProvider(event.includeClient(), new WoodDecoBlockStateProvider(packOutput, fileHelper));
		gen.addProvider(event.includeClient(), new WoodDecoItemModelProvider(packOutput, fileHelper));
		// server providers
		gen.addProvider(event.includeServer(), new WoodDecoLootGen(packOutput));
		gen.addProvider(event.includeServer(), new WoodDecoTagsProvider(packOutput, lookupProvider, fileHelper));
	}
}
