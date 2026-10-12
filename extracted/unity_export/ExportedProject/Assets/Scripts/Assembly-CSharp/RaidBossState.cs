using System;
using System.Collections.Generic;
using UnityEngine;

public class RaidBossState : MonoBehaviour
{
	[Serializable]
	protected class ColorContainer
	{
		public EnumData.UnitResType resType;

		public Color[] colors;
	}

	protected class ResContainer
	{
		private int m_divider;

		private ColorContainer m_colorContaner;

		private UISlider m_slider;

		private UILabelLocalized m_label;

		private UITexture m_thumbTexture;

		private int m_max;

		private int m_cur;

		private int m_firstDepthValue;

		private int m_firstDepthDivider;

		private int m_depth;

		private bool m_useBackgroundBar;

		public int Max
		{
			get
			{
				return 0;
			}
		}

		public int Cur
		{
			get
			{
				return 0;
			}
		}

		public void Initialize(GameObject resObject, ColorContainer colorContainer, int divider)
		{
		}

		public void InitObjs(EnumData.UnitResType resType, string objectKey, ColorContainer colorContainer, int divider, GameObject parent)
		{
		}

		public void SetMaxRes(int value, bool needUpdate)
		{
		}

		public void SetRes(int value, bool needUpdate)
		{
		}

		private void Update()
		{
		}

		public void SetSliderState(bool state)
		{
		}
	}

	[SerializeField]
	private ColorContainer[] m_colorContainers;

	[SerializeField]
	private int m_divider;

	private bool m_initialized;

	private Dictionary<EnumData.UnitResType, ResContainer> m_resContainerMap;

	private void Awake()
	{
	}

	public void Initialize()
	{
	}

	private ColorContainer FindColorContainer(EnumData.UnitResType resType)
	{
		return null;
	}

	public void InitRes(EnumData.UnitResType resType, int max, int cur)
	{
	}

	public void UpdateRes(EnumData.UnitResType resType, int value, bool immediate)
	{
	}

	public void SetResSliderState(EnumData.UnitResType resType, bool flag)
	{
	}

	public void Animate(EnumData.UnitResType resType, int from, int to, float time = 1f)
	{
	}

	private void OnHpUpdate(float value)
	{
	}

	private void OnMpUpdate(float value)
	{
	}

	private void OnHpCompleted(int value)
	{
	}

	private void OnMpCompleted(int value)
	{
	}

	private void OnUpdate(EnumData.UnitResType resType, int value)
	{
	}

	private void OnDestroy()
	{
	}
}
