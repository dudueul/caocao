using UnityEngine;

public class StageSelectDetail : MonoBehaviour
{
	public Transform m_NormalModeTr;

	public Transform m_HardcoreModeTr;

	public Transform m_ClassicModeTr;

	public GameObject m_curGrade;

	public UI2DSprite m_curMapImg;

	private UILabelLocalized m_detailScoreUI;

	private UILabelLocalized m_detailLevelUI;

	private UILabelLocalized m_detailTimeUI;

	private Transform m_grid;

	private EnumData.ScenarioModeLevel m_scenarioModeLevel;

	private void OnDestroy()
	{
	}

	public void UpdateUI(int scenarioId)
	{
	}

	private void SetUIReference()
	{
	}

	private void UpdateVictoryGrade(int scenarioId)
	{
	}
}
