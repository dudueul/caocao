using System;
using System.Collections.Generic;
using UnityEngine;

public class CaocaoRadioButton : MonoBehaviour
{
	private List<CaocaoRadioButton> m_SiblingButtons;

	public Action OnClickCallback;

	private string selectedUp;

	private string selectedPress;

	private string normalUp;

	private string normalPress;

	private Vector3 normalPos;

	private Vector3 pressedPos;

	private int normalHeight;

	private int pressedHeight;

	private bool pressedApplyGradient;

	private bool normalApplyGradient;

	private Color pressedTopColor;

	private Color normalTopColor;

	private Color pressedBottomColor;

	private Color normalBottomColor;

	private Color pressedColorTint;

	private Color normalColorTint;

	private UIButton m_button;

	private UISprite m_sprite;

	private UILabelLocalized m_label;

	private bool m_init;

	public UIButton button
	{
		get
		{
			return null;
		}
	}

	public UISprite sprite
	{
		get
		{
			return null;
		}
	}

	public UILabelLocalized label
	{
		get
		{
			return null;
		}
	}

	private void Awake()
	{
	}

	private void Init()
	{
	}

	private void OnDestroy()
	{
	}

	private void CollectSiblings()
	{
	}

	public void SetSpriteName(string pressed, string normal)
	{
	}

	public void SetSpriteName(string selectedUp, string selectedPress, string normalUp, string normalPress)
	{
	}

	public void SetPosition(Vector3 pressed, Vector3 normal)
	{
	}

	public void SetPositionX(float pressed, float normal)
	{
	}

	public void SetPositionY(float pressed, float normal)
	{
	}

	public void SetHeight(int pressed, int normal)
	{
	}

	public void SetColor(UILabelLocalized activeLabel, UILabelLocalized inactiveLabel)
	{
	}

	private void SetPressed()
	{
	}

	private void SetNormal()
	{
	}

	private void OnClick()
	{
	}

	public void SetClick()
	{
	}
}
