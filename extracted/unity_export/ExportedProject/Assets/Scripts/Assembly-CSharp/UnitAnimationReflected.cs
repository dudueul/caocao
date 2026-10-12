using UnityEngine;

public class UnitAnimationReflected : MonoBehaviour
{
	private static int m_statusColorPropID;

	private static int m_meshBoundsPropID;

	private static int m_maskTexturePropID;

	private static int m_colorPropID;

	private static bool ms_initStatic;

	protected MaterialPropertyBlock m_matProp;

	private Color m_statusColor;

	private Vector4 m_meshBounds;

	private Texture m_reflectedMaskTex;

	private Color m_color;

	protected static Texture2D ms_whiteTex;

	private void Awake()
	{
	}

	private static void InitStatics()
	{
	}

	protected void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnWillRenderObject()
	{
	}

	private void SetPropertyBlock()
	{
	}

	public void ApplyRenderingInfo(Mesh sharedMesh, Color statusColor, Color itweenColor)
	{
	}

	protected void OnUnitMaskChanged(UnitAnimationBase sender, EnumData.MaskType maskType)
	{
	}

	private static Texture GetReflectedUnitMaskingTexture(EnumData.MaskType maskType)
	{
		return null;
	}
}
