using LitJson;

namespace Worldboss
{
	public class WorldBossRankingListItem : UIReuseScrollViewCell
	{
		public class CellData : ITransmittable, IReuseCellData
		{
			public int Index { get; set; }

			public string name { get; set; }

			public ulong userId { get; set; }

			public int rank { get; set; }

			public ulong rankPoint { get; set; }

			public ulong totalRankPoint { get; set; }

			public int faceUnitId { get; set; }

			public int skinItemId { get; set; }

			protected override void DeserializeImpl(JsonData jsonData)
			{
			}
		}

		public UILabelLocalized rank;

		public UILabelLocalized nickname;

		public GuildUIMarkName guild;

		public UILabelLocalized rankPoint;

		public CellData cellData
		{
			get
			{
				return null;
			}
		}

		public override void UpdateData(IReuseCellData rankCellData)
		{
		}
	}
}
