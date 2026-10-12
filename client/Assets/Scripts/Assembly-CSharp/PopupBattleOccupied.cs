using UnityEngine;

public class PopupBattleOccupied : CaocaoUIButton
{
	[SerializeField]
	private UISprite m_bar;

	[SerializeField]
	private UILabelLocalized m_valueLabel;

	private int m_saveFlagId;

	private int m_currentValue;

	private int m_toValue;

	private int m_maxValue;

	public void Init(int checkSaveflagId, int currentValue, int maxValue)
	{
	}

	public void SetValue(int toValue)
	{
	}

	private void InitValue(int toValue)
	{
	}

	private void SetValueKey(int value)
	{
	}

	private void Animate()
	{
	}

	private float GetToValueFillAmount(int toValue, int maxValue)
	{
		return 0f;
	}

	private void OnBarUpdate(float value)
	{
	}

	private void OnValueUpdate(float time)
	{
	}

	private void OnCompleted()
	{
	}

	private void OnCheckSaveflagValue()
	{
	}

	private bool IsOpenContents()
	{
		return false;
	}

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnEnable()
	{
	}

	protected override void UpdateButtonState()
	{
	}

	protected override void OnClickImpl()
	{
	}
}
