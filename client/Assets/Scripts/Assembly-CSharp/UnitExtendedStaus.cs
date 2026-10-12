using System.Text;
using UnityEngine;

public class UnitExtendedStaus : MonoBehaviour
{
	public UILabelLocalized m_buffValue;

	public UILabelLocalized m_totalValue;

	public UISprite m_totalBar;

	public UISprite m_buffBar;

	private StringBuilder m_sb;

	private Color barColorGreen;

	private Color barColorBlue;

	private Color barColorRed;

	private Color textColorBlue;

	private Color textColorRed;

	public void UpdateUI(int value, int max, int buff)
	{
	}
}
