using UnityEngine;

public class PlaySfxOnStart : MonoBehaviour
{
	[SfxWeakRef]
	public string sfxAudioClip;

	public float delay;

	public bool loop;

	private void Start()
	{
	}
}
