package io.github.betterthanupdates.babricnil.transformer;

import io.github.betterthanupdates.babricnil.BabricNilPremain;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("net.fabricmc.impl.game.GameProviderHelper")
public class GameProviderHelperTransformer extends MiniTransformer {
	@Patch.Method("deobfuscate0")
	public void patchDeobfuscate0(PatchContext ctx) {
		ctx.jumpToStart();

		final PatchContext.SearchResult result = ctx.search(
				INVOKESTATIC("net/fabricmc/tinyremapper/TinyRemapper", "newRemapper", "()Lnet/fabricmc/tinyremapper/TinyRemapper$Builder;"),
				ALOAD(3),
				LDC("official"),
				ALOAD(4)
		);

		if (result.isSuccessful()) {
			result.jumpBefore();

			ctx.add(
					// This is the recommended way to do ASM hooks in NilLoader - invoke a helper defined
					// in an inner class for your transformer.
					INVOKESTATIC("io/github/betterthanupdates/babricnil/transformer/GameProviderTransformer$Hooks", "onDeobfuscate0", "()V")
			);

			result.erase();
		}
	}

	public static class Hooks {
		public static void onDeobfuscate0() {
			BabricNilPremain.logger.info("Hello from Fabric Loader patch!");
		}
	}
}
