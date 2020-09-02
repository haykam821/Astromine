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

package com.github.chainmailstudios.astromine.common.item.base;

import com.github.chainmailstudios.astromine.common.volume.fluid.FluidVolume;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;

import com.github.chainmailstudios.astromine.common.volume.fraction.Fraction;
import net.minecraft.util.Identifier;

public class FluidVolumeItem extends BaseVolumeItem<FluidVolume> {
	private final Fraction size;

	private FluidVolumeItem(Item.Settings settings, Fraction size) {
		super(settings);

		this.size = size;
	}

	public Fraction getSize() {
		return size;
	}

	public static FluidVolumeItem of(Item.Settings settings, Fraction size) {
		return new FluidVolumeItem(settings, size);
	}
}
