using System;

public class GuildWarCorpsCombatActiveFunc : UIReuseScrollViewCell
{
	public class CorpsCombatActionFuncCellData : IReuseCellData
	{
		public int guildwarActiveFunctionId;

		public bool selected;

		public Action OnSelect;

		public int Index { get; set; }
	}

	private CorpsCombatActionFuncCellData m_activeFuncCellData;

	public CorpsCombatActionFuncCellData ActiveFuncCellData
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void Select()
	{
	}
}
