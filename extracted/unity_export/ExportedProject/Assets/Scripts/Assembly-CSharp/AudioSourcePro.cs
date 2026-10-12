using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;
using antilunchbox;

[Serializable]
[ExecuteInEditMode]
public class AudioSourcePro : MonoBehaviour, IWeakResourceRef
{
	public AudioSource audioSource;

	public ClipType clipType;

	public string clipName;

	public string groupName;

	public SFXType sfxType;

	public float delay;

	private bool isBound;

	private bool isVisible;

	public bool OnStartActivated;

	public bool OnVisibleActivated;

	public bool OnInvisibleActivated;

	public bool OnCollisionEnterActivated;

	public bool OnCollisionExitActivated;

	public bool OnTriggerEnterActivated;

	public bool OnTriggerExitActivated;

	public bool OnMouseEnterActivated;

	public bool OnMouseClickActivated;

	public bool OnEnableActivated;

	public bool OnDisableActivated;

	public bool OnCollision2dEnterActivated;

	public bool OnCollision2dExitActivated;

	public bool OnTriggerEnter2dActivated;

	public bool OnTriggerExit2dActivated;

	public bool OnParticleCollisionActivated;

	public bool ShowEditor3D;

	public bool ShowEditor2D;

	public bool ShowEventTriggers;

	public int numSubscriptions;

	public List<AudioSubscription> audioSubscriptions;

	[SerializeField]
	public string mAudioClipPath;

	public bool componentsAreValid
	{
		get
		{
			return false;
		}
	}

	public bool audioIsValid
	{
		get
		{
			return false;
		}
	}

	public bool bypassEffects
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public AudioClip clip
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public float dopplerLevel
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool ignoreListenerPause
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool ignoreListenerVolume
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	public bool loop
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public float maxDistance
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float minDistance
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool mute
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public float pan
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float panLevel
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float pitch
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool playOnAwake
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public int priority
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public AudioRolloffMode rolloffMode
	{
		get
		{
			return AudioRolloffMode.Logarithmic;
		}
		set
		{
		}
	}

	public float spread
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float time
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public int timeSamples
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public AudioVelocityUpdateMode velocityUpdateMode
	{
		get
		{
			return AudioVelocityUpdateMode.Auto;
		}
		set
		{
		}
	}

	public float volume
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	public void Bind()
	{
	}

	public void Unbind()
	{
	}

	public void BindStandardEvent(AudioSourceStandardEvent evt, bool activated)
	{
	}

	private void PlaySoundInternal(AudioSourceStandardEvent evt)
	{
	}

	private AudioSubscription FindSubscriptionForEvent(AudioSourceStandardEvent evt)
	{
		return null;
	}

	private void PlayHandler()
	{
	}

	private void PlayCappedHandler(string cappedID)
	{
	}

	private void PlayLoopHandler()
	{
	}

	private void StopHandler()
	{
	}

	private void Start()
	{
	}

	private void OnBecameVisible()
	{
	}

	private void OnBecameInvisible()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnTriggerEnter2D(Collider2D other)
	{
	}

	private void OnTriggerExit2D(Collider2D other)
	{
	}

	private void OnCollisionEnter2D(Collision2D collision)
	{
	}

	private void OnCollisionExit2D(Collision2D collision)
	{
	}

	private void OnCollisionEnter(Collision collision)
	{
	}

	private void OnCollisionExit(Collision collision)
	{
	}

	private void OnTriggerEnter(Collider other)
	{
	}

	private void OnTriggerExit(Collider other)
	{
	}

	private void OnParticleCollision(GameObject other)
	{
	}

	private void OnMouseEnter()
	{
	}

	private void OnMouseDown()
	{
	}

	public void GetOutputData(float[] samples, int channel)
	{
	}

	public void GetSpectrumData(float[] samples, int channel, FFTWindow window)
	{
	}

	public void Pause()
	{
	}

	public void Play(ulong delay = 0uL)
	{
	}

	public void PlayDelayed(float delay)
	{
	}

	[DebuggerHidden]
	private IEnumerator PlayDelayedHelper(float delay, string nameValue)
	{
		return null;
	}

	public void PlayOneShot(AudioClip clip, float volumeScale)
	{
	}

	public void PlayScheduled(double time)
	{
	}

	public void SetScheduledEndTime(double time)
	{
	}

	public void SetScheduledStartTime(double time)
	{
	}

	public void Stop()
	{
	}

	public static void PlayClipAtPoint(AudioClip clip, Vector3 position, float volume = 1f)
	{
	}

	public void DisconnectResourceRef(List<string> adjustedPaths)
	{
	}

	void IWeakResourceRef.ConnectResourceRef()
	{
	}
}
