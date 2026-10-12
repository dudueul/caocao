public static class VoiceChatNoti
{
	public enum Type
	{
		Maintenance = 0,
		FailConnection = 1,
		Disconnect = 2,
		DenyMicrophonePermission = 3,
		AlreadyJoinRoom = 4,
		WatcherEnteredRoom = 5
	}

	public static void ShowPopup(this Type setError)
	{
	}
}
