using UnityEngine;

public class UIImageButton : MonoBehaviour
{
	public UISprite target;

	public string normalSprite;

	public string hoverSprite;

	public string pressedSprite;

	public string disabledSprite;

	public bool pixelSnap;

	public bool isEnabled
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private void OnEnable()
	{
	}

	private void OnValidate()
	{
	}

	private void UpdateImage()
	{
	}

	private void OnHover(bool isOver)
	{
	}

	private void OnPress(bool pressed)
	{
	}

	private void SetSprite(string sprite)
	{
	}
}
