package com.github.chainmailstudios.astromine.common.recipe;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import com.github.chainmailstudios.astromine.AstromineCommon;
import com.github.chainmailstudios.astromine.common.component.inventory.ItemInventoryComponent;
import com.github.chainmailstudios.astromine.common.component.inventory.compatibility.ItemInventoryComponentFromItemInventory;
import com.github.chainmailstudios.astromine.common.utilities.EnergyUtilities;
import com.github.chainmailstudios.astromine.common.utilities.IngredientUtilities;
import com.github.chainmailstudios.astromine.common.utilities.PacketUtilities;
import com.github.chainmailstudios.astromine.common.utilities.ParsingUtilities;
import com.github.chainmailstudios.astromine.common.utilities.StackUtilities;
import com.github.chainmailstudios.astromine.registry.AstromineBlocks;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class TrituratingRecipe implements Recipe<Inventory> {
	final Identifier identifier;
	final Ingredient input;
	final ItemStack output;
	final double energyConsumed;
	final int time;

	public TrituratingRecipe(Identifier identifier, Ingredient input, ItemStack output, double energyConsumed, int time) {
		this.identifier = identifier;
		this.input = input;
		this.output = output;
		this.energyConsumed = energyConsumed;
		this.time = time;
	}

	@Override
	public boolean matches(Inventory inventory, World world) {
		return ItemInventoryComponentFromItemInventory.of(inventory).getItemContents().values().stream().anyMatch(input);
	}

	@Override
	public ItemStack craft(Inventory inventory) {
		ItemInventoryComponent component = ItemInventoryComponentFromItemInventory.of(inventory);
		List<ItemStack> matching = Lists.newArrayList(component.getContentsMatching(input));

		ItemStack stack = matching.isEmpty() ? ItemStack.EMPTY : matching.get(0);

		for (Map.Entry<Integer, ItemStack> entry : component.getItemContents().entrySet()) {
			if (entry.getValue() == stack && !stack.isEmpty()) {
				component.getStack(entry.getKey()).decrement(1);

				break;
			}
		}

		return output.copy();
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getOutput() {
		return output.copy();
	}

	@Override
	public Identifier getId() {
		return identifier;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return Type.INSTANCE;
	}

	@Override
	public DefaultedList<Ingredient> getPreviewInputs() {
		DefaultedList<Ingredient> defaultedList = DefaultedList.of();
		defaultedList.add(this.input);
		return defaultedList;
	}

	@Override
	public ItemStack getRecipeKindIcon() {
		return new ItemStack(AstromineBlocks.TRITURATOR);
	}

	public int getTime() {
		return time;
	}

	public double getEnergyConsumed() {
		return energyConsumed;
	}

	public static final class Serializer implements RecipeSerializer<TrituratingRecipe> {
		public static final Identifier ID = AstromineCommon.identifier("triturating");

		public static final Serializer INSTANCE = new Serializer();

		private Serializer() {
			// Locked.
		}

		@Override
		public TrituratingRecipe read(Identifier identifier, JsonObject object) {
			TrituratingRecipe.Format format = new Gson().fromJson(object, TrituratingRecipe.Format.class);

			return new TrituratingRecipe(identifier,
					IngredientUtilities.fromJson(format.input),
					StackUtilities.fromJson(format.output),
					EnergyUtilities.fromJson(format.energyConsumed),
					ParsingUtilities.fromJson(format.time, Integer.class));
		}

		@Override
		public TrituratingRecipe read(Identifier identifier, PacketByteBuf buffer) {
			return new TrituratingRecipe(identifier,
					IngredientUtilities.fromPacket(buffer),
					StackUtilities.fromPacket(buffer),
					EnergyUtilities.fromPacket(buffer),
					PacketUtilities.fromPacket(buffer, Integer.class));
		}

		@Override
		public void write(PacketByteBuf buffer, TrituratingRecipe recipe) {
			IngredientUtilities.toPacket(buffer, recipe.input);
			StackUtilities.toPacket(buffer, recipe.output);
			EnergyUtilities.toPacket(buffer, recipe.energyConsumed);
			PacketUtilities.toPacket(buffer, recipe.time);
		}
	}

	public static final class Type implements AstromineRecipeType<TrituratingRecipe> {
		public static final Type INSTANCE = new Type();

		private Type() {
			// Locked.
		}
	}

	public static final class Format {
		JsonObject input;
		JsonObject output;
		@SerializedName("time")
		JsonPrimitive time;
		@SerializedName("energy_consumed")
		JsonElement energyConsumed;

		@Override
		public String toString() {
			return "Format{" +
					"input=" + input +
					", output=" + output +
					", time=" + time +
					", energyConsumed=" + energyConsumed +
					'}';
		}
	}
}