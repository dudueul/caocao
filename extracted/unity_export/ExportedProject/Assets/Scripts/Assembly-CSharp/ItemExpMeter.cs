using UnityEngine;

public class ItemExpMeter : MonoBehaviour
{
	public PrefabInstance m_weaponExpInst;

	public PrefabInstance m_armorExpInst;

	private UIStatProgressBar m_progressWeaponExp;

	private UIStatProgressBar m_progressArmorExp;

	public UIStatProgressBar progressWeaponExp
	{
		get
		{
			return null;
		}
	}

	public UIStatProgressBar progressArmorExp
	{
		get
		{
			return null;
		}
	}

	private void OnEnable()
	{
	}

	private void OnDestroy()
	{
	}

	public void SetUserUnit(UserUnit userUnit)
	{
	}

	public void SetWeapon(UserItem weapon)
	{
	}

	public void SetArmor(UserItem armor)
	{
	}
}
