using UnityEngine;

public class BattleArenaStateUI : MonoBehaviour
{
	private int ownHpTotal;

	private int ownHpMaxTotal;

	private int enemyHpTotal;

	private int enemyHpMaxTotal;

	private UISlider m_sliderOwn;

	private UISlider m_sliderEnemy;

	private UILabelLocalized m_labelOwnState;

	private UILabelLocalized m_labelEnemyState;

	private UISlider sliderOwn
	{
		get
		{
			return null;
		}
	}

	private UISlider sliderEnemy
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelOwnState
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelEnemyState
	{
		get
		{
			return null;
		}
	}

	private void Start()
	{
	}

	private void UpdateOwnGauge()
	{
	}

	private void UpdateEnemyGauge()
	{
	}

	private void UpdateGauge(int curHp, int maxHp, UISlider slider, UILabelLocalized label)
	{
	}

	private void OnBattleStart()
	{
	}

	private void OnStatusChanged(ControlFlow.statusType status)
	{
	}

	private void CheckChangedHP()
	{
	}

	private void OnDestroy()
	{
	}
}
