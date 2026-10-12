namespace TSI
{
	public interface ITSIListener : ITSIListenerType
	{
		void OnResult(TSIResult result);
	}
}
