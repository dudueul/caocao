namespace GuildWar.Backend
{
	public abstract class Session
	{
		public bool trySocketLogin;

		public abstract ConnectionInfo connectionInfo { get; }

		public abstract bool socketConnected { get; }

		public abstract bool socketLogined { get; }

		public abstract string authKey { get; }

		public abstract int userCnt { get; }

		public abstract bool maintenance { get; }
	}
}
