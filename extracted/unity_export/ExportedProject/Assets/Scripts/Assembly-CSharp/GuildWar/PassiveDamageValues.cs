using System.Collections.Generic;

namespace GuildWar
{
	public class PassiveDamageValues
	{
		public bool values_valid;

		public int values_valid_seq;

		public Dictionary<int, PassiveDamageValue> values;

		public long max_damage_valid_date;

		public int total_damages;
	}
}
