namespace TSI
{
	public interface ITSIPlatform
	{
		void Call(TSIRequestType type, params object[] args);
	}
}
