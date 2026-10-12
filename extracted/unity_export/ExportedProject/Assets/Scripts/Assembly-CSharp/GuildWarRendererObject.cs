using UnityEngine;

public class GuildWarRendererObject : GuildWarRenderer
{
	private string visibleObjectPrefabPath;

	private GameObject visibleObject;

	private Renderer visibleObjectRenderer;

	private bool flipX;

	private tk2dSprite tk2dSprite;

	private tk2dSpriteAnimator tk2dAnimator;

	private tk2dSpriteAnimation animationLibrary;

	private string defaultAnimationName;

	private bool animating;

	private static int? m_mainTexId;

	private static int? m_alphaDyeTexId;

	private static int? m_dyeAlphaTexId;

	private static int? m_alphaTexId;

	private static int? m_dyeTexId;

	private static int? m_redColorId;

	private static int? m_greenColorId;

	private static int? m_blueColorId;

	private static int? m_statusColorPropID;

	private static int? m_meshBoundsPropID;

	private static int? m_maskTexturePropID;

	private static int? m_colorPropID;

	public static int mainTexId
	{
		get
		{
			return 0;
		}
	}

	public static int alphaDyeTexId
	{
		get
		{
			return 0;
		}
	}

	public static int dyeAlphaTexId
	{
		get
		{
			return 0;
		}
	}

	public static int alphaTexId
	{
		get
		{
			return 0;
		}
	}

	public static int dyeTexId
	{
		get
		{
			return 0;
		}
	}

	public static int redColorId
	{
		get
		{
			return 0;
		}
	}

	public static int greenColorId
	{
		get
		{
			return 0;
		}
	}

	public static int blueColorId
	{
		get
		{
			return 0;
		}
	}

	public static int statusColorPropID
	{
		get
		{
			return 0;
		}
	}

	public static int meshBoundsPropID
	{
		get
		{
			return 0;
		}
	}

	public static int maskTexturePropID
	{
		get
		{
			return 0;
		}
	}

	public static int colorPropID
	{
		get
		{
			return 0;
		}
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public static GameObject GetCorpsBattleInformationObject()
	{
		return null;
	}

	public static void DisposeCorpsBattleInformationObject(GameObject gameObject)
	{
	}

	public bool SetVisibleObject(string prefabPath, string childName = "")
	{
		return false;
	}

	public void RemoveVisibleObject()
	{
	}

	public void SetVisibleObjectAnimationLibrary(tk2dSpriteAnimation animation)
	{
	}

	public void SetVisibleObjectDefaultAnimationName(string animationName)
	{
	}

	private void UpdateVisibleObjectDefaultAnimation()
	{
	}

	public void StartVisibleObjectAnimationOnce(string animationName)
	{
	}

	private void OnVisibleObjectAnimationCompleted(tk2dSpriteAnimator animator, tk2dSpriteAnimationClip clip)
	{
	}

	public virtual void RenderingUpdate()
	{
	}

	public GameObject GetVisibleObject()
	{
		return null;
	}

	public string GetVisibleObjectPrefabPath()
	{
		return null;
	}

	public Renderer GetVisibleObjectRenderer()
	{
		return null;
	}

	public void SetVisibleObjectFlipX(bool flag)
	{
	}

	private void UpdateVisibleObjectFlipX()
	{
	}

	public void HPChangeEffect(GuildWarEffectData_ChangeHpAction changeHpActionData)
	{
	}
}
