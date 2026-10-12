public static class MusicPlayerPlaybackStateHandler
{
	public enum PlaybackState
	{
		Interrupted = 0,
		Paused = 1,
		Playing = 2,
		SeekingBackward = 3,
		SeekingForward = 4,
		Stopped = 5
	}

	private static string objectName;

	private static MusicPlaybackStateGameObject eventObj;

	private static IMusicPlaybackStateHandler _plugin;

	public static void InitPlaybackStateHandler()
	{
	}

	public static bool IsPlaying()
	{
		return false;
	}

	public static void UpdateBGMMuteByMusicPlaybakcState()
	{
	}

	private static void OnPlaybackStateChanged(PlaybackState state)
	{
	}
}
