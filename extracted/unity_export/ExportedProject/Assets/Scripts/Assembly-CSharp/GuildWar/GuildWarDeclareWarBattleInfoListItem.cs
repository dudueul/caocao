using System;
using UnityEngine;

namespace GuildWar
{
	public class GuildWarDeclareWarBattleInfoListItem : UIReuseScrollViewCell
	{
		public enum BattleInfoObjectType
		{
			None = 0,
			DeclareColumn = 1,
			Declare = 2,
			TownColumn = 3,
			Town = 4
		}

		public class BattleInfoCellData : IReuseCellData
		{
			public int townId;

			public string townName;

			public int townGrade;

			public BattleInfoObjectType objectType;

			public EGuildWarAttendStatus attendStatus;

			public EGuildWarTownUIStatus townStatus;

			public bool isDivideActive;

			public Action OnSelect;

			public int Index { get; set; }
		}

		[SerializeField]
		private UILabelLocalized DeclareColumn;

		[SerializeField]
		private UILabelLocalized Declare;

		[SerializeField]
		private UILabelLocalized TownColumn;

		[SerializeField]
		private UILabelLocalized Town;

		[SerializeField]
		private GameObject Divide;

		private BattleInfoCellData m_battleInfoCellData;

		public BattleInfoCellData ActiveFuncCellData
		{
			get
			{
				return null;
			}
		}

		public override void UpdateData(IReuseCellData cellData)
		{
		}

		private void SetUIData(Transform parent, string name, string size, string status, Color color, bool isDivideActive)
		{
		}

		public void OnClickSelect()
		{
		}
	}
}
