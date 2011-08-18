package com.github.The414s.PoisonFood;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.The414s.util.T4Formatter;

public class PoisonFood extends JavaPlugin {

	
	protected Logger log = Logger.getLogger("Minecraft");
	protected Logger debug = Logger.getLogger("PoisonFood");
	private final PoisonFoodPlayerListener playerListener = new PoisonFoodPlayerListener(
			this);
	static String mainDirectory = "plugins/PoisonFood";
	PluginDescriptionFile pdfFile = this.getDescription();
	protected static PermHandle p;

	public short poisonMetadata = (short) 400;

	public Material poisonMaterial = Material.RED_MUSHROOM;
	
	@Override
	public void onDisable() {
		this.debug.info("Plugin disabled");
		for (Handler ele : debug.getHandlers()) {
			debug.removeHandler(ele);
			ele.close();
		}
		// this.log.info("["+pdfFile.getName() + "]["
		// + pdfFile.getVersion() + "] is disabled.");
		this.log.info("[PoisonFood]Plugin disabled");

		debug = null;
	}

	@SuppressWarnings("static-access")
	@Override
	public void onEnable() {
		debug.setUseParentHandlers(false);
		File file = new File("plugins/PoisonFood/plugin.log");
		new File(mainDirectory).mkdir();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				this.log.warning("[PoisonFood] Error #7" + e.getMessage());
			}
		}

		try {
			for (Handler ele : debug.getHandlers()) {
				debug.removeHandler(ele);
			}
			
			FileHandler fh = new FileHandler("plugins/PoisonFood/plugin.log");

			fh.setFormatter(new T4Formatter());
			debug.addHandler(fh);

		} catch (SecurityException e1) {
			this.log.warning("[PoisonFood] Error #5");
			this.debug.warning(" Error #5");
		} catch (IOException e1) {
			this.log.warning("[PoisonFood] Error #6");
			this.debug.warning(" Error #6");
		}
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener,
				Event.Priority.Low, this);
		
		new ConfigurationManager(this).load();
		playerListener.passConfig();
		p.handoff(this);
		this.debug.info(" Plugin enabled");
		this.log.info("[PoisonFood] Plugin enabled");
		poisonMetadata = (short) ConfigurationManager.config.getInt("poisonMetadata", 400);
		Material mat = Material.matchMaterial(ConfigurationManager.config.getString("poisonMaterial", "RED_MUSHROOM"));
		if (mat != null)
			poisonMaterial = mat;
		loadRecipes();
		
	}

	private void loadRecipes() {
		Server server = getServer();
		server.addRecipe(new ShapelessRecipe(new ItemStack(Material.PORK, 1, poisonMetadata)).addIngredient(Material.PORK, 0).addIngredient(poisonMaterial));
		server.addRecipe(new ShapelessRecipe(new ItemStack(Material.GRILLED_PORK, 1, poisonMetadata)).addIngredient(Material.GRILLED_PORK, 0).addIngredient(poisonMaterial));
		server.addRecipe(new ShapelessRecipe(new ItemStack(Material.BREAD, 1, poisonMetadata)).addIngredient(Material.BREAD, 0).addIngredient(poisonMaterial));
		server.addRecipe(new ShapelessRecipe(new ItemStack(Material.RAW_FISH, 1, poisonMetadata)).addIngredient(Material.RAW_FISH, 0).addIngredient(poisonMaterial));
		server.addRecipe(new ShapelessRecipe(new ItemStack(Material.COOKED_FISH, 1, poisonMetadata)).addIngredient(Material.COOKED_FISH, 0).addIngredient(poisonMaterial));
		server.addRecipe(new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP, 1, poisonMetadata)).addIngredient(Material.MUSHROOM_SOUP, 0).addIngredient(poisonMaterial));
		server.addRecipe(new ShapelessRecipe(new ItemStack(Material.APPLE, 1, poisonMetadata)).addIngredient(Material.APPLE, 0).addIngredient(poisonMaterial));
		/* It takes 8 mushrooms to poison a golden apple. */
		server.addRecipe(new ShapelessRecipe(new ItemStack(Material.GOLDEN_APPLE, 1, poisonMetadata)).addIngredient(Material.GOLDEN_APPLE, 0).addIngredient(8, poisonMaterial));
		server.addRecipe(new ShapelessRecipe(new ItemStack(Material.COOKIE, 1, poisonMetadata)).addIngredient(Material.COOKIE, 0).addIngredient(poisonMaterial));

	}
}
