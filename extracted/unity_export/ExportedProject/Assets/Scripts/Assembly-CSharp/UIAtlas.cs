using System;
using System.Collections.Generic;
using UnityEngine;

public class UIAtlas : MonoBehaviour
{
	[Serializable]
	private class Sprite
	{
		public string name;

		public Rect outer;

		public Rect inner;

		public bool rotated;

		public float paddingLeft;

		public float paddingRight;

		public float paddingTop;

		public float paddingBottom;

		public bool hasPadding
		{
			get
			{
				return false;
			}
		}
	}

	private enum Coordinates
	{
		Pixels = 0,
		TexCoords = 1
	}

	[HideInInspector]
	[SerializeField]
	private Material material;

	[HideInInspector]
	[SerializeField]
	private List<UISpriteData> mSprites;

	[HideInInspector]
	[SerializeField]
	private float mPixelSize;

	[HideInInspector]
	[SerializeField]
	private UIAtlas mReplacement;

	[HideInInspector]
	[SerializeField]
	private Coordinates mCoordinates;

	[HideInInspector]
	[SerializeField]
	private List<Sprite> sprites;

	[HideInInspector]
	[SerializeField]
	public int origTexWidth;

	[HideInInspector]
	[SerializeField]
	public int origTexHeight;

	private int mPMA;

	private Dictionary<string, int> mSpriteIndices;

	public Material spriteMaterial
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool premultipliedAlpha
	{
		get
		{
			return false;
		}
	}

	public List<UISpriteData> spriteList
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Texture texture
	{
		get
		{
			return null;
		}
	}

	public float pixelSize
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public UIAtlas replacement
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public UISpriteData GetSprite(string name)
	{
		return null;
	}

	public string GetRandomSprite(string startsWith)
	{
		return null;
	}

	public void MarkSpriteListAsChanged()
	{
	}

	public void SortAlphabetically()
	{
	}

	public BetterList<string> GetListOfSprites()
	{
		return null;
	}

	public BetterList<string> GetListOfSprites(string match)
	{
		return null;
	}

	private bool References(UIAtlas atlas)
	{
		return false;
	}

	public static bool CheckIfRelated(UIAtlas a, UIAtlas b)
	{
		return false;
	}

	public void MarkAsChanged()
	{
	}

	private bool Upgrade()
	{
		return false;
	}
}
