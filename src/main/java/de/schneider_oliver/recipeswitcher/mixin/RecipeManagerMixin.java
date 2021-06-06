package de.schneider_oliver.recipeswitcher.mixin;

import java.util.Map;
import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import de.schneider_oliver.recipeswitcher.common.AdvancedRecipeInventory;
import de.schneider_oliver.recipeswitcher.common.AdvancedRecipeManager;
import de.schneider_oliver.recipeswitcher.common.WrappedInt;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin implements AdvancedRecipeManager{

	@Inject(at = @At("HEAD"), method = "getFirstMatch(Lnet/minecraft/recipe/RecipeType;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/world/World;)Ljava/util/Optional;", cancellable = true)
	public <C extends Inventory, T extends Recipe<C>> void getFirstMatch(RecipeType<T> type, C inventory, World world, CallbackInfoReturnable<Optional<T>> info) {
		if (inventory instanceof AdvancedRecipeInventory) {
			info.setReturnValue(getNthMatch(type, inventory, world, ((AdvancedRecipeInventory)inventory).getWrappedMatch()));
		}
	}
	
	@Shadow
	abstract <C extends Inventory, T extends Recipe<C>> Map<Identifier, Recipe<C>> getAllOfType(RecipeType<T> type);
	
	public <C extends Inventory, T extends Recipe<C>> Optional<T> getNthMatch(RecipeType<T> type, C inventory, World world, WrappedInt match) {
		return this.getAllOfType(type).values().stream().flatMap((recipe) -> {
			return Util.stream(type.get(recipe, world, inventory));
		}).skip(match.intValue() - 1).findFirst();
	}
	
	public <C extends Inventory, T extends Recipe<C>> int getNumMatches(RecipeType<T> type, C inventory, World world) {
		return (int)this.getAllOfType(type).values().stream().flatMap((recipe) -> {
			return Util.stream(type.get(recipe, world, inventory));
		}).count();
	}
	
	
	
}
