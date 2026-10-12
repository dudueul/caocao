namespace GuildWar.Backend
{
	public abstract class WorldMetaData : ITransmittable
	{
		public abstract int season_detail_key { get; }

		public abstract int clan_db_shard { get; }

		public abstract int world_id { get; }

		public abstract int instance_index { get; }

		public abstract WorldStatus status { get; }

		public abstract string map { get; }

		public abstract string socket_server_uuid { get; }

		public abstract string logic_server_uuid { get; }

		public abstract string distributor_server_uuid { get; }

		public abstract string socket_server_ip { get; }

		public abstract string logic_server_ip { get; }

		public abstract string distributor_server_ip { get; }

		public abstract int socket_server_port { get; }

		public abstract int logic_server_port { get; }

		public abstract int distributor_server_port { get; }
	}
}
