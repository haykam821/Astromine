/*
 * MIT License
 *
 * Copyright (c) 2020 Chainmail Studios
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.chainmailstudios.astromine.technologies.client.rei.electricsmelting;

import com.github.chainmailstudios.astromine.technologies.client.rei.AstromineTechnologiesRoughlyEnoughItemsPlugin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.util.Identifier;

import me.shedaniel.rei.plugin.cooking.DefaultCookingDisplay;

@Environment(EnvType.CLIENT)
public class ElectricSmeltingDisplay extends DefaultCookingDisplay {
	private final double energyRequired;

	static {
		getFuel().clear();
	}

	public ElectricSmeltingDisplay(AbstractCookingRecipe recipe) {
		super(recipe);
		this.energyRequired = recipe.getCookTime() * 2;
	}

	@Override
	public Identifier getRecipeCategory() {
		return AstromineTechnologiesRoughlyEnoughItemsPlugin.ELECTRIC_SMELTING;
	}

	public double getEnergyRequired() {
		return energyRequired;
	}
}
