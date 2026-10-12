using Caocao;
using UnityEngine;

public class SkinItem : MonoBehaviour
{
	public int UnitSkinID { get; private set; }

	public int SkinItemID { get; private set; }

	public int UnitId { get; private set; }

	public bool Selected { get; private set; }

	public void UpdateUI(int unitId, int skinItemId, int unitSkinId, bool isDim)
	{
	}

	public void UpdateUI(UserUnit userUnit, bool isDim, bool applied)
	{
	}

	public void UpdateSkinAppliedIcon(bool applied)
	{
	}

	private void TurnClockwise(Transform target)
	{
	}

	private void TurnCounterClockwise(Transform target)
	{
	}

	private UnitDirection GetDirection(UnitDirection current, bool clockwise)
	{
		return UnitDirection.back;
	}

	public void SelectUI(bool select)
	{
	}
}
