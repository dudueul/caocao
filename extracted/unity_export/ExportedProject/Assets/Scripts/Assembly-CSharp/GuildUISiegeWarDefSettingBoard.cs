using UnityEngine;

public class GuildUISiegeWarDefSettingBoard : MonoBehaviour
{
	public UILabelLocalized label1Name;

	public UILabelLocalized label2Name;

	public UILabelLocalized label3Name;

	public UILabelLocalized label1;

	public UILabelLocalized label2;

	public UILabelLocalized label3;

	public UILabelLocalized label4;

	public UILabelLocalized label5;

	public UILabelLocalized labelUnitNum;

	public UILabelLocalized labelFacilityNum;

	public UILabelLocalized labelBarracksNum;

	public UILabelLocalized labelObstacleNum;

	private ReconController m_reconController;

	public ReconController reconController
	{
		get
		{
			return null;
		}
	}

	public void UpdateLabels()
	{
	}

	private void UpdateLeftLabels()
	{
	}

	public void UpdateRightLabels()
	{
	}

	public void Deactive()
	{
	}

	private void OnDestroy()
	{
	}
}
