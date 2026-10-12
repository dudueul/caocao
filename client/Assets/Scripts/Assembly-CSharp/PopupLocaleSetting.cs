using System.Collections.Generic;

public class PopupLocaleSetting : CaocaoUIModalPopup
{
	public UIGrid languageGrid;

	public UIGrid voiceGrid;

	private Dictionary<string, EnumData.Locale> m_voices;

	private List<LocaleItem> m_languageItems;

	private List<LocaleItem> m_voiceItems;

	private EnumData.Locale m_selectedLanguage;

	private EnumData.Locale m_selectedVoice;

	private void OnEnable()
	{
	}

	private void UpdateUI()
	{
	}

	public void OnOK()
	{
	}

	public void OnCancel()
	{
	}

	private void OnSelectLanguage(LocaleItem localeItem)
	{
	}

	private void OnSelectVoice(LocaleItem localeItem)
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
