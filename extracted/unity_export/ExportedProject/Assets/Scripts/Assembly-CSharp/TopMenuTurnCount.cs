using Caocao;
using UnityEngine;

public class TopMenuTurnCount : SelfRegister2UIComponentsMonobehaviour
{
	public UILabelLocalized m_label;

	public UISlider m_slider;

	public UITexture m_sliderThumb;

	public UISprite m_sliderGauge;

	public Color m_allyColor;

	public Color m_enemyColor;

	public void SetTurnCount(int turnCount, int totalTurnCount)
	{
	}

	public void SetActionCount(UnitSide side, int actionCount, int totalActionCount)
	{
	}
}
