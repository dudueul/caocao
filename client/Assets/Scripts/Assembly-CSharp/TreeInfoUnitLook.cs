using UnityEngine;

public class TreeInfoUnitLook : MonoBehaviour
{
	private UserUnit m_userUnit;

	public UILabelLocalized m_CostUI;

	public GameObject m_StarObj;

	public PrefabInstance m_ArenaSynergyType;

	private bool m_showHPMP;

	private GameObject m_unitSprite;

	private Vector3 m_position;

	private Vector3 m_scale;

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	public void UpdateUnitLook(UserUnit userUnit, Vector3 pos, Vector3 scale, bool showHPMP = false)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUnitSprite(UserUnit userUnit, Vector3 pos, Vector3 scale)
	{
	}

	private void SetUnitJobLevStar(UserUnit userUnit)
	{
	}
}
