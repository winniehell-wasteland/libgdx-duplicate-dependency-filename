package de.winniehell.libgdx.duplicatedependencyfilename;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class DuplicateDependencyFileName extends ApplicationAdapter {

	private AssetManager assetManager;

	class Apple {

	}

	class AppleLoader extends AsynchronousAssetLoader<Apple, AssetLoaderParameters<Apple>> {

		AppleLoader(FileHandleResolver resolver) {
			super(resolver);
		}

		@Override
		public void loadAsync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<Apple> parameter) {

		}

		@Override
		public Apple loadSync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<Apple> parameter) {
			return new Apple();
		}

		@Override
		public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, AssetLoaderParameters<Apple> parameter) {
			return null;
		}
	}

	class Pear {

	}

	class PearLoader extends AsynchronousAssetLoader<Pear, AssetLoaderParameters<Pear>> {

		PearLoader(FileHandleResolver resolver) {
			super(resolver);
		}

		@Override
		public void loadAsync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<Pear> parameter) {

		}

		@Override
		public Pear loadSync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<Pear> parameter) {
			return new Pear();
		}

		@Override
		public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, AssetLoaderParameters<Pear> parameter) {
			return null;
		}
	}

	class FruitSalad {

	}

	class FruitSaladLoader extends AsynchronousAssetLoader<FruitSalad, AssetLoaderParameters<FruitSalad>> {

		FruitSaladLoader(FileHandleResolver resolver) {
			super(resolver);
		}

		@Override
		public void loadAsync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<FruitSalad> parameter) {

		}

		@Override
		public FruitSalad loadSync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<FruitSalad> parameter) {
			return new FruitSalad();
		}

		@Override
		public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, AssetLoaderParameters<FruitSalad> parameter) {
			Array<AssetDescriptor> dependencies = new Array<AssetDescriptor>();
			dependencies.add(new AssetDescriptor<Apple>("ingredient", Apple.class));
			dependencies.add(new AssetDescriptor<Pear>("ingredient", Pear.class));
			return dependencies;
		}
	}

	@Override
	public void create () {
		InternalFileHandleResolver fileHandleResolver = new InternalFileHandleResolver();
		assetManager = new AssetManager(fileHandleResolver);
		assetManager.setLoader(Apple.class, new AppleLoader(fileHandleResolver));
		assetManager.setLoader(Pear.class, new PearLoader(fileHandleResolver));
		assetManager.setLoader(FruitSalad.class, new FruitSaladLoader(fileHandleResolver));
		assetManager.load("delicious", FruitSalad.class);

		assetManager.finishLoading();

		assetManager.get("delicious", FruitSalad.class);
		assetManager.get("ingredient", Apple.class);
		assetManager.get("ingredient", Pear.class);
	}

	@Override
	public void dispose () {
		assetManager.dispose();
	}
}
