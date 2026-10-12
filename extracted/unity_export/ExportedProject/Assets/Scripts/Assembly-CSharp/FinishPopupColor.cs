using System;
using System.Collections.Generic;
using UnityEngine;

public class FinishPopupColor : MonoBehaviour
{
	[Serializable]
	protected class ColorContainer
	{
		public EnumData.FinishPopupColorType colorType;

		public Color color;

		public bool stampLabelSet;
	}

	[SerializeField]
	private ColorContainer[] m_colorContainers;

	private bool m_initialized;

	private UILabelLocalized uiLabel;

	private UISprite uiSprite;

	private Dictionary<EnumData.FinishPopupColorType, ColorContainer> m_colorContainersMap;

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	public void SetColors(bool isWin)
	{
	}

	private bool isWinColorType(EnumData.FinishPopupColorType colorType)
	{
		return false;
	}

	private void SetColor(ColorContainer colorContainer)
	{
	}

	public void SetColor(EnumData.FinishPopupColorType colorType, UILabelLocalized label)
	{
	}

	public void SetColor(EnumData.FinishPopupColorType colorType, UISprite sprite)
	{
	}
}
