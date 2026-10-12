using UnityEngine;

public class UI2DSpriteAnimation : MonoBehaviour
{
	[SerializeField]
	protected int framerate;

	public bool ignoreTimeScale;

	public bool loop;

	public Sprite[] frames;

	private SpriteRenderer mUnitySprite;

	private UI2DSprite mNguiSprite;

	private int mIndex;

	private float mUpdate;

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	public int framesPerSecond
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public void Play()
	{
	}

	public void Pause()
	{
	}

	public void ResetToBeginning()
	{
	}

	private void Start()
	{
	}

	private void Update()
	{
	}

	private void UpdateSprite()
	{
	}
}
