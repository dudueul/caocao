using System.Collections.Generic;
using Caocao;
using UnityEngine;

public class ConditionMarkerController : MonoBehaviour
{
	[SerializeField]
	private GameObject m_buffAll1stObj;

	[SerializeField]
	private GameObject m_deBuffAll1stObj;

	[SerializeField]
	private GameObject[] m_buff1stObjs;

	[SerializeField]
	private GameObject[] m_deBuff1stObjs;

	[SerializeField]
	private GameObject m_buffAll2ndObj;

	[SerializeField]
	private GameObject m_deBuffAll2ndObj;

	[SerializeField]
	private GameObject[] m_buff2ndObjs;

	[SerializeField]
	private GameObject[] m_deBuff2ndObjs;

	private bool m_init;

	private UnitAnimation unitAnimation;

	private static Vector3 ms_mirroredByXFactor;

	private Vector3 m_mirroredByXScale;

	private Vector3 m_originalScale;

	[SerializeField]
	private Transform m_buffArrow;

	[SerializeField]
	private Transform m_debuffArrow;

	[SerializeField]
	private Transform[] m_buffPos;

	[SerializeField]
	private Transform[] m_debuffPos;

	[SerializeField]
	private Transform m_buffPosCenter;

	[SerializeField]
	private Transform m_debuffPosCenter;

	private List<GameObject> m_buffs;

	private List<GameObject> m_debuffs;

	private Dictionary<string, GameObject> m_pool;

	private int m_buffIndex;

	private int m_debuffIndex;

	private void Start()
	{
	}

	private void Init()
	{
	}

	private void OnUnitDirectionChanged(UnitAnimationBase animBase, UnitDirection unitDirection)
	{
	}

	public void SetConditionMarker(EnumData.Conditions condition, bool isTrue, int stackCount)
	{
	}

	private void SetConditionMarkers(Condition permCondition, bool isTrue, int stackCount)
	{
	}

	private void SetEffectUI(GameObject markerObj, Condition permCondition, bool isTrue)
	{
	}

	private void CheckStackCountUI(GameObject markerObj, int stackCount)
	{
	}

	private void CheckStackCountUINew(GameObject markerObj, int stackCount)
	{
	}

	private void SetRotation(GameObject markerObj, bool buffType, bool isTrue)
	{
	}

	private void UpdateTimer()
	{
	}

	public void ChangeRotation()
	{
	}

	private void UpdateActivation(List<GameObject> container, Transform[] parents, int index)
	{
	}

	private void OnDestroy()
	{
	}

	private void SetConditionMarkerRelease(GameObject allObj, GameObject[] objs)
	{
	}

	private void SetConditionAllMarker(GameObject allObj, GameObject[] objs)
	{
	}

	private GameObject GetEffect(string effectRes)
	{
		return null;
	}

	private void SetLocalPosition(int unitId)
	{
	}

	private void SetLocalPositionOffset(Transform tr, float offsetY)
	{
	}
}
