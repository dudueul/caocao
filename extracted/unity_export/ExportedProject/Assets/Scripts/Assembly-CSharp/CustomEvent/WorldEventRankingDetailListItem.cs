using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

namespace CustomEvent
{
	public class WorldEventRankingDetailListItem : MonoBehaviour
	{
		public class RankingData
		{
			private ObscuredInt m_rank;

			private ObscuredULong m_id;

			private ObscuredULong m_point;

			private ObscuredString m_nickname;

			public int rankBaseZero
			{
				get
				{
					return 0;
				}
			}

			public int rank
			{
				get
				{
					return 0;
				}
			}

			public ulong userId
			{
				get
				{
					return 0uL;
				}
			}

			public ulong point
			{
				get
				{
					return 0uL;
				}
			}

			public string nickname
			{
				get
				{
					return null;
				}
			}

			public RankingData(JsonData jsonData)
			{
			}

			public void Deserialize(JsonData jsonData)
			{
			}
		}

		[SerializeField]
		private UILabelLocalized ranking;

		[SerializeField]
		private UILabelLocalized nickname;

		[SerializeField]
		private UILabelLocalized point;

		public int Index { get; private set; }

		public void UpdateRankData(int index, RankingData rankingData, float rankPercent = -1f, bool isMe = false)
		{
		}
	}
}
