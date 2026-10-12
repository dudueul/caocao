using UnityEngine;

public class UnitListItem : MonoBehaviour
{
	public int UnitID { get; private set; }

	public bool Selected { get; private set; }

	public void UpdateUI(string text, int unitId, bool isking = false)
	{
	}

	public void SelectUI(bool select)
	{
	}
}
