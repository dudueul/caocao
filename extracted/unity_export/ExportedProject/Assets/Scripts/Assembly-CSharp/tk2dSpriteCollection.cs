using System;
using System.Collections.Generic;
using UnityEngine;

public class tk2dSpriteCollection : MonoBehaviour
{
	public enum NormalGenerationMode
	{
		None = 0,
		NormalsOnly = 1,
		NormalsAndTangents = 2
	}

	public enum TextureCompression
	{
		Uncompressed = 0,
		Reduced16Bit = 1,
		Compressed = 2,
		Dithered16Bit_Alpha = 3,
		Dithered16Bit_NoAlpha = 4
	}

	public enum AtlasFormat
	{
		UnityTexture = 0,
		Png = 1
	}

	[Serializable]
	public class AttachPointTestSprite
	{
		public string attachPointName;

		public tk2dSpriteCollectionData spriteCollection;

		public int spriteId;

		public bool CompareTo(AttachPointTestSprite src)
		{
			return false;
		}

		public void CopyFrom(AttachPointTestSprite src)
		{
		}
	}

	public const int CURRENT_VERSION = 4;

	[SerializeField]
	private tk2dSpriteCollectionDefinition[] textures;

	[SerializeField]
	private Texture2D[] textureRefs;

	public tk2dSpriteSheetSource[] spriteSheets;

	public tk2dSpriteCollectionFont[] fonts;

	public tk2dSpriteCollectionDefault defaults;

	public List<tk2dSpriteCollectionPlatform> platforms;

	public bool managedSpriteCollection;

	public bool loadable;

	public AtlasFormat atlasFormat;

	public int maxTextureSize;

	public bool forceTextureSize;

	public int forcedTextureWidth;

	public int forcedTextureHeight;

	public TextureCompression textureCompression;

	public int atlasWidth;

	public int atlasHeight;

	public bool forceSquareAtlas;

	public float atlasWastage;

	public bool allowMultipleAtlases;

	public bool removeDuplicates;

	public tk2dSpriteCollectionDefinition[] textureParams;

	public tk2dSpriteCollectionData spriteCollection;

	public bool premultipliedAlpha;

	public Material[] altMaterials;

	public Material[] atlasMaterials;

	public Texture2D[] atlasTextures;

	public TextAsset[] atlasTextureFiles;

	[SerializeField]
	private bool useTk2dCamera;

	[SerializeField]
	private int targetHeight;

	[SerializeField]
	private float targetOrthoSize;

	public tk2dSpriteCollectionSize sizeDef;

	public float globalScale;

	public float globalTextureRescale;

	public List<AttachPointTestSprite> attachPointTestSprites;

	[SerializeField]
	private bool pixelPerfectPointSampled;

	public FilterMode filterMode;

	public TextureWrapMode wrapMode;

	public bool userDefinedTextureSettings;

	public bool mipmapEnabled;

	public int anisoLevel;

	public tk2dSpriteDefinition.PhysicsEngine physicsEngine;

	public float physicsDepth;

	public bool disableTrimming;

	public bool disableRotation;

	public NormalGenerationMode normalGenerationMode;

	public int padAmount;

	public bool autoUpdate;

	public float editorDisplayScale;

	public int version;

	public string assetName;

	public Texture2D[] DoNotUse__TextureRefs
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool HasPlatformData
	{
		get
		{
			return false;
		}
	}

	public void Upgrade()
	{
	}
}
