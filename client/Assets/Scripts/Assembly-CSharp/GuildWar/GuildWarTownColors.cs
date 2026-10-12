using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

namespace GuildWar
{
	public class GuildWarTownColors : ITransmittable
	{
		private Dictionary<int, List<GuildWarTownColor>> m_key2GuildWarTownColor;

		private int m_selectedGuildWarWorld;

		private bool isModify;

		public int SelectedGuildWarWorld
		{
			get
			{
				return 0;
			}
		}

		// C# has no syntax for parameterized property 'Item'.
		public List<GuildWarTownColor> get_Item(int index)
		{
			return null;
		}

		public Action deserializeCallback { get; set; }

		public void RefreshGuildColor()
		{
		}

		public void UpdateGuildWarColor(int GuildWarWorld)
		{
		}

		public Color GetTownColorWithGuildId(ulong guildId)
		{
			return default(Color);
		}

		public string GetTownColorPlayerPrefsKey(ulong userId)
		{
			return null;
		}

		public void RemoveTownColorWithGuildId(ulong guildId)
		{
		}

		public void SetTownColorWithGuildId(ulong guildId, int index, Color color, string guildName, int emblemIndex, int rank)
		{
		}

		public void SaveTownColor()
		{
		}

		public GuildWarTownColor GetTownsWithGuildId(ulong guildId)
		{
			return null;
		}

		public List<ulong> GetGuildIds()
		{
			return null;
		}

		public List<ulong> GetGuildIds(int index)
		{
			return null;
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		public void Clear()
		{
		}
	}
}
