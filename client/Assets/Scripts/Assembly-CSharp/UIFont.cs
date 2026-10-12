using System;
using System.Collections.Generic;
using UnityEngine;

[ExecuteInEditMode]
public class UIFont : MonoBehaviour
{
	[HideInInspector]
	[SerializeField]
	private Material mMat;

	[HideInInspector]
	[SerializeField]
	private Rect mUVRect;

	[HideInInspector]
	[SerializeField]
	private BMFont mFont;

	[HideInInspector]
	[SerializeField]
	private UIAtlas mAtlas;

	[HideInInspector]
	[SerializeField]
	private UIFont mReplacement;

	[HideInInspector]
	[SerializeField]
	private List<BMSymbol> mSymbols;

	[HideInInspector]
	[SerializeField]
	private Font mDynamicFont;

	[HideInInspector]
	[SerializeField]
	private int mDynamicFontSize;

	[HideInInspector]
	[SerializeField]
	private FontStyle mDynamicFontStyle;

	[NonSerialized]
	private UISpriteData mSprite;

	private int mPMA;

	private int mPacked;

	public BMFont bmFont
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int texWidth
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int texHeight
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool hasSymbols
	{
		get
		{
			return false;
		}
	}

	public List<BMSymbol> symbols
	{
		get
		{
			return null;
		}
	}

	public UIAtlas atlas
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Material material
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	[Obsolete]
	public bool premultipliedAlpha
	{
		get
		{
			return false;
		}
	}

	public bool premultipliedAlphaShader
	{
		get
		{
			return false;
		}
	}

	public bool packedFontShader
	{
		get
		{
			return false;
		}
	}

	public Texture2D texture
	{
		get
		{
			return null;
		}
	}

	public Rect uvRect
	{
		get
		{
			return default(Rect);
		}
		set
		{
		}
	}

	public string spriteName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool isValid
	{
		get
		{
			return false;
		}
	}

	[Obsolete]
	public int size
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int defaultSize
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public UISpriteData sprite
	{
		get
		{
			return null;
		}
	}

	public UIFont replacement
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public bool isDynamic
	{
		get
		{
			return false;
		}
	}

	public Font dynamicFont
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public FontStyle dynamicFontStyle
	{
		get
		{
			return FontStyle.Normal;
		}
		set
		{
		}
	}

	private Texture dynamicTexture
	{
		get
		{
			return null;
		}
	}

	private void Trim()
	{
	}

	private bool References(UIFont font)
	{
		return false;
	}

	public static bool CheckIfRelated(UIFont a, UIFont b)
	{
		return false;
	}

	public void MarkAsChanged()
	{
	}

	public void UpdateUVRect()
	{
	}

	private BMSymbol GetSymbol(string sequence, bool createIfMissing)
	{
		return null;
	}

	public BMSymbol MatchSymbol(string text, int offset, int textLength)
	{
		return null;
	}

	public void AddSymbol(string sequence, string spriteName)
	{
	}

	public void RemoveSymbol(string sequence)
	{
	}

	public void RenameSymbol(string before, string after)
	{
	}

	public bool UsesSprite(string s)
	{
		return false;
	}
}
