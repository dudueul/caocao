using UnityEngine;

public class RunPassiveListListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public RuneSet data { get; set; }

		public UserRuneSlot runeSlot { get; set; }
	}

	private CellData m_cellData;

	[SerializeField]
	private RuneUI[] m_runeUIs;

	[SerializeField]
	private PrefabInstance m_passiveUIInst;

	[SerializeField]
	private UILabelLocalized m_passiveName;

	[SerializeField]
	private UILabelLocalized m_passiveDesc;

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnClickRuneUI(RuneUI runeUI)
	{
	}
}
