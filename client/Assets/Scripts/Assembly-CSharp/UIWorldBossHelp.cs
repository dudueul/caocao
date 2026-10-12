using UnityEngine;
using Worldboss;

public class UIWorldBossHelp : MonoBehaviour, IWorldBossTab
{
	public UIScrollView scrollView;

	public UITable grid;

	public UIGrid rewardRuleGrid;

	public UIButton battleInfo;

	public PopupWorldboss parent { get; private set; }

	public void Initialize(PopupWorldboss parent)
	{
	}

	public void SetActive(bool active)
	{
	}

	public void Awake()
	{
	}

	public void OnDestroy()
	{
	}

	public void Show()
	{
	}

	public void UpdateUI()
	{
	}
}
