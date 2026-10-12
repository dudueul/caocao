namespace TSI
{
	public class TSIBridge : Singleton<TSIBridge>
	{
		public static readonly int APPID;

		private TSIEvent m_event;

		private ITSIPlatform m_platform;

		protected override void Awake()
		{
		}

		public void Call(TSIRequestType type, ITSIListenerType listener, params object[] args)
		{
		}

		public void Call(TSIRequestType type, params object[] args)
		{
		}

		public void ShowQuit()
		{
		}
	}
}
