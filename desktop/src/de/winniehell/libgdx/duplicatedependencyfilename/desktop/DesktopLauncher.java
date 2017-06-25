package de.winniehell.libgdx.duplicatedependencyfilename.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.winniehell.libgdx.duplicatedependencyfilename.DuplicateDependencyFileName;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new DuplicateDependencyFileName(), config);
	}
}
