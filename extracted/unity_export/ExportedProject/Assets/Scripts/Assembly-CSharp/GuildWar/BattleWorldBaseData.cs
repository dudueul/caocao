using UnityEngine;

namespace GuildWar
{
	public class BattleWorldBaseData : MonoBehaviour
	{
		public class GuildColorBaseData
		{
			public int index;

			public ulong guildId;

			public Color color;

			public GuildColorBaseData()
			{
			}

			public GuildColorBaseData(int index, ulong guildId, Color color)
			{
			}
		}

		public Transform parent;

		public int townId;

		public int battleWorldId;

		public GuildColorBaseData GuildColorData;

		public void OnClickSelectTown()
		{
		}

		public void OnClickChangeBattleWorld()
		{
		}

		public void OnClickColorFocusShow()
		{
		}
	}
}
