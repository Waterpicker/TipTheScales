package com.blamejared.tipthescales;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blamejared.tipthescales.events.ClientEventHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;

@Mod("tipthescales")
public class TipTheScales {
    
    public static final Logger LOG = LogManager.getFormatterLogger("TipTheScales");
    
    public TipTheScales() {
    	ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }
    
    private void doClientStuff(final FMLClientSetupEvent event) {
        
        
        try {
            
            Class.forName("net.optifine.Config");
            LOG.info("OPTIFINE DETECTED! DISABLING TIPTHESCALES!!!");
            return;
        } catch(final Exception e) {
            // no-op
        }
        
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
    
}
