using UnityEngine;

public class UnitStatMeter : MonoBehaviour
{
	public int[] resMax;

	public int[] res;

	public int[] buffRes;

	public int expMax;

	public int exp;

	public int buffExp;

	public bool animImmediately;

	public PrefabInstance[] m_resInst;

	public GameObject[] m_resIcon;

	public PrefabInstance m_expInst;

	private UIStatProgressBar[] m_progressRes;

	private UIStatProgressBar m_progressExp;

	public UIStatProgressBar progressExp
	{
		get
		{
			return null;
		}
	}

	public UIStatProgressBar GetResProgress(EnumData.UnitResType resType)
	{
		return null;
	}

	public Transform GetResCurr(EnumData.UnitResType resType)
	{
		return null;
	}

	public Transform GetResNext(EnumData.UnitResType resType)
	{
		return null;
	}

	public Transform GetResArrow(EnumData.UnitResType resType)
	{
		return null;
	}

	private void OnEnable()
	{
	}

	private void OnDestroy()
	{
	}

	public void UpdateStat()
	{
	}

	public void SetUserUnit(UserUnit unit)
	{
	}

	public void SetNextStat(UserUnit unit)
	{
	}

	public void SetData(UserUnit.UnitRes maxRes, UserUnit.UnitRes res, int maxExp, int exp)
	{
	}

	public void SetDataExceptExp(UserUnit.UnitRes maxRes, UserUnit.UnitRes res)
	{
	}

	public void SetData(UnitData unitData)
	{
	}
}
