using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class UserArenaInfoUI : PopupUserInfo.InnerUI
{
	private enum TabMode
	{
		All = 0,
		Keep = 1,
		Recent = 2
	}

	private Transform transform;

	private JsonData jsonData;

	private TabMode tabMode;

	private bool initiated;

	private bool initiatedTab;

	private List<UserArenaInfoListItemUI.CellData> cellDatas;

	private UIReuseGrid m_grid;

	private CaocaoTabButtons m_tabButton;

	private GameObject m_objAll;

	private UIGrid m_gridScenario;

	private UILabel m_labelTry;

	private UILabel m_labelVictory;

	private UILabel m_labelStageClear;

	private UILabel m_labelGetStamp;

	private UILabel m_labelClear;

	private UILabel m_labelPerfect;

	public ulong uid { get; set; }

	private UIReuseGrid grid
	{
		get
		{
			return null;
		}
	}

	private CaocaoTabButtons tabButton
	{
		get
		{
			return null;
		}
	}

	private GameObject objAll
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridScenario
	{
		get
		{
			return null;
		}
	}

	private UILabel labelTry
	{
		get
		{
			return null;
		}
	}

	private UILabel labelVictory
	{
		get
		{
			return null;
		}
	}

	private UILabel labelStageClear
	{
		get
		{
			return null;
		}
	}

	private UILabel labelGetStamp
	{
		get
		{
			return null;
		}
	}

	private UILabel labelClear
	{
		get
		{
			return null;
		}
	}

	private UILabel labelPerfect
	{
		get
		{
			return null;
		}
	}

	public void Init(Transform trs, ulong uid)
	{
	}

	public void SetActive(bool active)
	{
	}

	private void OnKeepButtonCallback(UserArenaInfoListItemUI.CellData cellData)
	{
	}

	public void UpdateUI(JsonData response)
	{
	}

	private void UpdateScenarioRecordUI(Transform item, int winCnt, int bestLevel, int bestStamp, ArenaScenario pdScenario)
	{
	}

	private void UpdateUIImpl()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	private void UpdateTabLabel()
	{
	}

	public void Destroy()
	{
	}
}
