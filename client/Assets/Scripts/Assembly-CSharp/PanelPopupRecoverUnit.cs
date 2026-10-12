using System;
using System.Collections.Generic;
using System.Text;
using LitJson;

public class PanelPopupRecoverUnit
{
	private static Action m_callback;

	private static int m_needGold;

	private List<ulong> m_unitSrls;

	private static StringBuilder ms_stringBuilder;

	private static void OnRecoverEnergy(JsonData response)
	{
	}

	public static void CreatePopup(List<ulong> unitSrls, Action callback)
	{
	}
}
