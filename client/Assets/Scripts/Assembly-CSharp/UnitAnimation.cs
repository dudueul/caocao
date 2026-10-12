using UnityEngine;

public class UnitAnimation : UnitAnimationBase
{
	private static int m_statusColorPropID;

	private static int m_meshBoundsPropID;

	private static int m_maskTexturePropID;

	private static int m_colorPropID;

	private static bool m_init;

	private GameObject m_shadowObj;

	private Color m_statusColor;

	private Vector4 m_meshBounds;

	private Texture m_maskingTex;

	private Color m_color;

	private GameObject m_maskingEffectObj;

	private EnumData.MaskType m_prevMaskType;

	public Color color
	{
		get
		{
			return default(Color);
		}
		set
		{
		}
	}

	protected override void SetColor()
	{
	}

	private static void InitStatics()
	{
	}

	protected override void Awake()
	{
	}

	protected override void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	private void printMat()
	{
	}

	private void OnWillRenderObject()
	{
	}

	private void SetPropertyBlock()
	{
	}

	private void HandleSpriteChanged(tk2dBaseSprite spr)
	{
	}

	protected void OnUnitMaskChanged(UnitAnimationBase sender, EnumData.MaskType maskType)
	{
	}

	public void UpdateMasking()
	{
	}

	public void ResetMasking()
	{
	}

	private static Texture GetUnitMaskingTexure(EnumData.MaskType maskType)
	{
		return null;
	}
}
