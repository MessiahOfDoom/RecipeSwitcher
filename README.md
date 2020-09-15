# Messiahs RecipeSwitcher

This mod allows you to switch between multiple conflicting crafting recipes. This is done in a modular way, so expanding this mod for other crafting recipes is as simple as adding 2 more Mixins, one for the ScreenHandler of the crafting inventory, and one for the HandledScreen, which adds the button.


To create an Addon for a different crafting inventory, create a new Fabric Mod, add this
```gradle
repositories {
    maven {
        url = 'https://raw.githubusercontent.com/MessiahOfDoom/Maven-Repo/master'
    }
}
```
and this
```gradle
dependencies {
	
	//[...] all your other stuff here
	modImplementation "de.schneider-oliver:RecipeSwitcher:1.0.0"
	
}
```
to the build.gradle
as a dependency, replacing `1.0.0` with whatever the current version is.
