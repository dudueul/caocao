using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

public abstract class ShowPanelAnimation : MonoBehaviour
{
	public enum Target
	{
		left = 0,
		right = 1
	}

	public enum ElementType
	{
		start = 0,
		hp = start,
		mp = 1,
		ep = 2,
		exp = 3,
		weaponExp = 4,
		armorExp = 5,
		num = 6
	}

	private class ElementTypeComparer : IEqualityComparer<ElementType>
	{
		bool IEqualityComparer<ElementType>.Equals(ElementType x, ElementType y)
		{
			return false;
		}

		int IEqualityComparer<ElementType>.GetHashCode(ElementType obj)
		{
			return 0;
		}
	}

	[StructLayout((LayoutKind)0, Size = 16)]
	protected struct ElementAnimData
	{
		public int m_fromValue;

		public int m_toValue;

		public int m_maxValue;

		public int m_stepValue;

		public ElementAnimData(int from, int to, int max, int stepValue)
		{
			m_fromValue = 0;
			m_toValue = 0;
			m_maxValue = 0;
			m_stepValue = 0;
		}
	}

	protected bool m_playing;

	protected bool m_completed;

	protected UnitData m_triggerUnitData;

	protected UnitData m_targetUnitData;

	protected GameObject m_damagePopUp;

	protected DamageBalloonSimple m_damageBalloon;

	protected PanelFaceDirectionHelper m_faceDirectionHelper;

	protected readonly string icon_mp;

	protected readonly Color color_mp_bar;

	protected readonly string icon_ep;

	protected readonly Color color_ep_bar;

	protected Dictionary<ElementType, List<ElementAnimData>> m_type2Elements;

	protected static string ms_jobRelationFormat;

	protected static string ms_defaultStepLabelFormat;

	protected static string ms_tileInfoFormat;

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	public bool isCompleted
	{
		get
		{
			return false;
		}
	}

	public abstract void SetResult(List<EffectBase> effects, UnitData triggerUnitObj, UnitData targerUnitObj);

	public abstract void StartAnimation();

	public abstract void InitPanel();

	public abstract void ShowFaceDamaged(float delay);

	public abstract void ShowFaceDefended(float delay);

	public abstract void ShowFaceAttack(float delay);

	public abstract void ShowFaceCriticalAttack(float delay);

	protected virtual void OnDestroy()
	{
	}

	public static ShowPanelAnimation AddShowPanelAnimation(GameObject target)
	{
		return null;
	}

	public static GameObject GetShowPanelAnimationGameObject(Target target)
	{
		return null;
	}

	protected void AddValue(ElementType type, int from, int to, int max, int stepValue)
	{
	}

	protected void StackValue(ElementType type, int from, int to, int max, int stepValue)
	{
	}

	protected int GetMaxValue(ElementType type, int currentLev)
	{
		return 0;
	}

	protected int GetDataFromUnit(ElementType type)
	{
		return 0;
	}

	protected abstract UISlider GetSlider(ElementType type);

	protected abstract UILabelLocalized GetLabel(ElementType type);

	protected abstract UILabelLocalized GetStepLabel(ElementType type);

	protected abstract string GetStepLabelFormat(ElementType type);
}
