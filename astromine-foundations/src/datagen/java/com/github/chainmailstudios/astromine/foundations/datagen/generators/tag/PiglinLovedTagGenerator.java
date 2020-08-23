package com.github.chainmailstudios.astromine.foundations.datagen.generators.tag;

import com.github.chainmailstudios.astromine.datagen.generator.tag.set.GenericSetTagGenerator;
import com.github.chainmailstudios.astromine.datagen.material.MaterialItemType;
import com.github.chainmailstudios.astromine.datagen.material.MaterialSet;
import net.minecraft.util.Identifier;

public class PiglinLovedTagGenerator extends GenericSetTagGenerator {
	public PiglinLovedTagGenerator(MaterialItemType type) {
		super("piglin_loved", new Identifier("piglin_loved"), type);
	}

	@Override
	public boolean shouldGenerate(MaterialSet set) {
		return set.isPiglinLoved() && super.shouldGenerate(set);
	}
}
