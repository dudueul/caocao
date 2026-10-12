using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class tk2dButton : MonoBehaviour
{
	public delegate void ButtonHandlerDelegate(tk2dButton source);

	public Camera viewCamera;

	public string buttonDownSprite;

	public string buttonUpSprite;

	public string buttonPressedSprite;

	private int buttonDownSpriteId;

	private int buttonUpSpriteId;

	private int buttonPressedSpriteId;

	public AudioClip buttonDownSound;

	public AudioClip buttonUpSound;

	public AudioClip buttonPressedSound;

	public GameObject targetObject;

	public string messageName;

	private ButtonHandlerDelegate ButtonPressedEvent__BackingField;

	private ButtonHandlerDelegate ButtonAutoFireEvent__BackingField;

	private ButtonHandlerDelegate ButtonDownEvent__BackingField;

	private ButtonHandlerDelegate ButtonUpEvent__BackingField;

	private tk2dBaseSprite sprite;

	private bool buttonDown;

	public float targetScale;

	public float scaleTime;

	public float pressedWaitTime;

	public event ButtonHandlerDelegate ButtonPressedEvent
	{
		add
		{
		}
		remove
		{
		}
	}

	public event ButtonHandlerDelegate ButtonAutoFireEvent
	{
		add
		{
		}
		remove
		{
		}
	}

	public event ButtonHandlerDelegate ButtonDownEvent
	{
		add
		{
		}
		remove
		{
		}
	}

	public event ButtonHandlerDelegate ButtonUpEvent
	{
		add
		{
		}
		remove
		{
		}
	}

	private void OnEnable()
	{
	}

	private void Start()
	{
	}

	public void UpdateSpriteIds()
	{
	}

	private void PlaySound(AudioClip source)
	{
	}

	[DebuggerHidden]
	private IEnumerator coScale(Vector3 defaultScale, float startScale, float endScale)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator LocalWaitForSeconds(float seconds)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator coHandleButtonPress(int fingerId)
	{
		return null;
	}

	private void Update()
	{
	}
}
