using UnityEngine;

public class MoviePlayer : MonoBehaviour
{
	public static string movieName;

	private bool m_runCalled;

	private bool m_endCalled;

	private void Update()
	{
	}

	private void Run()
	{
	}

	private void OnVideoError(MediaPlayerCtrl.MEDIAPLAYER_ERROR errorcode, MediaPlayerCtrl.MEDIAPLAYER_ERROR errorcodeextra)
	{
	}

	private void OnMovieEnd()
	{
	}
}
