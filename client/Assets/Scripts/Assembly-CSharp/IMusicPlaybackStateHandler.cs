public interface IMusicPlaybackStateHandler
{
	string TargetPlatform();

	bool IsPlaying();

	void GetCurrentPlaybackState();
}
