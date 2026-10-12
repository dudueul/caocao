namespace GuildWar.Backend
{
	public abstract class ConnectionInfo
	{
		public abstract string socketServerUuid { get; }

		public abstract string socketServerIP { get; }

		public abstract int socketServerPort { get; }

		public abstract string logicServerUuid { get; }

		public abstract string logicServerIP { get; }

		public abstract int logicServerPort { get; }

		public abstract string distributorServerUuid { get; }

		public abstract string distributorServerIP { get; }

		public abstract int distributorServerPort { get; }

		public abstract ulong userId { get; }

		public string socketServerUri
		{
			get
			{
				return null;
			}
		}

		public string logicServerUri
		{
			get
			{
				return null;
			}
		}

		public string distributorServerUri
		{
			get
			{
				return null;
			}
		}
	}
}
