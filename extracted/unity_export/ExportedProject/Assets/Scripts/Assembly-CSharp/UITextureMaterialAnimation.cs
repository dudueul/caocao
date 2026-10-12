using UnityEngine;

[ExecuteInEditMode]
public class UITextureMaterialAnimation : MonoBehaviour
{
	public int m_FPS;

	public bool m_Loop;

	public Texture[] m_frames;

	public int mIndex;

	protected float mDelta;

	protected bool mActive;

	private Material m_mat;

	private UITexture m_uiTexture;

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

	public bool isPlaying
	{
		get
		{
			return false;
		}
	}

	protected virtual void Start()
	{
	}

	protected virtual void OnDestroy()
	{
	}

	protected virtual void Update()
	{
	}

	public void Init()
	{
	}

	public void Play()
	{
	}

	public void Pause()
	{
	}

	public void Reset()
	{
	}

	private void UpdateFrame(int index)
	{
	}
}
