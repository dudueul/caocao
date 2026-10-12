using LitJson;
using UnityEngine;

public abstract class UIPopularityRecoveryBase : MonoBehaviour
{
	[SerializeField]
	protected EnumData.PopularityRecoveryType m_type;

	[SerializeField]
	protected UILabelLocalized m_valueRecovery;

	[SerializeField]
	protected UILabelLocalized m_valueFood;

	[SerializeField]
	protected UILabelLocalized m_valueSilver;

	[SerializeField]
	protected UILabelLocalized m_valueEtc;

	protected int constIndex
	{
		get
		{
			return 0;
		}
	}

	public abstract void OnRecoveryBtnClick();

	public abstract void OnImprovedRecoveryBtnClick();

	protected abstract void UpdateUI();

	protected abstract void OnUpdatePopularity(JsonData response);

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}
}
