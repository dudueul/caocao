using System;
using System.Collections.Generic;
using CustomEvent;
using LitJson;
using UnityEngine;

public class PanelCustomEvents : CaocaoUIModalPopup
{
	[SerializeField]
	private NormalEvent normal;

	[SerializeField]
	private WorldEvent world;

	[SerializeField]
	private BingoEvent bingo;

	public CustomEventIngames eventIngames;

	public CustomEventUnits eventUnits;

	public CustomEventUsers eventUsers;

	public CustomEventRewards eventRewards;

	public CustomEventRewardLogs eventRewardLogs;

	public BingoEventUsers bingoUsers;

	private Action<ulong> onSelectTab__BackingField;

	private Action<HashSet<ulong>> onNewTag__BackingField;

	private Action<HashSet<ulong>> onClearTab__BackingField;

	private Action onIconClose__BackingField;

	private UIGrid m_gridEvent;

	private int m_worldRecordValue;

	private CustomEventIngame m_selectOld;

	private UIGrid gridEvent
	{
		get
		{
			return null;
		}
	}

	private event Action<ulong> onSelectTab
	{
		add
		{
		}
		remove
		{
		}
	}

	private event Action<HashSet<ulong>> onNewTag
	{
		add
		{
		}
		remove
		{
		}
	}

	private event Action<HashSet<ulong>> onClearTab
	{
		add
		{
		}
		remove
		{
		}
	}

	private event Action onIconClose
	{
		add
		{
		}
		remove
		{
		}
	}

	public static void Show(JsonData response, Action callback)
	{
	}

	public void UpdateUI()
	{
	}

	public void UpdateUI(int worldRecordValue)
	{
	}

	private void UpdateSubPanel(CustomEventIngame eventIngame)
	{
	}

	private void ClearGrid(UIGrid grid, UIScrollView scroll)
	{
	}

	private void ShowFinishEventMsg()
	{
	}

	public void DeserializeEventData(JsonData response)
	{
	}

	public void DownLoadImageFromWeb()
	{
	}

	public void LoadOrDownloadImage(CustomEventIngame eventIngame, Action<Texture> callback)
	{
	}

	public void OnShow(Action callback)
	{
	}

	private void OnClickEventIngameTab(CustomEventIngame ingame, bool forceUpdate = false)
	{
	}

	public void CheckEventState(CustomEventIngame eventIngame, out bool newTag, out bool clearTag)
	{
		newTag = default(bool);
		clearTag = default(bool);
	}

	private void CheckNormalEventState(CustomEventIngame eventIngame, out bool newTag, out bool clearTag)
	{
		newTag = default(bool);
		clearTag = default(bool);
	}

	private void CheckWorldEventState(CustomEventIngame eventIngame, out bool newTag, out bool clearTag)
	{
		newTag = default(bool);
		clearTag = default(bool);
	}

	private void CheckBingoEventState(CustomEventIngame eventIngame, out bool newTag, out bool clearTag)
	{
		newTag = default(bool);
		clearTag = default(bool);
	}

	private void InitWorldRecordValue()
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
