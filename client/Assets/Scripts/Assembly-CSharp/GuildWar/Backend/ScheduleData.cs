using System;

namespace GuildWar.Backend
{
	public abstract class ScheduleData : ITransmittable
	{
		public abstract int season_detail_key { get; }

		public abstract bool is_created { get; }

		public abstract DateTime season_end { get; }

		public abstract DateTime attend_start { get; }

		public abstract DateTime attend_end { get; }

		public abstract DateTime play_start { get; }

		public abstract DateTime play_end { get; }
	}
}
