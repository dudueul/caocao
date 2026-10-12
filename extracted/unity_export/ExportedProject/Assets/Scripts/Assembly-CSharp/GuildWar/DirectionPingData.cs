namespace GuildWar
{
	public abstract class DirectionPingData : IDataTableOwner, IDataType
	{
		public abstract ulong guild_id { get; }

		public abstract ulong user_id { get; }

		public abstract int ping_id { get; }

		public abstract short pos_x { get; }

		public abstract short pos_y { get; }

		public abstract DataTables GetDataTables();
	}
}
