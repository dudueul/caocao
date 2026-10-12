using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

namespace GuildWar
{
	public class GuildWarTownColor : ITransmittable
	{
		private ObscuredInt m_index;

		private ObscuredULong m_owner_guild_id;

		private Color m_color;

		private ObscuredString m_name;

		private ObscuredInt m_emblemIndex;

		private ObscuredInt m_rank;

		public int index
		{
			get
			{
				return 0;
			}
		}

		public ulong ownerGuildId
		{
			get
			{
				return 0uL;
			}
		}

		public Color color
		{
			get
			{
				return default(Color);
			}
		}

		public string name
		{
			get
			{
				return null;
			}
		}

		public int emblemIndex
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

		public GuildWarTownColor()
		{
		}

		public GuildWarTownColor(int index, ulong OwnerGuildId, Color color, string name, int emblemIndex, int rank)
		{
		}

		public void SetData(ulong OwnerGuildId, Color color, string name, int emblemIndex, int rank)
		{
		}

		public void SetGuildNameAndEmblem()
		{
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}
	}
}
