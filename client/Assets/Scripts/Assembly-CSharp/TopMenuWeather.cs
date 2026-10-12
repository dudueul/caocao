using UnityEngine;

public class TopMenuWeather : MonoBehaviour
{
	[SerializeField]
	private UISprite m_img;

	[SerializeField]
	private UILabelLocalized m_title;

	private Transform m_tooltipTf;

	private Transform m_descTf;

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private bool IsShowingTooltip()
	{
		return false;
	}

	private void UpdateUI()
	{
	}

	public void OnClickWeather()
	{
	}

	private void OnClickCloseTooltip()
	{
	}

	private void ShowTooltip(bool show)
	{
	}

	private void UpdateTooltip()
	{
	}
}
