using System;
using UnityEngine;

public class MusicPlaybackStateGameObject : MonoBehaviour
{
	public Action<MusicPlayerPlaybackStateHandler.PlaybackState> onPlaybackStateChanged;

	public void PlaybackStateChanged(string state)
	{
	}
}
