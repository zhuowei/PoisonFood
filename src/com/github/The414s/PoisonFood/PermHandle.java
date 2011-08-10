package com.github.The414s.PoisonFood;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PermHandle {

	protected static boolean cake;
	protected static boolean cookie;
	protected static boolean rawFish;
	protected static boolean cookedFish;
	protected static boolean rawPork;
	protected static boolean cookedPork;
	protected static boolean apple;
	protected static boolean goldenApple;
	protected static boolean soup;
	protected static boolean bread;

	private static boolean loadedP = true; //bukkit builtin permissions update
	private static boolean loaded = false;
	private static PoisonFood plugin;

	private static void load() {
		setupPermissions();
		loaded = true;
	}

	
	 static void handoff(PoisonFood poisonFood) {
		plugin = poisonFood;

	}

	protected static void newPlayer(Player player) {
		if (!loaded) {
			load();
		}
		if (loadedP) {
			cake = player.hasPermission("PoisonFood.immunity.cake");
			cookie = player.hasPermission("PoisonFood.immunity.cookie");
			rawFish = player.hasPermission("PoisonFood.immunity.rawFish");
			cookedFish = player.hasPermission("PoisonFood.immunity.cookedFish");
			rawPork = player.hasPermission("PoisonFood.immunity.rawPork");
			cookedPork = player.hasPermission("PoisonFood.immunity.cookedPork");
			apple = player.hasPermission("PoisonFood.immunity.apple");
			goldenApple = player.hasPermission("PoisonFood.immunity.goldenApple");
			soup = player.hasPermission("PoisonFood.immunity.soup");
			bread = player.hasPermission("PoisonFood.immunity.bread");
		} else {
			cake = false;
			cookie = false;
			rawFish = false;
			cookedFish = false;
			rawPork = false;
			cookedPork = false;
			apple = false;
			goldenApple = false;
			soup = false;
			bread = false;
		}

	}

	private static void setupPermissions() {
		
		plugin.log.info("[PoisonFood] Loading permissions");
	}
}
