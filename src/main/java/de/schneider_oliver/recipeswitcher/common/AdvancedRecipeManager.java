package de.schneider_oliver.recipeswitcher.common;

import java.util.Optional;

import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.world.World;

public interface AdvancedRecipeManager {

	
	public <C extends Inventory, T extends Recipe<C>> Optional<T> getNthMatch(RecipeType<T> type, C inventory, World world, WrappedInt match);

	public <C extends Inventory, T extends Recipe<C>> int getNumMatches(RecipeType<T> type, C inventory, World world);
	
}
