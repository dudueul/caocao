using UnityEngine;

public class GuildPopupChangePosition : CaocaoUIModalPopup
{
	public UITexture texFace;

	public UILabelLocalized labelRank;

	public UILabelLocalized labelName;

	public Transform trsJobMarkRoot;

	public UIButton[] tabButtons;

	public GameObject[] permissionObjs;

	private string nationName;

	private ulong uid;

	private int rank;

	private EnumData.GuildJopType myJobType;

	private EnumData.GuildJopType targetJobType;

	private EnumData.GuildJopType jobType;

	private bool changing;

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public static void Show(GuildUIMemberListObject.GuildMemberCellData cellData)
	{
	}

	public void OnChangedPosition()
	{
	}

	private void UpdateJobPermission()
	{
	}

	public void OnChangeClicked()
	{
	}

	private void OnMemberUpdated()
	{
	}

	protected override void OnDestroy()
	{
	}
}
