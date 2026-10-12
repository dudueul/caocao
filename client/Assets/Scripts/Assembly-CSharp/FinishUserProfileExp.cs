using UnityEngine;

public class FinishUserProfileExp : MonoBehaviour
{
	public static bool isComplete;

	public static bool willLevelUp;

	private int m_expBeforeBattle;

	private int m_levBeforeBattle;

	private int m_addUserExp;

	private float m_expLimit;

	private UILabelLocalized m_expValueUI;

	private UISprite m_expGaugeUI;

	private UILabelLocalized m_levelUI;

	private GameObject m_levelUpEffect;

	private bool m_isCompleteToLevelUp;

	private bool _init;

	private void Start()
	{
	}

	public void OnInit()
	{
	}

	private void OnDestroy()
	{
	}

	public void Apply(int addUserExp)
	{
	}

	public void Animate()
	{
	}

	private void OnUserExpEffectUpdate(float value)
	{
	}

	private void OnUserExpEffectCompleted()
	{
	}
}
