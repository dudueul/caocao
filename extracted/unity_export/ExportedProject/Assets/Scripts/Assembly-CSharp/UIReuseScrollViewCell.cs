using UnityEngine;

public abstract class UIReuseScrollViewCell : MonoBehaviour
{
	protected IReuseCellData m_reuseCellData;

	public virtual int Index
	{
		get
		{
			return 0;
		}
	}

	public virtual void UpdateData(IReuseCellData cellData)
	{
	}
}
