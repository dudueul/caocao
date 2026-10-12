using UnityEngine;

public class UIBrawlSimpleChangeUnitListItem : MonoBehaviour
{
	[SerializeField]
	private UITexture face;

	[SerializeField]
	private UISlider hp;

	[SerializeField]
	private GameObject waiting;

	public void SetUnitInfo(EnumData.Brawl_Unit_State unitState, UserUnit userUnit)
	{
	}

	private void GaugeSetting(UserUnit unit)
	{
	}
}
