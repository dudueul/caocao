namespace RockVR.Common
{
	public class EventDelegate
	{
		public delegate void ErrorDelegate(int error);

		public delegate void CompleteDelegate();

		public ErrorDelegate OnError;

		public CompleteDelegate OnComplete;
	}
}
