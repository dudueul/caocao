using System;
using UnityEngine;

public class LocaleItem : MonoBehaviour
{
	public UILabel label;

	public GameObject selectGo;

	private Action<LocaleItem> m_onClick;

	public EnumData.Locale locale { get; private set; }

	public void SetData(string text, EnumData.Locale locale, Action<LocaleItem> onClick)
	{
	}

	public void SelectItem(bool select)
	{
	}

	private void OnClick()
	{
	}
}
