using System.Xml;
using UnityEngine;

[ExecuteInEditMode]
public class UnitEditData : UnitDataForRenderer
{
	private static bool m_drawOrder;

	public string m_name;

	public int m_jobGroup;

	public int m_exp;

	public int m_gridX;

	public int m_gridY;

	public int m_group;

	public int m_aiType;

	public int m_levelCorrect;

	public int m_aiPriorityCorrect;

	public GameObject m_aiTarget;

	public bool m_isLeader;

	public int m_dir;

	public int m_appearance;

	public int m_slotOrder;

	public int m_slotType;

	public int m_slotTeamFaction;

	public bool drawOrder
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public void Copy(UnitEditData input)
	{
	}

	public XmlElement ToXML(XmlDocument xmlDoc)
	{
		return null;
	}

	public void ForceMaterialUpdate()
	{
	}

	private void OnDrawGizmos()
	{
	}
}
