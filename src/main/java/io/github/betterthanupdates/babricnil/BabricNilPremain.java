package io.github.betterthanupdates.babricnil;

import io.github.betterthanupdates.babricnil.transformer.GameProviderHelperTransformer;
import nilloader.api.ClassTransformer;
import nilloader.api.NilLogger;

public class BabricNilPremain implements Runnable {
	// NilLoader comes with a logger abstraction that Does The Right Thing depending on the environment.
	public static final NilLogger logger = NilLogger.get("BabricNil");

	private static final ClassTransformer GAME_PROVIDER_HELPER = new GameProviderHelperTransformer();
	
	@Override
	public void run() {
		logger.info("Patching Fabric Loader");
		ClassTransformer.register(GAME_PROVIDER_HELPER);
	}
}
