using LitJson;
using UnityEngine;

namespace Worldboss
{
	public class WorldBossHistoryListItem : UIWorldBossStateBase
	{
		public UIButton btnRanking;

		public UILabelLocalized appearTime;

		public GameObject clearMarkNormal;

		public GameObject clearMarkSpecial;

		public override void UpdateData(IReuseCellData baseCellData)
		{
		}

		protected override bool CheckEarlyClearSuccess()
		{
			return false;
		}

		public void OnClickRewardReceive()
		{
		}

		private void OnResponseGetReward(JsonData response)
		{
		}
	}
}
