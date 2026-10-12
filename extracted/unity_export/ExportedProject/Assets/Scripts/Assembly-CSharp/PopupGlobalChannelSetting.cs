using System;
using System.Collections.Generic;
using Chat;

public class PopupGlobalChannelSetting : CaocaoUIModalPopup
{
	public UIGrid languageGrid;

	private List<LocaleItem> m_languageItems;

	private EnumData.Locale m_selectedLanguage;

	public Action<EChatRoomLocale> onChangeRoomLocale { get; set; }

	public override void ShowPanel()
	{
	}

	private void UpdateUI()
	{
	}

	private void OnSelectLanguage(LocaleItem localeItem)
	{
	}

	public void OnOK()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
