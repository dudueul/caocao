using UnityEngine;

namespace tk2dRuntime
{
	internal static class SpriteCollectionGenerator
	{
		public static tk2dSpriteCollectionData CreateFromTexture(Texture texture, tk2dSpriteCollectionSize size, Rect region, Vector2 anchor)
		{
			return null;
		}

		public static tk2dSpriteCollectionData CreateFromTexture(Texture texture, tk2dSpriteCollectionSize size, string[] names, Rect[] regions, Vector2[] anchors)
		{
			return null;
		}

		public static tk2dSpriteCollectionData CreateFromTexture(Texture texture, tk2dSpriteCollectionSize size, Vector2 textureDimensions, string[] names, Rect[] regions, Rect[] trimRects, Vector2[] anchors, bool[] rotated)
		{
			return null;
		}

		public static tk2dSpriteCollectionData CreateFromTexture(GameObject parentObject, Texture texture, tk2dSpriteCollectionSize size, Vector2 textureDimensions, string[] names, Rect[] regions, Rect[] trimRects, Vector2[] anchors, bool[] rotated)
		{
			return null;
		}

		private static tk2dSpriteDefinition CreateDefinitionForRegionInTexture(string name, Vector2 textureDimensions, float scale, Rect uvRegion, Rect trimRect, Vector2 anchor, bool rotated)
		{
			return null;
		}

		public static tk2dSpriteCollectionData CreateFromTexturePacker(tk2dSpriteCollectionSize spriteCollectionSize, string texturePackerFileContents, Texture texture)
		{
			return null;
		}
	}
}
