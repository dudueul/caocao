using System.Collections.Generic;
using UnityEngine;

public class BoardInfo : MonoBehaviour
{
	private int m_dataId;

	private int m_dataId2;

	[SerializeField]
	private UITexture m_faceTex;

	[SerializeField]
	private UILabelLocalized m_desc;

	[SerializeField]
	private GameObject m_instItemObj;

	private ItemUI m_itemUI;

	[SerializeField]
	private PrefabInstance[] m_passivePrefabInstances;

	private List<PassiveUI> m_passiveUIs;

	public void InitInfo(EnumData.BoardType type, int dataId, int dataId2 = 0)
	{
	}

	private void UnitInfo(int unitId, int skinItemId = 0)
	{
	}

	private void TreasureInfo(int treasureId)
	{
	}

	private void OnClickPassive(SpecialStateUI specialStateUI)
	{
	}
}
