package net.ced7032.necromant.item;

import net.ced7032.necromant.Necromant;
import net.ced7032.necromant.item.custom.DowsingRodItem;
import net.ced7032.necromant.item.custom.NecromancerStaffItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Necromant.MOD_ID);

    public static final RegistryObject<Item> CITRINE = ITEMS.register( "citrine",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.NECROMANT_TAB)));

    public static final RegistryObject<Item> RAW_CITRINE = ITEMS.register( "raw_citrine",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.NECROMANT_TAB)));

    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register( "dowsing_rod",
            () -> new DowsingRodItem(new Item.Properties().tab(ModCreativeModeTab.NECROMANT_TAB).durability(16)));

    public static final RegistryObject<Item> NECROMANCER_STAFF = ITEMS.register( "necromancer_staff",
            () -> new NecromancerStaffItem(new Item.Properties().tab(ModCreativeModeTab.NECROMANT_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
