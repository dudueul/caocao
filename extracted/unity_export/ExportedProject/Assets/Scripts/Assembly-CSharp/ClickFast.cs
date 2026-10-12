using UnityEngine;

public class ClickFast : CaocaoUIButton
{
	private Color m_originalColor;

	private Color m_activeColor;

	private GameObject m_iconNormal;

	private GameObject m_iconSpeed;

	public UISprite m_btnColorSprite;

	private int inputCount;

	public GameObject iconNormal
	{
		get
		{
			return null;
		}
	}

	public GameObject iconSpeed
	{
		get
		{
			return null;
		}
	}

	protected override void UpdateButtonState()
	{
	}

	protected override void OnClickImpl()
	{
	}

	private void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void ChangeSpeedOnCFStatusChanged(ControlFlow.statusType type)
	{
	}

	private void ChangeSpeed()
	{
	}

	public void UpdateButton()
	{
	}
}
