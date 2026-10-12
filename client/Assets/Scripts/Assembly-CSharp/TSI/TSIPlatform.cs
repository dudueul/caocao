namespace TSI
{
	public abstract class TSIPlatform : ITSIPlatform
	{
		public abstract void Call(TSIRequestType type, params object[] args);

		protected abstract void callMethod(string method, params object[] args);
	}
}
