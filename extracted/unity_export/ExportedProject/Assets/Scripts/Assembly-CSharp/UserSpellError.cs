public class UserSpellError
{
	public enum Type
	{
		ERR_SELECT_TARGET = 0,
		ERR_SHORT_OF_CASH = 1,
		ERR_SELECT_QUEUE = 2
	}

	public static void ShowError(Type errType)
	{
	}

	private static string GetErrMessage(Type errType)
	{
		return null;
	}
}
