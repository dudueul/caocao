using System;
using Caocao;
using UnityEngine;

public abstract class UnitAnimationBase : MonoBehaviour
{
	private static Vector3 ms_mirroredByXFactor;

	private static string[] ms_battleUnitDirectionPostfix;

	private static string[] ms_eventUnitDirectionPostfix;

	private Vector3 m_originalScale;

	private Vector3 m_mirroredByXScale;

	protected MaterialPropertyBlock m_matProp;

	protected bool m_bStarted;

	protected static Texture2D ms_blackTex;

	protected static Texture2D ms_whiteTex;

	private static bool m_init;

	public Action<UnitAnimationBase, UnitDirection> UnitDirectionChanged;

	public Action<UnitAnimationBase, EnumData.MaskType> UnitMaskChanged;

	private UnitDirection m_unitDirection;

	private static Color[] m_unitStatusColorMutiflier;

	private static Color[] m_unitConditionColor;

	private UnitStatus m_unitStataus;

	private EnumData.MaskType m_unitMaskType;

	private bool[] m_unitCondition;

	private float m_shaderAlpha;

	public bool isUI { get; set; }

	public UnitAnimationReflected reflected { get; set; }

	public UnitDirection unitDirection
	{
		get
		{
			return UnitDirection.back;
		}
		set
		{
		}
	}

	public string unitDirectionPostfix
	{
		get
		{
			return null;
		}
	}

	public UnitStatus unitStatus
	{
		get
		{
			return UnitStatus.normal;
		}
		set
		{
		}
	}

	public EnumData.MaskType unitMaskType
	{
		get
		{
			return EnumData.MaskType.None;
		}
		protected set
		{
		}
	}

	public bool[] unitCondition
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public float shaderAlpha
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	private static void InitStatics()
	{
	}

	protected virtual void Awake()
	{
	}

	public void SetCondition(Caocao.UnitCondition condition, bool value)
	{
	}

	public void SetConditionEffect(EnumData.Conditions condition, bool isTrue, int stackCount)
	{
	}

	public bool GetCondition(Caocao.UnitCondition condition)
	{
		return false;
	}

	protected Color GetUnitColor()
	{
		return default(Color);
	}

	protected virtual void Start()
	{
	}

	protected abstract void SetColor();
}
