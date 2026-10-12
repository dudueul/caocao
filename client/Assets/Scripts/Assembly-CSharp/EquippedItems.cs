using UnityEngine;

public class EquippedItems : MonoBehaviour
{
	[HideInInspector]
	public ItemUI weapon;

	[HideInInspector]
	public ItemUI armor;

	[HideInInspector]
	public ItemUI kit;

	private void OnEnable()
	{
	}

	private void OnDestroy()
	{
	}

	public bool Contain(ItemUI item)
	{
		return false;
	}

	public void SetItems(UserItem weaponItem, UserItem armorItem, UserItem kitItem)
	{
	}

	public void SetUserUnit(UserUnit unit)
	{
	}
}
