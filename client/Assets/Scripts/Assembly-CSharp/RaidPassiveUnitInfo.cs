using System.Collections.Generic;
using UnityEngine;

public class RaidPassiveUnitInfo : MonoBehaviour
{
	[SerializeField]
	private UITexture m_texFace;

	[SerializeField]
	private List<GameObject> m_gradeObjs;

	[SerializeField]
	private UILabelLocalized m_name;

	[SerializeField]
	private UILabelLocalized m_level;

	public void UpdateUI(Raid pdRaid)
	{
	}

	private void UpdateGradeUI(int unitGrade)
	{
	}
}
